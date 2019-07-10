package com.pflager.redbook.vtrg;


import com.pflager.glut;

public class aapoly extends glut {
	boolean polySmooth = true;
	int NFACE = 6;
	int NVERT = 8;

	void init() {
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glBlendFunc(GL_SRC_ALPHA_SATURATE, GL_ONE);
		glClearColor(0.0, 0.0, 0.0, 0.0);
	}

	void drawCube(double x0, double x1, double y0, double y1, double z0, double z1) {
		double v[] = new double[24];
		double c[] = { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0,
				0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		/* indices of front, top, left, bottom, right, back faces */
		byte[] indices = {  4, 5, 6, 7 ,  2, 3, 7, 6 ,  0, 4, 7, 3 ,  0, 1, 5, 4 , 1, 5, 6, 2 ,  0, 3, 2, 1  };

		v[0]= v[9] = v[12] = v[21] = (float) x0;
		v[3] = v[6] = v[15] = v[18] = (float) x1;
		v[1] = v[4] = v[13] = v[16] = (float) y0;
		v[7] = v[10] = v[25] = v[22] = (float) y1;
		v[2] = v[5] = v[8] = v[11] = (float) z0;
		v[14] = v[17] = v[20] = v[23] = (float) z1;

		// #ifdef GL_VERSION_1_1
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);
		glVertexPointer(3, GL_FLOAT, 0, v);
		glColorPointer(4, GL_FLOAT, 0, c);
		glDrawElements(GL_QUADS, NFACE * 4, GL_UNSIGNED_BYTE, indices);
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);
		/*
		 * #else printf ("If this is GL Version 1.0, "); printf
		 * ("vertex arrays are not supported.\n"); exit(1); #endif
		 */
	}

	void display() {
		if (polySmooth) {
			glClear (GL_COLOR_BUFFER_BIT);
			glEnable (GL_BLEND);
			glEnable (GL_POLYGON_SMOOTH);
			glDisable (GL_DEPTH_TEST);
			}
			else {
			glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glDisable (GL_BLEND);
			glDisable (GL_POLYGON_SMOOTH);
			glEnable (GL_DEPTH_TEST);
			}
			glPushMatrix ();
			glTranslatef (0.0, 0.0, -8.0);
			glRotatef (30.0, 1.0, 0.0, 0.0);
			glRotatef (60.0, 0.0, 1.0, 0.0);
			drawCube(-0.5, 0.5, -0.5, 0.5, -0.5, 0.5);
			glPopMatrix ();
			glFlush ();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(30.0, w / h, 1.0, 20.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 't':
		case 'T':
			polySmooth = !polySmooth;
			glutPostRedisplay();
			break;
		case 27: /* Escape key */
			System.exit(0);
			break;
		default:
			break;
		}
	}

	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGBA | GLUT_ALPHA | GLUT_DEPTH);
		glutInitWindowSize(200, 200);
		glutCreateWindow("aapoly");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new aapoly().main(args.length, args));

	}
}

