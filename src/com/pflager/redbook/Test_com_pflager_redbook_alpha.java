package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_alpha extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("alpha"));
		alpha alphaObject = new alpha();
		new Thread(() -> {
			String argv[] = new String[0];
			alphaObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("alpha"));
		alphaObject.glutLeaveMainLoop();
	}

}
