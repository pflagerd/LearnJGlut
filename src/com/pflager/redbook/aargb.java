package com.pflager.redbook;

import com.pflager.glut;

public class aargb extends glut{
	static float rotAngle = 0.;
	/* Initialize antialiasing for RGBA mode, including alpha
	* blending, hint, and line width. Print out implementation
	* specific info on line width granularity and width. */
	
	   void init()
	{
		
		glEnable (GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glShadeModel (GL_FLAT);
		glClearColor (0.0, 0.0, 0.0, 0.0);
	}

	 void drawLeftTriangle()
	{
	/* draw yellow triangle on LHS of screen */
		 glBegin (GL_TRIANGLES);
			 glColor4f(1.0, 1.0, 0.0, 0.75);
			 glVertex3f(0.1, 0.9, 0.0);
			 glVertex3f(0.1, 0.1, 0.0);
			 glVertex3f(0.7, 0.5, 0.0);
		 glEnd();
	}
	 void drawRightTriangle()
	 {
	 /* draw cyan triangle on RHS of screen */
		 glBegin (GL_TRIANGLES);
			 glColor4f(0.0, 1.0, 1.0, 0.75);
			 glVertex3f(0.9, 0.9, 0.0);
			 glVertex3f(0.3, 0.5, 0.0);
			 glVertex3f(0.9, 0.1, 0.0);
		 glEnd();
	 }
	 
	 void display()
	 {
		 glClear(GL_COLOR_BUFFER_BIT);
		 if (leftFirst) {
			 drawLeftTriangle();
			 drawRightTriangle();
		 }
		 else {
			 drawRightTriangle();
			 drawLeftTriangle();
		 }
		 glFlush();
	 }
	 
	 void reshape(int w, int h)
	 {
		 glViewport(0, 0,  w, h);
		 glMatrixMode(GL_PROJECTION);
		 glLoadIdentity();
		 if (w <= h)
			 gluOrtho2D (-1.0, 1.0, -1.0*h/w, 1.0*h/w);
			 else
			 gluOrtho2D (-1.0*w/h,
			 1.0*w/h, -1.0, 1.0);
		 glMatrixMode(GL_MODELVIEW);
		 glLoadIdentity();
		 
	 }
	 
	 void keyboard(char key, int x, int y)
	 {
		 switch (key) {
			 case 'r':
			 case 'R':
				 rotAngle += 20.;
				 if (rotAngle >= 360.) rotAngle = 0;
				 glutPostRedisplay();
				 break;
			 case 27: /* Escape key */
			 System.exit(0);
			 break;
			 default:
			 break;
		 }
	 }
	 
	 int main(int argc, String[] argv)
	 {
		 glutInit(argc, argv);
		 glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
		 glutInitWindowSize (200, 200);
		 glutCreateWindow ("aargb");
		 init();
		 glutReshapeFunc (this::reshape);
		 glutKeyboardFunc (this::keyboard);
		 glutDisplayFunc (this::display);
		 glutMainLoop();
		 return 0;
	 }
	 public static void main(String[] args) {
			System.exit(new aargb().main(args.length, args));
			
		}
}
