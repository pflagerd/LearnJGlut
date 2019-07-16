package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.kvs.image;

class Test_com_pflager_redbook_image extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("image"));
		RunNewProcess(image.class.getName());
		boolean CompareImage = CompareImageSec("image");
		super.finalize();
		assertTrue(CompareImage);
	}

}