#********************************************
#* Author: Li, Wen
#* Date  : 3/19/2020
#* Description: simulate vehicle entry
#********************************************
import json
from time import sleep
from lib.EESensor import EESensor

class Vehicle ():
    
    def __init__(self, PlateNumber):
        self.PlateNumber = PlateNumber

    def Simulate (self):
        Sensor = EESensor()

        #1. simulate vehicle entry
        print ("===> vehicle " + self.PlateNumber + " try to entry..");
        IsEntry = Sensor.Entry (self.PlateNumber)
        if (IsEntry == 1):
            print ("===> vehicle " + self.PlateNumber + " entry success..");
        else:
            print ("===> vehicle " + self.PlateNumber + " entry fail..");
            return
            
        #2. modify the exit time
        Sensor.SetExitTime (self.PlateNumber)
        
        #3. simulate vehicle exit
        BillInfo = Sensor.Exit (self.PlateNumber)
        if (BillInfo == None):
            return
        print ("===> vehicle " + self.PlateNumber + " exit: [" + \
               "Bid: " + str(BillInfo["bid"]) + " "\
               "Fee: " + str(BillInfo["fee"]) + " "\
               "EntryTime: " + str(BillInfo["entryTime"]) + " "\
               "ExitTime: " + str(BillInfo["exitTime"]) + "]");
               
        #4. simulate pay the bill
        Sensor.Pay (BillInfo["bid"])
        print ("===> vehicle " + self.PlateNumber + " pays the bill..")
               
        #5. simulate checking pay status
        while (1):
            IsPayed = Sensor.IsPayed (BillInfo["bid"])
            if (IsPayed == 0):
                sleep(1)
            else:
                break
        print ("===> vehicle " + self.PlateNumber + " exit success..") 

        
        

    
