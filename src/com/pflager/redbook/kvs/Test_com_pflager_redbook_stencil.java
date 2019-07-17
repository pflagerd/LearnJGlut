package com.pflager.redbook.kvs;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.kvs.stencil;


@SuppressWarnings("serial")
public class Test_com_pflager_redbook_stencil extends ImageCompareJNA {
	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("stencil"));
		RunNewProcess(stencil.class.getName());
		boolean CompareImage = CompareImageSec("stencil");
		assertTrue(CompareImage);
	}
	
}




	