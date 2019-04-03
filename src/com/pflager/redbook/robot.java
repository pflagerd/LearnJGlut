package com.pflager.redbook;

import com.pflager.glut;

public class robot extends glut{

	static int shoulder = 0, elbow = 0;

	void init() 
	{
	   glClearColor (0.0, 0.0, 0.0, 0.0);
	   glShadeModel (GL_FLAT);
	}

	void display()
	{
	   glClear (GL_COLOR_BUFFER_BIT);
	   glPushMatrix();
	   glTranslatef (-1.0, 0.0, 0.0);
	   glRotatef ( shoulder, 0.0, 0.0, 1.0);
	   glTranslatef (1.0, 0.0, 0.0);
	   glPushMatrix();
	   glScalef (2.0, 0.4, 1.0);
	   glutWireCube (1.0);
	   glPopMatrix();

	   glTranslatef (1.0, 0.0, 0.0);
	   glRotatef ( elbow, 0.0, 0.0, 1.0);
	   glTranslatef (1.0, 0.0, 0.0);
	   glPushMatrix();
	   glScalef (2.0, 0.4, 1.0);
	   glutWireCube (1.0);
	   glPopMatrix();

	   glPopMatrix();
	   glutSwapBuffers();
	}

	void reshape (int w, int h)
	{
	   glViewport (0, 0,  w,  h); 
	   glMatrixMode (GL_PROJECTION);
	   glLoadIdentity ();
	   gluPerspective(65.0, (float) w/(float) h, 1.0, 20.0);
	   glMatrixMode(GL_MODELVIEW);
	   glLoadIdentity();
	   glTranslatef (0.0, 0.0, -5.0);
	}

	void keyboard (char key, int x, int y)
	{
	   switch (key) {
	      case 's':
	         shoulder = (shoulder + 5) % 360;
	         glutPostRedisplay();
	         break;
	      case 'S':
	         shoulder = (shoulder - 5) % 360;
	         glutPostRedisplay();
	         break;
	      case 'e':
	         elbow = (elbow + 5) % 360;
	         glutPostRedisplay();
	         break;
	      case 'E':
	         elbow = (elbow - 5) % 360;
	         glutPostRedisplay();
	         break;
	      case 27:
	        System. exit(0);
	         break;
	      default:
	         break;
	   }
	}

	public int main(int argc, String[] argv)
	{
	   glutInit(argc, argv);
	   glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB);
	   glutInitWindowSize (500, 500); 
	   glutInitWindowPosition (100, 100);
	   glutCreateWindow ("robot");
	   init ();
	   glutDisplayFunc(this::display); 
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0;
	}
	public static void main(String[] args) {
		System.exit(new robot().main(args.length, args));
	}
}
