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

/*  mipmap.c
 *  This program demonstrates using mipmaps for texture maps.
 *  To overtly show the effect of mipmaps, each mipmap reduction
 *  level has a solidly colored, contrasting texture image.
 *  Thus, the quadrilateral which is drawn is drawn with several
 *  different colors.
 */
import com.pflager.glut;

public class mipmap extends glut {

	byte[] mipmapImage32 = new byte[32 * 32 * 4];
	byte[] mipmapImage16 = new byte[16 * 16 * 4];
	byte[] mipmapImage8 = new byte[8 * 8 * 4];
	byte[] mipmapImage4 = new byte[4 * 4 * 4];
	byte[] mipmapImage2 = new byte[2 * 2 * 4];
	byte[] mipmapImage1 = new byte[1 * 1 * 4];

	void makeImages() {
		int i, j;

		for (i = 0; i < 32; i++) {
			for (j = 0; j < 32; j++) {
				mipmapImage32[i * 32 * 4 + j * 4 + 0] = (byte)255;
				mipmapImage32[i * 32 * 4 + j * 4 + 1] = (byte)255;
				mipmapImage32[i * 32 * 4 + j * 4 + 2] = 0;
				mipmapImage32[i * 32 * 4 + j * 4 + 3] = (byte)255;
			}
		}
		for (i = 0; i < 16; i++) {
			for (j = 0; j < 16; j++) {
				mipmapImage16[i * 16 * 4 + j * 4 + 0] = (byte)255;
				mipmapImage16[i * 16 * 4 + j * 4 + 1] = 0;
				mipmapImage16[i * 16 * 4 + j * 4 + 2] = (byte)255;
				mipmapImage16[i * 16 * 4 + j * 4 + 3] = (byte)255;
			}
		}
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				mipmapImage8[i * 8 * 4 + j * 4 + 0] = (byte)255;
				mipmapImage8[i * 8 * 4 + j * 4 + 1] = 0;
				mipmapImage8[i * 8 * 4 + j * 4 + 2] = 0;
				mipmapImage8[i * 8 * 4 + j * 4 + 3] = (byte)255;
			}
		}
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				mipmapImage4[i * 4 * 4 + j * 4 + 0] = 0;
				mipmapImage4[i * 4 * 4 + j * 4 + 1] = (byte)255;
				mipmapImage4[i * 4 * 4 + j * 4 + 2] = 0;
				mipmapImage4[i * 4 * 4 + j * 4 + 3] = (byte)255;
			}
		}
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 2; j++) {
				mipmapImage2[i * 2 * 4 + j * 4 + 0] = 0;
				mipmapImage2[i * 2 * 4 + j * 4 + 1] = 0;
				mipmapImage2[i * 2 * 4 + j * 4 + 2] = (byte)255;
				mipmapImage2[i * 2 * 4 + j * 4 + 3] = (byte)255;
			}
		}
		mipmapImage1[0 * 1 * 4 + 0 * 4 + 0] = (byte)255;
		mipmapImage1[0 * 1 * 4 + 0 * 4 + 1] = (byte)255;
		mipmapImage1[0 * 1 * 4 + 0 * 4 + 2] = (byte)255;
		mipmapImage1[0 * 1 * 4 + 0 * 4 + 3] = (byte)255;
	}

	void init() {
		glEnable(GL_DEPTH_TEST);
		glShadeModel(GL_FLAT);

		glTranslatef(0.0, 0.0, -3.6);
		makeImages();
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 32, 32, 0, GL_RGBA, GL_UNSIGNED_BYTE, mipmapImage32);
		glTexImage2D(GL_TEXTURE_2D, 1, GL_RGBA, 16, 16, 0, GL_RGBA, GL_UNSIGNED_BYTE, mipmapImage16);
		glTexImage2D(GL_TEXTURE_2D, 2, GL_RGBA, 8, 8, 0, GL_RGBA, GL_UNSIGNED_BYTE, mipmapImage8);
		glTexImage2D(GL_TEXTURE_2D, 3, GL_RGBA, 4, 4, 0, GL_RGBA, GL_UNSIGNED_BYTE, mipmapImage4);
		glTexImage2D(GL_TEXTURE_2D, 4, GL_RGBA, 2, 2, 0, GL_RGBA, GL_UNSIGNED_BYTE, mipmapImage2);
		glTexImage2D(GL_TEXTURE_2D, 5, GL_RGBA, 1, 1, 0, GL_RGBA, GL_UNSIGNED_BYTE, mipmapImage1);

		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
		glEnable(GL_TEXTURE_2D);
	}

	void display() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBegin(GL_QUADS);
		glTexCoord2f(0.0, 0.0);
		glVertex3f(-2.0, -1.0, 0.0);
		glTexCoord2f(0.0, 8.0);
		glVertex3f(-2.0, 1.0, 0.0);
		glTexCoord2f(8.0, 8.0);
		glVertex3f(2000.0, 1.0, -6000.0);
		glTexCoord2f(8.0, 0.0);
		glVertex3f(2000.0, -1.0, -6000.0);
		glEnd();
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60.0, (double) w / (double) h, 1.0, 30000.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
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
		glutInitWindowSize(500, 500);
		glutInitWindowPosition(50, 50);
		glutCreateWindow("mipmap");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new mipmap().main(args.length, args));
	}

}
