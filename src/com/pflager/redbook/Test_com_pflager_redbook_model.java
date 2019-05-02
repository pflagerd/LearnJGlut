package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_model extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("model"));
		model modelObject = new model();
		new Thread(() -> {
			String argv[] = new String[0];
			modelObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("model"));
		modelObject.glutLeaveMainLoop();
	}

}
