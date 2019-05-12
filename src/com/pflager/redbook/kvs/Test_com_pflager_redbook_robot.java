package com.pflager.redbook.kvs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.robot;

class Test_com_pflager_redbook_robot extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("robot"));
		RunNewProcess(robot.class.getName());
		assertTrue(CompareImageSec("robot"));
		super.finalize();
	}

}
