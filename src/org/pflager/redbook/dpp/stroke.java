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
 *  stroke.c
 *  This program demonstrates some characters of a
 *  stroke (vector) font.  The characters are represented
 *  by display lists, which are given numbers which
 *  correspond to the ASCII values of the characters.
 *  Use of glCallLists() is demonstrated.	{ return 0; }


 */
import com.pflager.glut;

public class stroke extends glut {

	final int PT = 1;
	final int STROKE = 2;
	final int END = 3;

	class CP {
		public CP(double x, double y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

		double x, y;
		int type;
	};

	CP Adata[] = { new CP(0, 0, PT), new CP(0, 9, PT), new CP(1, 10, PT), new CP(4, 10, PT), new CP(5, 9, PT), new CP(5, 0, STROKE), new CP(0, 5, PT), new CP(5, 5, END) };

	CP Edata[] = { new CP(5, 0, PT), new CP(0, 0, PT), new CP(0, 10, PT), new CP(5, 10, STROKE), new CP(0, 5, PT), new CP(4, 5, END) };

	CP Pdata[] = { new CP(0, 0, PT), new CP(0, 10, PT), new CP(4, 10, PT), new CP(5, 9, PT), new CP(5, 6, PT), new CP(4, 5, PT), new CP(0, 5, END) };

	CP Rdata[] = { new CP(0, 0, PT), new CP(0, 10, PT), new CP(4, 10, PT), new CP(5, 9, PT), new CP(5, 6, PT), new CP(4, 5, PT), new CP(0, 5, STROKE), new CP(3, 5, PT), new CP(5, 0, END) };

	CP Sdata[] = { new CP(0, 1, PT), new CP(1, 0, PT), new CP(4, 0, PT), new CP(5, 1, PT), new CP(5, 4, PT), new CP(4, 5, PT), new CP(1, 5, PT), new CP(0, 6, PT), new CP(0, 9, PT), new CP(1, 10, PT), new CP(4, 10, PT), new CP(5, 9, END) };

	/*
	 * drawLetter() interprets the instructions from the array for that letter and renders the letter with line segments.
	 */
	void drawLetter(CP[] l) {
		glBegin(GL_LINE_STRIP);
		for (int i = 0; i < l.length; i++) {
			switch (l[i].type) {
			case PT:
				glVertex2dv(new double[] { l[i].x, l[i].y });
				break;
			case STROKE:
				glVertex2dv(new double[] { l[i].x, l[i].y });
				glEnd();
				glBegin(GL_LINE_STRIP);
				break;
			case END:
				glVertex2dv(new double[] { l[i].x, l[i].y });
				glEnd();
				glTranslatef(8.0, 0.0, 0.0);
				return;
			}
		}
	}

	/* Create a display list for each of 6 characters */
	void init() {
		int base;

		glShadeModel(GL_FLAT);

		base = glGenLists(128);
		glListBase(base);
		glNewList(base + 'A', GL_COMPILE);
		drawLetter(Adata);
		glEndList();
		glNewList(base + 'E', GL_COMPILE);
		drawLetter(Edata);
		glEndList();
		glNewList(base + 'P', GL_COMPILE);
		drawLetter(Pdata);
		glEndList();
		glNewList(base + 'R', GL_COMPILE);
		drawLetter(Rdata);
		glEndList();
		glNewList(base + 'S', GL_COMPILE);
		drawLetter(Sdata);
		glEndList();
		glNewList(base + ' ', GL_COMPILE);
		glTranslatef(8.0, 0.0, 0.0);
		glEndList();
	}

	String test1 = "A SPARE SERAPE APPEARS AS";
	String test2 = "APES PREPARE RARE PEPPERS";

	void printStrokedString(String s) {
		int len = s.length();
		glCallLists(len, GL_BYTE, s.getBytes());
	}

	void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(1.0, 1.0, 1.0);
		glPushMatrix();
		glScalef(2.0, 2.0, 2.0);
		glTranslatef(10.0, 30.0, 0.0);
		printStrokedString(test1);
		glPopMatrix();
		glPushMatrix();
		glScalef(2.0, 2.0, 2.0);
		glTranslatef(10.0, 13.0, 0.0);
		printStrokedString(test2);
		glPopMatrix();
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0.0, (double) w, 0.0, (double) h);
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case ' ':
			glutPostRedisplay();
			break;
		case 27:
			System.exit(0);
		}
	}

	/*
	 * Main Loop Open window with initial window size, title bar, RGBA display mode, and handle input events.
	 */
	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(440, 120);
		glutCreateWindow("stroke");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new stroke().main(args.length, args));
	}

}
