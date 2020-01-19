
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


