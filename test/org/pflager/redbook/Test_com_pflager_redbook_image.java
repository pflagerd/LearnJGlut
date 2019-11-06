package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.image;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_image extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("image"));
		RunNewProcess(image.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("image");
		super.finalize();
		assertTrue(CompareImage);
	}

}