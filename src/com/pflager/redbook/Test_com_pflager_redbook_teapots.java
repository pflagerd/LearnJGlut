package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_teapots extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("teapots"));
		teapots teapotsObject = new teapots();
		new Thread(() -> {
			String argv[] = new String[0];
			teapotsObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("teapots"));
		teapotsObject.glutLeaveMainLoop();
	}

}
