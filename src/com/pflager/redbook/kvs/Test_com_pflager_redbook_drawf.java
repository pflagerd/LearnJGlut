package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_drawf extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("drawf"));
		RunNewProcess(drawf.class.getName());
		boolean CompareImage = CompareImageSec("drawf");
		super.finalize();
		assertTrue(CompareImage);
	}

}
