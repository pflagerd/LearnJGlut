package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.aargb;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_aargb extends ImageCompareJNA {

	
	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("aargb"));
		RunNewProcess(aargb.class.getName());
		boolean CompareImage = CompareImageSec("aargb");
		super.finalize();
		assertTrue(CompareImage);
	}

}
