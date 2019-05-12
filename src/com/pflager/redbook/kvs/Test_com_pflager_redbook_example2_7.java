package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.example2_7;

class Test_com_pflager_redbook_example2_7 extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("example2_7"));
		RunNewProcess(example2_7.class.getName());
		assertTrue(CompareImageSec("example2_7"));
		super.finalize();
	}

}
