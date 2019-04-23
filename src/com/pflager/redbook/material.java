package com.pflager.redbook;

import com.pflager.glut;

public class material extends glut{

	void init()
	{
	   double ambient[] = { 0.0, 0.0, 0.0, 1.0 };
	   double diffuse[] = { 1.0, 1.0, 1.0, 1.0 };
	   double specular[] = { 1.0, 1.0, 1.0, 1.0 };
	   double position[] = { 0.0, 3.0, 2.0, 0.0 };
	   double lmodel_ambient[] = { 0.4, 0.4, 0.4, 1.0 };
	   double local_view[] = { 0.0 };

	   glClearColor(0.0, 0.1, 0.1, 0.0);
	   glEnable(GL_DEPTH_TEST);
	   glShadeModel(GL_SMOOTH);

	   glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
	   glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse);
	   glLightfv(GL_LIGHT0, GL_POSITION, position);
	   glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
	   glLightModelfv(GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);

	   glEnable(GL_LIGHTING);
	   glEnable(GL_LIGHT0);
	}
	
	void display()
	{
		double no_mat[] = { 0.0, 0.0, 0.0, 1.0 };
		double mat_ambient[] = { 0.7, 0.7, 0.7, 1.0 };
		double mat_ambient_color[] = { 0.8, 0.8, 0.2, 1.0 };
		double mat_diffuse[] = { 0.1, 0.5, 0.8, 1.0 };
		double mat_specular[] = { 1.0, 1.0, 1.0, 1.0 };
		double no_shininess[] = { 0.0 };
		double low_shininess[] = { 5.0 };
		double high_shininess[] = { 100.0 };
		double mat_emission[] = {0.3, 0.2, 0.2, 0.0};

	   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	/*  draw sphere in first row, first column
	 *  diffuse reflection only; no ambient or specular  
	 */
	   glPushMatrix();
	   glTranslatef (-3.75, 3.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, no_mat);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, no_mat);
	   glMaterialfv(GL_FRONT, GL_SHININESS, no_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in first row, second column
	 *  diffuse and specular reflection; low shininess; no ambient
	 */
	   glPushMatrix();
	   glTranslatef (-1.25, 3.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, no_mat);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	   glMaterialfv(GL_FRONT, GL_SHININESS, low_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in first row, third column
	 *  diffuse and specular reflection; high shininess; no ambient
	 */
	   glPushMatrix();
	   glTranslatef (1.25, 3.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, no_mat);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	   glMaterialfv(GL_FRONT, GL_SHININESS, high_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in first row, fourth column
	 *  diffuse reflection; emission; no ambient or specular reflection
	 */
	   glPushMatrix();
	   glTranslatef (3.75, 3.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, no_mat);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, no_mat);
	   glMaterialfv(GL_FRONT, GL_SHININESS, no_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, mat_emission);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in second row, first column
	 *  ambient and diffuse reflection; no specular  
	 */
	   glPushMatrix();
	   glTranslatef (-3.75, 0.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, no_mat);
	   glMaterialfv(GL_FRONT, GL_SHININESS, no_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in second row, second column
	 *  ambient, diffuse and specular reflection; low shininess
	 */
	   glPushMatrix();
	   glTranslatef (-1.25, 0.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	   glMaterialfv(GL_FRONT, GL_SHININESS, low_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in second row, third column
	 *  ambient, diffuse and specular reflection; high shininess
	 */
	   glPushMatrix();
	   glTranslatef (1.25, 0.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	   glMaterialfv(GL_FRONT, GL_SHININESS, high_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in second row, fourth column
	 *  ambient and diffuse reflection; emission; no specular
	 */
	   glPushMatrix();
	   glTranslatef (3.75, 0.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, no_mat);
	   glMaterialfv(GL_FRONT, GL_SHININESS, no_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, mat_emission);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in third row, first column
	 *  colored ambient and diffuse reflection; no specular  
	 */
	   glPushMatrix();
	   glTranslatef (-3.75, -3.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_color);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, no_mat);
	   glMaterialfv(GL_FRONT, GL_SHININESS, no_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in third row, second column
	 *  colored ambient, diffuse and specular reflection; low shininess
	 */
	   glPushMatrix();
	   glTranslatef (-1.25, -3.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_color);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	   glMaterialfv(GL_FRONT, GL_SHININESS, low_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in third row, third column
	 *  colored ambient, diffuse and specular reflection; high shininess
	 */
	   glPushMatrix();
	   glTranslatef (1.25, -3.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_color);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	   glMaterialfv(GL_FRONT, GL_SHININESS, high_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	/*  draw sphere in third row, fourth column
	 *  colored ambient and diffuse reflection; emission; no specular
	 */
	   glPushMatrix();
	   glTranslatef (3.75, -3.0, 0.0);
	   glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_color);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, no_mat);
	   glMaterialfv(GL_FRONT, GL_SHININESS, no_shininess);
	   glMaterialfv(GL_FRONT, GL_EMISSION, mat_emission);
	   glutSolidSphere(1.0, 16, 16);
	   glPopMatrix();

	   glFlush();
	}

	void reshape(int w, int h)
	{
	   glViewport(0, 0, w, h);
	   glMatrixMode(GL_PROJECTION);
	   glLoadIdentity();
	   if (w <= (h * 2))
	      glOrtho (-6.0, 6.0, -3.0*(h*2)/w, 3.0*(h*2)/w, -10.0, 10.0);
	   else
	      glOrtho (-6.0*w/(h*2), 6.0*w/(h*2), -3.0, 3.0, -10.0, 10.0);
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

	public int main(int argc, String[] argv)
	{
	   glutInit(argc, argv);
	   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	   glutInitWindowSize (600, 450);
	   glutCreateWindow("material");
	   init();
	   glutReshapeFunc(this::reshape);
	   glutDisplayFunc(this::display);
	   glutKeyboardFunc (this::keyboard);
	   glutMainLoop();
	   return 0; 
	}

	public static void main(String[] args) {
		System.exit(new material().main(args.length, args));
	}
}
