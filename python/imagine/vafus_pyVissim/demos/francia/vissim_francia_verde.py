import socket

__author__ = 'c.vargas124'
import win32com.client
# Iniciando Vissim...
vissim = win32com.client.Dispatch("Vissim.Vissim")
vissim.LoadNet("C:\Users\c.vargas124\Desktop\int_rincon\int_rincon.inpx")

# Obteniendo referencias a los elementos...
sim = vissim.Simulation
net = vissim.Net
veh = net.Vehicles

color_test = "ff00ff00"
color_other = "ffffff00"

# Preparando Socket
HOST = "157.253.192.213"
PORT = 65000

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((HOST, PORT))

for i in range(0, 100000):

    out = "FRAME "+str(i)+"%"

    for k in range(1, 300):
        sim.runsinglestep()

    num_vh = 0
    for v in veh:
        parking_lot = v.AttValue("CurParkLot")
        dest_zone = v.AttValue("DestZone")
        if str(parking_lot) == "None" and str(dest_zone) == str(5):

            # if str(dest_zone) == str(5):
            #     color = color_test
            # else:
            #     color = color_other
            color = color_test

            num_vh += 1
            vh_id = v.AttValue("No")
            #color = v.AttValue("Color1")

            pos_front = v.AttValue("CoordFront")
            pos_rear = v.AttValue("CoordRear")

            out += str(vh_id)+" "+str(pos_front)+" "+str(pos_rear)+" "+str(color)+"@"

    print "Enviando frame "+str(i)+" ("+str(num_vh)+" vehiculos)"
    sock.sendall(out+"\n")
#vissim.Exit()