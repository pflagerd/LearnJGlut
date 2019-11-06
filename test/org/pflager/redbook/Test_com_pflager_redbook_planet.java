package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.planet;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_planet extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("planet"));
		RunNewProcess(planet.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("planet");
		super.finalize();
		assertTrue(CompareImage);
	}

}
