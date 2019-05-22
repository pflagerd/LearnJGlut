package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.teapots;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_teapots extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("teapots"));
		RunNewProcess(teapots.class.getName());
		boolean CompareImage = CompareImageSec("teapots");
		super.finalize();
		assertTrue(CompareImage);
	}

}
