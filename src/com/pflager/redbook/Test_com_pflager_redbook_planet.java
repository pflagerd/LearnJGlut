package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_planet extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("planet"));
		planet planetObject = new planet();
		new Thread(() -> {
			String argv[] = new String[0];
			planetObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("planet"));
		planetObject.glutLeaveMainLoop();
	}

}
