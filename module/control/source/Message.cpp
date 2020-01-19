/***********************************************************
 * Author: Wen Li
 * Date  : 1/18/2020
 * Describe: message decode and encode
 * History:
   <1> 01/18/2020 , create
************************************************************/

#include "Message.h"


DWORD Message::Decode (CHAR* Msg)
{
    return M_SUCCESS;
}

Message::~Message ()
{
}


Message::Message (CHAR* Msg)
{
    DWORD Ret = Decode(Msg);
    assert (Ret != M_FAIL);   
}

Message::Message ()
{
	
}


