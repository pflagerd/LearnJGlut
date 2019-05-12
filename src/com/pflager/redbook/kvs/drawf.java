package com.pflager.redbook.kvs;

import com.pflager.glut;

public class drawf extends glut {

	byte rasters[] = new byte[] {
			(byte)0xc0, (byte)0x00, (byte)0xc0, (byte)0x00, (byte)0xc0,(byte) 0x00,(byte) 0xc0, (byte)0x00,(byte) 0xc0,(byte) 0x00,
			(byte)0xff,(byte) 0x00,(byte) 0xff,(byte) 0x00,(byte) 0xc0,(byte) 0x00,(byte) 0xc0,(byte) 0x00,(byte) 0xc0, (byte)0x00,
			(byte)0xff, (byte)0xc0, (byte)0xff, (byte)0xc0};
	
	void init() {
		glPixelStorei (GL_UNPACK_ALIGNMENT, 1);
		glClearColor (0.0, 0.0, 0.0, 0.0);
	}



	void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f (1.0, 1.0, 1.0);
		glRasterPos2i (20, 20);
		glBitmap (10, 12, 0.0, 0.0, 11.0, 0.0, rasters);
		glBitmap (10, 12, 0.0, 0.0, 11.0, 0.0, rasters);
		glBitmap (10, 12, 0.0, 0.0, 11.0, 0.0, rasters);
		glFlush();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w,  h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho (0, w, 0, h, -1.0, 1.0);
		glMatrixMode(GL_MODELVIEW);
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
		glutInitWindowSize(100, 100);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("drawf");
		init();
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutDisplayFunc(this::display);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new drawf().main(args.length, args));

	}
}
