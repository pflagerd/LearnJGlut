package org.pflager.redbook.kvs;

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

/*  checker.c
 *  This program texture maps a checkerboard image onto
 *  two rectangles.
 *
 *  If running this program on OpenGL 1.0, texture objects are
 *  not used.
 */
import com.pflager.glut;

public class checker extends glut {
	/* Create checkerboard texture */
	final int checkImageWidth = 64;
	final int checkImageHeight = 64;

	byte[] checkImage = new byte[checkImageHeight * checkImageWidth * 4];

	void makeCheckImage() {
		int i, j, c;

		for (i = 0; i < checkImageHeight; i++) {
			for (j = 0; j < checkImageWidth; j++) {
				c = (((i & 0x8) == 0 ? 1 : 0) ^ ((j & 0x8) == 0 ? 1 : 0)) * 255;
				checkImage[i * checkImageWidth * 4 + j * 4 + 0] = (byte) c;
				checkImage[i * checkImageWidth * 4 + j * 4 + 1] = (byte) c;
				checkImage[i * checkImageWidth * 4 + j * 4 + 2] = (byte) c;
				checkImage[i * checkImageWidth * 4 + j * 4 + 3] = (byte) 255;
			}
		}
	}

	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glShadeModel(GL_FLAT);
		glEnable(GL_DEPTH_TEST);

		makeCheckImage();
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, 4, checkImageWidth, checkImageHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE, checkImage);
	}

	void display() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glEnable(GL_TEXTURE_2D);
		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);

		glBegin(GL_QUADS);
		glTexCoord2f(0.0, 0.0);
		glVertex3f(-2.0, -1.0, 0.0);
		glTexCoord2f(0.0, 1.0);
		glVertex3f(-2.0, 1.0, 0.0);
		glTexCoord2f(1.0, 1.0);
		glVertex3f(0.0, 1.0, 0.0);
		glTexCoord2f(1.0, 0.0);
		glVertex3f(0.0, -1.0, 0.0);

		glTexCoord2f(0.0, 0.0);
		glVertex3f(1.0, -1.0, 0.0);
		glTexCoord2f(0.0, 1.0);
		glVertex3f(1.0, 1.0, 0.0);
		glTexCoord2f(1.0, 1.0);
		glVertex3f(2.41421, 1.0, -1.41421);
		glTexCoord2f(1.0, 0.0);
		glVertex3f(2.41421, -1.0, -1.41421);
		glEnd();
		glFlush();
		glDisable(GL_TEXTURE_2D);
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60.0, (double) w / (double) h, 1.0, 30.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(0.0, 0.0, -3.6);
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
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
		glutInitWindowSize(250, 250);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("checker");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new checker().main(args.length, args));
	}

}
