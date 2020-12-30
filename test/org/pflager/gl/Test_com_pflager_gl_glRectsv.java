package org.pflager.gl;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class Test_com_pflager_gl_glRectsv extends glutTest {

	@Test
	void testFullyFilledCanvas_II_II() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);

			glRectsv(new short[] { -1, -1 }, new short[] { 1, 1 });

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
	void testSquareInTheMiddleOfTheCanvas_DD_DD() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);

			glOrtho(-4, 4, -4, 4, 1, -1);
			glRectsv(new short[] { -2, -2 }, new short[] { 2, 2 });

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
	void testPartiallyFilledCanvas_II_FF() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);

			glOrtho(-4, 4, -4, 4, 1, -1);
			glRectsv(new short[] { -4, 0 }, new short[] { 0, 4 });

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
