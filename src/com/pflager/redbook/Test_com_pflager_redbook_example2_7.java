package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_example2_7 extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("example2_7"));
		new Thread(() -> {
			example2_7 example2_7Object = new example2_7();
			String argv[] = new String[0];
			example2_7Object.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("example2_7"));

	}

}
