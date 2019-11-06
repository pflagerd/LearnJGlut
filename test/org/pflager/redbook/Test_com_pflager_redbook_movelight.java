package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.movelight;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_movelight extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("movelight"));
		RunNewProcess(movelight.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("movelight");
		super.finalize();
		assertTrue(CompareImage);
	}

}
