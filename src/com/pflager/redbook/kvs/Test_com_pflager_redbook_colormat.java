package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.colormat;

class Test_com_pflager_redbook_colormat extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("colormat"));
		RunNewProcess(colormat.class.getName());
		boolean CompareImage = CompareImageSec("colormat");
		super.finalize();
		assertTrue(CompareImage);
	}

}
