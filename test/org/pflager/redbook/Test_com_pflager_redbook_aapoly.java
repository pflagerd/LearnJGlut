package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.aapoly;
@SuppressWarnings("serial")
public class Test_com_pflager_redbook_aapoly extends ImageCompareJNA  {
	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("aapoly"));
		RunNewProcess(aapoly.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("aapoly");
		super.finalize();
		assertTrue(CompareImage);
	}
}
