package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.unproject;

class Test_com_pflager_redbook_unproject extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("unproject"));
		RunNewProcess(unproject.class.getName());
		assertTrue(CompareImageSec("unproject"));
		super.finalize();
	}

}
