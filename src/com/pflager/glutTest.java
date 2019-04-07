package com.pflager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class glutTest extends glut {
	
	public void singleShotDisplayTest(DisplayFunc displayFunc) throws InterruptedException, IOException {
		String thisFullyQualifiedMethodName = new Throwable().getStackTrace()[1].getClassName() + "." + new Throwable().getStackTrace()[1].getMethodName();
		
		Thread thread = new Thread() {
			public void run() {
				glutInit(new String[] {thisFullyQualifiedMethodName});
				glutSet(GLUT_ACTION_ON_WINDOW_CLOSE, GLUT_ACTION_GLUTMAINLOOP_RETURNS);
				glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
				glutInitWindowSize(400, 400);
				glutInitWindowPosition(100, 100);
				glutCreateWindow(thisFullyQualifiedMethodName);
				glClearColor(0.0, 0.0, 0.0, 0.0);
				glShadeModel(GL_FLAT);
				glutDisplayFunc(displayFunc);
				glutMainLoop();
			}
		};
		thread.start();
		thread.join();
		System.out.println(thisFullyQualifiedMethodName);
		assertTrue(FileUtils.contentEquals(new File("artifacts/tmp.png"), new File("artifacts/" + thisFullyQualifiedMethodName + ".png")));
	}

}
