package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.alpha;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_alpha extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("alpha"));
		RunNewProcess(alpha.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("alpha");
		super.finalize();
		assertTrue(CompareImage);
		
	}

}
