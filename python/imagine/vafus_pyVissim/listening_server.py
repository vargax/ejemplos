__author__ = 'c.vargas124'
# ------------------------
# Imports
# ------------------------
import socket
import asyncore
import threading
import simulation
# ------------------------
# Constants
# ------------------------
MODULE_NAME = "Listening Server"

# ------------------------
# Classes
# ------------------------


class ServerThread(threading.Thread):  # Support class to run the 'Server' in its own thread
    def __init__(self, port):
        threading.Thread.__init__(self)
        self.server = Server(port)

    def run(self):
        asyncore.loop()

    def stop(self):
        self.server.stop()
        self.join()


class Server(asyncore.dispatcher):  # Class in charge of listening for incoming connections
    def __init__(self, port):
        asyncore.dispatcher.__init__(self)

        self.host = socket.gethostname()
        self.port = port
        self.clients = []  # List to store the active servers...

        self.create_socket(socket.AF_INET, socket.SOCK_STREAM)
        self.set_reuse_addr()
        self.bind((self.host, self.port))
        self.listen(5)
        print "["+MODULE_NAME+"] Listening on {h}:{p}".format(h=self.host, p=self.port)

    def handle_accept(self):
        pair = self.accept()
        if pair is not None:
            sock, addr = pair
            print "["+MODULE_NAME+"] We got a connection from {a}".format(a=addr)
            self.clients.append(simulation.SocketHandler(self, sock, addr))

    def stop(self):
        self.close()
        if len(self.clients) != 0:
            print "["+MODULE_NAME+"] Warning: There are "+str(len(self.clients))+" active sockets!"
            for client in self.clients:
                client.disconnect()