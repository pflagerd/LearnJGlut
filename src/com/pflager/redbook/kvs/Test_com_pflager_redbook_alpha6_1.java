package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_alpha6_1 extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("alpha6_1"));
		RunNewProcess(alpha6_1.class.getName());
		assertTrue(CompareImageSec("alpha6_1"));
		super.finalize();
	}

}
