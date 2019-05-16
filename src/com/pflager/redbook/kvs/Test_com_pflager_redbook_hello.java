package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.hello;

class Test_com_pflager_redbook_hello extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("hello"));
		RunNewProcess(hello.class.getName());
		boolean CompareImage = CompareImageSec("hello");
		super.finalize();
		assertTrue(CompareImage);
	}

}
