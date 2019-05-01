package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_scene extends ImageCompareJNA {

	@Test
	void test() {
		assertTrue(CaptureCImage("scene"));
		new Thread(() -> {
			scene sceneObject = new scene();
			String argv[] = new String[0];
			sceneObject.main(0, argv);
		}).start();
		assertTrue(CompareImageSec("scene"));

	}

}
