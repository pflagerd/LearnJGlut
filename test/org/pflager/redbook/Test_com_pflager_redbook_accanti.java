
package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.accanti;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_accanti extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureRedbookReferencePng("accanti"));
		RunNewProcess(accanti.class.getName());
		boolean CompareImage = CompareImageSec("accanti");
		super.finalize();
		assertTrue(CompareImage);
	}

}