package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_bezcurve extends ImageCompareJNA {

//	@Test
//	void test() {
//		assertTrue(CaptureCImage("bezcurve"));
//		
//		bezcurve bezcurveObject = new bezcurve();
//		new Thread(() -> {
//			String argv[] = new String[0];
//			bezcurveObject.main(0, argv);
//		}).start();
//		assertTrue(CompareImageSec("bezcurve"));
//	}
	
	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("bezcurve"));
//		ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, bezcurve.class.getName());
//		Process process = builder.inheritIO().start();
		RunNewProcess(bezcurve.class.getName());
		assertTrue(CompareImageSec("bezcurve"));
		//process.destroy();
		super.finalize( );  
	}

}
