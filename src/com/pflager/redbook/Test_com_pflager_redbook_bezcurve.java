package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_bezcurve extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("bezcurve"));
		new Thread(() -> {
			bezcurve bezcurveObject = new bezcurve();
			String argv[] = new String[0];
			bezcurveObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("bezcurve"));

	}

}
