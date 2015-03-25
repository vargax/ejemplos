# -*- coding: utf-8 -*-
import vissim

__author__ = 'cvargasc'
# ------------------------
# Imports
# ------------------------
import asynchat
# ------------------------
# Constants
# ------------------------
MODULE_NAME = "Simulation"

# ------------------------
# Classes
# ------------------------


class Protocol(object):  # Class for the protocol definition
    # These constants represents the commands that this server recognise.
    _SEPARATOR = '::'  # If the command requires additional information, it should come in the same message
    END_MESSAGE = "\n"  # The character Java uses to indicate the end of a message

    # OPERATIVE COMMANDS:
    CONNECT = 'CONNECT'
    DISCONNECT = 'DISCONNECT'
    DOWNLOAD = 'DOWNLOAD'

    OK = 'OK'  # "Got it!"
    RETRY = 'RETRY'  # "Something went wrong..."

    # SIMULATION RELATED commands in its own class (vissim.py):
    SIMULATION_COMMAND = "SC"

    def __init__(self, message):
        message = str(message).split(self._SEPARATOR)
        self.command = message.pop(0)
        self.data = message


class SocketHandler(asynchat.async_chat):  # Core class in charge of handle each incoming connection
    # -------------------------------
    # Constructor
    # -------------------------------
    def __init__(self, server, sock, client):
        asynchat.async_chat.__init__(self, sock=sock)
        self.server = server
        self.log_id = '['+MODULE_NAME+' ({h}:{p})]'.format(h=client[0], p=client[1])
        self.set_terminator(Protocol.END_MESSAGE)  # Here we set the end of the communication... the same Java uses!
        self.buffer = []  # We start with an empty buffer
        self.handlers = {
            Protocol.CONNECT: self._handle_connect,
            Protocol.DISCONNECT: self._handle_disconnect,
            Protocol.SIMULATION_COMMAND: self._handle_simulation_command,
        }
        print self.log_id+': Initializing Vissim instance...'
        self.simulator = vissim.Vissim()
        print self.log_id+': I am ready to handle this connection...'

    # -------------------------------
    # Asynchat required methods
    # -------------------------------
    def collect_incoming_data(self, data):
        self.buffer.append(data)  # Here we append the incoming data to the buffer

    def found_terminator(self):  # The message is complete... What are we going to do now?
        incoming_message = ''.join(self.buffer)
        print self.log_id+"<"+incoming_message+"> --> IN"
        protocol = Protocol(incoming_message)
        if protocol.command in self.handlers:
            self.handlers[protocol.command](protocol.data)
        else:
            print self.log_id+": I don't know how to handle "+incoming_message
            self._reply(Protocol.RETRY)
        self._message_complete()

    # -------------------------------
    # Handlers for incoming commands (Should be private)
    # -------------------------------
    def _message_complete(self):  # Utility function to clean everything and be ready for the next message...
        self.buffer = []  # We must reset the buffer to be ready for the next message...

    def _reply(self, outgoing_message):
        print self.log_id+"OUT --> <"+outgoing_message+">"
        self.push(outgoing_message)
        self.push(self.terminator)

    def _handle_connect(self, data):
        self._reply(Protocol.OK)

    def _handle_disconnect(self, data):
        self.close()
        self.server.clients.remove(self)

    def _handle_simulation_command(self, data):
        command = vissim.Command(data.pop(0), data)
        self.simulator.handle_simulation_command(command)

    # -------------------------------
    # Handlers for outgoing commands (Should be public)
    # -------------------------------
    def disconnect(self):
        self._reply(Protocol.DISCONNECT)
        self.close()
        self.server.clients.remove(self)
