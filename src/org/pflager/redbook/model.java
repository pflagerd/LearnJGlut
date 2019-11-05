package org.pflager.redbook;
import com.pflager.glut;
public class model extends glut{

	void init() 
	{
	   glClearColor (0.0, 0.0, 0.0, 0.0);
	   glShadeModel (GL_FLAT);
	}

	void draw_triangle()
	{
	   glBegin (GL_LINE_LOOP);
	   glVertex2f(0.0, 25.0);
	   glVertex2f(25.0, -25.0);
	   glVertex2f(-25.0, -25.0);
	   glEnd();
	}

	void display()
	{
	   glClear (GL_COLOR_BUFFER_BIT);
	   glColor3f (1.0, 1.0, 1.0);

	   glLoadIdentity ();
	   glColor3f (1.0, 1.0, 1.0);
	   draw_triangle ();

	   glEnable (GL_LINE_STIPPLE);
	   glLineStipple (1, 0xF0F0);
	   glLoadIdentity ();
	   glTranslatef (-20.0f, 0.0f, 0.0f);
	   draw_triangle ();

	   glLineStipple (1, 0xF00F);
	   glLoadIdentity ();
	   glScalef (1.5f, 0.5f, 1.0f);
	   draw_triangle ();

	   glLineStipple (1, 0x8888);
	   glLoadIdentity ();
	   glRotatef (90.0, 0.0, 0.0, 1.0);
	   draw_triangle ();
	   glDisable (GL_LINE_STIPPLE);

	   glFlush ();
	}

	void reshape (int w, int h)
	{
	   glViewport (0, 0,  w,  h);
	   glMatrixMode (GL_PROJECTION);
	   glLoadIdentity ();
	   if (w <= h)
	      glOrtho (-50.0, 50.0, -50.0*(float)h/(float)w,50.0*(float)h/(float)w, -1.0, 1.0);
	   else
	      glOrtho (-50.0*(float)w/(float)h,50.0*(float)w/(float)h, -50.0, 50.0, -1.0, 1.0);
	   glMatrixMode(GL_MODELVIEW);
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
	   glutCreateWindow ("model");
	   init ();
	   glutDisplayFunc(this::display); 
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc (this::keyboard);
	   glutMainLoop();
	   return 0;
	}

	public static void main(String[] args) {
		System.exit(new model().main(args.length, args));
	}
}
