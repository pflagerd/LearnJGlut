package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.template2D;

class Test_com_pflager_redbook_template2D extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("template2D"));
		RunNewProcess(template2D.class.getName());
		assertTrue(CompareImageSec("template2D"));
		super.finalize();
	}

}
