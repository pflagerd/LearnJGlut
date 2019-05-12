package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.bezsurf;

class Test_com_pflager_redbook_bezsurf extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("bezsurf"));
		RunNewProcess(bezsurf.class.getName());
		assertTrue(CompareImageSec("bezsurf"));
		super.finalize();
	}

}
