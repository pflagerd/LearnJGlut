package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_bezcurve extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("bezcurve"));
		RunNewProcess(bezcurve.class.getName());
		boolean CompareImage = CompareImageSec("bezcurve");
		super.finalize();
		assertTrue(CompareImage);
	}

}
