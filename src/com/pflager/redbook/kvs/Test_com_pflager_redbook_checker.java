package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_checker extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("checker"));
		RunNewProcess(checker.class.getName());
		boolean CompareImage = CompareImageSec("checker");
		super.finalize();
		assertTrue(CompareImage);
	}

}
