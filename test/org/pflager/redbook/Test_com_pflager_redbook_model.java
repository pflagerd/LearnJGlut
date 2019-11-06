package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.model;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_model extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("model"));
		RunNewProcess(model.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("model");
		super.finalize();
		assertTrue(CompareImage);
	}

}
