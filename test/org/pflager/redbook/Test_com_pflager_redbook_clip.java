package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.clip;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_clip extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureRedbookReferencePng("clip"));
		RunNewProcess(clip.class.getName());
		boolean CompareImage = CompareImageSec("clip");
		super.finalize();
		assertTrue(CompareImage);
	}

	
}
