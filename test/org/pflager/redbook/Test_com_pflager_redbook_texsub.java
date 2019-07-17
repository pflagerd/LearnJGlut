package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.kvs.texsub;

	@SuppressWarnings("serial")
	class Test_com_pflager_redbook_texsub extends ImageCompareJNA {

		@Test
		void test() throws Throwable {
			assertTrue(CaptureCImage("texsub"));
			RunNewProcess(texsub.class.getName());
			boolean CompareImage = CompareImageSec("texsub");
			super.finalize();
			assertTrue(CompareImage);
		}
}
