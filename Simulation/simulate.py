#!/usr/bin/python
#********************************************
#* Author: Li, Wen
#* Date  : 3/19/2020
#* Description: simulate entry
#********************************************
#********************************************
#* Author: Nong, Yu
#* Date  : 3/30/2020
#* Description: Add Reservation Test Script
#********************************************


import sys, getopt
import requests
import random
import re
from lib.Vehicle import Vehicle

#################################################################
# simulate vehicle entry and out
#################################################################
def GeneratePlate ():
    # vehicle WAXXXXXX
    PlateNumber = "WA"  
    Alpha = [chr(i) for i in range(65, 91)]    
    PlateNumber += str (random.randint(0,9)) + Alpha[random.randint(0, 25)]
    PlateNumber += str (random.randint(0,9)) + Alpha[random.randint(0, 25)]
    PlateNumber += str (random.randint(0,9)) + Alpha[random.randint(0, 25)]   
    return PlateNumber

def SimulateVehicle(VehicleCount):
    for No in range(0, VehicleCount, 1):
        print ("\n==========================================================")
        PlateNumber = GeneratePlate ()
        Veh = Vehicle(PlateNumber)
        Veh.Simulate ()
        print ("==========================================================\n")
#################################################################

#################################################################
# Simulate Reservation
#################################################################
def GetAccount():
    # Account TestXXXX
    Account = "Test"
    Password = "Test"
    print("TestAccount: "+Account+"; Password:"+Password+"\n")
    return Account,Password

def SimulateReservation(Count):
    for No in range(0, Count, 1):
        print ("\n==========================================================")
        Account, Password = GetAccount()

        loginURL = "http://localhost:8080/RQProject1/user/login"
        loginData = {'username': Account, 'password': Password}
        headers = {'user-agent':'Mozolloa/5.0'}
        s = requests.Session()
        print("Trying to login")
        response = s.post(url=loginURL,headers=headers,data=loginData)
        if "sign out" in response.text:
            print("Login Successfully\n")
        else:
            print("Login Failed\n")
            return

        print("Trying to list reservation")
        listURL = "http://localhost:8080/RQProject1/reservation/reservationList"
        response = s.get(url=listURL,headers=headers)
        #print(response.text)
        PDT = re.findall("PDT",response.text)
        print("Find",len(PDT)//2,"Reservations\n")
        
        
        print("Trying to make a reservation")
        reserveURL = "http://localhost:8080/RQProject1/reservation/reservation"
        reserveData = {"name":"Test","plateNumber":GeneratePlate(),"RStartDate":"03/31/2020 8:23 PM","REndDate":"03/31/2020 9:23 PM"}
        response = s.post(url=reserveURL,headers=headers,data=reserveData)
        resNum = 0
        if "Thank you for your reservation" in response.text:
            print("Reserve Successfully\n")
            for i in range(len(response.text)):
                #print(response.text[i:i+18])
                if response.text[i:i+18]=="Reservation number":
                    for j in range(5):
                        if response.text[i+20+j]=='<':
                            break
                    resNum = response.text[i+20:i+20+j]
                    print("Reservation Number:",resNum)
        else:
            print("Reserve Failed\n")
        
        print("Trying to list reservation")
        listURL = "http://localhost:8080/RQProject1/reservation/reservationList"
        response = s.get(url=listURL,headers=headers)
        #print(response.text)
        PDT = re.findall("PDT",response.text)
        print("Find",len(PDT)//2,"Reservations\n")

        print("Trying to cancel a reservation")
        cancelURL = "http://localhost:8080/RQProject1/reservation/cancel"
        cancelData = {"reservationNumber":resNum}
        response = s.post(url=cancelURL,headers=headers,data=cancelData)
        if "Success" in response.text:
            print("Cancel Successfully\n")
        else:
            print("Cancel Failed\n")
        
        print("Trying to list reservation")
        listURL = "http://localhost:8080/RQProject1/reservation/reservationList"
        response = s.get(url=listURL,headers=headers)
        #print(response.text)
        PDT = re.findall("PDT",response.text)
        print("Find",len(PDT)//2,"Reservations\n")


        print ("==========================================================\n")


#################################################################





#################################################################
# simulate entry
#################################################################
def main(argv):
    Type = None
    Count = 1
   
    # get simulation type
    try:
        opts, args = getopt.getopt(argv,"ht:c:",["type=", "count="])
    except getopt.GetoptError:
        print ("simulate.py -t <type_name>")
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print ("simulate.py -t vehicle ------- simulate vehicle entry and exit\n");
            sys.exit()
        elif opt in ("-t", "--type"):
            Type = arg;
        elif opt in ("-c", "--count"):
            Count = int (arg);
    
    # start simulation
    if (Type == "vehicle"):
        SimulateVehicle(Count)
    elif (Type == "reservation"):
        SimulateReservation(Count)
    else:
        print ("simulate.py -t <type_name>")  
   

if __name__ == "__main__":
   main(sys.argv[1:])
