/*
 * Copyright (c) 1993-1997, Silicon Graphics, Inc.
 * ALL RIGHTS RESERVED
 * Permission to use, copy, modify, and distribute this software for
 * any purpose and without fee is hereby granted, provided that the above
 * copyright notice appear in all copies and that both the copyright notice
 * and this permission notice appear in supporting documentation, and that
 * the name of Silicon Graphics, Inc. not be used in advertising
 * or publicity pertaining to distribution of the software without specific,
 * written prior permission.
 *
 * THE MATERIAL EMBODIED ON THIS SOFTWARE IS PROVIDED TO YOU "AS-IS"
 * AND WITHOUT WARRANTY OF ANY KIND, EXPRESS, IMPLIED OR OTHERWISE,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY OR
 * FITNESS FOR A PARTICULAR PURPOSE.  IN NO EVENT SHALL SILICON
 * GRAPHICS, INC.  BE LIABLE TO YOU OR ANYONE ELSE FOR ANY DIRECT,
 * SPECIAL, INCIDENTAL, INDIRECT OR CONSEQUENTIAL DAMAGES OF ANY
 * KIND, OR ANY DAMAGES WHATSOEVER, INCLUDING WITHOUT LIMITATION,
 * LOSS OF PROFIT, LOSS OF USE, SAVINGS OR REVENUE, OR THE CLAIMS OF
 * THIRD PARTIES, WHETHER OR NOT SILICON GRAPHICS, INC.  HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH LOSS, HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, ARISING OUT OF OR IN CONNECTION WITH THE
 * POSSESSION, USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * US Government Users Restricted Rights
 * Use, duplication, or disclosure by the Government is subject to
 * restrictions set forth in FAR 52.227.19(c)(2) or subparagraph
 * (c)(1)(ii) of the Rights in Technical Data and Computer Software
 * clause at DFARS 252.227-7013 and/or in similar or successor
 * clauses in the FAR or the DOD or NASA FAR Supplement.
 * Unpublished-- rights reserved under the copyright laws of the
 * United States.  Contractor/manufacturer is Silicon Graphics,
 * Inc., 2011 N.  Shoreline Blvd., Mountain View, CA 94039-7311.
 *
 * OpenGL(R) is a registered trademark of Silicon Graphics, Inc.
 */

/*  texgen.c
 *  This program draws a texture mapped teapot with
 *  automatically generated texture coordinates.  The
 *  texture is rendered as stripes on the teapot.
 *  Initially, the object is drawn with texture coordinates
 *  based upon the object coordinates of the vertex
 *  and distance from the plane x = 0.  Pressing the 'e'
 *  key changes the coordinate generation to eye coordinates
 *  of the vertex.  Pressing the 'o' key switches it back
 *  to the object coordinates.  Pressing the 's' key
 *  changes the plane to a slanted one (x + y + z = 0).
 *  Pressing the 'x' key switches it back to x = 0.
 */

package org.pflager.redbook.dpp;

/*
 * Copyright (c) 1993-1997, Silicon Graphics, Inc.
 * ALL RIGHTS RESERVED
 * Permission to use, copy, modify, and distribute this software for
 * any purpose and without fee is hereby granted, provided that the above
 * copyright notice appear in all copies and that both the copyright notice
 * and this permission notice appear in supporting documentation, and that
 * the name of Silicon Graphics, Inc. not be used in advertising
 * or publicity pertaining to distribution of the software without specific,
 * written prior permission.
 *
 * THE MATERIAL EMBODIED ON THIS SOFTWARE IS PROVIDED TO YOU "AS-IS"
 * AND WITHOUT WARRANTY OF ANY KIND, EXPRESS, IMPLIED OR OTHERWISE,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY OR
 * FITNESS FOR A PARTICULAR PURPOSE.  IN NO EVENT SHALL SILICON
 * GRAPHICS, INC.  BE LIABLE TO YOU OR ANYONE ELSE FOR ANY DIRECT,
 * SPECIAL, INCIDENTAL, INDIRECT OR CONSEQUENTIAL DAMAGES OF ANY
 * KIND, OR ANY DAMAGES WHATSOEVER, INCLUDING WITHOUT LIMITATION,
 * LOSS OF PROFIT, LOSS OF USE, SAVINGS OR REVENUE, OR THE CLAIMS OF
 * THIRD PARTIES, WHETHER OR NOT SILICON GRAPHICS, INC.  HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH LOSS, HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, ARISING OUT OF OR IN CONNECTION WITH THE
 * POSSESSION, USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * US Government Users Restricted Rights
 * Use, duplication, or disclosure by the Government is subject to
 * restrictions set forth in FAR 52.227.19(c)(2) or subparagraph
 * (c)(1)(ii) of the Rights in Technical Data and Computer Software
 * clause at DFARS 252.227-7013 and/or in similar or successor
 * clauses in the FAR or the DOD or NASA FAR Supplement.
 * Unpublished-- rights reserved under the copyright laws of the
 * United States.  Contractor/manufacturer is Silicon Graphics,
 * Inc., 2011 N.  Shoreline Blvd., Mountain View, CA 94039-7311.
 *
 * OpenGL(R) is a registered trademark of Silicon Graphics, Inc.
 */

/*
 * hello.c
 * This is a simple, introductory OpenGL program.
 */
import com.pflager.glut;

public class texgen extends glut {

	final int stripeImageWidth = 32;
	byte[] stripeImage = new byte[4 * stripeImageWidth];

//	static GLuint texName

	void makeStripeImage() {
		int j;

		for (j = 0; j < stripeImageWidth; j++) {
			stripeImage[4 * j] = (byte) ((j <= 4) ? 255 : 0);
			stripeImage[4 * j + 1] = (byte) ((j > 4) ? 255 : 0);
			stripeImage[4 * j + 2] = (byte) 0;
			stripeImage[4 * j + 3] = (byte) 255;
		}
	}

	/* planes for texture coordinate generation */
	double xequalzero[] = { 1.0, 0.0, 0.0, 0.0 };
	double slanted[] = { 1.0, 1.0, 1.0, 0.0 };
	double[] currentCoeff;
	int currentPlane;
	int currentGenMode;

	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glEnable(GL_DEPTH_TEST);
		glShadeModel(GL_SMOOTH);

		makeStripeImage();
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

//   glGenTextures(1, &texName);
//   glBindTexture(GL_TEXTURE_1D, texName);
		glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
//   glTexImage1D(GL_TEXTURE_1D, 0, GL_RGBA, stripeImageWidth, 0,
//                GL_RGBA, GL_UNSIGNED_BYTE, stripeImage);
// #else
		glTexImage1D(GL_TEXTURE_1D, 0, 4, stripeImageWidth, 0, GL_RGBA, GL_UNSIGNED_BYTE, stripeImage);

		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		currentCoeff = xequalzero;
		currentGenMode = GL_OBJECT_LINEAR;
		currentPlane = GL_OBJECT_PLANE;
		glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, currentGenMode);
		glTexGenfv(GL_S, currentPlane, currentCoeff);

		glEnable(GL_TEXTURE_GEN_S);
		glEnable(GL_TEXTURE_1D);
		glEnable(GL_CULL_FACE);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glEnable(GL_AUTO_NORMAL);
		glEnable(GL_NORMALIZE);
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glMaterialf(GL_FRONT, GL_SHININESS, 64.0);
	}

	void display() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glPushMatrix();
		glRotatef(45.0, 0.0, 0.0, 1.0);
		//   glBindTexture(GL_TEXTURE_1D, texName);
		glutSolidTeapot(2.0);
		glPopMatrix();
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if (w <= h)
			glOrtho(-3.5, 3.5, -3.5 * (double) h / (double) w, 3.5 * (double) h / (double) w, -3.5, 3.5);
		else
			glOrtho(-3.5 * (double) w / (double) h, 3.5 * (double) w / (double) h, -3.5, 3.5, -3.5, 3.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 'e':
		case 'E':
			currentGenMode = GL_EYE_LINEAR;
			currentPlane = GL_EYE_PLANE;
			glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, currentGenMode);
			glTexGenfv(GL_S, currentPlane, currentCoeff);
			glutPostRedisplay();
			break;
		case 'o':
		case 'O':
			currentGenMode = GL_OBJECT_LINEAR;
			currentPlane = GL_OBJECT_PLANE;
			glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, currentGenMode);
			glTexGenfv(GL_S, currentPlane, currentCoeff);
			glutPostRedisplay();
			break;
		case 's':
		case 'S':
			currentCoeff = slanted;
			glTexGenfv(GL_S, currentPlane, currentCoeff);
			glutPostRedisplay();
			break;
		case 'x':
		case 'X':
			currentCoeff = xequalzero;
			glTexGenfv(GL_S, currentPlane, currentCoeff);
			glutPostRedisplay();
			break;
		case 27:
			System.exit(0);
			break;
		default:
			break;
		}
	}

	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
		glutInitWindowSize(256, 256);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("texgen");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new texgen().main(args.length, args));
	}

}
