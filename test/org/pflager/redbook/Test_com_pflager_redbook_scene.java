package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.scene;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_scene extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureReferencePng("scene"));
		RunNewProcess(scene.class.getName());
		boolean CompareImage = CompareImageSec("scene");
		super.finalize();
		assertTrue(CompareImage);
	}

}
