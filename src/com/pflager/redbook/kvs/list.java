package com.pflager.redbook.kvs;

import com.pflager.glut;

public class list extends glut {

	int listName;

	void init() {
		listName = glGenLists(1);
		glNewList(listName, GL_COMPILE);
		glColor3f(1.0, 0.0, 0.0); /* current color red */
		glBegin(GL_TRIANGLES);
		glVertex2f(0.0, 0.0);
		glVertex2f(1.0, 0.0);
		glVertex2f(0.0, 1.0);
		glEnd();
		glTranslatef(1.5, 0.0, 0.0); /* move position */
		glEndList();
		glShadeModel(GL_FLAT);
	}

	void drawLine() {
		glBegin(GL_LINES);
		glVertex2f(0.0, 0.5);
		glVertex2f(15.0, 0.5);
		glEnd();
	}

	void display() {
		int i;
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(0.0, 1.0, 0.0); /* current color green */
		for (i = 0; i < 10; i++) /* draw 10 triangles */
			glCallList(listName);
		drawLine(); /* is this line green? NO! */
		/* where is the line drawn? */
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if (w <= h)
			gluOrtho2D(0.0, 2.0, -0.5 * h / w, 1.5 * h / w);
		else
			gluOrtho2D(0.0, 2.0 * w / h, -0.5, 1.5);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
		}
	}

	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(650, 50);
		glutCreateWindow("list");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new list().main(args.length, args));

	}
}
