package com.pflager.redbook;

import com.pflager.glut;

public class planet extends glut {

	static int year = 0, day = 0;
	void init() 
	{
	   glClearColor (0.0, 0.0, 0.0, 0.0);
	   glShadeModel (GL_FLAT);
	}

	void display()
	{
	   glClear (GL_COLOR_BUFFER_BIT);
	   glColor3f (1.0, 1.0, 1.0);

	   glPushMatrix();
	   glutWireSphere(1.0, 20, 16);   /* draw sun */
	   glRotatef ( year, 0.0, 1.0, 0.0);
	   glTranslatef (2.0, 0.0, 0.0);
	   glRotatef ( day, 0.0, 1.0, 0.0);
	   glutWireSphere(0.2, 10, 8);    /* draw smaller planet */
	   glPopMatrix();
	   glutSwapBuffers();
	}

	void reshape (int w, int h)
	{
	   glViewport (0, 0,  w,  h); 
	   glMatrixMode (GL_PROJECTION);
	   glLoadIdentity ();
	   gluPerspective(60.0, (float) w/(float) h, 1.0, 20.0);
	   glMatrixMode(GL_MODELVIEW);
	   glLoadIdentity();
	   gluLookAt (0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	}

	void keyboard (char key, int x, int y)
	{
	   switch (key) {
	      case 'd':
	         day = (day + 10) % 360;
	         glutPostRedisplay();
	         break;
	      case 'D':
	         day = (day - 10) % 360;
	         glutPostRedisplay();
	         break;
	      case 'y':
	         year = (year + 5) % 360;
	         glutPostRedisplay();
	         break;
	      case 'Y':
	         year = (year - 5) % 360;
	         glutPostRedisplay();
	         break;
	      case 27:
	         System.exit(0);
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
	   glutCreateWindow ("planet");
	   init ();
	   glutDisplayFunc(this::display); 
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0;
	}
	public static void main(String[] args) {
		System.exit(new planet().main(args.length, args));
	}
}
