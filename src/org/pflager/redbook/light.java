package org.pflager.redbook;

import com.pflager.glut;

public class light extends glut{

	void init() 
	{
	   float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	   float mat_shininess[] = { 50.0f };
	   float light_position[] = { 1.0f, 1.0f, 1.0f, 0.0f };

	   glClearColor (0.0, 0.0, 0.0, 0.0);
	   glShadeModel (GL_SMOOTH);

	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	   glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);
	   glLightfv(GL_LIGHT0, GL_POSITION, light_position);

	   glEnable(GL_LIGHTING);
	   glEnable(GL_LIGHT0);
	   glEnable(GL_DEPTH_TEST);
	}

	void display()
	{
	   glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	   glutSolidSphere (1.0, 20, 16);
	   glFlush ();
	}

	void reshape (int w, int h)
	{
	   glViewport (0, 0,  w,  h);
	   glMatrixMode (GL_PROJECTION);
	   glLoadIdentity();
	   if (w <= h)
	      glOrtho (-1.5, 1.5, -1.5*h/w,1.5*h/w, -10.0, 10.0);
	   else
	      glOrtho (-1.5*w/h,1.5*w/h, -1.5, 1.5, -10.0, 10.0);
	   glMatrixMode(GL_MODELVIEW);
	   glLoadIdentity();
	}

	void keyboard( char key, int x, int y)
	{
	   switch (key) {
	      case 27:
	        System. exit(0);
	         break;
	   }
	}

	public int main(int argc, String[] argv)
	{
	   glutInit(argc, argv);
	   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	   glutInitWindowSize (500, 500); 
	   glutInitWindowPosition (100, 100);
	   glutCreateWindow ("light");
	   init ();
	   glutDisplayFunc(this::display); 
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0;
	}
	public static void main(String[] args) {
		System.exit(new light().main(args.length, args));
	}
}
