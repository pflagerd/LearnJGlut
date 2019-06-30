#The following will fix 3D initializers
s/\([a-zA-Z]\+\)[ 	]\+\([a-zA-Z_0-9]\+\)\[.\]\[.\]\[.\]{.*/\1\[\]\[\]\[\] \2 /

#

# double ctlpoints[4][4][3];
s/\([a-zA-Z]\+\)[ 	]\+\([a-zA-Z_0-9]\+\)\(\[.\]\)\(\[.\]\)\(\[.\]\);.*/\1\[\]\[\]\[\] \2 = new \1\3\4\5;/


#s/\#define[ 	]\+\([a-zA-Z]\+\)[ 	]\+\([a-zA-Z]\+\)/final int \1 = \2\;/
s/#define[ 	]\+\([a-zA-Z]\+\)[ 	]\+\([0-9a-fA-FxX]\+\)/final int \1 = \2;/
s/int main(int argc, char\*\* argv)/public int main(int argc, String\[] argv)/

#glViewport(0, 0, (GLsizei) w, (GLsizei) h)
s/glViewport(0, 0, (GLsizei) w, (GLsizei) h)/glViewport(0, 0, w, h)/g

s/\([ 	]\)exit[ 	]*(0)/\1System.exit(0)/g

#fprintf (stderr,
s/fprintf[ 	]*(stderr,/System.err.printf(/g

s/GLUnurbsObj \*/GLUnurbs /g

s/&argc/argc/g

s/const byte \*/String /g
s/const char \*/String /g
s/GLenum/int/g
s/unsigned char/char/g
s/GLdouble/double/g
s/GLfloat/double/g
s/GLint/int/g
s/GLuint/int/g
s/GLbyte/byte/g
s/GLubyte/byte/g
s/(void)/()/g