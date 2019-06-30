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
 *  trim.c
 *  This program draws a NURBS surface in the shape of a
 *  symmetrical hill, using both a NURBS curve and pwl
 *  (piecewise linear) curve to trim part of the surface.
 */
package org.pflager.redbook.dpp;

import com.pflager.glut;

public class trim extends glut {
	double[][][] ctlpoints = new double[4][4][3];

	GLUnurbs theNurb;

	/*
	 *  Initializes the control points of the surface to a small hill.
	 *  The control points range from -3 to +3 in x, y, and z
	 */
	void init_surface()
	{
	   int u, v;
	   for (u = 0; u < 4; u++) {
	      for (v = 0; v < 4; v++) {
	         ctlpoints[u][v][0] = 2.0*((double)u - 1.5);
	         ctlpoints[u][v][1] = 2.0*((double)v - 1.5);

	         if ( (u == 1 || u == 2) && (v == 1 || v == 2))
	            ctlpoints[u][v][2] = 3.0;
	         else
	            ctlpoints[u][v][2] = -3.0;
	      }
	   }
	}

	void nurbsError(int errorCode)
	{
	   String estring;

	   estring = gluErrorString(errorCode);
	   System.err.printf( "Nurbs Error: %s\n", estring);
	   System.exit(0);
	}

	/*  Initialize material property and depth buffer.
	 */
	void init()
	{
	   double mat_diffuse[] = { 0.7, 0.7, 0.7, 1.0 };
	   double mat_specular[] = { 1.0, 1.0, 1.0, 1.0 };
	   double mat_shininess[] = { 100.0 };

	   glClearColor (0.0, 0.0, 0.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	   glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);

	   glEnable(GL_LIGHTING);
	   glEnable(GL_LIGHT0);
	   glEnable(GL_DEPTH_TEST);
	   glEnable(GL_AUTO_NORMAL);
	   glEnable(GL_NORMALIZE);

	   init_surface();

	   theNurb = gluNewNurbsRenderer();
	   gluNurbsProperty(theNurb, GLU_SAMPLING_TOLERANCE, 25.0);
	   gluNurbsProperty(theNurb, GLU_DISPLAY_MODE, GLU_FILL);
	   gluNurbsCallback(theNurb, GLU_ERROR, this::nurbsError);
	}

	void display()
	{
	   double knots[] = {0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0};
	   double edgePt[][] = /* counter clockwise */
	      {{0.0, 0.0}, {1.0, 0.0}, {1.0, 1.0}, {0.0, 1.0}, {0.0, 0.0}};
	   double curvePt[][] = /* clockwise */
	      {{0.25, 0.5}, {0.25, 0.75}, {0.75, 0.75}, {0.75, 0.5}};
	   double curveKnots[] =
	      {0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0};
	   double pwlPt[][] = /* clockwise */
	      {{0.75, 0.5}, {0.5, 0.25}, {0.25, 0.5}};

	   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	   glPushMatrix();
	   glRotatef(330.0, 1.,0.,0.);
	   glScalef (0.5, 0.5, 0.5);

	   gluBeginSurface(theNurb);
	   gluNurbsSurface(theNurb, 8, knots, 8, knots,
	                   4 * 3, 3, flatten(ctlpoints),
	                   4, 4, GL_MAP2_VERTEX_3);
	   gluBeginTrim (theNurb);
	      gluPwlCurve (theNurb, 5, flatten(edgePt), 2, GLU_MAP1_TRIM_2);
	   gluEndTrim (theNurb);
	   gluBeginTrim (theNurb);
	      gluNurbsCurve (theNurb, 8, curveKnots, 2,
	                     flatten(curvePt), 4, GLU_MAP1_TRIM_2);
	      gluPwlCurve (theNurb, 3, flatten(pwlPt), 2, GLU_MAP1_TRIM_2);
	   gluEndTrim (theNurb);
	   gluEndSurface(theNurb);

	   glPopMatrix();
	   glFlush();
	}

	void reshape(int w, int h)
	{
	   glViewport(0, 0, w, h);
	   glMatrixMode(GL_PROJECTION);
	   glLoadIdentity();
	   gluPerspective (45.0, (double)w/(double)h, 3.0, 8.0);

	   glMatrixMode(GL_MODELVIEW);
	   glLoadIdentity();
	   glTranslatef (0.0, 0.0, -5.0);
	}

	void keyboard(char key, int x, int y)
	{
	   switch (key) {
	      case 27:
	         System.exit(0);
	         break;
	   }
	}

	/*  Main Loop
	 */
	public int main(int argc, String[] argv)
	{
	   glutInit(argc, argv);
	   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	   glutInitWindowSize (500, 500);
	   glutInitWindowPosition (100, 100);
	   glutCreateWindow("trim");
	   init();
	   glutReshapeFunc(this::reshape);
	   glutDisplayFunc(this::display);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0;
	}

	public static void main(String[] args) {
		System.exit(new trim().main(args.length, args));
	}

}
