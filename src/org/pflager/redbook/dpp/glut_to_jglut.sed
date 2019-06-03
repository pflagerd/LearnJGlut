#double rect[4][3] = {50.0, 50.0, 0.0,
s/\([a-zA-Z]\+\)[ 	]\+\([a-zA-Z_0-9]\+\)\(\[.*\]\) = \(.*\)/\1\[\]\[\] \2 = new \1\[\]\[\] \4/g

s/GLfloat/double/g
s/GLint/int/g
s/GLuint/int/g
s/GLbyte/byte/g
s/GLubyte/byte/g