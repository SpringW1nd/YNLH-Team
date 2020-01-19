#############################################################################
# Author: Wen Li
# Date  : 1/18/2020
# Description: makefile for entry of module of project
##############################################################################
export CC = gcc 
export LD = gcc

CUR_PATH = $(PWD)
export HEADER   = $(CUR_PATH)/interface
export LIB_PATH = $(CUR_PATH)/lib

export CFLAGS     = -I$(HEADER) -std=c++11 -g -fpic -O3
export LD_CFLAGES = -lstdc++ 

SUBDIRS = control

$(SUBDIRS):init
	+$(MAKE) -C $@

init:
	@if [ ! -d "lib" ]; then	mkdir lib; fi
	@echo begin compile $(PWD)

.PHONY : clean
clean:
	for dir in $(SUBDIRS);\
	do $(MAKE) -C $$dir clean;\
	done
	@rm -rf lib