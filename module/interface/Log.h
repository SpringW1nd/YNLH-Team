/***********************************************************
 * Author: Wen Li
 * Date  : 1/18/2020
 * Describe: debug API, for logging
 * History:
   <1> 01/18/2020 , create
************************************************************/

#ifndef _LOG_H_
#define _LOG_H_
#include <stdarg.h>

#define BUF_SIZE  (1024)

static inline VOID DebugLog(DWORD Switch, const CHAR* Format, ...)
{
    char Log[BUF_SIZE] = {0};
	
	if (Switch == 0)
	{
		return;
	}

    va_list ap;
    va_start(ap, Format);
    (VOID)vsnprintf (Log, sizeof(Log)-1, Format, ap);
    va_end(ap);
 
    FILE *fd = fopen("ctrl.log", "a");
    if (fd != NULL)
    {
        time_t Now;
        time(&Now);

        CHAR TimeBuf[64];
        sprintf(TimeBuf, "%s", ctime(&Now));
        TimeBuf[strlen(TimeBuf)-1] = 0;
 
        fprintf(fd, "[%s]%s\r\n", TimeBuf, Log);
        fflush(fd);
        fclose(fd);
    }
}



#endif 
