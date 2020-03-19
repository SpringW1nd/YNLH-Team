#********************************************
#* Author: Li, Wen
#* Date  : 3/19/2020
#* Description: simulate vehicle entry
#********************************************
import json
from lib.EESensor import EESensor

class Vehicle ():
    
    def __init__(self, PlateNumber):
        self.PlateNumber = PlateNumber

    def Simulate (self):
        Sensor = EESensor()

        #1. simulate vehicle entry
        print ("===> " + self.PlateNumber + " try to entry..");
        IsEntry = Sensor.Entry (self.PlateNumber)
        if (IsEntry == 1):
            print ("===> " + self.PlateNumber + " entry success..");
        else:
            print ("===> " + self.PlateNumber + " entry fail..");
            return
            
        #2. modify the exit time
        
        #3. simulate vehicle exit
        BillInfo = Sensor.Exit (self.PlateNumber)
        if (BillInfo == None):
            return
        print ("===> " + PlateNumber + "exit: " + \
               "Bid: " + Results["bid"] + " "\
               "Fee: " + Results["fee"] + " "\
               "EntryTime: " + Results["entryTime"] + " "\
               "ExitTime: " + Results["exitTime"] + " ");
               
        #4. simulate checking pay status
        while (1):
            IsPayed = Sensor.IsPayed (Results["bid"])
            if (IsPayed == 0):
                sleep(1)
            else:
                break
        print ("===> " + PlateNumber + " exit success..") 

        
        

    
