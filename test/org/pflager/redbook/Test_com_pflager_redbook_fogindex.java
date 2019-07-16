package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.kvs.fogindex;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_fogindex extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("fogindex"));
		RunNewProcess(fogindex.class.getName());
		boolean CompareImage = CompareImageSec("fogindex");
		super.finalize();
		assertTrue(CompareImage);
	}

}
