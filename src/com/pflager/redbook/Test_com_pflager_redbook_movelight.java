package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_movelight extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("movelight"));
		movelight movelightObject = new movelight();
		new Thread(() -> {
			String argv[] = new String[0];
			movelightObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("movelight"));
		movelightObject.glutLeaveMainLoop();
	}

}
