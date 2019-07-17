package com.pflager.redbook.vtrg;

import com.pflager.glut;

public class mipmap extends glut{

	byte mipmapImage32[][][]=new byte[32][32][4];
	byte mipmapImage16[][][]=new byte[16][16][4];
	byte mipmapImage8[][][]=new byte[8][8][4];
	byte mipmapImage4[][][]=new byte[4][4][4];
	byte mipmapImage2[][][]=new byte[2][2][4];
	byte mipmapImage1[][][]=new byte[1][1][4];
	static int texName[]=new int[1];
	
	void makeImages()
	{
		int i, j;
		for (i = 0; i < 32; i++) {
			for (j = 0; j < 32; j++) {
				mipmapImage32[i][j][0] = (byte)255;
				mipmapImage32[i][j][1] = (byte)255;
				mipmapImage32[i][j][2] = (byte)0;
				mipmapImage32[i][j][3] = (byte)255;
			}
		}
		for (i = 0; i < 16; i++) {
			for (j = 0; j < 16; j++) {
				mipmapImage16[i][j][0] = (byte)255;
				mipmapImage16[i][j][1] = (byte)0;
				mipmapImage16[i][j][2] = (byte)255;
				mipmapImage16[i][j][3] = (byte)255;
			}
		}
	for (i = 0; i < 8; i++) {
		for (j = 0; j < 8; j++) {
			mipmapImage8[i][j][0] = (byte)255;
			mipmapImage8[i][j][1] = (byte)0;
			mipmapImage8[i][j][2] = (byte)0;
			mipmapImage8[i][j][3] = (byte)255;
		}
	}
	
		for (i = 0; i < 4; i++) {
				for (j = 0; j < 4; j++) {
					mipmapImage4[i][j][0] = (byte)0;
					mipmapImage4[i][j][1] = (byte)255;
					mipmapImage4[i][j][2] = (byte)0;
					mipmapImage4[i][j][3] = (byte)255;
				}
		}
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 2; j++) {
				mipmapImage2[i][j][0] =(byte) 0;
				mipmapImage2[i][j][1] = (byte)0;
				mipmapImage2[i][j][2] = (byte)255;
				mipmapImage2[i][j][3] = (byte)255;
			}
		}
		mipmapImage1[0][0][0] = (byte)255;
		mipmapImage1[0][0][1] = (byte)255;
		mipmapImage1[0][0][2] = (byte)255;
		mipmapImage1[0][0][3] = (byte)255;
	}
	
	
	void init()
	{
		glEnable(GL_DEPTH_TEST);
		glShadeModel(GL_FLAT);
		glTranslatef(0.0, 0.0, -3.6);
		makeImages();
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glGenTextures(1, texName);
		glBindTexture(GL_TEXTURE_2D, texName[0]);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER,
		GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,
		GL_NEAREST_MIPMAP_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 32, 32, 0,GL_RGBA, GL_UNSIGNED_BYTE,flatten( mipmapImage32));
		glTexImage2D(GL_TEXTURE_2D, 1, GL_RGBA, 16, 16, 0,GL_RGBA, GL_UNSIGNED_BYTE,flatten( mipmapImage16));
		glTexImage2D(GL_TEXTURE_2D, 2, GL_RGBA, 8, 8, 0,GL_RGBA, GL_UNSIGNED_BYTE, flatten(mipmapImage8));
		glTexImage2D(GL_TEXTURE_2D, 3, GL_RGBA, 4, 4, 0,GL_RGBA, GL_UNSIGNED_BYTE,flatten( mipmapImage4));
		glTexImage2D(GL_TEXTURE_2D, 4, GL_RGBA, 2, 2, 0,GL_RGBA, GL_UNSIGNED_BYTE,flatten( mipmapImage2));
		glTexImage2D(GL_TEXTURE_2D, 5, GL_RGBA, 1, 1, 0,GL_RGBA, GL_UNSIGNED_BYTE,flatten( mipmapImage1));
		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
		glEnable(GL_TEXTURE_2D);
	}
	void display()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBindTexture(GL_TEXTURE_2D, texName[0]);
		glBegin(GL_QUADS);
		glTexCoord2f(0.0, 0.0); glVertex3f(-2.0, -1.0, 0.0);
		glTexCoord2f(0.0, 8.0); glVertex3f(-2.0, 1.0, 0.0);
		glTexCoord2f(8.0, 8.0); glVertex3f(2000.0, 1.0, -6000.0);
		glTexCoord2f(8.0, 0.0); glVertex3f(2000.0, -1.0, -6000.0);
		glEnd();
		glFlush();
	}
	void reshape(int w, int h)
	{
		glViewport(0, 0,  w,  h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60.0, (double)w/(double)h, 1.0, 30000.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	void keyboard ( char key, int x, int y)
	{
		switch (key) {
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
		glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
		glutInitWindowSize(500, 500);
		glutInitWindowPosition(50, 50);
		glutCreateWindow("mipmap");
		init();
		glutDisplayFunc(this::display);
		glutReshapeFunc(this::reshape);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}
	public static void main(String[] args) {
		System.exit(new mipmap().main(args.length, args));
	}
}
