package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.clip;

class Test_com_pflager_redbook_clip extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("clip"));
		RunNewProcess(clip.class.getName());
		boolean CompareImage = CompareImageSec("clip");
		super.finalize();
		assertTrue(CompareImage);
	}

}
