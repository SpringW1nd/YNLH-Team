#********************************************
#* Author: Li, Wen
#* Date  : 3/19/2020
#* Description: simulate vehicle entry
#********************************************
import json
import random
from lib.Message import Message

class EESensor ():
    
    MsgHandle = Message ("http://192.168.132.1:8080/RQProject1")
    
    def __init__(self):

        self.ActBase = "/vehicle"

# data format: username=hello&password=123&comfirmPw=123&name=liwen&phoneNumber=123456&email=11%40qq.com
    def Entry (self, PlateNumber):
        Action  = self.ActBase + "/in"
        Datas   = "plateNumber="+PlateNumber
        Results = EESensor.MsgHandle.SendRequest ("post", Action, Data=Datas)
        if (Results == None):
            return 0
        return Results["status"]

    def Exit (self, PlateNumber):
        Action  = self.ActBase + "/out"
        Datas   = "plateNumber="+PlateNumber
        Results = EESensor.MsgHandle.SendRequest ("post", Action, Data=Datas)
        if (Results == None):
            return None        
        return Results
        
    def Pay (self, Bid):
        Action  = self.ActBase + "/pay"
        Datas   = "bid="+str(Bid)
        Results = EESensor.MsgHandle.SendRequest ("post", Action, Data=Datas)
        if (Results == None):
            return None        
        return Results
        
    def IsPayed (self, Bid):
        Action  = self.ActBase + "/is-payed"
        Datas   = "bid="+str(Bid)
        Results = EESensor.MsgHandle.SendRequest ("post", Action, Data=Datas)
        if (Results == None):
            return 0
        return Results["payed"]
        
    def SetExitTime (self, PlateNumber):
        Action  = self.ActBase + "/set-exit-time"
        Datas   = "plateNumber="+PlateNumber + "&Hours=" + str(random.randint(1,24))
        Results = EESensor.MsgHandle.SendRequest ("post", Action, Data=Datas)
        if (Results == None):
            return None        
        return Results["status"]


    
