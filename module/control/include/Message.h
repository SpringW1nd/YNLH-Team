/***********************************************************
 * Author: Wen Li
 * Date  : 1/18/2020
 * Describe: message decode and encode
 * History:
   <1> 01/18/2020 , create
************************************************************/

#ifndef _MESAGE_H_
#define _MESAGE_H_ 
#include "BasicType.h"

using namespace std;

class Message
{
private:

    
private:
    DWORD Decode (CHAR* Msg);

public:
    Message (CHAR* Msg);
	
    Message ();
    ~Message ();
  
};


#endif



