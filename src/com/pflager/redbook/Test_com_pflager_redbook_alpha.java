package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class Test_com_pflager_redbook_alpha extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("alpha"));
		RunNewProcess(alpha.class.getName());
		assertTrue(CompareImageSec("alpha"));
		super.finalize();
	}

}
