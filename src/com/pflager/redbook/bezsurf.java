package com.pflager.redbook;

import com.pflager.glut;

// Example 1-2
public class bezsurf extends glut implements glut.DisplayFunc, glut.ReshapeFunc, glut.KeyboardFunc {

	double ctrlpoints[][][] = new double[][][] {
		//@formatter:off
		new double[][] { 
			new double[] {-1.5, -1.5, 4.0}, 
			new double[] {-0.5, -1.5, 2.0}, 
			new double[] {0.5, -1.5, -1.0}, 
			new double[] {1.5, -1.5, 2.0}}, 
		new double[][] { 
			new double[] {-1.5, -0.5, 1.0}, 
			new double[] {-0.5, -0.5, 3.0}, 
			new double[] {0.5, -0.5, 0.0}, 
			new double[] {1.5, -0.5, -1.0}}, 
		new double[][] { 
			new double[] {-1.5, 0.5, 4.0}, 
			new double[] {-0.5, 0.5, 0.0}, 
			new double[] {0.5, 0.5, 3.0}, 
			new double[] {1.5, 0.5, 4.0}}, 
		new double[][] {
			new double[] {-1.5, 1.5, -2.0}, 
			new double[] {-0.5, 1.5, -2.0}, 
			new double[] {0.5, 1.5, 0.0}, 
			new double[] {1.5, 1.5, -1.0}}
		//@formatter:on
	};

	@Override
	public void display() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f(1.0, 1.0, 1.0);
		glPushMatrix();
		glRotatef(85.0, 1.0, 1.0, 1.0);
		for (int j = 0; j <= 8; j++) {
			glBegin(GL_LINE_STRIP);
			for (int i = 0; i <= 30; i++)
				glEvalCoord2f( i / 30.0,  j / 8.0);
			glEnd();
			glBegin(GL_LINE_STRIP);
			for (int i = 0; i <= 30; i++)
				glEvalCoord2f(j / 8.0,  i / 30.0);
			glEnd();
		}
		glPopMatrix();
		glFlush();
	}

	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glMap2f(GL_MAP2_VERTEX_3, 0, 1, 3, 4, 0, 1, 12, 4, ctrlpoints);
		glEnable(GL_MAP2_VERTEX_3);
		glMapGrid2f(20, 0.0, 1.0, 20, 0.0, 1.0);
		glEnable(GL_DEPTH_TEST);
		glShadeModel(GL_FLAT);
	}

	@Override
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

	@Override
	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
			break;
		}
	}

	@Override
	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
		glutInitWindowSize(500, 500);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("bezsurf");
		init();
		glutDisplayFunc(this);
		glutReshapeFunc(this);
		glutKeyboardFunc(this);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new bezsurf().main(args.length, args));
	}

}
