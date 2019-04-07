package com.pflager.redbook;

import com.pflager.glut;

// Example 1-2
public class template2D extends glut implements glut.DisplayFunc, glut.ReshapeFunc, glut.KeyboardFunc {

	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glShadeModel(GL_FLAT);
	}

	@Override
	public void display() {
		glClear(GL_COLOR_BUFFER_BIT);

		/* select white for all lines */
		glColor3f(1.0, 1.0, 1.0);

		glFlush();
	}

	@Override
	public void reshape(int w, int h) {
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
		System.exit(new template2D().main(args.length, args));
	}

}
