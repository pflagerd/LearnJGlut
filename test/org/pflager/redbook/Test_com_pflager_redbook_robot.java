package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.robot;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_robot extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(captureCRedbookReferencePng("robot"));
		RunNewProcess(robot.class.getName());
		boolean CompareImage = captureAndCompareJGlutRedbookWithCRedbook("robot");
		super.finalize();
		assertTrue(CompareImage);
	}

}
