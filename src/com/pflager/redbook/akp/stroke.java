package com.pflager.redbook.akp;

import com.pflager.glut;

public class stroke extends glut{

	final int PT = 1;
	final int  STROKE = 2;
	final int  END = 3;

//	typedef struct charpoint {
//	   GLfloat   x, y;
//	   int    type;
//	} CP;
	
	class CP {
		public CP(float x, float y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
		
		float x, y;
		int type;
	};
	
	CP Adata[] = { new CP(0, 0, PT), new CP(0, 9, PT), new CP(1, 10, PT),
			new CP(4, 10, PT), new CP(5, 9, PT), new CP(5, 0, STROKE), new CP(0, 5, PT),
			new CP(5, 5, END) };

	CP Edata[] = { new CP(5, 0, PT), new CP(0, 0, PT), new CP(0, 10, PT),
			new CP(5, 10, STROKE), new CP(0, 5, PT), new CP(4, 5, END) };

	CP Pdata[] = { new CP(0, 0, PT), new CP(0, 10, PT), new CP(4, 10, PT),
			new CP(5, 9, PT), new CP(5, 6, PT), new CP(4, 5, PT), new CP(0, 5, END) };

	CP Rdata[] = { new CP(0, 0, PT), new CP(0, 10, PT), new CP(4, 10, PT),
			new CP(5, 9, PT), new CP(5, 6, PT), new CP(4, 5, PT), new CP(0, 5, STROKE),
			new CP(3, 5, PT), new CP(5, 0, END) };

	CP Sdata[] = { new CP(0, 1, PT), new CP(1, 0, PT), new CP(4, 0, PT),
			new CP(5, 1, PT), new CP(5, 4, PT), new CP(4, 5, PT), new CP(1, 5, PT),
			new CP(0, 6, PT), new CP(0, 9, PT), new CP(1, 10, PT), new CP(4, 10, PT),
			new CP(5, 9, END) };

	/*  drawLetter() interprets the instructions from the array
	 *  for that letter and renders the letter with line segments.
	 */
	void drawLetter(CP l[])
	{
		int i = 0;
	   glBegin(GL_LINE_STRIP);
	   while (i < l.length) {
		   float[] m = {l[i].x, l[i].y};
	      switch (l[i].type) {
	         case PT:
	            glVertex2fv(m);
	            break;
	         case STROKE:
	            glVertex2fv(m);
	            glEnd();
	            glBegin(GL_LINE_STRIP);
	            break;
	         case END:
	            glVertex2fv(m);
	            glEnd();
	            glTranslatef(8.0, 0.0, 0.0);
	            return;
	      }
	      i++;
	   }
	}

	/*  Create a display list for each of 6 characters	*/
	void init ()
	{
	   int base;

	   glShadeModel (GL_FLAT);

	   base = glGenLists (128);
	   glListBase(base);
	   glNewList(base+'A', GL_COMPILE); drawLetter(Adata); glEndList();
	   glNewList(base+'E', GL_COMPILE); drawLetter(Edata); glEndList();
	   glNewList(base+'P', GL_COMPILE); drawLetter(Pdata); glEndList();
	   glNewList(base+'R', GL_COMPILE); drawLetter(Rdata); glEndList();
	   glNewList(base+'S', GL_COMPILE); drawLetter(Sdata); glEndList();
	   glNewList(base+' ', GL_COMPILE); glTranslatef(8.0, 0.0, 0.0); glEndList();
	}

	String test1 = "A SPARE SERAPE APPEARS AS";
	String test2 = "APES PREPARE RARE PEPPERS";

	void printStrokedString(String s)
	{
	   int len = s.length();
	   
	   for (int chr = 0; chr < len; chr++) {
		   glCallList(((byte) s.charAt(chr) + 1));
	   }
	}

	void display()
	{
	   glClear(GL_COLOR_BUFFER_BIT);
	   glColor3f(1.0, 1.0, 1.0);
	   glPushMatrix();
	   glScalef(2.0, 2.0, 2.0);
	   glTranslatef(10.0, 30.0, 0.0);
	   printStrokedString(test1);
	   glPopMatrix();
	   glPushMatrix();
	   glScalef(2.0, 2.0, 2.0);
	   glTranslatef(10.0, 13.0, 0.0);
	   printStrokedString(test2);
	   glPopMatrix();
	   glFlush();
	}

	void reshape(int w, int h)
	{
	   glViewport(0, 0,  w, h);
	   glMatrixMode (GL_PROJECTION);
	   glLoadIdentity ();
	   gluOrtho2D (0.0, w, 0.0, h);
	}

	void keyboard(char key, int x, int y)
	{
	   switch (key) {
	      case ' ':
	         glutPostRedisplay();
	         break;
	      case 27:
	         System.exit(0);
	   }
	}

	/*  Main Loop
	 *  Open window with initial window size, title bar, 
	 *  RGBA display mode, and handle input events.
	 */
	int main(int argc, String[] argv)
	{
	   glutInit(argc, argv);
	   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
	   glutInitWindowSize (440,600);
	   glutCreateWindow ("stroke");
	   init ();
	   glutReshapeFunc(this::reshape);
	   glutKeyboardFunc(this::keyboard);
	   glutDisplayFunc(this::display);
	   glutMainLoop();
	   return 0;
	}

	public static void main(String[] args) {
		System.exit(new stroke().main(args.length, args));
	}

}
