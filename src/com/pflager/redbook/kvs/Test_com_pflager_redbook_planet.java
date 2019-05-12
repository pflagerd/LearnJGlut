package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.planet;

class Test_com_pflager_redbook_planet extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("planet"));
		RunNewProcess(planet.class.getName());
		assertTrue(CompareImageSec("planet"));
		super.finalize();
	}

}
