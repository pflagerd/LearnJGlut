package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_template3D extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("template3D"));
		template3D template3DObject = new template3D();
		new Thread(() -> {
			String argv[] = new String[0];
			template3DObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("template3D"));
		template3DObject.glutLeaveMainLoop();
	}

}
