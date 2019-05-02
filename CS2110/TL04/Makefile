TARGET = merge_sort
CFILES = $(wildcard *.c)
OFILES = $(CFILES:%.c=%.o)
CFLAGS = -std=c99 -pedantic -Wall -Werror -Wextra -g
CC = gcc
LD = gcc
VALOPT = --leak-check=yes --show-reachable=yes --tool=memcheck

build : $(OFILES)
	$(LD) $^ -o $(TARGET)

%.o : %.c
	$(CC) $(CFLAGS) $^ -c -o $@

merge_sort : build
	./$(TARGET)

run-valgrind : build
	valgrind $(VALOPT) ./$(TARGET)

clean :
	rm -f $(OFILES) $(TARGET)

