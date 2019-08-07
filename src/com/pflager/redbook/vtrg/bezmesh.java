package com.pflager.redbook.vtrg;

import com.pflager.glut;

public class bezmesh extends glut{

	double ctrlpoints[][][] = {
			   { {-1.5, -1.5, 4.0},
			     {-0.5, -1.5, 2.0},
			     {0.5, -1.5, -1.0},
			     {1.5, -1.5, 2.0}},
			   { {-1.5, -0.5, 1.0},
			     {-0.5, -0.5, 3.0},
			     {0.5, -0.5, 0.0},
			     {1.5, -0.5, -1.0}},
			   { {-1.5, 0.5, 4.0},
			     {-0.5, 0.5, 0.0},
			     {0.5, 0.5, 3.0},
			     {1.5, 0.5, 4.0}},
			   { {-1.5, 1.5, -2.0},
			     {-0.5, 1.5, -2.0},
			     {0.5, 1.5, 0.0},
			     {1.5, 1.5, -1.0}}
			};

			void initlights()
			{
			   double ambient[] = {0.2, 0.2, 0.2, 1.0};
			   double position[] = {0.0, 0.0, 2.0, 1.0};
			   double mat_diffuse[] = {0.6, 0.6, 0.6, 1.0};
			   double mat_specular[] = {1.0, 1.0, 1.0, 1.0};
			   double mat_shininess[] = {50.0};

			   glEnable(GL_LIGHTING);
			   glEnable(GL_LIGHT0);

			   glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
			   glLightfv(GL_LIGHT0, GL_POSITION, position);

			   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
			   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
			   glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);
			}

			void display()
			{
			   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			   glPushMatrix();
			   glRotatef(85.0, 1.0, 1.0, 1.0);
			   glEvalMesh2(GL_FILL, 0, 20, 0, 20);
			   glPopMatrix();
			   glFlush();
			}

			void init()
			{
			   glClearColor(0.0, 0.0, 0.0, 0.0);
			   glEnable(GL_DEPTH_TEST);
			   glMap2f(GL_MAP2_VERTEX_3, 0, 1, 3, 4,0, 1, 12, 4, ctrlpoints);
			   glEnable(GL_MAP2_VERTEX_3);
			   glEnable(GL_AUTO_NORMAL);
			   glMapGrid2f(20, 0.0, 1.0, 20, 0.0, 1.0);
			   initlights();       /* for lighted version only */
			}

			void reshape(int w, int h)
			{
			   glViewport(0, 0,  w,  h);
			   glMatrixMode(GL_PROJECTION);
			   glLoadIdentity();
			   if (w <= h)
			      glOrtho(-4.0, 4.0, -4.0*(double)h/(double)w, 4.0*(double)h/(double)w, -4.0, 4.0);
			   else
			      glOrtho(-4.0*(double)w/(double)h,4.0*(double)w/(double)h, -4.0, 4.0, -4.0, 4.0);
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
			   glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
			   glutInitWindowSize (500, 500);
			   glutInitWindowPosition (100, 100);
			   glutCreateWindow("bezmesh");
			   init();
			   glutReshapeFunc(this::reshape);
			   glutDisplayFunc(this::display);
			   glutKeyboardFunc(this::keyboard);
			   glutMainLoop();
			   return 0;
			}
			public static void main(String[] args) {
				System.exit(new bezmesh().main(args.length, args));

			}

}
