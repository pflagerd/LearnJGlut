package org.pflager.redbook;

import com.pflager.glut;

// Example 1-3.
public class double_ extends glut implements glut.DisplayFunc, glut.IdleFunc, glut.MouseFunc, glut.ReshapeFunc {

	static double spin = 0.0;

	@Override
	public void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glPushMatrix();
		glRotatef(spin, 0.0, 0.0, 1.0);
		glColor3f(1.0, 1.0, 1.0);
		glRectf(-25.0, -25.0, 25.0, 25.0);
		glPopMatrix();

		glutSwapBuffers();
	}

	@Override
	public void idle() {
		spin = spin + 2.0;
		if (spin > 360.0)
			spin = spin - 360.0;
		glutPostRedisplay();
	}

	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glShadeModel(GL_FLAT);
	}

	@Override
	public void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-50.0, 50.0, -50.0, 50.0, -1.0, 1.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	@Override
	public void mouse(int button, int state, int x, int y) {
		switch (button) {
		case GLUT_LEFT_BUTTON:
			if (state == GLUT_DOWN)
				glutIdleFunc(this);
			break;
		case GLUT_MIDDLE_BUTTON:
		case GLUT_RIGHT_BUTTON:
			if (state == GLUT_DOWN)
				glutIdleFunc(null);
			break;
		default:
			break;
		}
	}

	/*
	 * Request double buffer display mode. Register mouse input callback functions
	 */
	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
		glutInitWindowSize(250, 250);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("double");
		init();
		glutDisplayFunc(this);
		glutReshapeFunc(this);
		glutMouseFunc(this);
		glutMainLoop();
		return 0; /* ANSI C requires main to return int. */
	}

	public static void main(String[] args) {
		System.exit(new double_().main(args.length, args));
	}

}
