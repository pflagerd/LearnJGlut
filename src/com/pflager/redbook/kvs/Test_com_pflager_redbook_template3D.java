package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.template3D;

class Test_com_pflager_redbook_template3D extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("template3D"));
		RunNewProcess(template3D.class.getName());
		assertTrue(CompareImageSec("template3D"));
		super.finalize();
	}

}
