package com.pflager.redbook.kvs;

import com.pflager.glut;

public class dof extends glut {

	final int MAX_SAMPLES = 66;

	/* 2 jitter points */
	double[][] j2 = { { 0.246490, 0.249999 }, { -0.246490, -0.249999 } };

	/* 3 jitter points */
	double[][] j = { { -0.373411, -0.250550 }, { 0.256263, 0.368119 }, { 0.117148, -0.117570 } };

	/* 4 jitter points */
	double[][] j4 = { { -0.208147, 0.353730 }, { 0.203849, -0.353780 }, { -0.292626, -0.149945 }, { 0.296924, 0.149994 } };

	/* 8 jitter points */
	double[][] j8 = { { -0.334818, 0.435331 }, { 0.286438, -0.393495 }, { 0.459462, 0.141540 }, { -0.414498, -0.192829 }, { -0.183790, 0.082102 }, { -0.079263, -0.317383 }, { 0.102254, 0.299133 }, { 0.164216, -0.054399 } };

	/* 15 jitter points */
	double[][] j15 = { { 0.285561, 0.188437 }, { 0.360176, -0.065688 }, { -0.111751, 0.275019 }, { -0.055918, -0.215197 }, { -0.080231, -0.470965 }, { 0.138721, 0.409168 }, { 0.384120, 0.458500 }, { -0.454968, 0.134088 }, { 0.179271, -0.331196 }, { -0.307049, -0.364927 }, { 0.105354, -0.010099 }, { -0.154180, 0.021794 }, { -0.370135, -0.116425 }, { 0.451636, -0.300013 }, { -0.370610, 0.387504 } };

	/* 24 jitter points */
	double[][] j24 = { { 0.030245, 0.136384 }, { 0.018865, -0.348867 }, { -0.350114, -0.472309 }, { 0.222181, 0.149524 }, { -0.393670, -0.266873 }, { 0.404568, 0.230436 }, { 0.098381, 0.465337 }, { 0.462671, 0.442116 }, { 0.400373, -0.212720 }, { -0.409988, 0.263345 }, { -0.115878, -0.001981 }, { 0.348425, -0.009237 }, { -0.464016, 0.066467 }, { -0.138674, -0.468006 }, { 0.144932, -0.022780 }, { -0.250195, 0.150161 }, { -0.181400, -0.264219 }, { 0.196097, -0.234139 }, { -0.311082, -0.078815 }, { 0.268379, 0.366778 }, { -0.040601, 0.327109 }, { -0.234392, 0.354659 }, { -0.003102, -0.154402 }, { 0.297997, -0.417965 } };

	/* 66 jitter points */
	double[][] j66 = { { 0.266377, -0.218171 }, { -0.170919, -0.429368 }, { 0.047356, -0.387135 }, { -0.430063, 0.363413 }, { -0.221638, -0.313768 }, { 0.124758, -0.197109 }, { -0.400021, 0.482195 }, { 0.247882, 0.152010 }, { -0.286709, -0.470214 }, { -0.426790, 0.004977 }, { -0.361249, -0.104549 }, { -0.040643, 0.123453 }, { -0.189296, 0.438963 }, { -0.453521, -0.299889 }, { 0.408216, -0.457699 }, { 0.328973, -0.101914 }, { -0.055540, -0.477952 }, { 0.194421, 0.453510 }, { 0.404051, 0.224974 }, { 0.310136, 0.419700 }, { -0.021743, 0.403898 }, { -0.466210, 0.248839 }, { 0.341369, 0.081490 }, { 0.124156, -0.016859 }, { -0.461321, -0.176661 }, { 0.013210, 0.234401 }, { 0.174258, -0.311854 }, { 0.294061, 0.263364 }, { -0.114836, 0.328189 }, { 0.041206, -0.106205 }, { 0.079227, 0.345021 }, { -0.109319, -0.242380 }, { 0.425005, -0.332397 }, { 0.009146, 0.015098 }, { -0.339084, -0.355707 }, { -0.224596, -0.189548 }, { 0.083475, 0.117028 }, { 0.295962, -0.334699 }, { 0.452998, 0.025397 }, { 0.206511, -0.104668 },
			{ 0.447544, -0.096004 }, { -0.108006, -0.002471 }, { -0.380810, 0.130036 }, { -0.242440, 0.186934 }, { -0.200363, 0.070863 }, { -0.344844, -0.230814 }, { 0.408660, 0.345826 }, { -0.233016, 0.305203 }, { 0.158475, -0.430762 }, { 0.486972, 0.139163 }, { -0.301610, 0.009319 }, { 0.282245, -0.458671 }, { 0.482046, 0.443890 }, { -0.121527, 0.210223 }, { -0.477606, -0.424878 }, { -0.083941, -0.121440 }, { -0.345773, 0.253779 }, { 0.234646, 0.034549 }, { 0.394102, -0.210901 }, { -0.312571, 0.397656 }, { 0.200906, 0.333293 }, { 0.018703, -0.261792 }, { -0.209349, -0.065383 }, { 0.076248, 0.478538 }, { -0.073036, -0.355064 }, { 0.145087, 0.221726 } };

	int teapotList;

	/*
	 * accFrustum() The first 6 arguments are identical to the glFrustum() call.
	 *
	 * pixdx and pixdy are anti-alias jitter in pixels. Set both equal to 0.0 for no anti-alias jitter. eyedx and eyedy are depth-of field jitter in pixels. Set both equal to 0.0 for no depth of field effects.
	 *
	 * focus is distance from eye to plane in focus. focus must be greater than, but not equal to 0.0.
	 *
	 * Note that accFrustum() calls glTranslatef(). You will probably want to insure that your ModelView matrix has been initialized to identity before calling accFrustum().
	 */
	void accFrustum(double left, double right, double bottom, double top, double near, double far, double pixdx, double pixdy, double eyedx, double eyedy, double focus) {
		double xwsize, ywsize;
		double dx, dy;
		int[] viewport = new int[4];

		glGetIntegerv(GL_VIEWPORT, viewport);

		xwsize = right - left;
		ywsize = top - bottom;

		dx = -(pixdx * xwsize / (double) viewport[2] + eyedx * near / focus);
		dy = -(pixdy * ywsize / (double) viewport[3] + eyedy * near / focus);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glFrustum(left + dx, right + dx, bottom + dy, top + dy, near, far);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(-eyedx, -eyedy, 0.0);
	}

	/*
	 * accPerspective()
	 *
	 * The first 4 arguments are identical to the gluPerspective() call. pixdx and pixdy are anti-alias jitter in pixels. Set both equal to 0.0 for no anti-alias jitter. eyedx and eyedy are depth-of field jitter in pixels. Set both equal to 0.0 for no depth of field effects.
	 *
	 * focus is distance from eye to plane in focus. focus must be greater than, but not equal to 0.0.
	 *
	 * Note that accPerspective() calls accFrustum().
	 */
	void accPerspective(double fovy, double aspect, double near, double far, double pixdx, double pixdy, double eyedx, double eyedy, double focus) {
		double fov2, left, right, bottom, top;

		fov2 = ((fovy * Math.PI) / 180.0) / 2.0;

		top = near / (Math.cos(fov2) / Math.sin(fov2));
		bottom = -top;

		right = top * aspect;
		left = -right;

		accFrustum(left, right, bottom, top, near, far, pixdx, pixdy, eyedx, eyedy, focus);
	}

	void init() {
		double ambient[] = { 0.0, 0.0, 0.0, 1.0 };
		double diffuse[] = { 1.0, 1.0, 1.0, 1.0 };
		double position[] = { 0.0, 3.0, 3.0, 0.0 };

		double lmodel_ambient[] = { 0.2, 0.2, 0.2, 1.0 };
		double local_view[] = { 0.0 };

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

	void renderTeapot(double x, double y, double z, double ambr, double ambg, double ambb, double difr, double difg, double difb, double specr, double specg, double specb, double shine) {
		double[] mat = new double[4];

		glPushMatrix();
		glTranslatef(x, y, z);
		mat[0] = ambr;
		mat[1] = ambg;
		mat[2] = ambb;
		mat[3] = 1.0;
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
	 * display() draws 5 teapots into the accumulation buffer several times; each time with a jittered perspective. The focal point is at z = 5.0, so the gold teapot will stay in focus. The amount of jitter is adjusted by the magnitude of the accPerspective() jitter; in this example, 0.33. In this example, the teapots are drawn 8 times. See jitter.h
	 */
	void display() {
		int jitter;
		int[] viewport = new int[4];

		glGetIntegerv(GL_VIEWPORT, viewport);
		glClear(GL_ACCUM_BUFFER_BIT);

		for (jitter = 0; jitter < 8; jitter++) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			accPerspective(45.0, (double) viewport[2] / (double) viewport[3], 1.0, 15.0, 0.0, 0.0, 0.33 * j8[jitter][0], 0.33 * j8[jitter][1], 5.0);

			/* ruby, gold, silver, emerald, and cyan teapots */
			renderTeapot(-1.1, -0.5, -4.5, 0.1745, 0.01175, 0.01175, 0.61424, 0.04136, 0.04136, 0.727811, 0.626959, 0.626959, 0.6);
			renderTeapot(-0.5, -0.5, -5.0, 0.24725, 0.1995, 0.0745, 0.75164, 0.60648, 0.22648, 0.628281, 0.555802, 0.366065, 0.4);
			renderTeapot(0.2, -0.5, -5.5, 0.19225, 0.19225, 0.19225, 0.50754, 0.50754, 0.50754, 0.508273, 0.508273, 0.508273, 0.4);
			renderTeapot(1.0, -0.5, -6.0, 0.0215, 0.1745, 0.0215, 0.07568, 0.61424, 0.07568, 0.633, 0.727811, 0.633, 0.6);
			renderTeapot(1.8, -0.5, -6.5, 0.0, 0.1, 0.06, 0.0, 0.50980392, 0.50980392, 0.50196078, 0.50196078, 0.50196078, .25);
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
	public int main(int argc, String[] argv) {
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