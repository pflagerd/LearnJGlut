package com.pflager.redbook.vtrg;

import com.pflager.glut;

public class accpersp extends glut{

	static int ACSIZE=8;
	static double PI =3.14159265358979323846f;
	void init()
	{
		double mat_ambient[] = { 1.0, 1.0, 1.0, 1.0 };
		double mat_specular[] = { 1.0, 1.0, 1.0, 1.0 };
		double light_position[] = { 0.0, 0.0, 10.0, 1.0 };
		double lm_ambient[] = { 0.2, 0.2, 0.2, 1.0 };
		glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
		glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialf(GL_FRONT, GL_SHININESS, 50.0);
		glLightfv(GL_LIGHT0, GL_POSITION, light_position);
		glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lm_ambient);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glEnable(GL_DEPTH_TEST);
		glShadeModel (GL_FLAT);
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glClearAccum(0.0,0.0, 0.0, 0.0);
	}
	
	void displayObjects()
	{
		double torus_diffuse[] = { 0.7, 0.7, 0.0, 1.0 };
		double cube_diffuse[] = { 0.0, 0.7, 0.7, 1.0 };
		double sphere_diffuse[] = { 0.7, 0.0, 0.7, 1.0 };
		double octa_diffuse[] = { 0.7, 0.4, 0.4, 1.0 };
		glPushMatrix ();
		glTranslatef (0.0, 0.0, -5.0);
		glRotatef (30.0, 1.0, 0.0, 0.0);
		glPushMatrix ();
		glTranslatef (-0.80, 0.35, 0.0);
		glRotatef (100.0, 1.0, 0.0, 0.0);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, torus_diffuse);
		glutSolidTorus (0.275, 0.85, 16, 16);
		glPopMatrix ();
		glPushMatrix ();
		glTranslatef (-0.75, -0.50, 0.0);
		glRotatef (45.0, 0.0, 0.0, 1.0);
		glRotatef (45.0, 1.0, 0.0, 0.0);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, cube_diffuse);
		glutSolidCube (1.5);
		glPopMatrix ();
		glPushMatrix();
		glTranslatef (0.75, 0.60, 0.0);
		glRotatef (30.0, 1.0, 0.0, 0.0);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, sphere_diffuse);
		glutSolidSphere (1.0, 16, 16);
		glPopMatrix ();
		glPushMatrix ();
		glTranslatef (0.70, -0.90, 0.25);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, octa_diffuse);
		glutSolidOctahedron ();
		glPopMatrix ();
		glPopMatrix ();
	}
	void accFrustum(double left, double right, double bottom,double top, double near, double far, double pixdx,double pixdy, double eyedx, double eyedy,double focus)
	{
			double xwsize, ywsize;
			double dx, dy;
			int viewport[]=new int[4];
			glGetIntegerv (GL_VIEWPORT, viewport);
			xwsize = right - left;
			ywsize = top - bottom;
			dx = -(pixdx*xwsize/(double) viewport[2] +
			eyedx*near/focus);
			dy = -(pixdy*ywsize/(double) viewport[3] +
			eyedy*near/focus);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glFrustum (left + dx, right + dx, bottom + dy, top + dy,
			near, far);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glTranslatef (-eyedx, -eyedy, 0.0);
	}
	void accPerspective(double fovy, double aspect,double near, double far, double pixdx, double pixdy,double eyedx, double eyedy, double focus)
	{
			double fov2,left,right,bottom,top;
			fov2 = ((fovy*PI) / 180.0) / 2.0;
			top = near / (Math.cos(fov2) / Math.sin(fov2));
			bottom = -top;
			right = top * aspect;
			left = -right;
			accFrustum (left, right, bottom, top, near, far,
			pixdx, pixdy, eyedx, eyedy, focus);
	}
	void display()
	{
		int viewport[]=new int[4];
		int jitter;
		glGetIntegerv (GL_VIEWPORT, viewport);
		glClear(GL_ACCUM_BUFFER_BIT);
		for (jitter = 0; jitter < ACSIZE; jitter++) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//accPerspective(50.0, (double) viewport[2] / (double) viewport[3], 1.0, 15.0, j8[jitter].x, j8[jitter].y,0.0, 0.0, 1.0);
		accPerspective(50.0, (double) viewport[2] / (double) viewport[3], 1.0, 15.0, 0, 0,0.0, 0.0, 1.0);
		displayObjects ();
		glAccum(GL_ACCUM, 1.0/ACSIZE);
		}
		glAccum (GL_RETURN, 1.0);
		glFlush();
	}
	
	void reshape(int w, int h)
	{
		glViewport(0, 0,  w,  h);
	}
	
	/* Main Loop
	* Be certain you request an accumulation buffer.
	*/
	int main(int argc, String[] argv)
	{
		glutInit(argc, argv);
		glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB
		| GLUT_ACCUM | GLUT_DEPTH);
		glutInitWindowSize (250, 250);
		glutInitWindowPosition (100, 100);
		glutCreateWindow ("accpersp");
		init();
		glutReshapeFunc(this::reshape);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}
	
	public static void main(String[] args) {
		System.exit(new accpersp().main(args.length, args));
	}
}
