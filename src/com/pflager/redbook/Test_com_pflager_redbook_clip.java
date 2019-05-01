package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_clip extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("clip"));
		new Thread(() -> {
			clip clipObject = new clip();
			String argv[] = new String[0];
			clipObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("clip"));

	}

}
