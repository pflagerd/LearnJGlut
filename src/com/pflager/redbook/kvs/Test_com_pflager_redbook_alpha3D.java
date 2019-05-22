package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.alpha3D;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_alpha3D extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("alpha3D"));
		RunNewProcess(alpha3D.class.getName());
		boolean CompareImage = CompareImageSec("alpha3D");
		super.finalize();
		assertTrue(CompareImage);
	}

}
