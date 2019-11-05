package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pflager.redbook.double_;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_double_ extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("double"));
		RunNewProcess(double_.class.getName());
		boolean CompareImage = CompareImageSec("double");
		super.finalize();
		assertTrue(CompareImage);
	}

}
