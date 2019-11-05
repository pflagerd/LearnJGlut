package org.pflager.redbook;

import com.pflager.glut;

public class alpha3D extends glut {

	final static double MAXZ = 8.0;
	final static double MINZ = -8.0;
	final double ZINC = 0.4;

	static float solidZ = (float) MAXZ;
	static float transparentZ = (float) MINZ;
	static int sphereList, cubeList;

	/* Initialize alpha blending function. */
	void init() {
		float mat_specular[] = { 1.0f, 1.0f, 1.0f, 0.15f };
		float mat_shininess[] = { 100.0f };
		float position[] = { 0.5f, 0.5f, 1.0f, 0.0f };
		glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);
		glLightfv(GL_LIGHT0, GL_POSITION, position);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glEnable(GL_DEPTH_TEST);
		sphereList = glGenLists(1);
		glNewList(sphereList, GL_COMPILE);
		glutSolidSphere(0.4, 16, 16);
		glEndList();
		cubeList = glGenLists(1);
		glNewList(cubeList, GL_COMPILE);
		glutSolidCube(0.6);
		glEndList();
	}

	void display() {
		float mat_solid[] = { 0.75f, 0.75f, 0.0f, 1.0f };
		float mat_zero[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float mat_transparent[] = { 0.0f, 0.8f, 0.8f, 0.6f };
		float mat_emission[] = { 0.0f, 0.3f, 0.3f, 0.6f };
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glPushMatrix();
		glTranslatef(-0.15, -0.15, solidZ);
		glMaterialfv(GL_FRONT, GL_EMISSION, mat_zero);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_solid);
		glCallList(sphereList);
		glPopMatrix();

		glPushMatrix();
		glTranslatef(0.15, 0.15, transparentZ);
		glRotatef(15.0, 1.0, 1.0, 0.0);
		glRotatef(30.0, 0.0, 1.0, 0.0);
		glMaterialfv(GL_FRONT, GL_EMISSION, mat_emission);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_transparent);
		glEnable(GL_BLEND);
		glDepthMask(GL_FALSE);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		glCallList(cubeList);
		glDepthMask(GL_TRUE);
		glDisable(GL_BLEND);
		glPopMatrix();
		glutSwapBuffers();

	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if (w <= h)
			glOrtho(-1.5, 1.5, -1.5 * h / w, 1.5 * h / w, -10.0, 10.0);
		else
			glOrtho(-1.5 * w / h, 1.5 * w / h, -1.5, 1.5, -10.0, 10.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 'a':
		case 'A':
			solidZ = (float) MAXZ;
			transparentZ = (float) MINZ;
			glutIdleFunc(this::animate);
			glutPostRedisplay();
			break;
		case 'r':
		case 'R':
			solidZ = (float) MAXZ;
			transparentZ = (float) MINZ;
			glutPostRedisplay();
			break;
		case 27: /* Escape key */
			System.exit(0);
			break;
		default:
			break;
		}
	}

	void animate() {
		if (solidZ <= MINZ || transparentZ >= MAXZ)
			glutIdleFunc(null);
		else {
			solidZ -= ZINC;
			transparentZ += ZINC;
			glutPostRedisplay();
		}
	}

	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(500, 500);
		glutCreateWindow("alpha3D");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new alpha3D().main(args.length, args));

	}
}