package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.varray;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_varray extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("varray"));
		RunNewProcess(varray.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("varray");
		super.finalize();
		assertTrue(CompareImage);
	}

}
