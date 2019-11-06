package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.cube;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_cube extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureRedbookReferencePng("cube"));
		RunNewProcess(cube.class.getName());
		boolean CompareImage = CompareImageSec("cube");
		super.finalize();
		assertTrue(CompareImage);
	}

}
