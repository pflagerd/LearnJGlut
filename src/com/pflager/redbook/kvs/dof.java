package com.pflager.redbook.kvs;

import com.pflager.glut;

public class dof extends glut {

	static double near = 0;
	static double far = 0;

	static double PI_ = 3.14159265358979323846;

	int teapotList;

	void accFrustum(double left, double right, double bottom, double top, double near, double far, double pixdx,
			double pixdy, double eyedx, double eyedy, double focus) {
		double xwsize, ywsize;
		double dx, dy;
		int viewport[] = new int[4];

		glGetIntegerv(GL_VIEWPORT, viewport);

		xwsize = right - left;
		ywsize = top - bottom;

		dx = -(pixdx * xwsize / viewport[2] + eyedx * near / focus);
		dy = -(pixdy * ywsize / viewport[3] + eyedy * near / focus);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glFrustum(left + dx, right + dx, bottom + dy, top + dy, near, far);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(-eyedx, -eyedy, 0.0);
	}

	void accPerspective(double fovy, double aspect, double near, double far, double pixdx, double pixdy, double eyedx,
			double eyedy, double focus) {
		double fov2, left, right, bottom, top;

		fov2 = ((fovy * PI_) / 180.0) / 2.0;

		top = near / (Math.cos(fov2) / Math.sin(fov2));
		bottom = -top;

		right = top * aspect;
		left = -right;

		accFrustum(left, right, bottom, top, near, far, pixdx, pixdy, eyedx, eyedy, focus);
	}

	void init() {
		float ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float position[] = { 0.0f, 3.0f, 3.0f, 0.0f };
		float specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float lmodel_ambient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
		float local_view[] = { 0.0f };

		glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
		glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse);
		glLightfv(GL_LIGHT0, GL_POSITION, position);

		glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
		glLightModelfv(GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);

		glFrontFace(GL_CW);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glEnable(GL_AUTO_NORMAL);
		glEnable(GL_NORMALIZE);
		glEnable(GL_DEPTH_TEST);

		glClearColor(0.0, 0.0, 0.0, 0.0);
		glClearAccum(0.0, 0.0, 0.0, 0.0);
		/* make teapot display list */
		teapotList = glGenLists(1);
		glNewList(teapotList, GL_COMPILE);
		glutSolidTeapot(0.5);
		glEndList();
	}

	void renderTeapot(float x, float y, float z, float ambr, float ambg, float ambb, float difr, float difg, float difb,
			float specr, float specg, float specb, float shine) {
		float mat[] = new float[4];

		glPushMatrix();
		glTranslatef(x, y, z);
		mat[0] = ambr;
		mat[1] = ambg;
		mat[2] = ambb;
		mat[3] = 1.0f;
		glMaterialfv(GL_FRONT, GL_AMBIENT, mat);
		mat[0] = difr;
		mat[1] = difg;
		mat[2] = difb;
		glMaterialfv(GL_FRONT, GL_DIFFUSE, mat);
		mat[0] = specr;
		mat[1] = specg;
		mat[2] = specb;
		glMaterialfv(GL_FRONT, GL_SPECULAR, mat);
		glMaterialf(GL_FRONT, GL_SHININESS, shine * 128.0);
		glCallList(teapotList);
		glPopMatrix();
	}

	/*
	 * display() draws 5 teapots into the accumulation buffer several times; each
	 * time with a jittered perspective. The focal point is at z = 5.0, so the gold
	 * teapot will stay in focus. The amount of jitter is adjusted by the magnitude
	 * of the accPerspective() jitter; in this example, 0.33. In this example, the
	 * teapots are drawn 8 times. See jitter.h
	 */
	void display() {
		int jitter;
		int viewport[] = new int[4];

		glGetIntegerv(GL_VIEWPORT, viewport);
		glClear(GL_ACCUM_BUFFER_BIT);

		for (jitter = 0; jitter < 8; jitter++) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			accPerspective(45.0, viewport[2] / viewport[3], 1.0, 15.0, 0.0, 0.0, 0.0, 0.0, 5.0);

			/* ruby, gold, silver, emerald, and cyan teapots */
			renderTeapot(-1.1f, -0.5f, -4.5f, 0.1745f, 0.01175f, 0.01175f, 0.61424f, 0.04136f, 0.04136f, 0.727811f,
					0.626959f, 0.626959f, 0.6f);
			renderTeapot(-0.5f, -0.5f, -5.0f, 0.24725f, 0.1995f, 0.0745f, 0.75164f, 0.60648f, 0.22648f, 0.628281f,
					0.555802f, 0.366065f, 0.4f);
			renderTeapot(0.2f, -0.5f, -5.5f, 0.19225f, 0.19225f, 0.19225f, 0.50754f, 0.50754f, 0.50754f, 0.508273f,
					0.508273f, 0.508273f, 0.4f);
			renderTeapot(1.0f, -0.5f, -6.0f, 0.0215f, 0.1745f, 0.0215f, 0.07568f, 0.61424f, 0.07568f, 0.633f, 0.727811f,
					0.633f, 0.6f);
			renderTeapot(1.8f, -0.5f, -6.5f, 0.0f, 0.1f, 0.06f, 0.0f, 0.50980392f, 0.50980392f, 0.50196078f,
					0.50196078f, 0.50196078f, .25f);
			glAccum(GL_ACCUM, 0.125);
		}
		glAccum(GL_RETURN, 1.0);
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
			break;
		}
	}

	/*
	 * Main Loop Be certain you request an accumulation buffer.
	 */
	int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_ACCUM | GLUT_DEPTH);
		glutInitWindowSize(400, 400);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("dof");
		init();
		glutReshapeFunc(this::reshape);
		glutDisplayFunc(this::display);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new dof().main(args.length, args));
	}

}