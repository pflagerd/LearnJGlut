package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.teapots;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_teapots extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureRedbookReferencePng("teapots"));
		RunNewProcess(teapots.class.getName());
		boolean CompareImage = CompareImageSec("teapots");
		super.finalize();
		assertTrue(CompareImage);
	}

}
