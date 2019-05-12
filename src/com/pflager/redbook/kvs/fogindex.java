package com.pflager.redbook.kvs;

import com.pflager.glut;

public class fogindex extends glut {

	/*
	 * Initialize color map and fog. Set screen clear color to end of color ramp.
	 */
	final static int NUMCOLORS = 32;
	final static int RAMPSTART = 16;

	void init() {
		int i;
		glEnable(GL_DEPTH_TEST);
		for (i = 0; i < NUMCOLORS; i++) {
			float shade;
			shade = (float) (NUMCOLORS - i) / (float) NUMCOLORS;
			glutSetColor(RAMPSTART + i, shade, shade, shade);
		}
		glEnable(GL_FOG);
		glFogi(GL_FOG_MODE, GL_LINEAR);
		glFogi(GL_FOG_INDEX, NUMCOLORS);
		glFogf(GL_FOG_START, 1.0);
		glFogf(GL_FOG_END, 6.0);
		glHint(GL_FOG_HINT, GL_NICEST);
		glClearIndex((float) (NUMCOLORS + RAMPSTART - 1));
	}

	void renderSphere(double x, double y, double z) {
		glPushMatrix();
		glTranslatef(x, y, z);
		glutWireSphere(0.4, 16, 16);
		glPopMatrix();
	}
	/*
	 * display() draws 5 spheres at different z positions.
	 */

	void display() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glIndexi(RAMPSTART);
		renderSphere(-2., -0.5, -1.0);
		renderSphere(-1., -0.5, -2.0);
		renderSphere(0., -0.5, -3.0);
		renderSphere(1., -0.5, -4.0);
		renderSphere(2., -0.5, -5.0);
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if (w <= h)
			glOrtho(-2.5, 2.5, -2.5 * h / w, 2.5 * h / w, -10.0, 10.0);
		else
			glOrtho(-2.5 * w / h, 2.5 * w / h, -2.5, 2.5, -10.0, 10.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
		}
	}

	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_INDEX | GLUT_DEPTH);
		glutInitWindowSize(500, 500);
		glutCreateWindow("fogindex");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new fogindex().main(args.length, args));

	}
}
