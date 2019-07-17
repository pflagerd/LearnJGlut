package com.pflager.redbook.kvs;

import com.pflager.glut;

public class bezmesh extends glut {

	double ctrlpoints[][][] = {
			new double[][] { new double[] { -1.5, -1.5, 4.0 }, new double[] { -0.5, -1.5, 2.0 },
					new double[] { 0.5, -1.5, -1.0 }, new double[] { 1.5, -1.5, 2.0 } },
			new double[][] { new double[] { -1.5, -0.5, 1.0 }, new double[] { -0.5, -0.5, 3.0 },
					new double[] { 0.5, -0.5, 0.0 }, new double[] { 1.5, -0.5, -1.0 } },
			new double[][] { new double[] { -1.5, 0.5, 4.0 }, new double[] { -0.5, 0.5, 0.0 },
					new double[] { 0.5, 0.5, 3.0 }, new double[] { 1.5, 0.5, 4.0 } },
			new double[][] { new double[] { -1.5, 1.5, -2.0 }, new double[] { -0.5, 1.5, -2.0 },
					new double[] { 0.5, 1.5, 0.0 }, new double[] { 1.5, 1.5, -1.0 } } };

	void initlights() {
		float ambient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
		float position[] = { 0.0f, 0.0f, 2.0f, 1.0f };
		float mat_diffuse[] = { 0.6f, 0.6f, 0.6f, 1.0f };
		float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float mat_shininess[] = { 50.0f };
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
		glLightfv(GL_LIGHT0, GL_POSITION, position);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);
	}

	public void display() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glPushMatrix();
		glRotatef(85.0, 1.0, 1.0, 1.0);
		glEvalMesh2(GL_FILL, 0, 20, 0, 20);
		glPopMatrix();
		glFlush();
	}

	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glEnable(GL_DEPTH_TEST);
		glMap2f(GL_MAP2_VERTEX_3, 0, 1, 3, 4, 0, 1, 12, 4, ctrlpoints);
		glEnable(GL_MAP2_VERTEX_3);
		glEnable(GL_AUTO_NORMAL);
		glMapGrid2f(20, 0.0, 1.0, 20, 0.0, 1.0);
		initlights();
	}

	public void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if (w <= h)
			glOrtho(-4.0, 4.0, -4.0 * h / w, 4.0 * h / w, -4.0, 4.0);
		else
			glOrtho(-4.0 * w / h, 4.0 * w / h, -4.0, 4.0, -4.0, 4.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
			break;
		}
	}

	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
		glutInitWindowSize(500, 500);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("bezmesh");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new bezmesh().main(args.length, args));
	}

}
