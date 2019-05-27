package com.pflager.redbook.vtrg;

import com.pflager.glut;

public class stroke extends glut {
	final int PT = 1;
	final int STROKE = 2;
	final int END = 3;

	class charpoint {
		public charpoint(float x, float y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

		float x, y;
		int type;
	};

	charpoint Adata[] = { new charpoint(0, 0, PT), new charpoint(0, 9, PT), new charpoint(1, 10, PT),
			new charpoint(4, 10, PT), new charpoint(5, 9, PT), new charpoint(5, 0, STROKE), new charpoint(0, 5, PT),
			new charpoint(5, 5, END) };

	charpoint Edata[] = { new charpoint(5, 0, PT), new charpoint(0, 0, PT), new charpoint(0, 10, PT),
			new charpoint(5, 10, STROKE), new charpoint(0, 5, PT), new charpoint(4, 5, END) };

	charpoint Pdata[] = { new charpoint(0, 0, PT), new charpoint(0, 10, PT), new charpoint(4, 10, PT),
			new charpoint(5, 9, PT), new charpoint(5, 6, PT), new charpoint(4, 5, PT), new charpoint(0, 5, END) };

	charpoint Rdata[] = { new charpoint(0, 0, PT), new charpoint(0, 10, PT), new charpoint(4, 10, PT),
			new charpoint(5, 9, PT), new charpoint(5, 6, PT), new charpoint(4, 5, PT), new charpoint(0, 5, STROKE),
			new charpoint(3, 5, PT), new charpoint(5, 0, END) };

	charpoint Sdata[] = { new charpoint(0, 1, PT), new charpoint(1, 0, PT), new charpoint(4, 0, PT),
			new charpoint(5, 1, PT), new charpoint(5, 4, PT), new charpoint(4, 5, PT), new charpoint(1, 5, PT),
			new charpoint(0, 6, PT), new charpoint(0, 9, PT), new charpoint(1, 10, PT), new charpoint(4, 10, PT),
			new charpoint(5, 9, END) };

	/*
	 * drawLetter() interprets the instructions from the array for that letter and
	 * renders the letter with line segments.
	 */
	void drawLetter(charpoint l[]) {
		glBegin(GL_LINE_STRIP);
		for (int lns = 0; lns < l.length; lns++) {
			switch (l[lns].type) {
			case PT:
				glVertex2fv(new float[] { l[lns].x, l[lns].y });
				break;
			case STROKE: {
				glVertex2fv(new float[] { l[lns].x, l[lns].y });
				glEnd();
				glBegin(GL_LINE_STRIP);
			}
				break;
			case END: {
				glVertex2fv(new float[] { l[lns].x, l[lns].y });
				glEnd();
				glTranslatef(8.0, 0.0, 0.0);
			}
				return;
			}
		}
	}

	/* Create a display list for each of 6 characters */
	void init() {
		int base;
		glShadeModel(GL_FLAT);
		base = glGenLists(128);
		glListBase(base);
		glNewList(base + 'A', GL_COMPILE);
		drawLetter(Adata);
		glEndList();
		glNewList(base + 'E', GL_COMPILE);
		drawLetter(Edata);
		glEndList();
		glNewList(base + 'P', GL_COMPILE);
		drawLetter(Pdata);
		glEndList();
		glNewList(base + 'R', GL_COMPILE);
		drawLetter(Rdata);
		glEndList();
		glNewList(base + 'S', GL_COMPILE);
		drawLetter(Sdata);
		glEndList();
		glNewList(base + ' ', GL_COMPILE);
		glTranslatef(8.0, 0.0, 0.0);
		glEndList();
	}

	String test1 = "A SPARE SERAPE APPEARS AS";
	String test2 = "APES PREPARE RARE PEPPERS";

	void printStrokedString(String s) {
		int len = s.length();
		int chars[] = new int[len];
		//for (int chr = 0; chr < len; chr++) {
		//	glCallList(((byte) s.charAt(chr) + 1));
			// chars[chr] = (((byte) s.charAt(chr) + 1) );
		//}
		//glCallLists(len, GL_INT , chars);
		glCallLists(s.length(), GL_UNSIGNED_BYTE,  s.toString().getBytes());
	}

	void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(1.0, 1.0, 1.0);
		glPushMatrix();
		glScalef(2.0, 2.0, 2.0);
		glTranslatef(10.0, 30.0, 0.0);
		printStrokedString(test1);
		glPopMatrix();
		glPushMatrix();
		glScalef(2.0, 2.0, 2.0);
		glTranslatef(10.0, 13.0, 0.0);
		printStrokedString(test2);
		glPopMatrix();
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0.0, w, 0.0, h);
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case ' ':
			glutPostRedisplay();
			break;
		case 27:
			System.exit(0);
		}
	}

	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
		glutInitWindowSize(440, 120);
		glutCreateWindow("stroke");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new stroke().main(args.length, args));

	}
}
