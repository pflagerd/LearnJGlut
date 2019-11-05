package org.pflager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.pflager.glut;

class TestPOINTS extends glut {

	public void display() {
		glClear(GL_COLOR_BUFFER_BIT);

		glBegin(GL_POINTS); {
			glVertex3d(0, 0, 0);
		}
		glEnd();

		glFinish(); //waits for display to settle down.
		
		try {
			captureCanvasAsImageFile("artifacts/TestPOINTS.png");
		} catch (IOException ioException) {
			System.err.println("Couldn't save file artifacts/TestPOINTS.png");
			ioException.printStackTrace(System.err);			
		}
		glutLeaveMainLoop();
		
	}

	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		
		System.out.println("Action on Window Close == " + glutGet(GLUT_ACTION_ON_WINDOW_CLOSE));
		assertEquals(GLUT_ACTION_EXIT, glutGet(GLUT_ACTION_ON_WINDOW_CLOSE));
		
		glutSet(GLUT_ACTION_ON_WINDOW_CLOSE, GLUT_ACTION_GLUTMAINLOOP_RETURNS);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);		
		glutInitWindowSize(400, 400);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("TestPOINTS");
		
		int[] viewport = new int[4];
		glGetIntegerv(GL_VIEWPORT, viewport);
		int width = viewport[2];
		int height= viewport[3];
		System.out.println("x == " + viewport[0] + ", y == " + viewport[1] + ", width == " + width + ", height == " + height);

		viewport = null;
		viewport = glGetIntegerv(GL_VIEWPORT);
		System.out.println("x == " + viewport[0] + ", y == " + viewport[1] + ", width == " + width + ", height == " + height);
		
		float [] currentColorFloat = new float[4];
		glGetFloatv(GL_CURRENT_COLOR, currentColorFloat);
		
		float [] currentColor = glGetFloatv(GL_CURRENT_COLOR);
		for (int i = 0; i < currentColor.length; i++) {
			System.out.println("currentColor[" + i + "] == " + currentColor[i]);
		}
		
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glShadeModel(GL_FLAT);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}
	
	@Test
	public void test_POINT_At_Origin_3D() throws InterruptedException {
		Thread thread = new Thread() {
			public void run() {
				new TestPOINTS().main(1, new String[] { "TestPOINTS" });
			}
		};
		thread.start();
		
		Thread.sleep(1000);
		
		thread.join();
	}
}
