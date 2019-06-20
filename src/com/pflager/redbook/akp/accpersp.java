package com.pflager.redbook.akp;

import com.pflager.glut;
//import java.math.*;
import java.lang.*;

public class accpersp extends glut{

	//#ifdef WIN32
	static double  near = 0;
	static double  far = 0;
	//#endif

	static double PI_ = 3.14159265358979323846;

	/* accFrustum()
	 * The first 6 arguments are identical to the glFrustum() call.
	 *  
	 * pixdx and pixdy are anti-alias jitter in pixels. 
	 * Set both equal to 0.0 for no anti-alias jitter.
	 * eyedx and eyedy are depth-of field jitter in pixels. 
	 * Set both equal to 0.0 for no depth of field effects.
	 *
	 * focus is distance from eye to plane in focus. 
	 * focus must be greater than, but not equal to 0.0.
	 *
	 * Note that accFrustum() calls glTranslatef().  You will 
	 * probably want to insure that your ModelView matrix has been 
	 * initialized to identity before calling accFrustum().
	 */
	void accFrustum(double left, double right, double bottom, 
	   double top, double near, double far, double pixdx, 
	   double pixdy, double eyedx, double eyedy, double focus)
	{
	   double xwsize, ywsize; 
	   double dx, dy;
	   int viewport[] = new int[4];

	   glGetIntegerv (GL_VIEWPORT, viewport);
		
	   xwsize = right - left;
	   ywsize = top - bottom;
		
	   dx = -(pixdx*xwsize/ viewport[2] + eyedx*near/focus);
	   dy = -(pixdy*ywsize/ viewport[3] + eyedy*near/focus);
		
	   glMatrixMode(GL_PROJECTION);
	   glLoadIdentity();
	   glFrustum (left + dx, right + dx, bottom + dy, top + dy, near, far);
	   glMatrixMode(GL_MODELVIEW);
	   glLoadIdentity();
	   glTranslatef (-eyedx, -eyedy, 0.0);
	}

	/* accPerspective()
	 * 
	 * The first 4 arguments are identical to the gluPerspective() call.
	 * pixdx and pixdy are anti-alias jitter in pixels. 
	 * Set both equal to 0.0 for no anti-alias jitter.
	 * eyedx and eyedy are depth-of field jitter in pixels. 
	 * Set both equal to 0.0 for no depth of field effects.
	 *
	 * focus is distance from eye to plane in focus. 
	 * focus must be greater than, but not equal to 0.0.
	 *
	 * Note that accPerspective() calls accFrustum().
	 */
	void accPerspective(double fovy, double aspect, 
	   double near, double far, double pixdx, double pixdy, 
	   double eyedx, double eyedy, double focus)
	{
	   double fov2,left,right,bottom,top;

	   fov2 = ((fovy*PI_) / 180.0) / 2.0;

	   top = near / (Math.cos(fov2) / Math.sin(fov2));
	   bottom = -top;

	   right = top * aspect;
	   left = -right;

	   accFrustum (left, right, bottom, top, near, far,
	               pixdx, pixdy, eyedx, eyedy, focus);
	}

	/*  Initialize lighting and other values.
	 */
	void init()
	{
	   float mat_ambient[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	   float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	   float light_position[] = { 0.0f, 0.0f, 10.0f, 1.0f };
	   float lm_ambient[] = { 0.2f, 0.2f, 0.2f, 1.0f };

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
	   glClearAccum(0.0, 0.0, 0.0, 0.0);
	}

	void displayObjects() 
	{
	   float torus_diffuse[] = { 0.7f, 0.7f, 0.0f, 1.0f };
	   float cube_diffuse[] = { 0.0f, 0.7f, 0.7f, 1.0f };
	   float sphere_diffuse[] = { 0.7f, 0.0f, 0.7f, 1.0f };
	   float octa_diffuse[] = { 0.7f, 0.4f, 0.4f, 1.0f };
	    
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

	   glPushMatrix ();
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

	static int ACSIZE	= 8;

	void display()
	{
	   int viewport[] = new int[4];
	   int jitter;

	   glGetIntegerv (GL_VIEWPORT, viewport);

	   glClear(GL_ACCUM_BUFFER_BIT);
	   for (jitter = 0; jitter < ACSIZE; jitter++) {
	      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	      accPerspective (50.0, viewport[2]/viewport[3], 1.0, 15.0, 0.0, 0.0, 0.0, 0.0, 1.0);
	      displayObjects ();
	      glAccum(GL_ACCUM, 1.0/ACSIZE);
	   }
	   glAccum (GL_RETURN, 1.0);
	   glFlush();
	}

	void reshape(int w, int h)
	{
	   glViewport(0, 0,w, h);
	}

	void keyboard(char key, int x, int y)
	{
	   switch (key) {
	      case 27:
	         System.exit(0);
	         break;
	   }
	}

	/*  Main Loop
	 *  Be certain you request an accumulation buffer.
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
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0;
	}
	public static void main(String[] args) {
		System.exit(new accpersp().main(args.length, args));
	}
}
