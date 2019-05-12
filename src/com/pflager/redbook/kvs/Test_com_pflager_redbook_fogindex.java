package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_fogindex extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("fogindex"));
		RunNewProcess(fogindex.class.getName());
		assertTrue(CompareImageSec("fogindex"));
		super.finalize();
	}

}
