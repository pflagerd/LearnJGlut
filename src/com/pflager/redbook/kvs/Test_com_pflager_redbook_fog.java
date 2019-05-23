package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_fog extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("fog"));
		RunNewProcess(fog.class.getName());
		boolean CompareImage = CompareImageSec("fog");
		super.finalize();
		assertTrue(CompareImage);
	}

}
