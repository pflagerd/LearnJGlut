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

/*  texturesurf.c
 *  This program uses evaluators to generate a curved
 *  surface and automatically generated texture coordinates.
 */

package org.pflager.redbook.dpp;

import com.pflager.glut;

public class texturesurf extends glut {

	double[][][] ctrlpoints = { { { -1.5, -1.5, 4.0 }, { -0.5, -1.5, 2.0 }, { 0.5, -1.5, -1.0 }, { 1.5, -1.5, 2.0 } }, { { -1.5, -0.5, 1.0 }, { -0.5, -0.5, 3.0 }, { 0.5, -0.5, 0.0 }, { 1.5, -0.5, -1.0 } }, { { -1.5, 0.5, 4.0 }, { -0.5, 0.5, 0.0 }, { 0.5, 0.5, 3.0 }, { 1.5, 0.5, 4.0 } }, { { -1.5, 1.5, -2.0 }, { -0.5, 1.5, -2.0 }, { 0.5, 1.5, 0.0 }, { 1.5, 1.5, -1.0 } } };

	double[][][] texpts = { { { 0.0, 0.0 }, { 0.0, 1.0 } }, { { 1.0, 0.0 }, { 1.0, 1.0 } } };

	void display() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f(1.0, 1.0, 1.0);
		glEvalMesh2(GL_FILL, 0, 20, 0, 20);
		glFlush();
	}

	final int imageWidth = 64;
	final int imageHeight = 64;
	byte[] image = new byte[3 * imageWidth * imageHeight];

	void makeImage() {
		int i, j;
		double ti, tj;

		for (i = 0; i < imageWidth; i++) {
			ti = 2.0 * Math.PI * i / imageWidth;
			for (j = 0; j < imageHeight; j++) {
				tj = 2.0 * Math.PI * j / imageHeight;

				image[3 * (imageHeight * i + j)] = 		(byte) (new Double(127.0 * (1.0 + Math.sin(ti))).intValue() & 0xFF);
				image[3 * (imageHeight * i + j) + 1] = 	(byte) (new Double(127.0 * (1.0 + Math.cos(2 * tj))).intValue() & 0xFF);
				image[3 * (imageHeight * i + j) + 2] = 	(byte) (new Double(127.0 * (1.0 + Math.cos(ti + tj))).intValue() & 0xFF);
			}
		}
	}

	void init() {
		glMap2f(GL_MAP2_VERTEX_3, 0, 1, 3, 4, 0, 1, 12, 4, ctrlpoints);
		glMap2f(GL_MAP2_TEXTURE_COORD_2, 0, 1, 2, 2, 0, 1, 4, 2, texpts);
		glEnable(GL_MAP2_TEXTURE_COORD_2);
		glEnable(GL_MAP2_VERTEX_3);
		glMapGrid2f(20, 0.0, 1.0, 20, 0.0, 1.0);
		makeImage();
		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, imageWidth, imageHeight, 0, GL_RGB, GL_UNSIGNED_BYTE, image);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		glShadeModel(GL_FLAT);
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if (w <= h)
			glOrtho(-4.0, 4.0, -4.0 * (double) h / (double) w, 4.0 * (double) h / (double) w, -4.0, 4.0);
		else
			glOrtho(-4.0 * (double) w / (double) h, 4.0 * (double) w / (double) h, -4.0, 4.0, -4.0, 4.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glRotatef(85.0, 1.0, 1.0, 1.0);
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
			break;
		}
	}

	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
		glutInitWindowSize(500, 500);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("texturesurf");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new texturesurf().main(args.length, args));
	}

}
