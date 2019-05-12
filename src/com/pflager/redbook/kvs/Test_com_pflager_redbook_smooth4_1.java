package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_smooth4_1 extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("smooth4_1"));
		RunNewProcess(smooth4_1.class.getName());
		assertTrue(CompareImageSec("smooth4_1"));
		super.finalize();
	}

}
