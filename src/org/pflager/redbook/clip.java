package org.pflager.redbook;

import com.pflager.glut;

public class clip extends glut{

	void init() 
	{
	   glClearColor (0.0, 0.0, 0.0, 0.0);
	   glShadeModel (GL_FLAT);
	}

	void display()
	{
	   double eqn[] = {0.0, 1.0, 0.0, 0.0};
	   double eqn2[] = {1.0, 0.0, 0.0, 0.0};

	   glClear(GL_COLOR_BUFFER_BIT);

	   glColor3f (1.0, 1.0, 1.0);
	   glPushMatrix();
	   glTranslatef (0.0, 0.0, -5.0);

	/*    clip lower half -- y < 0          */
	   glClipPlane (GL_CLIP_PLANE0, eqn);
	   glEnable (GL_CLIP_PLANE0);
	/*    clip left half -- x < 0           */
	   glClipPlane (GL_CLIP_PLANE1, eqn2);
	   glEnable (GL_CLIP_PLANE1);

	   glRotatef (90.0, 1.0, 0.0, 0.0);
	   glutWireSphere(1.0, 20, 16);
	   glPopMatrix();
	   glFlush ();
	}

	void reshape (int w, int h)
	{
	   glViewport (0, 0,  w,  h); 
	   glMatrixMode (GL_PROJECTION);
	   glLoadIdentity ();
	   gluPerspective(60.0,  w/ h, 1.0, 20.0);
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
	   glutCreateWindow ("clip");
	   init ();
	   glutDisplayFunc(this::display);
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();

	   return 0;
	}
	
	public static void main(String[] args) {
		System.exit(new clip().main(args.length, args));
	}
	
}
