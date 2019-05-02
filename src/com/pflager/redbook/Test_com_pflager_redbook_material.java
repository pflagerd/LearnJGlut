package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_material extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("material"));
		material materialObject = new material();
		new Thread(() -> {
			String argv[] = new String[0];
			materialObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("material"));
		materialObject.glutLeaveMainLoop();
	}

}
