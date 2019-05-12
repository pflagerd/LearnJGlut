package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_bezsurf extends ImageCompareJNA {

//	@Test
//	void test() {
//		assertTrue(CaptureCImage("bezsurf"));
//		bezsurf bezsurfObject = new bezsurf();
//		new Thread(() -> {
//			String argv[] = new String[0];
//			bezsurfObject.main(0, argv);
//		}).start();
//		assertTrue(CompareImageSec("bezsurf"));
//		bezsurfObject.glutLeaveMainLoop();
//	}
	
	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("bezsurf"));
		RunNewProcess(bezsurf.class.getName());
//		ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, bezsurf.class.getName());
//		Process process = builder.inheritIO().start();
		assertTrue(CompareImageSec("bezsurf"));
		//process.destroy();
		 super.finalize( );  
	}

}
