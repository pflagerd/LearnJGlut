package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.smooth;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_smooth extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("smooth"));
		RunNewProcess(smooth.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("smooth");
		super.finalize();
		assertTrue(CompareImage);
	}

}
