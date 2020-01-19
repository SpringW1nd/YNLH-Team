#############################################################################
# Author: Wen Li
# Date  : 1/18/2020
# Description: park core： core progress for parking service
##############################################################################
CC = gcc 
LD = gcc

SOURCE_PATH  = source
INCLUDE_PATH = include

PROJECT_PATH = $(PWD)/../..
MODULE_PATH  = $(PROJECT_PATH)/module/lib

OBJ_PATH     = obj

CFLAGS      = -I$(INCLUDE_PATH) -std=c++11 -g -O3
LD_CFLAGES  = -lstdc++ -L$(MODULE_PATH) -lcontrol

SOURCE_FILES = $(wildcard $(SOURCE_PATH)/*.cpp)
OBJ_FILES    = $(addprefix $(OBJ_PATH)/, $(addsuffix .o,$(notdir $(basename $(SOURCE_FILES)))))

TARGET = parkcore

.PHONY:all clean

all: $(TARGET)

$(TARGET): $(OBJ_FILES)
	$(LD) -o $@ $^ $(LD_CFLAGES)
	
$(OBJ_PATH)/%.o: $(SOURCE_PATH)/%.cpp
	@if [ ! -d $(OBJ_PATH) ];then mkdir $(OBJ_PATH); fi
	$(CC) $(CFLAGS) -c -o $@ $<

clean:
	rm -rf $(OBJ_PATH) $(TARGET)
