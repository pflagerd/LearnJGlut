package org.pflager.redbook;

import com.pflager.glut;

public class teapots extends glut{


	int teapotList;

	/*
	 * Initialize depth buffer, projection matrix, light source, and lighting
	 * model.  Do not specify a material property here.
	 */
	void init()
	{
	   double ambient[] = {0.0, 0.0, 0.0, 1.0};
	   double diffuse[] = {1.0, 1.0, 1.0, 1.0};
	   double position[] = {0.0, 3.0, 3.0, 0.0};

	   double lmodel_ambient[] = {0.2, 0.2, 0.2, 1.0};
	   double local_view[] = {0.0};

	   glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
	   glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse);
	   glLightfv(GL_LIGHT0, GL_POSITION, position);
	   glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
	   glLightModelfv(GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);

	   glFrontFace(GL_CW);
	   glEnable(GL_LIGHTING);
	   glEnable(GL_LIGHT0);
	   glEnable(GL_AUTO_NORMAL);
	   glEnable(GL_NORMALIZE);
	   glEnable(GL_DEPTH_TEST);
	/*  be efficient--make teapot display list  */
	   teapotList = glGenLists(1);
	   glNewList (teapotList, GL_COMPILE);
	   glutSolidTeapot(1.0);
	   glEndList ();
	}

	/*
	 * Move object into position.  Use 3rd through 12th
	 * parameters to specify the material property.  Draw a teapot.
	 */
	void renderTeapot(float x, float y,
	   float ambr, float ambg, float ambb,
	   float difr, float difg, float difb,
	   float specr, float specg, float specb, float shine)
	{
	   float mat[] = new float[4];

	   glPushMatrix();
	   glTranslatef(x, y, 0.0);
	   mat[0] = ambr; mat[1] = ambg; mat[2] = ambb; mat[3] = 1.0f;
	   glMaterialfv(GL_FRONT, GL_AMBIENT, mat);
	   mat[0] = difr; mat[1] = difg; mat[2] = difb;
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat);
	   mat[0] = specr; mat[1] = specg; mat[2] = specb;
	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat);
	   glMaterialf(GL_FRONT, GL_SHININESS, shine * 128.0);
	   glCallList(teapotList);
	   glPopMatrix();
	}

	/**
	 *  First column:  emerald, jade, obsidian, pearl, ruby, turquoise
	 *  2nd column:  brass, bronze, chrome, copper, gold, silver
	 *  3rd column:  black, cyan, green, red, white, yellow plastic
	 *  4th column:  black, cyan, green, red, white, yellow rubber
	 */
	void display()
	{
	   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	   renderTeapot(2.0f, 17.0f, 0.0215f, 0.1745f, 0.0215f,
	      0.07568f, 0.61424f, 0.07568f, 0.633f, 0.727811f, 0.633f, 0.6f);
	   renderTeapot(2.0f, 14.0f, 0.135f, 0.2225f, 0.1575f,
	      0.54f, 0.89f, 0.63f, 0.316228f, 0.316228f, 0.316228f, 0.1f);
	   renderTeapot(2.0f, 11.0f, 0.05375f, 0.05f, 0.06625f,
	      0.18275f, 0.17f, 0.22525f, 0.332741f, 0.328634f, 0.346435f, 0.3f);
	   renderTeapot(2.0f, 8.0f, 0.25f, 0.20725f, 0.20725f,
	      1f, 0.829f, 0.829f, 0.296648f, 0.296648f, 0.296648f, 0.088f);
	   renderTeapot(2.0f, 5.0f, 0.1745f, 0.01175f, 0.01175f,
	      0.61424f, 0.04136f, 0.04136f, 0.727811f, 0.626959f, 0.626959f, 0.6f);
	   renderTeapot(2.0f, 2.0f, 0.1f, 0.18725f, 0.1745f,
	      0.396f, 0.74151f, 0.69102f, 0.297254f, 0.30829f, 0.306678f, 0.1f);
	   renderTeapot(6.0f, 17.0f, 0.329412f, 0.223529f, 0.027451f,
	      0.780392f, 0.568627f, 0.113725f, 0.992157f, 0.941176f, 0.807843f,
	      0.21794872f);
	   renderTeapot(6.0f, 14.0f, 0.2125f, 0.1275f, 0.054f,
	      0.714f, 0.4284f, 0.18144f, 0.393548f, 0.271906f, 0.166721f, 0.2f);
	   renderTeapot(6.0f, 11.0f, 0.25f, 0.25f, 0.25f,
	      0.4f, 0.4f, 0.4f, 0.774597f, 0.774597f, 0.774597f, 0.6f);
	   renderTeapot(6.0f, 8.0f, 0.19125f, 0.0735f, 0.0225f,
	      0.7038f, 0.27048f, 0.0828f, 0.256777f, 0.137622f, 0.086014f, 0.1f);
	   renderTeapot(6.0f, 5.0f, 0.24725f, 0.1995f, 0.0745f,
	      0.75164f, 0.60648f, 0.22648f, 0.628281f, 0.555802f, 0.366065f, 0.4f);
	   renderTeapot(6.0f, 2.0f, 0.19225f, 0.19225f, 0.19225f,
	      0.50754f, 0.50754f, 0.50754f, 0.508273f, 0.508273f, 0.508273f, 0.4f);
	   renderTeapot(10.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.01f, 0.01f, 0.01f,
	      0.50f, 0.50f, 0.50f, .25f);
	   renderTeapot(10.0f, 14.0f, 0.0f, 0.1f, 0.06f, 0.0f, 0.50980392f, 0.50980392f,
	      0.50196078f, 0.50196078f, 0.50196078f, .25f);
	   renderTeapot(10.0f, 11.0f, 0.0f, 0.0f, 0.0f,
	      0.1f, 0.35f, 0.1f, 0.45f, 0.55f, 0.45f, .25f);
	   renderTeapot(10.0f, 8.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f,
	      0.7f, 0.6f, 0.6f, .25f);
	   renderTeapot(10.0f, 5.0f, 0.0f, 0.0f, 0.0f, 0.55f, 0.55f, 0.55f,
	      0.70f, 0.70f, 0.70f, .25f);
	   renderTeapot(10.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f,
	      0.60f, 0.60f, 0.50f, .25f);
	   renderTeapot(14.0f, 17.0f, 0.02f, 0.02f, 0.02f, 0.01f, 0.01f, 0.01f,
	      0.4f, 0.4f, 0.4f, .078125f);
	   renderTeapot(14.0f, 14.0f, 0.0f, 0.05f, 0.05f, 0.4f, 0.5f, 0.5f,
	      0.04f, 0.7f, 0.7f, .078125f);
	   renderTeapot(14.0f, 11.0f, 0.0f, 0.05f, 0.0f, 0.4f, 0.5f, 0.4f,
	      0.04f, 0.7f, 0.04f, .078125f);
	   renderTeapot(14.0f, 8.0f, 0.05f, 0.0f, 0.0f, 0.5f, 0.4f, 0.4f,
	      0.7f, 0.04f, 0.04f, .078125f);
	   renderTeapot(14.0f, 5.0f, 0.05f, 0.05f, 0.05f, 0.5f, 0.5f, 0.5f,
	      0.7f, 0.7f, 0.7f, .078125f);
	   renderTeapot(14.0f, 2.0f, 0.05f, 0.05f, 0.0f, 0.5f, 0.5f, 0.4f,
	      0.7f, 0.7f, 0.04f, .078125f);
	   glFlush();
	}

	void reshape(int w, int h)
	{
	   glViewport(0, 0, w, h);
	   glMatrixMode(GL_PROJECTION);
	   glLoadIdentity();
	   if (w <= h)
	      glOrtho(0.0, 16.0, 0.0, 16.0*(float)h/(float)w,
	              -10.0, 10.0);
	   else
	      glOrtho(0.0, 16.0*(float)w/(float)h, 0.0, 16.0,
	              -10.0, 10.0);
	   glMatrixMode(GL_MODELVIEW);
	}

	void keyboard( char key, int x, int y)
	{
	   switch (key) {
	      case 27:
	         System.exit(0);
	         break;
	   }
	}

	/*
	 * Main Loop
	 */
	public int main(int argc, String[] argv)
	{
	   glutInit(argc, argv);
	   glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	   glutInitWindowSize(500, 600);
	   glutInitWindowPosition(50,50);
	   glutCreateWindow("teapots");
	   init();
	   glutReshapeFunc(this::reshape);
	   glutDisplayFunc(this::display);
	   glutKeyboardFunc (this::keyboard);
	   glutMainLoop();
	   return 0;
	}

	public static void main(String[] args) {
		System.exit(new teapots().main(args.length, args));
	}

}
