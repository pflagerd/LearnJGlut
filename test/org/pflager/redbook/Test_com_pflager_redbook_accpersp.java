package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.accpersp;
@SuppressWarnings("serial")
public class Test_com_pflager_redbook_accpersp extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("accpersp"));
		RunNewProcess(accpersp.class.getName());
		boolean CompareImage = CompareImageSec("accpersp");
		super.finalize();
		assertTrue(CompareImage);
	}
}
