package com.pflager.redbook.akp;

import com.pflager.glut;

public class checker extends glut{

	/*	Create checkerboard texture	*/
	static int checkImageWidth = 64;
	static int checkImageHeight = 64;
	static byte checkImage[]= new byte[checkImageHeight*checkImageWidth*4];

	static int texName[]=new int[1];


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
	   glEnable(GL_DEPTH_TEST);

	   makeCheckImage();
	   glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

	   glGenTextures(1, texName);
	   glBindTexture(GL_TEXTURE_2D, texName[0]);


	   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	   glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, checkImageWidth, checkImageHeight, 
	                0, GL_RGBA, GL_UNSIGNED_BYTE, checkImage);
	

	}

	void display()
	{
	   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	   glEnable(GL_TEXTURE_2D);
	   glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
	   glBindTexture(GL_TEXTURE_2D, texName[0]);
	

	   glBegin(GL_QUADS);
	   glTexCoord2f(0.0, 0.0); glVertex3f(-2.0, -1.0, 0.0);
	   glTexCoord2f(0.0, 1.0); glVertex3f(-2.0, 1.0, 0.0);
	   glTexCoord2f(1.0, 1.0); glVertex3f(0.0, 1.0, 0.0);
	   glTexCoord2f(1.0, 0.0); glVertex3f(0.0, -1.0, 0.0);

	   glTexCoord2f(0.0, 0.0); glVertex3f(1.0, -1.0, 0.0);
	   glTexCoord2f(0.0, 1.0); glVertex3f(1.0, 1.0, 0.0);
	   glTexCoord2f(1.0, 1.0); glVertex3f(2.41421, 1.0, -1.41421);
	   glTexCoord2f(1.0, 0.0); glVertex3f(2.41421, -1.0, -1.41421);
	   glEnd();
	   glFlush();
	   glDisable(GL_TEXTURE_2D);
	}

	void reshape(int w, int h)
	{
	   glViewport(0, 0,w,h);
	   glMatrixMode(GL_PROJECTION);
	   glLoadIdentity();
	   gluPerspective(60.0,  w/ h, 1.0, 30.0);
	   glMatrixMode(GL_MODELVIEW);
	   glLoadIdentity();
	   glTranslatef(0.0, 0.0, -3.6);
	}

	void keyboard (char key, int x, int y)
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
	   glutInitWindowSize(250, 250);
	   glutInitWindowPosition(100, 100);
	   glutCreateWindow("checker");
	   init();
	   glutDisplayFunc(this::display);
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0; 
	}
	public static void main(String[] args) {
		System.exit(new checker().main(args.length, args));
	}
}
