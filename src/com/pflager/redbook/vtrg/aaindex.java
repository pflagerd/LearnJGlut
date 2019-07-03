package com.pflager.redbook.vtrg;

import com.pflager.glut;

public class aaindex extends glut {
	static float rotAngle = 0;
	final static float RAMPSIZE = 16;
	final static float RAMP1START = 32;
	final static float RAMP2START = 48;

	/*
	 * Initialize antialiasing for color-index mode, including loading a green color
	 * ramp starting at RAMP1START, and a blue color ramp starting at RAMP2START.
	 * The ramps must be a multiple of 16.
	 */

	void init() {
		for (float i = 0; i < RAMPSIZE; i++) {
			float shade;
			shade = (float) i / (float) RAMPSIZE;
			glutSetColor((int) (RAMP1START + (int) i), 0., shade, 0.);
			glutSetColor((int) (RAMP2START + (int) i), 0., 0., shade);
		}
		glEnable(GL_LINE_SMOOTH);
		glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);
		glLineWidth(1.5);
		glClearIndex((float) RAMP1START);
	}

	void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glIndexi((int) RAMP1START);
		glPushMatrix();
		glRotatef(-rotAngle, 0.0, 0.0, 0.1);
		glBegin(GL_LINES);
		glVertex2f(-0.5, 0.5);
		glVertex2f(0.5, -0.5);
		glEnd();
		glPopMatrix();
		glIndexi((int) RAMP2START);
		glPushMatrix();
		glRotatef(rotAngle, 0.0, 0.0, 0.1);
		glBegin(GL_LINES);
		glVertex2f(0.5, 0.5);
		glVertex2f(-0.5, -0.5);
		glEnd();
		glPopMatrix();
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if (w <= h)
			gluOrtho2D(-1.0, 1.0, -1.0 * h / w, 1.0 * h / w);
		else
			gluOrtho2D(-1.0 * w / h, 1.0 * w / h, -1.0, 1.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 'r':
		case 'R':
			rotAngle += 20.;
			if (rotAngle >= 360.)
				rotAngle = 0;
			glutPostRedisplay();
			break;
		case 27: /* Escape key */
			System.exit(0);
			break;
		default:
			break;
		}
	}

	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_INDEX);
		glutInitWindowSize(200, 200);
		glutCreateWindow("aaindex");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new aaindex().main(args.length, args));

	}
}