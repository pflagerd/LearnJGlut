package org.pflager.redbook.kvs;

import com.pflager.glut;

public class torus extends glut {

	int theTorus;

	/* Draw a torus */
	void torus_(int numc, int numt) {
		int i, j, k;
		double s, t, x, y, z, twopi;
		double M_PI = 3.14;
		twopi = 2 * (double) M_PI;
		for (i = 0; i < numc; i++) {
			glBegin(GL_QUAD_STRIP);
			for (j = 0; j <= numt; j++) {
				for (k = 1; k >= 0; k--) {
					s = (i + k) % numc + 0.5;
					t = j % numt;
					x = (1 + .1 * Math.cos(s * twopi / numc)) * Math.cos(t * twopi / numt);
					y = (1 + .1 * Math.cos(s * twopi / numc)) * Math.sin(t * twopi / numt);
					z = .1 * Math.sin(s * twopi / numc);
					glVertex3f(x, y, z);
				}
			}
			glEnd();
		}
	}

	/* Create display list with Torus and initialize state */
	void init() {
		theTorus = glGenLists(1);
		glNewList(theTorus, GL_COMPILE);
		torus_(8, 25);
		glEndList();
		glShadeModel(GL_FLAT);
		glClearColor(0.0, 0.0, 0.0, 0.0);
	}

	void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(1.0, 1.0, 1.0);
		glCallList(theTorus);
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(30, w / h, 1.0, 100.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		gluLookAt(0, 0, 10, 0, 0, 0, 0, 1, 0);
	}

	/*
	 * Rotate about x-axis when "x" typed; rotate about y-axis when "y" typed; "i"
	 * returns torus to original view
	 */
	void keyboard(char key, int x, int y) {
		switch (key) {
		case 'x':
		case 'X':
			glRotatef(30., 1.0, 0.0, 0.0);
			glutPostRedisplay();
			break;
		case 'y':
		case 'Y':
			glRotatef(30., 0.0, 1.0, 0.0);
			glutPostRedisplay();
			break;
		case 'i':
		case 'I':
			glLoadIdentity();
			gluLookAt(0, 0, 10, 0, 0, 0, 0, 1, 0);
			glutPostRedisplay();
			break;
		case 27:
			System.exit(0);
		}
	}

	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(200, 200);
		glutCreateWindow("torus");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new torus().main(args.length, args));

	}
}
