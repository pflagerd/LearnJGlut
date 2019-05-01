package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_materialf extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("materialf"));
		new Thread(() -> {
			materialf materialfObject = new materialf();
			String argv[] = new String[0];
			materialfObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("materialf"));

	}

}
