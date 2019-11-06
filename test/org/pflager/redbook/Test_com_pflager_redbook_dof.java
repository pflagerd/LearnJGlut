package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.dof;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_dof extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("dof"));
		RunNewProcess(dof.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("dof");
		super.finalize();
		assertTrue(CompareImage);
	}

}