# -*- coding: utf-8 -*-
"""
Created on Tue Jun 19 10:56:42 2018

@author: chen
"""
import logging
logging.getLogger("scapy.runtime").setLevel(logging.ERROR)
from scapy.all import *
import sys
from datetime import datetime # Other stuff
from time import strftime
import traceback


SYNACK = 0x12
RSTACK = 0x14
def checkPort(minPort, maxPort) :
    try:
        if int(int(minPort) >= 0 and int(maxPort) >= 0 and int(maxPort) >= int(minPort)):

            ports = range(int(minPort), int(maxPort) + 1)
            return ports
        else:
            print("\n Range of Ports. Error")
            print("[!] Exiting...")
            sys.exit(1)
    except Exception:
         print("\n Range of Ports. Error")
         print("[!] Exiting...")
         sys.exit(1)

def checkHost(targetIP):
    conf.L3socket = L3RawSocket
    conf.verb = False
    try:
        ping = sr1(IP(dst = targetIP)/ICMP(),timeout=1)
        print("\n[*] Target is Up, Beginning Scan...")
    except Exception:
        print("\n[!] Couldn't Resolve Target")
        print("[!] Exiting...")
        traceback.print_exc()
        sys.exit(1)

def scanport(port):
    conf.L3socket = L3RawSocket
    try:
        srcPort = RandShort()        conf.verb = False
        SYN_ACK_Packet = sr1(IP(dst = targetIP)/TCP(sport = srcPort, dport = port, flags = "S"),timeout=3)
        packetFlags = SYN_ACK_Packet.getlayer(TCP).flags
        #print(packetFlags)
        return isFlags(packetFlags)
        RSTpkt = IP(dst = targetIP)/TCP(sport = srcPort, dport = port, flags = "R")
        send(RSTpkt)
    except KeyboardInterrupt:
        RSTpkt = IP(dst = target)/TCP(sport = srcPort, dport = port, flags = "R")
        send(RSTpkt)
        print("\n[*] User Requested Shutdown...")
        print("[*] Exiting...")
        sys.exit(1)

def isFlags(packetFlags):
    if packetFlags == SYNACK:
        return True
    else:
        return False

def getTime():
    return datetime.now()

def totalTime(startTime, endTime):
    print("[*] Total Scan Duration: " + str(endTime - startTime))

def scanStart():
    print("[*] Scanning Started at " + strftime("%H:%M:%S") + "!\n")

def scanningPrintOpenPort(ports):
    for port in ports:
        status = scanport(port)
        #print(port)
        isPortOpen(status, port)

def isPortOpen(status, port):
    if status == True:
        print("Port " + str(port) + ": Open")

#################################Main################################

startTime = getTime()
try:
    targetIP = input("[*] Enter Target IP Address: ")
    minPort = input("[*] Enter Minumum Port Number: ")
    maxPort = input("[*] Enter Maximum Port Number: ")    ports = checkPort(minPort, maxPort)
except KeyboardInterrupt:
    print("\n[*] User Requested Shutdown...")
    print("[*] Exiting...")
    sys.exit(1)

#ports = range(int(minPort), int(maxPort)+1)

checkHost(targetIP)
scanStart()

scanningPrintOpenPort(ports)

endTime = getTime()

print("\n[*] Scanning Finished!")

totalTime(startTime,endTime)
