package com.pflager.redbook.kvs;

import com.pflager.glut;

public class bezcurve extends glut {

	double[][] ctrlpoints = new double[][] { new double[] { -4.0, -4.0, 0.0 }, new double[] { -2.0, 4.0, 0.0 },
			new double[] { 2.0, -4.0, 0.0 }, new double[] { 4.0, 4.0, 0.0 } };

	private void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glShadeModel(GL_FLAT);
		glMap1f(GL_MAP1_VERTEX_3, 0.0, 1.0, 3, 4, ctrlpoints);
		glEnable(GL_MAP1_VERTEX_3);
	}

	public void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(1.0, 1.0, 1.0);
		glBegin(GL_LINE_STRIP);
		for (int i = 0; i <= 30; i++) {
			glEvalCoord1f(i / 30.0);
		}
		glEnd();
		/* The following code displays the control points as dots. */
		glPointSize(5.0);
		glColor3f(1.0, 1.0, 0.0);
		glBegin(GL_POINTS);
		for (int i = 0; i < ctrlpoints.length; i++)
			glVertex3fv(ctrlpoints[i]);
		glEnd();
		glFlush();
	}

	public void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if (w <= h)
			glOrtho(-5.0, 5.0, -5.0 * h / w, 5.0 * h / w, -5.0, 5.0);
		else
			glOrtho(-5.0 * w / h, 5.0 * w / h, -5.0, 5.0, -5.0, 5.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(500, 500);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("bezcurve");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new bezcurve().main(args.length, args));
	}

}
