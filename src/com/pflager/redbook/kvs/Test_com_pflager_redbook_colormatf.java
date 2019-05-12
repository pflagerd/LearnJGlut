package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.colormatf;

class Test_com_pflager_redbook_colormatf extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("colormatf"));
		RunNewProcess(colormatf.class.getName());
		boolean CompareImage = CompareImageSec("colormatf");
		super.finalize();
		assertTrue(CompareImage);
	}

}
