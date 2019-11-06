package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.aargb;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_aargb extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		if (!Files.exists(Paths.get("artifacts/Test_com_pflager_redbook_aargb.reference.png"))) {
			assertTrue(captureRedbookReferencePng("aargb"));
		}
		RunNewProcess(aargb.class.getName());
		boolean CompareImage = CompareImageSec("aargb");
		super.finalize();
		assertTrue(CompareImage);
	}
}
