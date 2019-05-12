package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.materialf;

class Test_com_pflager_redbook_materialf extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("materialf"));
		RunNewProcess(materialf.class.getName());
		assertTrue(CompareImageSec("materialf"));
		super.finalize();
	}

}
