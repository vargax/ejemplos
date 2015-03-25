# -*- coding: utf-8 -*-
__author__ = 'cvargasc'
# ------------------------
# Imports
# ------------------------
import socket
import asyncore
import asynchat
import threading
# ------------------------
# Classes
# ------------------------


class Protocol(object):  # Class for the protocol definition
    # These constants represents the commands that this server recognise. If the
    _SEPARATOR = '::'  # If the command requires some data, or a subcommand, it should come in the same message
    CONNECT = 'CONNECT'
    DISCONNECT = 'DISCONNECT'
    SEND = 'SEND'  # This command should be
    RECEIVE = 'RECEIVE'
    OK = 'OK'  # "Got it!"
    RETRY = 'RETRY'  # "Something went wrong..."

    def __init__(self, command, data=None):
        self.command = command
        self.data = data


class Server(asyncore.dispatcher):  # Class in charge of listening for incoming connections

    def __init__(self, py_vissim, port):
        asyncore.dispatcher.__init__(self)

        self.py_vissim = py_vissim  # Attribute to talk with the main class

        self.host = socket.gethostname()
        self.port = port
        self.clients = []

        self.create_socket(socket.AF_INET, socket.SOCK_STREAM)
        self.set_reuse_addr()
        self.bind((self.host, self.port))
        self.listen(5)
        print "[Server] Listening on {h}:{p}".format(h=self.host, p=self.port)

    def handle_accept(self):
        pair = self.accept()
        if pair is not None:
            sock, addr = pair
            print "[Server] We got a connection from {a}".format(a=addr)
            self.clients.append(SocketHandler(self, sock, addr))

    def stop(self):
        self.close()
        if len(self.clients) != 0:
            print "[Server] Warning: There are "+str(len(self.clients))+" active sockets!"
            for client in self.clients:
                client.disconnect()


class ServerThread(threading.Thread):  # Support class to run the 'Server' in its own thread
    def __init__(self, py_vissim, port):
        threading.Thread.__init__(self)
        self.server = Server(py_vissim, port)

    def run(self):
        asyncore.loop()

    def stop(self):
        self.server.stop()
        self.join()


class SocketHandler(asynchat.async_chat):  # Core class in charge of handle each incoming connection
    # -------------------------------
    # Constructor
    # -------------------------------
    def __init__(self, server, sock, client):
        asynchat.async_chat.__init__(self, sock=sock)
        self.server = server
        self.log_id = '[SocketHandler ({h}:{p})]'.format(h=client[0], p=client[1])
        self.set_terminator('\n')  # Here we set the end of the communication with a new line... the same Java uses!
        self.buffer = []  # We start with an empty buffer
        self.handlers = {
            Protocol.CONNECT: self._handle_connect,
            Protocol.SEND: self._handle_receive,  # If jVissim is sending something, I have to RECEIVE it...
            Protocol.RECEIVE: self._handle_send,  # If jVissim needs something, I have to SEND it...
            Protocol.DISCONNECT: self._handle_disconnect,
        }
        self.actually_handling = None  # We use this flag for protocol operations that require more that one message...
        self.handling_stage = None  # This flag is for the handler to know in which phase it was...
        print self.log_id+': I am ready to handle this connection...'

    # -------------------------------
    # Asynchat required methods
    # -------------------------------
    def collect_incoming_data(self, data):
        self.buffer.append(data)  # Here we append the incoming data to the buffer

    def found_terminator(self):  # The message is complete... What are we going to do now?
        incoming_message = ''.join(self.buffer)
        print self.log_id+"<"+incoming_message+"> --> IN"
        if self.actually_handling is None:
            if incoming_message in self.handlers:
                self.actually_handling = self.handlers[incoming_message]
                self.handlers[incoming_message]()
            else:
                print self.log_id+": I don't know how to handle "+incoming_message
                self._handle_send(Protocol.RETRY)
                self._message_complete()
        else:
            self.handlers[self.actually_handling]()

    # -------------------------------
    # Handlers for incoming commands (Should be private)
    # -------------------------------
    def _message_complete(self):  # Utility function to clean everything and be ready for the next message...
        self.actually_handling = None  # We are not handling anything...
        self.handling_stage = None
        self.buffer = []  # We must reset the buffer to be ready for the next message...

    def _handle_connect(self):
        self._handle_send(Protocol.OK)
        self._message_complete()

    def _handle_send(self, outgoing_message):
        print self.log_id+"OUT --> <"+outgoing_message+">"
        self.push(outgoing_message)
        self.push(self.terminator)

    def _handle_receive(self):
        phases = {
            'ACCEPTED': 0,  # Accepted the incoming file...
            'WROTE': 1,  # Wrote the buffer to the hard drive...
        }

        if self.handling_stage is None:
            self._handle_send(Protocol.OK)
            self.buffer = []  # Cleaning the buffer to fill it with the incoming file...
            self.handling_stage = phases['ACCEPTED']
        elif self.handling_stage is phases['ACCEPTED']:
            self._handle_send(Protocol.OK)
            self.server.py_vissim.write_file(self.buffer)
            self.handling_stage = phases['WROTE']
            self._handle_send(Protocol.OK)
            self._message_complete()

    def _handle_disconnect(self):
        self.close()
        self.server.clients.remove(self)

    # -------------------------------
    # Handlers for outgoing commands (Should be public)
    # -------------------------------
    def disconnect(self):
        self._handle_send(Protocol.DISCONNECT)
        self.close()
        self.server.clients.remove(self)
