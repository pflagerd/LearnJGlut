package com.pflager.redbook.vtrg;

import com.pflager.glut;

public class image extends glut{

	/*	Create checkerboard image	*/
	int	checkImageWidth=64;
	int	checkImageHeight=64;
	byte[] checkImage = new byte[checkImageHeight * checkImageWidth * 4];
	static double zoomFactor = 1.0;
	static int height;

	void makeCheckImage()
	{
	   int i, j, c;
	    
	   for (i = 0; i < checkImageHeight; i++) {
	      for (j = 0; j < checkImageWidth; j++) {
	    	  c = (((i & 0x8) == 0 ? 1 : 0) ^ ((j & 0x8) == 0 ? 1 : 0)) * 255;
	    	    checkImage[i * checkImageWidth * 4 + j * 4 + 0] = (byte) c;
				checkImage[i * checkImageWidth * 4 + j * 4 + 1] = (byte) c;
				checkImage[i * checkImageWidth * 4 + j * 4 + 2] = (byte) c;
				checkImage[i * checkImageWidth * 4 + j * 4 + 3] = (byte) 255;

	      }
	   }
	}
	void init()
	{
		glClearColor (0.0, 0.0, 0.0, 0.0);
		glShadeModel(GL_FLAT);
		makeCheckImage();
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
	}
	void display()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glRasterPos2i(0, 0);
		glDrawPixels(checkImageWidth, checkImageHeight, GL_RGBA,GL_UNSIGNED_BYTE, checkImage);
		glFlush();
	}
	void reshape(int w, int h)
	{
		glViewport(0, 0,  w,  h);
		height = (int) h;
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0.0, (double) w, 0.0, (double) h);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	void motion(int x, int y)
	{
		int screeny;
		screeny = height - (int) y;
		glRasterPos2i (x, screeny);
		glPixelZoom (zoomFactor, zoomFactor);
		glCopyPixels (0, 0, checkImageWidth, checkImageHeight,
		GL_COLOR);
		glPixelZoom (1.0, 1.0);
		glFlush ();
	}
	void keyboard(char key, int x, int y)
	{
		switch (key) {
		case 'r':
		case 'R':
		zoomFactor = 1.0;
		glutPostRedisplay();
		System.out.printf ("zoomFactor reset to 1.0\n");
		break;
		case 'z':
		zoomFactor += 0.5;
		if (zoomFactor >= 3.0)
		zoomFactor = 3.0;
		System.out.printf ("zoomFactor is now %4.1f\n", zoomFactor);
		break;
		case 'Z':
		zoomFactor -= 0.5;
		if (zoomFactor <= 0.5)
		zoomFactor = 0.5;
		System.out.printf ("zoomFactor is now %4.1f\n", zoomFactor);
		break;
		case 27:
		System.exit(0);
		break;
		default:
		break;
		}
	}
	int main(int argc, String[] argv)
	{
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
		glutInitWindowSize(250, 250);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("image");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMotionFunc(this::motion);
		glutMainLoop();
		return 0;
	}
	public static void main(String[] args) {
		System.exit(new image().main(args.length, args));
	}
}
