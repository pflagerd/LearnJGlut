package com.pflager.redbook.akp;

import com.pflager.glut;

public class fog extends glut {

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
	 *  fog.c
	 *  This program draws 5 red spheres, each at a different 
	 *  z distance from the eye, in different types of fog.  
	 *  Pressing the f key chooses between 3 types of 
	 *  fog:  exponential, exponential squared, and linear.  
	 *  In this program, there is a fixed density value, as well 
	 *  as fixed start and end values for the linear fog.
	 */
	int fogMode;

	/*  Initialize depth buffer, fog, light source, 
	 *  material property, and lighting model.
	 */
	void init()
	{
	   float position[] = { 0.5f, 0.5f, 3.0f, 0.0f };

	   glEnable(GL_DEPTH_TEST);

	   glLightfv(GL_LIGHT0, GL_POSITION, position);
	   glEnable(GL_LIGHTING);
	   glEnable(GL_LIGHT0);
	   {
	      float mat[] = {0.1745f, 0.01175f, 0.01175f, 0.0f};	
	      glMaterialfv (GL_FRONT, GL_AMBIENT, mat);
	      mat[0] = 0.61424f; mat[1] = 0.04136f; mat[2] = 0.04136f;	
	      glMaterialfv (GL_FRONT, GL_DIFFUSE, mat);
	      mat[0] = 0.727811f; mat[1] = 0.626959f; mat[2] = 0.626959f;
	      glMaterialfv (GL_FRONT, GL_SPECULAR, mat);
	      glMaterialf (GL_FRONT, GL_SHININESS, 0.6*128.0);
	   }

	   glEnable(GL_FOG);
	   {
	      float fogColor[] = {0.5f, 0.5f, 0.5f, 1.0f};

	      fogMode = GL_EXP;
	      glFogi (GL_FOG_MODE, fogMode);
	      glFogfv (GL_FOG_COLOR, fogColor);
	      glFogf (GL_FOG_DENSITY, 0.35);
	      glHint (GL_FOG_HINT, GL_DONT_CARE);
	      glFogf (GL_FOG_START, 1.0);
	      glFogf (GL_FOG_END, 5.0);
	   }
	   glClearColor(0.5, 0.5, 0.5, 1.0);  /* fog color */
	}

	void renderSphere (float x, float y, float z)
	{
	   glPushMatrix();
	   glTranslatef (x, y, z);
	   glutSolidSphere(0.4, 16, 16);
	   glPopMatrix();
	}

	/* display() draws 5 spheres at different z positions.
	 */
	void display()
	{
	   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	   renderSphere (-2.0f, -0.5f, -1.0f);
	   renderSphere (-1.0f, -0.5f, -2.0f);
	   renderSphere (0.0f, -0.5f, -3.0f);
	   renderSphere (1.0f, -0.5f, -4.0f);
	   renderSphere (2.0f, -0.5f, -5.0f);
	   glFlush();
	}

	void reshape(int w, int h)
	{
	   glViewport(0, 0, w, h);
	   glMatrixMode(GL_PROJECTION);
	   glLoadIdentity();
	   if (w <= h)
	      glOrtho (-2.5, 2.5, -2.5*h/w,
	         2.5*h/w, -10.0, 10.0);
	   else
	      glOrtho (-2.5*w/h,
	         2.5*w/h, -2.5, 2.5, -10.0, 10.0);
	   glMatrixMode(GL_MODELVIEW);
	   glLoadIdentity ();
	}

	void keyboard( char key, int x, int y)
	{
	   switch (key) {
	      case 'f':
	      case 'F':
	         if (fogMode == GL_EXP) {
		    fogMode = GL_EXP2;
		    System.out.println("Fog mode is GL_EXP2\n");
	         }
	         else if (fogMode == GL_EXP2) {
	            fogMode = GL_LINEAR;
	            System.out.println("Fog mode is GL_LINEAR\n");
	         }
	         else if (fogMode == GL_LINEAR) {
	            fogMode = GL_EXP;
	            System.out.println("Fog mode is GL_EXP\n");
	         }
	         glFogi (GL_FOG_MODE, fogMode);
	         glutPostRedisplay();
	         break;
	      case 27:
	         System.exit(0);
	         break;
	      default:
	         break;
	   }
	}


	/*  Main Loop
	 *  Open window with initial window size, title bar, 
	 *  RGBA display mode, depth buffer, and handle input events.
	 */
	int main(int argc,String[] argv)
	{
	   glutInit(argc, argv);
	   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	   glutInitWindowSize(500, 500);
	   glutCreateWindow("fog");
	   init();
	   glutReshapeFunc (this::reshape);
	   glutKeyboardFunc (this::keyboard);
	   glutDisplayFunc (this::display);
	   glutMainLoop();
	   return 0;
	}

	public static void main(String[] args) {
		System.exit(new fog().main(args.length, args));
	}
}
