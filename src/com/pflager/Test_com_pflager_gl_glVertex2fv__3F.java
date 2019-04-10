package com.pflager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import java.lang.Math;

class Test_com_pflager_gl_glVertex2fv__3F extends glutTest {

	@Test
	void testDrawSpiralStartingAtTheExactCenter_II_II() throws IOException, InterruptedException {
		singleShotDisplayTest(() -> {
			glClear(GL_COLOR_BUFFER_BIT);
			final int circle_points = 256;
			final int circle_count = 9;
			final double circle_spacing = (1/(double)circle_count);
			glBegin(GL_POINTS);
			double IncrementalRadius = 0 ; 
			for (double radius = circle_spacing; (radius < 1.1); radius = ( radius +circle_spacing)) {
				double k = 0;
				for (double i = 0; i < circle_points; i++) { 
					IncrementalRadius = IncrementalRadius +  (1/ (double)(circle_points*circle_count));
					k = k + 360 / (double) circle_points; //Increments Spacing between points
			    	glColor3f(0f, 0f, 1f);
					glVertex2f(Math.cos(Math.toRadians(k)) * IncrementalRadius, Math.sin(Math.toRadians(k)) * IncrementalRadius);
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

}
