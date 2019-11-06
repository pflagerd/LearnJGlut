package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.bezcurve;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_bezcurve extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("bezcurve"));
		RunNewProcess(bezcurve.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("bezcurve");
		super.finalize();
		assertTrue(CompareImage);
	}

}
