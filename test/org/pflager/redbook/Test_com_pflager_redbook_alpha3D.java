package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.alpha3D;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_alpha3D extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("alpha3D"));
		RunNewProcess(alpha3D.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("alpha3D");
		super.finalize();
		assertTrue(CompareImage);
	}

}
