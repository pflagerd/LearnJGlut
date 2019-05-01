package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_smooth4_1 extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("smooth4_1"));
		new Thread(() -> {
			smooth4_1 smooth4_1Object = new smooth4_1();
			String argv[] = new String[0];
			smooth4_1Object.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("smooth4_1"));

	}

}
