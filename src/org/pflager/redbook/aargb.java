package org.pflager.redbook;

import com.pflager.glut;

public class aargb extends glut {
	static float rotAngle = 0;
	/*
	 * Initialize antialiasing for RGBA mode, including alpha blending, hint, and
	 * line width. Print out implementation specific info on line width granularity
	 * and width.
	 */

	void init() {
		float values[] = new float[2];
		glGetFloatv(GL_LINE_WIDTH_GRANULARITY, values);
		System.out.println("GL_LINE_WIDTH_GRANULARITY value is " + values[0] + ".1f\n");
		glGetFloatv(GL_LINE_WIDTH_RANGE, values);
		System.out.println("GL_LINE_WIDTH_RANGE values are " + values[0] + ".1f " + values[1] + ".1f\n");
		glEnable(GL_LINE_SMOOTH);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);
		glLineWidth(1.5);
		glClearColor(0.0, 0.0, 0.0, 0.0);
	}

	void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(0.0, 1.0, 0.0);
		glPushMatrix();
		glRotatef(-rotAngle, 0.0, 0.0, 0.1);
		glBegin(GL_LINES);
		glVertex2f(-0.5, 0.5);
		glVertex2f(0.5, -0.5);
		glEnd();
		glPopMatrix();
		glColor3f(0.0, 0.0, 1.0);
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
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(200, 200);
		glutCreateWindow("aargb");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new aargb().main(args.length, args));

	}
}
