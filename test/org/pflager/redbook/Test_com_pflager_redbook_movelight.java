package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.movelight;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_movelight extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("movelight"));
		RunNewProcess(movelight.class.getName());
		boolean CompareImage = CompareImageSec("movelight");
		super.finalize();
		assertTrue(CompareImage);
	}

}
