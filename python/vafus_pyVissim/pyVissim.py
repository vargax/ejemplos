# -*- coding: utf-8 -*-
import vissim

__author__ = 'cvargasc'
# ------------------------
# Imports
# ------------------------
import listening_server
import os
import shutil
# ------------------------
# Constants
# ------------------------
PORT = 3006
WORKING_DIRECTORY = 'data'
# ------------------------
# Methods
# ------------------------


def write_file(filename, byte_stream):
    working_file = open(WORKING_DIRECTORY+'/'+filename, 'wb')
    working_file.write(byte_stream)
    working_file.close()
# ------------------------
# Classes
# ------------------------


class PyVissim(object):

    def __init__(self):
        # Initializing filesystem...
        print "Preparing the workspace..."
        if os.path.exists(WORKING_DIRECTORY):
            print "Cleaning working directory..."
            shutil.rmtree(WORKING_DIRECTORY)
        os.mkdir(WORKING_DIRECTORY)
        # Initializing networking...
        print "Preparing communication server..."
        # This is the communication server, it is going to listen for incoming connections, it has its own thread:
        self.list_server = listening_server.ServerThread(PORT)
        self.list_server.start()  # Here we start the thread for the server
        print "Server is ready..."

    def shutdown(self):
        print "Trying to stop ServerThread..."
        print "The server should die in 30 seconds..."
        self.list_server.stop()


##  --> INIT --> #######################################################
pyvissim = PyVissim()
#vissim = vissim.Vissim()
raw_input("Press any key to shutdown the application... \n")
pyvissim.shutdown()
