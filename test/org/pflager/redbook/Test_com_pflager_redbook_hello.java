package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.hello;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_hello extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("hello"));
		RunNewProcess(hello.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("hello");
		super.finalize();
		assertTrue(CompareImage);
	}

}
