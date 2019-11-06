package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.texsub;

	@SuppressWarnings("serial")
	class Test_com_pflager_redbook_texsub extends ImageCompareJNA {

		@Test
		void test() throws Throwable {
			assertTrue(captureReferencePng("texsub"));
			RunNewProcess(texsub.class.getName());
			boolean CompareImage = CompareImageSec("texsub");
			super.finalize();
			assertTrue(CompareImage);
		}
}
