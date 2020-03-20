#********************************************
#* Author: Li, Wen
#* Date  : 3/19/2020
#* Description: pack of http message
#********************************************
import requests

class Message():

    def __init__(self, ServerURI):
        self.ServerURI = ServerURI

    def _HttpPost(self, Url, Data):
        Result = requests.post(Url, headers={"Accept": "application/json"}, data=Data)
        if (Result.status_code != 200):
            print (Result)
            return None
        return Result.json()
        
    def _HttpGet(self, Url):
        Result = requests.get(Url)
        if (Result.status_code != 200):
            print (Result)
            return None
        return Result.json()
        
    def SendRequest (self, Method, Action, Data="None"):
        Result = None
        Url = self.ServerURI + Action
        print ("===> Send request to " + Url + ", Data= " + Data)
        
        if (Method == "get"):
            Result = self._HttpGet (Url)
        elif (Method == "post"):
            Result = self._HttpPost (Url, Data)
        else:
            print (Method + " ===> not supported!!!!\n")
        
        return Result


    
