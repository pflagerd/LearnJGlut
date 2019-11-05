package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.drawf;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_drawf extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("drawf"));
		RunNewProcess(drawf.class.getName());
		boolean CompareImage = CompareImageSec("drawf");
		super.finalize();
		assertTrue(CompareImage);
	}

}
