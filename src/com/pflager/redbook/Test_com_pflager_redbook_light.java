package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_light extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("light"));
		light lightObject = new light();
		new Thread(() -> {
			String argv[] = new String[0];
			lightObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("light"));
		lightObject.glutLeaveMainLoop();
	}

}
