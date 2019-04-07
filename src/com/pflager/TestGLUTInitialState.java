package com.pflager;

import org.junit.jupiter.api.Test;

class TestGLUTInitialState extends glut {

	public void display() {
		/* clear all pixels */
		glClear(GL_COLOR_BUFFER_BIT);

		/*
		 * draw white polygon (rectangle) with corners at (0.25, 0.25, 0.0) and (0.75,
		 * 0.75, 0.0)
		 */
		glColor3f(1.0, 1.0, 1.0);
		glBegin(GL_POLYGON);
		glVertex3f(0.25, 0.25, 0.0);
		glVertex3f(0.75, 0.25, 0.0);
		glVertex3f(0.75, 0.75, 0.0);
		glVertex3f(0.25, 0.75, 0.0);
		glEnd();

		/*
		 * don't wait! start processing buffered OpenGL routines
		 */
		glFlush();
	}

	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-1.0, 1.0, -1.0, 1.0, 1.0, -1.0);
		
		float[] projectionMatrixStack = glGetFloatv(GL_PROJECTION_MATRIX);
		for (int i = 0; i < projectionMatrixStack.length / 16; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print("[");
				for (int k = 0; k < 4; k++) {
					if (k != 0) {
						System.out.print(", ");
					}
					System.out.printf("%14.8g", projectionMatrixStack[i * 16 + j * 4 + k]);					
				}
				System.out.println("]");
			}
			
			System.out.println("\n");
		}
			
	}

	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(250, 250);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("TestGLUTInitial");
		
		float[] projectionMatrixStack = glGetFloatv(GL_PROJECTION_MATRIX);
		for (int i = 0; i < projectionMatrixStack.length / 16; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print("[");
				for (int k = 0; k < 4; k++) {
					if (k != 0) {
						System.out.print(", ");
					}
					System.out.printf("%14.8g", projectionMatrixStack[i * 16 + j * 4 + k]);					
				}
				System.out.println("]");
			}
			
			System.out.println("\n");
		}
		
		
		init();
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	@Test
	void test() throws InterruptedException {
		Thread thread = new Thread() {
			public void run() {
				new TestGLUTInitialState().main(1, new String[] { "TestGLUTInitialState" });
			}
		};
		thread.start();

		Thread.sleep(1000);

		glutLeaveMainLoop();

		thread.join();
	}

}
