package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.material;

class Test_com_pflager_redbook_material extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("material"));
		RunNewProcess(material.class.getName());
		boolean CompareImage = CompareImageSec("material");
		super.finalize();
		assertTrue(CompareImage);
	}

}
