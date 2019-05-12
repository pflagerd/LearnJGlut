package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.light;

class Test_com_pflager_redbook_light extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("light"));
		RunNewProcess(light.class.getName());
		assertTrue(CompareImageSec("light"));
		super.finalize();
	}

}
