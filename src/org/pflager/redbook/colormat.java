package org.pflager.redbook;


import com.pflager.glut;

public class colormat extends glut{

	double diffuseMaterial[] = { 0.5, 0.5, 0.5, 1.0 };
	void init() 
	{
	   double mat_specular[] = { 1.0, 1.0, 1.0, 1.0 };
	   double light_position[] = { 1.0, 1.0, 1.0, 0.0 };

	   glClearColor (0.0, 0.0, 0.0, 0.0);
	   glShadeModel (GL_SMOOTH);
	   glEnable(GL_DEPTH_TEST);
	   glMaterialfv(GL_FRONT, GL_DIFFUSE, diffuseMaterial);
	   glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	   glMaterialf(GL_FRONT, GL_SHININESS, 25.0);
	   glLightfv(GL_LIGHT0, GL_POSITION, light_position);
	   glEnable(GL_LIGHTING);
	   glEnable(GL_LIGHT0);

	   glColorMaterial(GL_FRONT, GL_DIFFUSE);
	   glEnable(GL_COLOR_MATERIAL);
	}
	
	void display()
	{
	   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	   glutSolidSphere(1.0, 20, 16);
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
	void mouse(int button, int state, int x, int y)
	{
	   switch (button) {
	      case GLUT_LEFT_BUTTON:
	         if (state == GLUT_DOWN) {
	            diffuseMaterial[0] += 0.1;
	            if (diffuseMaterial[0] > 1.0)
	               diffuseMaterial[0] = 0.0;
	            glColor4fv(diffuseMaterial);
	            glutPostRedisplay();
	         }
	         break;
	      case GLUT_MIDDLE_BUTTON:
	         if (state == GLUT_DOWN) {
	            diffuseMaterial[1] += 0.1;
	            if (diffuseMaterial[1] > 1.0)
	               diffuseMaterial[1] = 0.0;
	            glColor4fv(diffuseMaterial);
	            glutPostRedisplay();
	         }
	         break;
	      case GLUT_RIGHT_BUTTON:
	         if (state == GLUT_DOWN) {
	            diffuseMaterial[2] += 0.1;
	            if (diffuseMaterial[2] > 1.0)
	               diffuseMaterial[2] = 0.0;
	            glColor4fv(diffuseMaterial);
	            glutPostRedisplay();
	         }
	         break;
	      default:
	         break;
	   }
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
	   glutInitWindowSize (500, 500); 
	   glutInitWindowPosition (100, 100);
	   glutCreateWindow ("colormat");
	   init ();
	   glutDisplayFunc(this::display); 
	   glutReshapeFunc(this::reshape);
	   glutMouseFunc(this::mouse);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0;
	}
	public static void main(String[] args) {
		System.exit(new colormat().main(args.length, args));
		
	}
}
