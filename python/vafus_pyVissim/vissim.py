# -*- coding: utf-8 -*-
import threading
from adodbapi.adodbapi import paramstyle
import pythoncom

__author__ = 'cvargasc'
# ------------------------
# Imports
# ------------------------
import win32com.client
# ------------------------
# Constants
# ------------------------
MODULE_NAME = "Vissim"

# ------------------------
# Classes
# ------------------------


class Command(object):
    # These constants represents the actions that VISSIM recognise:
    LOAD_SIMULATION = "LS"

    def __init__(self, action, params):
        self.action = action
        self.params = params


class Vissim(object):
    def __init__(self):
        self.log_id = '['+MODULE_NAME+']'

        self.vissim = None
        self.sim = None
        self.net = None
        self.veh = None

        self.actions = {
            Command.LOAD_SIMULATION: self._load_simulation,
        }

        #t = threading.Thread(target=self._prepare)
        print self.log_id+': Calling VISSIM COM interface...'
        #t.start()
        self._prepare()

    def _prepare(self):
        pythoncom.CoInitialize()
        self.vissim = win32com.client.Dispatch("Vissim.Vissim")
        self.sim = self.vissim.Simulation
        self.net = self.vissim.Net
        self.veh = self.net.Vehicles

    def handle_simulation_command(self, command):
        if command.action in self.actions:
            self.actions[command.command](command.params)
        else:
            raise UnknownActionException("Action "+command.action+" unknown")

    def _load_simulation(self, params):
        path = str(params.pop())
        if path.endswith("inpx"):
            self.vissim.LoadNet(path)
        elif path.endswith("anm"):
            self.vissim.ImportANM(path)
        else:
            raise IncorrectParamException(Command.LOAD_SIMULATION, params.insert(0, path))


class UnknownActionException(Exception):
    def __init__(self, value):
        self.value = value

    def __str__(self):
        return repr(self.value)


class IncorrectParamException(Exception):
    def __init__(self, action, param):
        self.message = "Action "+action+" does not recognise the given parameters "+repr(param)
        self.action = action
        self.param = param

    def __str__(self):
        return repr(self.message)