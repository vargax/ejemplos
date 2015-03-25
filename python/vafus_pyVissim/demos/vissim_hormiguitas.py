import socket

__author__ = 'c.vargas124'
import win32com.client
vissim = win32com.client.Dispatch("Vissim.Vissim")
vissim.LoadNet("C:\Users\c.vargas124\Desktop\int_rincon\int_rincon.inpx")
sim = vissim.Simulation
net = vissim.Net
veh = net.Vehicles

#queue_counters =

# Preparando Socket
HOST = "157.253.192.213"
PORT = 65000

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((HOST, PORT))

for i in range(0, 1000):

    out = "FRAME "+str(i)+"%"

    for k in range(1, 3):
        sim.runsinglestep()

    for v in veh:
        vh_id = v.AttValue("No")

        color = v.AttValue("Color1")

        pos_front = v.AttValue("CoordFront")
        pos_rear = v.AttValue("CoordRear")

        v.DestParkLot = 1

        out += str(vh_id)+" "+str(pos_front)+" "+str(pos_rear)+" "+str(color)+"@"

    print "Enviando frame "+str(i)+" ("+str(len(veh))+" vehiculos)"
    sock.sendall(out+"\n")
#vissim.Exit()