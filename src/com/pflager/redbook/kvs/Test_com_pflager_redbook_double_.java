package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.double_;

class Test_com_pflager_redbook_double_ extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("double_"));
		RunNewProcess(double_.class.getName());
		assertTrue(CompareImageSec("double_"));
		super.finalize();
	}

}
