#The following will fix 3D initializers
s/\([a-zA-Z]\+\)[ 	]\+\([a-zA-Z_0-9]\+\)\[.\]\[.\]\[.\]/\1\[\]\[\]\[\] \2 /

#s/\#define[ 	]\+\([a-zA-Z]\+\)[ 	]\+\([a-zA-Z]\+\)/final int \1 = \2\;/
s/#define[ 	]\+\([a-zA-Z]\+\)[ 	]\+\([0-9a-fA-FxX]\+\)/final int \1 = \2;/

s/unsigned char/char/g
s/GLfloat/double/g
s/GLint/int/g
s/GLuint/int/g
s/GLbyte/byte/g
s/GLubyte/byte/g
s/(void)/()/g