package com.pflager.redbook;

import com.pflager.glut;

public class smooth4_1 extends glut {

	
	
	void init() 
	{
		glClearColor (0.0, 0.0, 0.0, 0.0);
		glShadeModel (GL_SMOOTH);
	}
	
	void display()
	{
		glClear (GL_COLOR_BUFFER_BIT);
		triangle ();
		glFlush ();
	}
	void triangle()
	{
		glBegin (GL_TRIANGLES);
		glColor3f (1.0, 0.0, 0.0);
		glVertex2f (5.0, 5.0);
		glColor3f (0.0, 1.0, 0.0);
		glVertex2f (25.0, 5.0);
		glColor3f (0.0, 0.0, 1.0);
		glVertex2f (5.0, 25.0);
		glEnd();
		
	}
	void reshape (int w, int h)
	{
		glViewport (0, 0,  w,  h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		if (w <= h)
		gluOrtho2D (0.0, 30.0, 0.0, 30.0*h/ w);
		else
		gluOrtho2D (0.0, 30.0*w/ h, 0.0, 30.0);
		glMatrixMode(GL_MODELVIEW);
	}

	public int main(int argc, String[] argv)
	{
		 glutInit(argc, argv);
		   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
		   glutInitWindowSize (500, 500); 
		   glutInitWindowPosition (100, 100);
		   glutCreateWindow ("smooth4_1");
		   init ();
		   glutDisplayFunc(this::display); 
		   glutReshapeFunc(this::reshape);
		   glutMainLoop();
		   return 0;
	}
	public static void main(String[] args) {
		System.exit(new smooth4_1().main(args.length, args));
	}
}
