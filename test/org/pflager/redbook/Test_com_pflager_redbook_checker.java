package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.checker;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_checker extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("checker"));
		RunNewProcess(checker.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("checker");
		super.finalize();
		assertTrue(CompareImage);
	}

}
