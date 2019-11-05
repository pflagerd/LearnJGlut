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
 *  tess.c
 *  This program demonstrates polygon tessellation.
 *  Two tesselated objects are drawn.  The first is a
 *  rectangle with a triangular hole.  The second is a
 *  smooth shaded, self-intersecting star.
 *
 *  Note the exterior rectangle is drawn with its vertices
 *  in counter-clockwise order, but its interior clockwise.
 *  Note the combineCallback is needed for the self-intersecting
 *  star.  Also note that removing the TessProperty for the
 *  star will make the interior unshaded (WINDING_ODD).
 */
package org.pflager.redbook.dpp;

import com.pflager.glut;

public class tess extends glut {

	int startList;

	void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(1.0, 1.0, 1.0);
		glCallList(startList);
		glCallList(startList + 1);
		glFlush();
	}

	void begin(int which) {
		glBegin(which);
	}

	void error(int errorCode) {
		System.err.printf("Tessellation Error: %s\n", gluErrorString(errorCode));
		System.exit(0);
	}

	void end() {
		glEnd();
	}

	void vertexCallback(double[] /* 3 */ vertex, double[] /* 3 */ color) {
		glVertex3dv(vertex);
		glColor3dv(color);
	}

	/*
	 * combineCallback is used to create a new vertex when edges intersect. coordinate location is trivial to calculate, but weight[4] may be used to average color, normal, or texture coordinate data. In this program, color is weighted.
	 */

	void combineCallback(double coords[] /* 3 */, double vertex_data[][] /* 4 x 4 */, double weight[] /* 4 */, double[] vertex_out /* 6 */ ) {
		int i;

		vertex_out[0] = coords[0];
		vertex_out[1] = coords[1];
		vertex_out[2] = coords[2];
		for (i = 3; i < 7; i++)
			vertex_out[i] = weight[0] * vertex_data[0][i] + weight[1] * vertex_data[1][i] + weight[2] * vertex_data[2][i] + weight[3] * vertex_data[3][i];
	}

	GLUtesselator tobj;

	public void vertex(Object vertex_data) {
		glVertex3dv((double[])vertex_data);
	}

	void init() {
		double[][] rect = new double[][] { { 50.0, 50.0, 0.0 }, { 200.0, 50.0, 0.0 }, { 200.0, 200.0, 0.0 }, { 50.0, 200.0, 0.0 } };
		double[][] tri = new double[][] { { 75.0, 75.0, 0.0 }, { 125.0, 175.0, 0.0 }, { 175.0, 75.0, 0.0 } };
		double[][] star = new double[][] { { 250.0, 50.0, 0.0, 1.0, 0.0, 1.0 }, { 325.0, 200.0, 0.0, 1.0, 1.0, 0.0 }, { 400.0, 50.0, 0.0, 0.0, 1.0, 1.0 }, { 250.0, 150.0, 0.0, 1.0, 0.0, 0.0 }, { 400.0, 150.0, 0.0, 0.0, 1.0, 0.0 } };

		glClearColor(0.0, 0.0, 0.0, 0.0);

		startList = glGenLists(2);

		tobj = gluNewTess();
		gluTessCallback(tobj, GLU_TESS_VERTEX, (VertexFunc) this::vertex);
		gluTessCallback(tobj, GLU_TESS_BEGIN, (BeginFunc) this::begin);
		gluTessCallback(tobj, GLU_TESS_END, (EndFunc) this::end);
		gluTessCallback(tobj, GLU_TESS_ERROR, (ErrorFunc) this::error);

		/* rectangle with triangular hole inside */
		glNewList(startList, GL_COMPILE);
		glShadeModel(GL_FLAT);
		gluTessBeginPolygon(tobj, null);
	      gluTessBeginContour(tobj);
	         gluTessVertex(tobj, rect[0], rect[0]);
	         gluTessVertex(tobj, rect[1], rect[1]);
	         gluTessVertex(tobj, rect[2], rect[2]);
	         gluTessVertex(tobj, rect[3], rect[3]);
	      gluTessEndContour(tobj);
	      gluTessBeginContour(tobj);
	         gluTessVertex(tobj, tri[0], tri[0]);
	         gluTessVertex(tobj, tri[1], tri[1]);
	         gluTessVertex(tobj, tri[2], tri[2]);
	      gluTessEndContour(tobj);
		gluTessEndPolygon(tobj);
		glEndList();

////	   gluTessCallback(tobj, GLU_TESS_VERTEX,
////			   (_GLUfuncptr)vertexCallback);
//		gluTessCallback(tobj, GLU_TESS_BEGIN, (BeginFunc) this::begin);
//		gluTessCallback(tobj, GLU_TESS_END, (EndFunc) this::end);
//		gluTessCallback(tobj, GLU_TESS_ERROR, (ErrorFunc) this::error);
////	   gluTessCallback(tobj, GLU_TESS_COMBINE,
////			   (_GLUfuncptr)combineCallback);
//
//		/* smooth shaded, self-intersecting star */
//		glNewList(startList + 1, GL_COMPILE);
//		glShadeModel(GL_SMOOTH);
////	   gluTessProperty(tobj, GLU_TESS_WINDING_RULE,
////	                   GLU_TESS_WINDING_POSITIVE);
//		gluTessBeginPolygon(tobj, null);
//		{
//	      gluTessBeginContour(tobj); {
//	         gluTessVertex(tobj, star[0], star[0]);
//	         gluTessVertex(tobj, star[1], star[1]);
//	         gluTessVertex(tobj, star[2], star[2]);
//	         gluTessVertex(tobj, star[3], star[3]);
//	         gluTessVertex(tobj, star[4], star[4]);
//	      }
//	      gluTessEndContour(tobj);
//		}
//		gluTessEndPolygon(tobj);
//		glEndList();
		gluDeleteTess(tobj);
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0.0, (double) w, 0.0, (double) h);
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
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(500, 500);
		glutCreateWindow("tess");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new tess().main(args.length, args));
	}

}
