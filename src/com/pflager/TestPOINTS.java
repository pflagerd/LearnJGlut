package com.pflager;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class TestPOINTS extends glut {

	public void display() {
		glClear(GL_COLOR_BUFFER_BIT);

		glBegin(GL_POINTS); {
			glVertex3d(0, 0, 0);
		}
		glEnd();

		glFinish(); //waits for display to settle down.
		
		screenShot("artifacts/TestPOINTS.png");		
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
	
	public void screenShot(String imageFile) {
		//glReadBuffer(GL_FRONT);
		// Get viewport size
		int[] viewport = new int[4];
		glGetIntegerv(GL_VIEWPORT, viewport);
		int width = viewport[2];
		int height= viewport[3];
		System.out.println("width == " + width + ", height == " + height);
		
		int bpp = 4; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
		byte [] pixelBytes = new byte[width * height * bpp];
		glReadPixels(0, 0, width, height, GL_RGBA, GL_UNSIGNED_BYTE, pixelBytes );
		
		File file = new File(imageFile);
		String format = "PNG";
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for(int x = 0; x < width; x++) 
		{
		    for(int y = 0; y < height; y++)
		    {
		        int i = (x + (width * y)) * bpp;
		        int r = pixelBytes[i] & 0xFF;
		        int g = pixelBytes[i + 1] & 0xFF;
		        int b = pixelBytes[i + 2] & 0xFF;
		        image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
		    }
		}
		   
	    try {
			ImageIO.write(image, format, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
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
		
		glutLeaveMainLoop();
		
		thread.join();
	}
}
