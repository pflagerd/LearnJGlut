package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_robot extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("robot"));
		robot robotObject = new robot();
		new Thread(() -> {
			String argv[] = new String[0];
			robotObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("robot"));
		robotObject.glutLeaveMainLoop();
	}

}
