package org.pflager.redbook;

import com.pflager.glut;

// Example 2-9
public class template3D extends glut {

	final int CONE = 1;
	final int CUBE = 2;
	final int DODECAHEDRON = 3;
	final int ICOSAHEDRON = 4;
	final int OCTAHEDRON = 5;
	final int SPHERE = 6;
	final int TEAPOT = 7;
	final int TETRAHEDRON = 8;
	final int TORUS = 9;

	int shape = CONE;
	double [] rotation_angle = new double[] {0.0, 0.0, 0.0};
	double [] rotation_rate = new double[] {Math.PI / 20.0, Math.PI / 20.0, Math.PI / 20.0};
	
	void idle() {
		rotation_angle[0] += rotation_rate[0];
		rotation_angle[1] += rotation_rate[1];
		rotation_angle[2] += rotation_rate[2];
		display(); // Come back here and figure out how to do constant rate.
	}
	
	void init() {
		glClearColor(0.0, 0.0, 0.0, 0.0);
		glEnable(GL_LINE_SMOOTH);
		glShadeModel(GL_SMOOTH);
	}

	public void display() {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3d(1.0, 1.0, 1.0);
		
		glPushMatrix();
		glRotated(rotation_angle[0], 1.0, 0.0, 0.0);
		glRotated(rotation_angle[1], 0.0, 1.0, 0.0);
		glRotated(rotation_angle[2], 0.0, 0.0, 1.0);

		switch (shape) {
			case CONE:				
				glutWireCone(1.0, 2.0, 16, 16);
				break;
			case CUBE:
				glutWireCube(2.0);
				break;
			case DODECAHEDRON:
				glutWireDodecahedron();
				break;
			case ICOSAHEDRON:
				glutWireIcosahedron();
				break;
			case OCTAHEDRON:
				glutWireOctahedron();
				break;
			case SPHERE:
				glutWireSphere(2.0, 16, 16);
				break;
			case TEAPOT:
				glutWireTeapot(1.0);
				break;
			case TETRAHEDRON:
				glutWireTetrahedron();
				break;
			case TORUS:
				glutWireTorus(0.4, 1.6, 32, 64);
				break;
			default:
				return;
		}
		glPopMatrix();

		glutSwapBuffers();
	}

	void reshape(int w, int h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-2.1, 2.1, -2.1, 2.1, -2.1, 2.1); // 2.1 is big enough to accommodate \sqrt{3} of Dodecahedron, allows even 1, 2 for others.
	}

	void mouse(int button, int state, int x, int y) {
		switch (button) {
			case GLUT_LEFT_BUTTON:
				if (state == GLUT_DOWN) {
					shape = shape > CONE?  shape - 1 : TORUS;
				}
				break;
			case GLUT_MIDDLE_BUTTON:
			case GLUT_RIGHT_BUTTON:
				if (state == GLUT_DOWN) {
					shape = shape < TORUS ? shape + 1 : CONE;
				}
				break;
			default:
				break;
		}
	}

	void keyboard(char key, int x, int y) {
		switch (key) {
			case 27:
				System.exit(0);
				break;
				
			case 'X':
				rotation_rate[0] *= 2.0;
				break;
				
			case 'x':
				rotation_rate[0] /= 2.0;
				break;
				
			case 'Y':
				rotation_rate[1] *= 2.0;
				break;
				
			case 'y':
				rotation_rate[1] /= 2.0;
				break;
				
			case 'Z':
				rotation_rate[2] *= 2.0;
				break;
				
			case 'z':
				rotation_rate[2] /= 2.0;
				break;
				
			default:
				break;				
		}
	}

	public int main(int argc, String[] argv) {
		glutInit(argc, argv);
		glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
		glutInitWindowSize(350, 350);
		glutInitWindowPosition(100, 100);
		glutCreateWindow("template3D.java");
		init();
		glutDisplayFunc(this::display);
		glutIdleFunc(this::idle);
		glutReshapeFunc(this::reshape);
		glutMouseFunc(this::mouse);
		glutKeyboardFunc(this::keyboard);
		glutMainLoop();
		return 0;
	}

	public static void main(String[] args) {
		System.exit(new template3D().main(args.length, args));
	}

}
