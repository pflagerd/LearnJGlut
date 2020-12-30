package org.pflager;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Test_com_pflager_gl_glVertex2f__DD extends glutTest {
	final float PI = (float) 3.1415926535898;

	@Test
	void testFullyFilledCanvasWithNinePointsCircle_DD() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);
			int circle_points = 128;
			glBegin(GL_POINTS);
			for (double radius = 0.111; (radius <= 1); radius = radius + 0.111) {
				double k = 0;
				for (double i = 0; i < circle_points; i++) {
					k = k + 2.8125;
					glVertex2f(Math.cos(Math.toRadians(k)) * radius, Math.sin(Math.toRadians(k)) * radius);
				}
			}
			glEnd();
			glFinish(); // waits for display to settle down.
			try {
				captureCanvasAsImageFile("artifacts/tmp.png");
			} catch (IOException ioException) {
				System.err.println("Couldn't save file artifacts/tmp.png");
				ioException.printStackTrace(System.err);
			}
			glutLeaveMainLoop();
		});
	}

	@Test
	void testFullyFilledCanvasWithNinePointsCircle_FD() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);
			int circle_points = 128;
			glBegin(GL_POINTS);
			for (double radius = 0.111; (radius <= 1); radius = radius + 0.111) {
				double k = 0;
				for (double i = 0; i < circle_points; i++) {
					k = k + 2.8125;
					glVertex2f((float) (Math.cos(Math.toRadians(k)) * radius), Math.sin(Math.toRadians(k)) * radius);
				}
			}
			glEnd();
			glFinish(); // waits for display to settle down.
			try {
				captureCanvasAsImageFile("artifacts/tmp.png");
			} catch (IOException ioException) {
				System.err.println("Couldn't save file artifacts/tmp.png");
				ioException.printStackTrace(System.err);
			}
			glutLeaveMainLoop();
		});
	}

	@Test
	void testFullyFilledCanvasWithNinePointsCircle_FF() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);
			int circle_points = 128;
			glBegin(GL_POINTS);
			for (double radius = 0.111; (radius <= 1); radius = radius + 0.111) {
				double k = 0;
				for (double i = 0; i < circle_points; i++) {
					k = k + 2.8125;
					glVertex2f((float) (Math.cos(Math.toRadians(k)) * radius), (float) (Math.sin(Math.toRadians(k)) * radius));
				}
			}
			glEnd();
			glFinish(); // waits for display to settle down.
			try {
				captureCanvasAsImageFile("artifacts/tmp.png");
			} catch (IOException ioException) {
				System.err.println("Couldn't save file artifacts/tmp.png");
				ioException.printStackTrace(System.err);
			}
			glutLeaveMainLoop();
		});
	}

	@Test
	void testFullyFilledCanvasWithNineLineLoopCircle_DD() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);
			int circle_points = 128;
			for (double radius = 0.111; (radius <= 1); radius = radius + 0.111) {
				glBegin(GL_LINE_LOOP);
				double k = 0;
				for (double i = 0; i < circle_points; i++) {
					k = k + 2.8125;
					glVertex2f(Math.cos(Math.toRadians(k)) * radius, Math.sin(Math.toRadians(k)) * radius);
				}
				glEnd();
			}
			glFinish(); // waits for display to settle down.
			try {
				captureCanvasAsImageFile("artifacts/tmp.png");
			} catch (IOException ioException) {
				System.err.println("Couldn't save file artifacts/tmp.png");
				ioException.printStackTrace(System.err);
			}
			glutLeaveMainLoop();
		});
	}

}
