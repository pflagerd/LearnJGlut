package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.movelight;

class Test_com_pflager_redbook_movelight extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("movelight"));
		RunNewProcess(movelight.class.getName());
		assertTrue(CompareImageSec("movelight"));
		super.finalize();
	}

}
