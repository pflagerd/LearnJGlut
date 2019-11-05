package org.pflager.redbook.vtrg;

import com.pflager.glut;

public class texturesurf extends glut{

		double ctrlpoints[][][] = {
			   {{ -1.5, -1.5, 4.0}, { -0.5, -1.5, 2.0}, 
			    {0.5, -1.5, -1.0}, {1.5, -1.5, 2.0}}, 
			   {{ -1.5, -0.5, 1.0}, { -0.5, -0.5, 3.0}, 
			    {0.5, -0.5, 0.0}, {1.5, -0.5, -1.0}}, 
			   {{ -1.5, 0.5, 4.0}, { -0.5, 0.5, 0.0}, 
			    {0.5, 0.5, 3.0}, {1.5, 0.5, 4.0}}, 
			   {{ -1.5, 1.5, -2.0}, { -0.5, 1.5, -2.0}, 
			    {0.5, 1.5, 0.0}, {1.5, 1.5, -1.0}}
			};

		double texpts[][][] = {{{0.0, 0.0}, {0.0, 1.0}}, 
									{{1.0, 0.0}, {1.0, 1.0}}};
		
		int imageWidth=64;
		int	imageHeight=64;
		byte image[]=new byte[3*imageWidth*imageHeight];
		
		void display()
		{
		   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		   glColor3f(1.0, 1.0, 1.0);
		   glEvalMesh2(GL_FILL, 0, 20, 0, 20);
		   glFlush();
		}
		void makeImage()
		{
		   int i, j;
		   double ti, tj;
		   
		   for (i = 0; i < imageWidth; i++) {
		      ti = 2.0*3.14159265*i/imageWidth;
		      for (j = 0; j < imageHeight; j++) {
		         tj = 2.0*3.14159265*j/imageHeight;

		         image[3*(imageHeight*i+j)] = (byte) ((byte)127*(1.0+Math.sin(ti)));
		         image[3*(imageHeight*i+j)+1] = (byte) ((byte)127*(1.0+Math.cos(2*tj)));
		         image[3*(imageHeight*i+j)+2] = (byte) ((byte)127*(1.0+Math.cos(ti+tj)));
		      }
		   }
		}
		
		void init()
		{
		   glMap2f(GL_MAP2_VERTEX_3, 0, 1, 3, 4,0, 1, 12, 4, ctrlpoints);
		   glMap2f(GL_MAP2_TEXTURE_COORD_2, 0, 1, 2, 2, 0, 1, 4, 2, texpts);
		   glEnable(GL_MAP2_TEXTURE_COORD_2);
		   glEnable(GL_MAP2_VERTEX_3);
		   glMapGrid2f(20, 0.0, 1.0, 20, 0.0, 1.0);
		   makeImage();
		   glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
		   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		   glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, imageWidth, imageHeight, 0,GL_RGB, GL_UNSIGNED_BYTE, image);
		   glEnable(GL_TEXTURE_2D);
		   glEnable(GL_DEPTH_TEST);
		   glShadeModel (GL_FLAT);
		}

		void reshape(int w, int h)
		{
		   glViewport(0, 0,  w,  h);
		   glMatrixMode(GL_PROJECTION);
		   glLoadIdentity();
		   if (w <= h)
		      glOrtho(-4.0, 4.0, -4.0*(double)h/(double)w, 4.0*(double)h/(double)w, -4.0, 4.0);
		   else
		      glOrtho(-4.0*(double)w/(double)h, 4.0*(double)w/(double)h, -4.0, 4.0, -4.0, 4.0);
		   glMatrixMode(GL_MODELVIEW);
		   glLoadIdentity();
		   glRotatef(85.0, 1.0, 1.0, 1.0);
		}

		void keyboard( char key, int x, int y)
		{
		   switch (key) {
		      case 27:
		         System.exit(0);
		         break;
		   }
		}

		int main(int argc, String[] argv)
		{
		   glutInit(argc, argv);
		   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
		   glutInitWindowSize (500, 500);
		   glutInitWindowPosition (100, 100);
		   glutCreateWindow ("teturesurf");
		   init ();
		   glutDisplayFunc(this::display);
		   glutReshapeFunc(this::reshape);
		   glutKeyboardFunc(this::keyboard);
		   glutMainLoop();
		   return 0;
		}
		public static void main(String[] args) {
			System.exit(new texturesurf().main(args.length, args));

		}
}
