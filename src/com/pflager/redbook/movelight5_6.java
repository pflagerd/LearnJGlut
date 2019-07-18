package com.pflager.redbook;

import com.pflager.glut;

public class movelight5_6 extends glut {
	static int spin = 0;

	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glShadeModel(GL_SMOOTH);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glEnable(GL_DEPTH_TEST);
	}

	
	void display() {
		float position[] = { 0.0f, 0.0f, 1.5f, 1.0f };
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glPushMatrix();
		glTranslatef(0.0, 0.0, -5.0);
		glPushMatrix();
		glRotated(spin, 1.0, 0.0, 0.0);
		glLightfv(GL_LIGHT0, GL_POSITION, position);
		glTranslated (0.0, 0.0, 1.5);
		glDisable(GL_LIGHTING);
		glColor3f(0.0, 1.0, 1.0);
		glutWireCube(0.1);
		glEnable(GL_LIGHTING);
		glPopMatrix();
		glutSolidTorus(0.275, 0.85, 8, 15);
		glPopMatrix();
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(40.0, w / h, 1.0, 20.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
			break;
		}
	}

	void mouse(int button, int state, int x, int y) {
		switch (button) {
		case GLUT_LEFT_BUTTON:
			if (state == GLUT_DOWN) {
				spin = (spin + 30) % 360;
				glutPostRedisplay();
			}
			break;
		default:
			break;
		}
	}

	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
		glutInitWindowSize(500, 500);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("movelight");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMouseFunc(this::mouse);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new movelight5_6().main(args.length, args));
	}
}
