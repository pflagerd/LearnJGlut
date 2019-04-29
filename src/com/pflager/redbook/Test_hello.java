package com.pflager.redbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_hello extends ImageCompareJNA {

	@Test
	void test() {
		hello helloobj = new hello();
		   ImageCompareJNA ImageCompareJNAObj = new ImageCompareJNA();
		   ImageCompareJNAObj.CaptureCImage("hello");
			   new Thread(() -> {
					   helloobj.main(0, new String[0] );
				}).start();
			   ImageCompareJNAObj.CompareImage2("hello");
	}
	

}
