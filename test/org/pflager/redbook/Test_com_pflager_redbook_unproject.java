package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.unproject;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_unproject extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("unproject"));
		RunNewProcess(unproject.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("unproject");
		super.finalize();
		assertTrue(CompareImage);
	}

}
