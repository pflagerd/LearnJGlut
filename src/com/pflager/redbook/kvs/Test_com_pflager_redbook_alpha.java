package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.alpha;

class Test_com_pflager_redbook_alpha extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("alpha"));
		RunNewProcess(alpha.class.getName());
		assertTrue(CompareImageSec("alpha"));
		super.finalize();
	}

}
