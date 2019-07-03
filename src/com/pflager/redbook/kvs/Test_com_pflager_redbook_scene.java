package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.scene;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_scene extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("scene"));
		RunNewProcess(scene.class.getName());
		boolean CompareImage = CompareImageSec("scene");
		super.finalize();
		assertTrue(CompareImage);
	}

}
