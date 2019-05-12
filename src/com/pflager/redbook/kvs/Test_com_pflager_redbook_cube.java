package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.cube;

class Test_com_pflager_redbook_cube extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("cube"));
		RunNewProcess(cube.class.getName());
		assertTrue(CompareImageSec("cube"));
		super.finalize();
	}

}
