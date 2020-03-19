#!/usr/bin/python
#********************************************
#* Author: Li, Wen
#* Date  : 3/19/2020
#* Description: simulate entry
#********************************************
import sys, getopt
import random
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
# simulate entry
#################################################################
def main(argv):
    Type = None
    VehicleCount = 1
   
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
            VehicleCount = int (arg);
    
    # start simulation
    if (Type == "vehicle"):
        SimulateVehicle(VehicleCount)
    else:
        print ("run.py -s <all/collect/repostats/langstats/discripstats/topics>")  
   

if __name__ == "__main__":
   main(sys.argv[1:])