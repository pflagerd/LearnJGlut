package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_varray extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("varray"));
		new Thread(() -> {
			varray varrayObject = new varray();
			String argv[] = new String[0];
			varrayObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("varray"));

	}

}
