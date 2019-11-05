package org.pflager.redbook;

import com.pflager.glut;

// Example 2-9
public class cube extends glut {

	void init() 
	{
	   glClearColor (0.0, 0.0, 0.0, 0.0);
	   glShadeModel (GL_FLAT);
	}

	void display()
	{
	   glClear (GL_COLOR_BUFFER_BIT);
	   glColor3f (1.0, 1.0, 1.0);
	   glLoadIdentity ();             /* clear the matrix */
	           /* viewing transformation  */
	   gluLookAt (0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	   glScalef (1.0, 2.0, 1.0);      /* modeling transformation */ 
	   glutWireCube (1.0);
	   glFlush ();
	}

	void reshape (int w, int h)
	{
	   glViewport (0, 0,  w,  h); 
	   glMatrixMode (GL_PROJECTION);
	   glLoadIdentity ();
	   glFrustum (-1.0, 1.0, -1.0, 1.0, 1.5, 20.0);
	   glMatrixMode (GL_MODELVIEW);
	}

	void keyboard(char key, int x, int y)
	{
	   switch (key) {
	      case 27:
	         System.exit(0);
	         break;
	   }
	}

	public int main(int argc, String[] argv)
	{
	   glutInit(argc, argv);
	   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
	   glutInitWindowSize (500, 500); 
	   glutInitWindowPosition (100, 100);
	   glutCreateWindow ("cube");
	   init ();
	   glutDisplayFunc(this::display); 
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0;
	}
	
	public static void main(String[] args) {
		System.exit(new cube().main(args.length, args));
	}

}
