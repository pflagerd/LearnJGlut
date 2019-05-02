package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_bezcurve extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("bezcurve"));
		
		bezcurve bezcurveObject = new bezcurve();
		bezcurveObject.glutLeaveMainLoop();
		new Thread(() -> {
			String argv[] = new String[0];
			bezcurveObject.main(0, argv);
			bezcurveObject.glutMainLoop();
		}).start();
		assertTrue(CompareImageSec("bezcurve"));
		bezcurveObject.glutLeaveMainLoop();
	}

}
