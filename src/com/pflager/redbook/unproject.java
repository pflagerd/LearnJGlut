package com.pflager.redbook;

import com.pflager.glut;

public class unproject extends glut {

	void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glFlush();
	}

	/* Change these values for a different transformation */
	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45.0, w / h, 1.0, 100.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	void mouse(int button, int state, int x, int y) {
		int viewport[] = new int[4];
		double mvmatrix[] = new double[16];
		double projmatrix[] = new double[16];
		int realy; /* OpenGL y coordinate position */

		switch (button) {
			case GLUT_LEFT_BUTTON:
				if (state == GLUT_DOWN) {
					glGetIntegerv(GL_VIEWPORT, viewport);
					glGetDoublev(GL_MODELVIEW_MATRIX, mvmatrix);
					glGetDoublev(GL_PROJECTION_MATRIX, projmatrix);
					/* note viewport[3] is height of window in pixels */
					realy = viewport[3] - (int) y - 1;
					System.out.printf("Coordinates at cursor are (%4d, %4d)\n", x, realy);
					
					double wxyz[] = new double[3];
					// gluUnProject((double) x, (double) realy, 0.0, mvmatrix, projmatrix, viewport, wxyz);
					// gluUnProject(new double[] {(double) x, (double) realy, 0.0}, mvmatrix, projmatrix, viewport, wxyz);
					// wxyz = gluUnProject((double) x, (double) realy, 0.0, mvmatrix, projmatrix, viewport);
					wxyz = gluUnProject(new double[] {(double) x, (double) realy, 0.0}, mvmatrix, projmatrix, viewport);
					
					System.out.printf("World coords at z=0.0 are (%f, %f, %f)\n", wxyz[0], wxyz[1], wxyz[2]);
										
					//gluUnProject((double) x, (double) realy, 1.0, mvmatrix, projmatrix, viewport, wxyz);
					// gluUnProject(new double[] {(double) x, (double) realy, 1.0}, mvmatrix, projmatrix, viewport, wxyz);
					// wxyz = gluUnProject((double) x, (double) realy, 1.0, mvmatrix, projmatrix, viewport);
					wxyz = gluUnProject(new double[] {(double) x, (double) realy, 1.0}, mvmatrix, projmatrix, viewport);
					
					System.out.printf("World coords at z=1.0 are (%f, %f, %f)\n", wxyz[0], wxyz[1], wxyz[2]);
				}
				break;
			case GLUT_RIGHT_BUTTON:
				if (state == GLUT_DOWN)
					System.exit(0);
				break;
			default:
				break;
		}
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
			case 27:
				System.exit(0);
				break;
		}
	}

	/*
	 * Open window, register input callback functions
	 */
	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(500, 500);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("unproject");
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMouseFunc(this::mouse);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new unproject().main(args.length, args));
	}
}
