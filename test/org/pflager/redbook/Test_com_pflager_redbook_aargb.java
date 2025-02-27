package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_aargb extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		String testClassName = getClass().getName();
		testClassName = testClassName.substring(testClassName.lastIndexOf('.') + 1);
		String programName = testClassName.substring(testClassName.lastIndexOf("_") + 1);
		
		if (!Files.exists(Paths.get("artifacts/" + testClassName + ".reference.png"))) {
			assertTrue(captureCRedbookReferencePng(programName));
		}
		RunNewProcess(aargb.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook(programName);
		super.finalize();
		assertTrue(CompareImage);
	}
}
