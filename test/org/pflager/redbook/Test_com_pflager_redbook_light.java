package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.light;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_light extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("light"));
		RunNewProcess(light.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("light");
		super.finalize();
		assertTrue(CompareImage);
	}

}
