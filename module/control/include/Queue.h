
#ifndef _QUEUE_H_
#define _QUEUE_H_ 
#include "BasicType.h"


using namespace std;

class Queue
{
private:
    DWORD m_DataLen;
    DWORD m_BufNum;
    CHAR* m_Buffer;
    
    queue <CHAR*> m_BufQueue;
    queue <CHAR*> m_DataQueue;
    
	pthread_mutex_t m_BufLock;
    pthread_mutex_t m_DataLock;

private:
    VOID QueueInit ();

    CHAR* AllocBuf ();
    VOID ReleaseBuf (CHAR* Buf);

public:
    Queue (DWORD Size, DWORD DataLen);
    Queue ();
    ~Queue ();

    DWORD QueuePush (CHAR*    Data, DWORD DataLen);
    DWORD QueuePop    (CHAR* Data, DWORD DataLen);
};


#endif







