package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_hello extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("hello"));
		new Thread(() -> {
			hello helloObject = new hello();
			String argv[] = new String[0];
			helloObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("hello"));

	}

}
