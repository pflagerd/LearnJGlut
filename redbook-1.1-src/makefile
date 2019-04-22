SHELL = /bin/bash

directory_containing_this_makefile=$(dir $(realpath $(firstword $(MAKEFILE_LIST))))

CPPFLAGS=\
	-std=gnu99\
	-O0\
	-g3\
	-m64\
	-I$(directory_containing_this_makefile)

LDFLAGS=\
	-Wl,--no-as-needed\
	-Wl,--start-group\
	-L/usr/lib64 -lglut -lGLU -lGL\
	-Wl,--end-group

.PHONY: all
all: bin/hello bin/double bin/lines bin/polys
	@printf "\nExecuting target: all\n"

bin/double: src/double
	cd src; $(MAKE) install

bin/hello: src/hello
	cd src; $(MAKE) install

bin/lines: src/lines
	cd src; $(MAKE) install
	
bin/polys: src/polys
	cd src; $(MAKE) install
	
src/double: src/Makefile src/double.c
	cd src; $(MAKE) -j 16

src/hello: src/Makefile src/hello.c
	cd src; $(MAKE) -j 16

src/lines: src/Makefile src/lines.c
	cd src; $(MAKE) -j 16

src/polys: src/Makefile src/polys.c
	cd src; $(MAKE) -j 16

src/Makefile: src/configure
	@printf '\nExecuting target: $(directory_containing_this_makefile)src/Makefile\n'
	@printf "SHELL=$(SHELL)\n"
	@printf "$(directory_containing_this_makefile)src/configure\n"
	@printf "$(dir $(directory_containing_this_makefile))src/configure\n"
	@printf "CPPFLAGS=$(CPPFLAGS)\n"
	@printf "LDFLAGS=$(LDFLAGS)\n"
	cd src; ./configure --prefix=$(directory_containing_this_makefile) CPPFLAGS="$(CPPFLAGS)" LDFLAGS="$(LDFLAGS)"

.PHONY: autoreconf
src/configure: src/configure.ac src/Makefile.am makefile
	@printf '\nExecuting target: src/configure\n'
	cd src; autoreconf --force --install 2>/dev/null # the 2>/dev/null is to suppress info that eclipse thinks are errors

.PHONY: clean
clean:
	rm -rf bin/
	rm -f src/bezcurves src/bezsurfs src/double src/hello src/lines src/polys
	rm -rf src/.deps/
	rm -f src/com_pflager_gl.h
	rm -f src/com_pflager_glut.h
	rm -rf src/.libs/
	rm -rf src/autom4te.cache/
	rm -rf src/config/
	rm -rf src/m4/
	rm -f src/Makefile
	rm -f src/Makefile.in
	rm -f src/aclocal.m4
	rm -f src/config.log
	rm -f src/config.status
	rm -f src/configure
	rm -f src/*.la
	rm -f src/*.lo
	rm -f src/*.o
	rm -f src/libtool
	