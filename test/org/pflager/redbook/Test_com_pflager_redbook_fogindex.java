package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.fogindex;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_fogindex extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("fogindex"));
		RunNewProcess(fogindex.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("fogindex");
		super.finalize();
		assertTrue(CompareImage);
	}

}
