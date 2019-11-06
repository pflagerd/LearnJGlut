package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.fog;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_fog extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("fog"));
		RunNewProcess(fog.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("fog");
		super.finalize();
		assertTrue(CompareImage);
	}

}
