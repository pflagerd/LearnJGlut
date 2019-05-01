package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_smooth extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("smooth"));
		new Thread(() -> {
			smooth smoothObject = new smooth();
			String argv[] = new String[0];
			smoothObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("smooth"));

	}

}
