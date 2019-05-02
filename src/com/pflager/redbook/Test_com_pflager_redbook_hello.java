package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_hello extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("hello"));
		hello helloObject = new hello();
		
		new Thread(() -> {
			String argv[] = new String[0];
			helloObject.main(0, argv);
			helloObject.glutMainLoop();
		}).start();
		assertTrue(CompareImageSec("hello"));
		helloObject.glutLeaveMainLoop();
	}

}
