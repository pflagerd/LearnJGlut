package com.pflager.redbook.akp;

import com.pflager.glut;

public class bezcurve extends glut{

	double[][] ctrlpoints = new double[][]{
			new double[]{ -4.0f, -4.0f, 0.0f}, new double[] { -2.0f, 4.0f, 0.0f}, 
			new double[]{2.0f, -4.0f, 0.0f}, new double[]{4.0f, 4.0f, 0.0f}};

		void init()
		{
		   glClearColor(0.0, 0.0, 0.0, 0.0);
		   glShadeModel(GL_FLAT);
		   glMap1f(GL_MAP1_VERTEX_3, 0.0, 1.0, 3, 4, ctrlpoints);
		   glEnable(GL_MAP1_VERTEX_3);
		}

		void display()
		{
		   int i;

		   glClear(GL_COLOR_BUFFER_BIT);
		   glColor3f(1.0, 1.0, 1.0);
		   glBegin(GL_LINE_STRIP);
		      for (i = 0; i <= 30; i++) 
		         glEvalCoord1f((float) i/30.0);
		   glEnd();
		   /* The following code displays the control points as dots. */
		   glPointSize(5.0);
		   glColor3f(1.0, 1.0, 0.0);
		   glBegin(GL_POINTS);
		      for (i = 0; i < 4; i++) 
		         glVertex3fv(ctrlpoints[i]);
		   glEnd();
		   glFlush();
		}

		void reshape(int w, int h)
		{
		   glViewport(0, 0, w, h);
		   glMatrixMode(GL_PROJECTION);
		   glLoadIdentity();
		   if (w <= h)
		      glOrtho(-5.0, 5.0, -5.0*h/w, 
		               5.0*h/w, -5.0, 5.0);
		   else
		      glOrtho(-5.0*w/h, 
		               5.0*w/h, -5.0, 5.0, -5.0, 5.0);
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
		   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
		   glutInitWindowSize (500, 500);
		   glutInitWindowPosition (100, 100);
		   glutCreateWindow ("bezcurve");
		   init ();
		   glutDisplayFunc(this::display);
		   glutReshapeFunc(this::reshape);
		   glutKeyboardFunc (this::keyboard);
		   glutMainLoop();
		   return 0;
		}
		
		public static void main(String[] args) {
			System.exit(new bezcurve().main(args.length, args));
		}
}
