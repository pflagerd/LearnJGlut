package com.pflager.redbook.akp;

import com.pflager.glut;

public class texsub extends glut
{
	
	//#ifdef GL_VERSION_1_1
	//final boolean GL_VERSION_1_1 = false;
	
	 int checkImageWidth =64;
	 int checkImageHeight =64;
	 int subImageWidth =16;
	 int subImageHeight =16;
	
	//static GLubyte checkImage[checkImageHeight][checkImageWidth][4];
	//final byte checkImage[][][]= new byte[checkImageHeight][checkImageWidth][4];
	byte checkImage[]=new byte[checkImageHeight*checkImageWidth*4];
	byte subImage[]=new byte[subImageHeight*subImageWidth*4];

	//static GLubyte subImage[subImageHeight][subImageWidth][4];
	//final byte subImage[][][]= new byte[subImageHeight][subImageWidth][4];
	static int texName[] = new int[1];

	void makeCheckImages()
	{
	   int i, j, c;
	    
	   for (i = 0; i < checkImageHeight; i++) {
	      for (j = 0; j < checkImageWidth; j++) {
	    	  c = (((i & 0x8) == 0 ? 1 : 0) ^ ((j & 0x8) == 0 ? 1 : 0)) * 255;
	    	  checkImage [i * checkImageWidth * 4 + j * 4 + 0] = (byte) c;
	  		checkImage[i * checkImageWidth * 4 + j * 4 + 1] = (byte) c;
			checkImage[i * checkImageWidth * 4 + j * 4 + 2] = (byte) c;
			checkImage[i * checkImageWidth * 4 + j * 4 + 3] = (byte) 255;

	      }
	   }
	   for (i = 0; i < subImageHeight; i++) {
	      for (j = 0; j < subImageWidth; j++) {
	         c = (((i & 0x4) == 0 ? 1 : 0) ^ ((j & 0x4) == 0 ? 1 : 0)) * 255;
	         subImage [i  * subImageWidth *4 + j * 4 + 0] = (byte) c;
	 		subImage [i  * subImageWidth *4 + j * 4 + 1] = (byte) 0;
	 		subImage [i  * subImageWidth *4 + j * 4 + 2] = (byte) 0;
	 		subImage [i  * subImageWidth *4 + j * 4 + 3] = (byte) 255;
	      }
	   }
	}

	void init()
	{    
	   glClearColor (0.0, 0.0, 0.0, 0.0);
	   glShadeModel(GL_FLAT);
	   glEnable(GL_DEPTH_TEST);

	   makeCheckImages();
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
	   glViewport(0, 0, w,  h);
	   glMatrixMode(GL_PROJECTION);
	   glLoadIdentity();
	   gluPerspective(60.0,  w/h, 1.0, 30.0);
	   glMatrixMode(GL_MODELVIEW);
	   glLoadIdentity();
	   glTranslatef(0.0, 0.0, -3.6);
	}

	void keyboard ( char key, int x, int y)
	{
	   switch (key) {
	      case 's':
	      case 'S':
	         glBindTexture(GL_TEXTURE_2D, texName[0]);
	         glTexSubImage2D(GL_TEXTURE_2D, 0, 12, 44, subImageWidth,
	                         subImageHeight, GL_RGBA,
	                         GL_UNSIGNED_BYTE, subImage);
	         glutPostRedisplay();
	         break;
	      case 'r':
	      case 'R':
	         glBindTexture(GL_TEXTURE_2D, texName[0]);
	         glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, checkImageWidth,
	                      checkImageHeight, 0, GL_RGBA,
	                      GL_UNSIGNED_BYTE, checkImage);
	         glutPostRedisplay();
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
	   glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	   glutInitWindowSize(250, 250);
	   glutInitWindowPosition(100, 100);
	   glutCreateWindow("texsub");
	   init();
	   glutDisplayFunc(this::display);
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc(this::keyboard);
	   glutMainLoop();
	   return 0; 
	}
	
	public static void main(String[] args) {
		System.exit(new texsub().main(args.length, args));
	}
	
	/*else
	int main(int argc, String[] argv)
	{
	    //fprintf (stderr, "This program demonstrates a feature which is not in OpenGL Version 1.0.\n");
	    //fprintf (stderr, "If your implementation of OpenGL Version 1.0 has the right extensions,\n");
	    //fprintf (stderr, "you may be able to modify this program to make it run.\n");
	    return 0;
	}*/
}
