package com.pflager.redbook;

import com.pflager.glut;

public class alpha  extends glut{
	
	boolean leftFirst = true;

	/* Initialize alpha blending function. */
	static   void init()
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
		 gluOrtho2D (0.0, 1.0, 0.0, 1.0*h/w);
		 else
		 gluOrtho2D (0.0, 1.0*w/h, 0.0, 1.0);
	 }
	 
	 void keyboard(char key, int x, int y)
	 {
		 switch (key) {
			 case 't':
			 case 'T':
			 leftFirst = !leftFirst;
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
		 glutCreateWindow ("alpha");
		 init();
		 glutReshapeFunc (this::reshape);
		 glutKeyboardFunc (this::keyboard);
		 glutDisplayFunc (this::display);
		 glutMainLoop();
		 return 0;
	 }
}
