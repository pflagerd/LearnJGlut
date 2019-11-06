package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.material;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_material extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureReferencePng("material"));
		RunNewProcess(material.class.getName());
		boolean CompareImage = CompareImageSec("material");
		super.finalize();
		assertTrue(CompareImage);
	}

}
