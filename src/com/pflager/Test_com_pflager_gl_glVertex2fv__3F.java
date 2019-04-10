package com.pflager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import java.lang.Math;

class Java_com_pflager_gl_glVertex2fv__3F extends glutTest {

	@Test
	void testDrawSpiralStartingAtTheExactCenter_II_II() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);
			float x,y,z = -50,angle;
		    glBegin(GL_POINTS);

		    for(angle = 0; angle < 360; angle += 1)
		    {   
		        x = (float) (50 * Math.cos(angle));
		        y = (float) (50 * Math.sin(angle));
		       // glVertex2fv(x, y);
		       // glVertex2fv(x,y,z);
		        z+=1;
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

}
