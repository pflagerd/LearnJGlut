#!/bin/bash

function build() {
	[ $1.c -nt $1 ] && ../../x86_64-8.1.0-release-posix-seh-rt_v6-rev0/mingw64/bin/gcc -DFREEGLUT_STATIC -I../../freeglut/include $1.c -o $1 -L../../freeglut/lib/x64 -lfreeglut_static -lopengl32 -lglu32 -lwinmm -lgdi32 -Wl,--subsystem,windows
}

for program in `ls -1 *.c`; do
   build ${program%.c}
done