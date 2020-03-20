#********************************************
#* Author: Li, Wen
#* Date  : 3/19/2020
#* Description: simulate vehicle entry
#********************************************
import json
from lib.Message import Message

class EESensor ():
    
    MsgHandle = Message ("http://192.168.132.1:8080/RQProject1")
    
    def __init__(self):

        self.ActBase = "/vehicle"

    def Entry (self, PlateNumber):
        Action  = self.ActBase + "/in"
        Datas   = json.dumps({"plateNumber": PlateNumber})
        Results = EESensor.MsgHandle.SendRequest ("post", Action, Data=Datas)
        if (Results == None):
            return 0
        return Results["status"]

    def Exit (self, PlateNumber):
        Action  = self.ActBase + "/out"
        Datas   = json.dumps({"plateNumber": PlateNumber})
        Results = EESensor.MsgHandle.SendRequest ("post", Action, Data=Datas)
        if (Results == None):
            return None        
        return Results
        
    def IsPayed (self, Bid):
        Action  = self.ActBase + "payed/"
        Datas   = json.dumps({"bid": Bid})
        Results = EESensor.MsgHandle.SendRequest ("post", Action, Data=Datas)
        if (Results == None):
            return 0
        return Results["payed"]


    
