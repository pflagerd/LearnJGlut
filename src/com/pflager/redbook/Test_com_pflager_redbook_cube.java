package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_cube extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("cube"));
		cube cubeObject = new cube();
		new Thread(() -> {
			String argv[] = new String[0];
			cubeObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("cube"));
		cubeObject.glutLeaveMainLoop();
	}

}
