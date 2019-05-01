package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_template2D extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("template2D"));
		new Thread(() -> {
			template2D template2DObject = new template2D();
			String argv[] = new String[0];
			template2DObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("template2D"));

	}

}
