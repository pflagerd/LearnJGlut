package org.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pflager.redbook.kvs.dof;

@SuppressWarnings("serial")
class Test_com_pflager_redbook_dof extends ImageCompareJNA {

	@Test
	void test() throws Throwable {
		assertTrue(CaptureCImage("dof"));
		RunNewProcess(dof.class.getName());
		boolean CompareImage = CompareImageSec("dof");
		super.finalize();
		assertTrue(CompareImage);
	}

}