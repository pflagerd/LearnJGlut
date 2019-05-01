package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_bezsurf extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("bezsurf"));
		new Thread(() -> {
			bezsurf bezsurfObject = new bezsurf();
			String argv[] = new String[0];
			bezsurfObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("bezsurf"));

	}

}
