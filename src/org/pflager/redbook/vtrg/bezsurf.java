package org.pflager.redbook.vtrg;

import com.pflager.glut;

public class bezsurf extends glut{

	double ctrlpoints[][][] = {
			{{-1.5, -1.5, 4.0}, {-0.5, -1.5, 2.0},
			{0.5, -1.5, -1.0}, {1.5, -1.5, 2.0}},
			{{-1.5, -0.5, 1.0}, {-0.5, -0.5, 3.0},
			{0.5, -0.5, 0.0}, {1.5, -0.5, -1.0}},
			{{-1.5, 0.5, 4.0}, {-0.5, 0.5, 0.0},
			{0.5, 0.5, 3.0}, {1.5, 0.5, 4.0}},
			{{-1.5, 1.5, -2.0}, {-0.5, 1.5, -2.0},
			{0.5, 1.5, 0.0}, {1.5, 1.5, -1.0}}
			};
	void init()
	{
		glClearColor (0.0, 0.0, 0.0, 0.0);
		glMap2f(GL_MAP2_VERTEX_3, 0, 1, 3, 4,0, 1, 12, 4, ctrlpoints);
		glEnable(GL_MAP2_VERTEX_3);
		glMapGrid2f(20, 0.0, 1.0, 20, 0.0, 1.0);
		glEnable(GL_DEPTH_TEST);
		glShadeModel(GL_FLAT);
	}
	void display()
	{
		int i, j;
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f(1.0, 1.0, 1.0);
		glPushMatrix ();
		glRotatef(85.0, 1.0, 1.0, 1.0);
		for (j = 0; j <= 8; j++) {
			glBegin(GL_LINE_STRIP);
			for (i = 0; i <= 30; i++)
				glEvalCoord2f((double)i/30.0, (double)j/8.0);
				glEnd();
				glBegin(GL_LINE_STRIP);
				for (i = 0; i <= 30; i++)
				glEvalCoord2f((double)j/8.0, (double)i/30.0);
				glEnd();
		}
			glPopMatrix ();
			glFlush();
	}
	void reshape(int w, int h)
	{
	   glViewport(0, 0,  w,  h);
	   glMatrixMode(GL_PROJECTION);
	   glLoadIdentity();
	   if (w <= h)
	      glOrtho(-4.0, 4.0, -4.0*(double)h/(double)w, 4.0*(double)h/(double)w, -4.0, 4.0);
	   else
	      glOrtho(-4.0*(double)w/(double)h, 4.0*(double)w/(double)h, -4.0, 4.0, -4.0, 4.0);
	   glMatrixMode(GL_MODELVIEW);
	   glLoadIdentity();
	}

	void keyboard( char key, int x, int y)
	{
	   switch (key) {
	      case 27:
	         System.exit(0);
	         break;
	   }
	}

	int main(int argc, String[] argv)
	{
	   glutInit(argc, argv);
	   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	   glutInitWindowSize (500, 500);
	   glutInitWindowPosition (100, 100);
	   glutCreateWindow ("bezsurf");
	   init ();
	   glutDisplayFunc(this::display);
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0;
	}
	public static void main(String[] args) {
		System.exit(new bezsurf().main(args.length, args));

	}
}
