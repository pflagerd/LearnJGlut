package org.pflager.redbook.kvs;

import com.pflager.glut;

public class stencil extends glut {

	final int YELLOWMAT = 1;
	final  int BLUEMAT = 2;

	void init() {
		
		float yellow_diffuse[] = new float [] { 0.7f, 0.7f, 0.0f, 1.0f };
		float yellow_specular[] = new float [] { 1.0f, 1.0f, 1.0f, 1.0f };
		float blue_diffuse[] = new float [] { 0.1f, 0.1f, 0.7f, 1.0f };
		float blue_specular[] = new float [] { 0.1f, 1.0f, 1.0f, 1.0f };
		float position_one[] = new float [] { 1.0f, 1.0f, 1.0f, 0.0f };
		
		glNewList(YELLOWMAT, GL_COMPILE);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, yellow_diffuse);
		glMaterialfv(GL_FRONT, GL_SPECULAR, yellow_specular);
		glMaterialf(GL_FRONT, GL_SHININESS, 64.0);
		glEndList();
		glNewList(BLUEMAT, GL_COMPILE);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, blue_diffuse);
		glMaterialfv(GL_FRONT, GL_SPECULAR, blue_specular);
		glMaterialf(GL_FRONT, GL_SHININESS, 45.0);
		glEndList();
		glLightfv(GL_LIGHT0, GL_POSITION, position_one);
		glEnable(GL_LIGHT0);
		glEnable(GL_LIGHTING);
		glEnable(GL_DEPTH_TEST);
		glClearStencil(0x0);
		glEnable(GL_STENCIL_TEST);
	}

	/*
	 * Draw a sphere in a diamond-shaped section in the middle of a window with 2
	 * torii.
	 */
	void display() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		/* draw blue sphere where the stencil is 1 */
		glStencilFunc(GL_EQUAL, 0x1, 0x1);
		glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
		glCallList(BLUEMAT);
		glutSolidSphere(0.5, 15, 15);

		/* draw the tori where the stencil is not 1 */
		glStencilFunc(GL_NOTEQUAL, 0x1, 0x1);
		glPushMatrix();
		glRotatef(45.0, 0.0, 0.0, 1.0);
		glRotatef(45.0, 0.0, 1.0, 0.0);
		glCallList(YELLOWMAT);
		glutSolidTorus(0.275, 0.85, 15, 15);
		glPushMatrix();
		glRotatef(90.0, 1.0, 0.0, 0.0);
		glutSolidTorus(0.275, 0.85, 15, 15);
		glPopMatrix();
		glPopMatrix();
	}

	/*
	 * Whenever the window is reshaped, redefine the coordinate system and redraw
	 * the stencil area.
	 */
	void reshape(int w, int h) {
		glViewport(0, 0, w, h);

		/* create a diamond shaped stencil area */
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if (w <= h)
			gluOrtho2D(-3.0, 3.0, -3.0 * h / w, 3.0 * h / w);
		else
			gluOrtho2D(-3.0 * w / h, 3.0 * w / h, -3.0, 3.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glClear(GL_STENCIL_BUFFER_BIT);
		glStencilFunc(GL_ALWAYS, 0x1, 0x1);
		glStencilOp(GL_REPLACE, GL_REPLACE, GL_REPLACE);
		glBegin(GL_QUADS);
		glVertex2f(-1.0, 0.0);
		glVertex2f(0.0, 1.0);
		glVertex2f(1.0, 0.0);
		glVertex2f(0.0, -1.0);
		glEnd();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45.0, w / h, 3.0, 7.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(0.0, 0.0, -5.0);
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
			break;
		}
	}

	/*
	 * Main Loop Be certain to request stencil bits.
	 */
	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH | GLUT_STENCIL);
		glutInitWindowSize(400, 400);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("stencil");
		init();
		glutReshapeFunc(this::reshape);
		glutDisplayFunc(this::display);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new stencil().main(args.length, args));
	}

}
