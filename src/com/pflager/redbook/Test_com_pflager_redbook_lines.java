package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_lines extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("lines"));
		new Thread(() -> {
			lines linesObject = new lines();
			String argv[] = new String[0];
			linesObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("lines"));

	}

}
