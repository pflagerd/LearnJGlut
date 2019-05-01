package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_colormat extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("colormat"));
		new Thread(() -> {
			colormat colormatObject = new colormat();
			String argv[] = new String[0];
			colormatObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("colormat"));

	}

}
