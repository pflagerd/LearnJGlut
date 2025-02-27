package org.pflager.redbook.vtrg;

import com.pflager.glut;

public class polyoff  extends glut{

	int list;
	int spinx = 0;
	int spiny = 0;
	double tdist = 0.0;
	double polyfactor = 1.0;
	double polyunits = 1.0;

	void init ()
	{
	    double light_ambient[] = { 0.0, 0.0, 0.0, 1.0 };
	    double light_diffuse[] = { 1.0, 1.0, 1.0, 1.0 };
	    double light_specular[] = { 1.0, 1.0, 1.0, 1.0 };
	    double light_position[] = { 1.0, 1.0, 1.0, 0.0 };

	    double global_ambient[] = { 0.2, 0.2, 0.2, 1.0 };

	    glClearColor (0.0, 0.0, 0.0, 1.0);

	    list = glGenLists(1);
	    glNewList (list, GL_COMPILE);
	       glutSolidSphere(1.0, 20, 12);
	    glEndList ();

	    glEnable(GL_DEPTH_TEST);

	    glLightfv (GL_LIGHT0, GL_AMBIENT, light_ambient);
	    glLightfv (GL_LIGHT0, GL_DIFFUSE, light_diffuse);
	    glLightfv (GL_LIGHT0, GL_SPECULAR, light_specular);
	    glLightfv (GL_LIGHT0, GL_POSITION, light_position);
	    glLightModelfv (GL_LIGHT_MODEL_AMBIENT, global_ambient);
	}

	void display ()
	{
		double gray[] = { 0.8, 0.8, 0.8, 1.0 };
		double black[] = { 0.0, 0.0, 0.0, 1.0 };

	    glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	    glPushMatrix ();
	    glTranslatef (0.0, 0.0, tdist);
	    glRotatef ((double) spinx, 1.0, 0.0, 0.0);
	    glRotatef ((double) spiny, 0.0, 1.0, 0.0);

	    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, gray);
	    glMaterialfv(GL_FRONT, GL_SPECULAR, black);
	    glMaterialf(GL_FRONT, GL_SHININESS, 0.0);
	    glEnable(GL_LIGHTING);
	    glEnable(GL_LIGHT0);
	    glEnable(GL_POLYGON_OFFSET_FILL);
	    glPolygonOffset(polyfactor, polyunits);
	    glCallList (list);
	    glDisable(GL_POLYGON_OFFSET_FILL);

	    glDisable(GL_LIGHTING);
	    glDisable(GL_LIGHT0);
	    glColor3f (1.0, 1.0, 1.0);
	    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
	    glCallList (list);
	    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

	    glPopMatrix ();
	    glFlush ();
	}

	void reshape(int width, int height)
	{
	    glViewport (0, 0, width, height);
	    glMatrixMode (GL_PROJECTION);
	    glLoadIdentity ();
	    gluPerspective(45.0, (double)width/(double)height,1.0, 10.0);
	    glMatrixMode (GL_MODELVIEW);
	    glLoadIdentity ();
	    gluLookAt (0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	}
	void mouse(int button, int state, int x, int y) {
	    switch (button) {
		case GLUT_LEFT_BUTTON:
		    switch (state) {
			case GLUT_DOWN:
			    spinx = (spinx + 5) % 360;
	                    glutPostRedisplay();
			    break;
			default:
			    break;
	            }
	            break;
		case GLUT_MIDDLE_BUTTON:
		    switch (state) {
			case GLUT_DOWN:
			    spiny = (spiny + 5) % 360;
	                    glutPostRedisplay();
			    break;
			default:
			    break;
	            }
	            break;
		case GLUT_RIGHT_BUTTON:
		    switch (state) {
			case GLUT_UP:
			    System.exit(0);
			    break;
			default:
			    break;
	            }
	            break;
	        default:
	            break;
	    }
	}

	void keyboard ( char key, int x, int y)
	{
	   switch (key) {
	      case 't':
	         if (tdist < 4.0) {
	            tdist = (tdist + 0.5);
	            glutPostRedisplay();
	         }
	         break;
	      case 'T':
	         if (tdist > -5.0) {
	            tdist = (tdist - 0.5);
	            glutPostRedisplay();
	         }
	         break;
	      case 'F':
	         polyfactor = polyfactor + 0.1;
		 System.out.printf ("polyfactor is %f\n", polyfactor);
	         glutPostRedisplay();
	         break;
	      case 'f':
	         polyfactor = polyfactor - 0.1;
		 System.out.printf ("polyfactor is %f\n", polyfactor);
	         glutPostRedisplay();
	         break;
	      case 'U':
	         polyunits = polyunits + 1.0;
		 System.out.printf ("polyunits is %f\n", polyunits);
	         glutPostRedisplay();
	         break;
	      case 'u':
	         polyunits = polyunits - 1.0;
		 System.out.printf ("polyunits is %f\n", polyunits);
	         glutPostRedisplay();
	         break;
	      default:
	         break;
	   }
	}

	int main(int argc, String[] argv)
	{
	    glutInit(argc, argv);
	    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	    glutCreateWindow("polyoff");
	    glutReshapeFunc(this::reshape);
	    glutDisplayFunc(this::display);
	    glutMouseFunc(this::mouse);
	    glutKeyboardFunc(this::keyboard);
	    init();
	    glutMainLoop();
	    return 0;
	}
	public static void main(String[] args) {
		System.exit(new polyoff().main(args.length, args));
	}

}
