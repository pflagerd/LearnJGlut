package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.varray;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_varray extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("varray"));
		RunNewProcess(varray.class.getName());
		boolean CompareImage = CompareImageSec("varray");
		super.finalize();
		assertTrue(CompareImage);
	}

}
