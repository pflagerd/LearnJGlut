package com.pflager.redbook;

import com.pflager.glut;

// Example 1-2
public class example2_7 extends glut implements glut.DisplayFunc, glut.ReshapeFunc, glut.KeyboardFunc {

	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glShadeModel(GL_FLAT);
	}

	@Override
	public void display() {
		glClear(GL_COLOR_BUFFER_BIT);

		/* select white for all lines */
		glColor3f(1.0, 1.0, 1.0);
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glBegin(GL_POLYGON);
			glEdgeFlag(GL_TRUE);
			glVertex3fv(new double[] {0.0, 0.0, 0.0});
			glEdgeFlag(GL_FALSE);
			glVertex3fv(new double[] {0.4 * width, 0.8 * height, 0.0});
			glEdgeFlag(GL_TRUE);
			glVertex3fv(new double[] {0.8 * width, 0.8 * height, 0.0});
		glEnd();

		glFlush();
	}
	
	int width;
	int height;

	@Override
	public void reshape(int w, int h) {
		width = w;
		height = h;
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0.0, (double) w, 0.0, (double) h);
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
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(400, 150);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("lines");
		init();
		glutDisplayFunc(this);
		glutReshapeFunc(this);
		glutKeyboardFunc(this);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new example2_7().main(args.length, args));
	}

}
