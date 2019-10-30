package com.pflager.redbook.akp;

import com.pflager.glut;

public class texgen extends glut{

	 int	stripeImageWidth = 32;
	 byte stripeImage[]= new byte[4*stripeImageWidth];

	 int texName[]=new int[1];

	 void makeStripeImage()
		{
		   int j;
		    
		   for (j = 0; j < stripeImageWidth; j++) {
		      stripeImage[4*j] = (byte) ((j<=4) ? 255 : 0);
		      stripeImage[4*j+1] = (byte) ((j>4) ? 255 : 0);
		      stripeImage[4*j+2] = (byte) 0;
		      stripeImage[4*j+3] = (byte) 255;
		   }
		}
		/*  planes for texture coordinate generation  */
		double xequalzero[] = {1.0, 0.0, 0.0, 0.0};
		double slanted[] = {1.0, 1.0, 1.0, 0.0};
		double currentCoeff[];
		int currentPlane;
		int currentGenMode;
		
		void init()
		{
		   glClearColor (0.0, 0.0, 0.0, 0.0);
		   glEnable(GL_DEPTH_TEST);
		   glShadeModel(GL_SMOOTH);

		   makeStripeImage();
		   glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		
		   glGenTextures(1, texName);
		   glBindTexture(GL_TEXTURE_1D, texName[0]);
		
		   glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		   glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		   glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		   glTexImage1D(GL_TEXTURE_1D, 0, GL_RGBA, stripeImageWidth, 0, GL_RGBA, GL_UNSIGNED_BYTE, stripeImage);
		   glTexImage1D(GL_TEXTURE_1D, 0, 4, stripeImageWidth, 0,GL_RGBA, GL_UNSIGNED_BYTE, stripeImage);

		   glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		   currentCoeff = xequalzero;
		   currentGenMode = GL_OBJECT_LINEAR;
		   currentPlane = GL_OBJECT_PLANE;
		   glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, currentGenMode);
		   glTexGenfv(GL_S, currentPlane, currentCoeff);
		   

		   glEnable(GL_TEXTURE_GEN_S);
		   glEnable(GL_TEXTURE_1D);
		   glEnable(GL_CULL_FACE);
		   glEnable(GL_LIGHTING);
		   glEnable(GL_LIGHT0);
		   glEnable(GL_AUTO_NORMAL);
		   glEnable(GL_NORMALIZE);
		   glFrontFace(GL_CW);
		   glCullFace(GL_BACK);
		   glMaterialf (GL_FRONT, GL_SHININESS, 64.0);
		}
		
		void display()
		{
		   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		   glPushMatrix ();
		   glRotatef(45.0, 0.0, 0.0, 1.0);
		   glBindTexture(GL_TEXTURE_1D, texName[0]);
		   glutSolidTeapot(2.0);
		   glPopMatrix ();
		   glFlush();
		}
		
		void reshape(int w, int h)
		{
		   glViewport(0, 0,  w,  h);
		   glMatrixMode(GL_PROJECTION);
		   glLoadIdentity();
		   if (w <= h)
		      glOrtho (-3.5, 3.5, -3.5*(double)h/(double)w, 3.5*(double)h/(double)w, -3.5, 3.5);
		   else
		      glOrtho (-3.5*(double)w/(double)h, 3.5*(double)w/(double)h, -3.5, 3.5, -3.5, 3.5);
		   glMatrixMode(GL_MODELVIEW);
		   glLoadIdentity();
		}
		void keyboard (char key, int x, int y)
		{
		   switch (key) {
		      case 'e':
		      case 'E':
		         currentGenMode = GL_EYE_LINEAR;
		         currentPlane = GL_EYE_PLANE;
		         glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, currentGenMode);
		         glTexGenfv(GL_S, currentPlane, currentCoeff);
		         glutPostRedisplay();
		         break;
		      case 'o':
		      case 'O':
		         currentGenMode = GL_OBJECT_LINEAR;
		         currentPlane = GL_OBJECT_PLANE;
		         glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, currentGenMode);
		         glTexGenfv(GL_S, currentPlane, currentCoeff);
		         glutPostRedisplay();
		         break;
		      case 's':
		      case 'S':
		         currentCoeff = slanted;
		         glTexGenfv(GL_S, currentPlane, currentCoeff);
		         glutPostRedisplay();
		         break;
		      case 'x':
		      case 'X':
		         currentCoeff = xequalzero;
		         glTexGenfv(GL_S, currentPlane, currentCoeff);
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
		   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
		   glutInitWindowSize(256, 256);
		   glutInitWindowPosition(100, 100);
		   glutCreateWindow ("texgen");
		   init ();
		   glutDisplayFunc(this::display);
		   glutReshapeFunc(this::reshape);
		   glutKeyboardFunc(this::keyboard);
		   glutMainLoop();
		   return 0;
		}
		public static void main(String[] args) {
			System.exit(new texgen().main(args.length, args));

		}
}
