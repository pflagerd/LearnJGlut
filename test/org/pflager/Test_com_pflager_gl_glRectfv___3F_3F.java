package org.pflager;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Test_com_pflager_gl_glRectfv___3F_3F extends glutTest {

	@Test
	void testFullyCoveredCanvas_II_II() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);

			glRectfv(new float[] {-1, -1}, new float[] {1, 1});

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
	void testSquareInTheMiddleOfTheCanvas_FF_FF() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);

			glRectfv(new float[] {-0.25f, -0.50f}, new float[] {0.75f, 1.00f});

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
	void testPartiallyCoveredCanvas_IF_FI() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);

			glRectfv(new float[] {0, 0.0f}, new float[] {-1.0f, 1});

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
