package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.model;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_model extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("model"));
		RunNewProcess(model.class.getName());
		boolean CompareImage = CompareImageSec("model");
		super.finalize();
		assertTrue(CompareImage);
	}

}
