package com.pflager.graphs;

import com.pflager.glut;

public class PositionVsTimeGraph extends glut {

	void renderBitmapString(float x, float y, int font, String string){
	    glRasterPos2f(x, y);
	    for (char c : string.toCharArray()) {
	        glutBitmapCharacter(font, c);
	    }
	} 
	
	void display() {
		/* clear all pixels */
		glClear(GL_COLOR_BUFFER_BIT);

		/*
		 * draw white polygon (rectangle) with corners at (0.25, 0.25, 0.0) and (0.75,
		 * 0.75, 0.0)
		 */
		glColor3f(1.0, 1.0, 1.0);
		
		renderBitmapString(-0.9, 0, GLUT_BITMAP_HELVETICA_12, "A confused dragonfly flies backward and forward in a straight line.");
		
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
		/* select clearing color */
		glClearColor(1.0, 1.0, 1.0, 1.0);

		/* initialize viewing values */
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
	}

	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(874, 729);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("PositionVsTimeGraph");
		init();
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new PositionVsTimeGraph().main(args.length, args));
	}
}
