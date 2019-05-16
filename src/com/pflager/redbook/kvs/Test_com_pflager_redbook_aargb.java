package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.aargb;

class Test_com_pflager_redbook_aargb extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("aargb"));
		RunNewProcess(aargb.class.getName());
		boolean CompareImage = CompareImageSec("aargb");
		super.finalize();
		assertTrue(CompareImage);
	}

}
