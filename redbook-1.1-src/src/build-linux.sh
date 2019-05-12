#!/bin/bash

function build() {
	[ $1.c -nt $1 ] && gcc $1.c -o $1  -lglut -lGL -lGLU -lm
}

for program in `ls -1 *.c`; do
   build ${program%.c}
done