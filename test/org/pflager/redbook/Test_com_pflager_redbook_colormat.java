package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.colormat;

@SuppressWarnings("serial")
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
