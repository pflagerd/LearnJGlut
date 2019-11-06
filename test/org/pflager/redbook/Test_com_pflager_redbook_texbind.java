package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.texbind;

@SuppressWarnings("serial")
public class Test_com_pflager_redbook_texbind extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureReferencePng("texbind"));
		RunNewProcess(texbind.class.getName());
		boolean CompareImage = CompareImageSec("texbind");
		super.finalize();
		assertTrue(CompareImage);
	}
}
