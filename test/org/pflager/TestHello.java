package org.pflager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;


class TestHello {

	@Test
	void test() throws IOException {
		// Run hello executable from redbook-1.1-src
		Process helloProcess = Runtime.getRuntime().exec("../redbook-1.1-src/src/hello");
		// Wait long enough for it to run, then capture its client area as a .png
		// Kill it.
		helloProcess.destroy();
		// Run hello.java
		helloProcess = Runtime.getRuntime().exec(new String[] { System.getProperty("java.home") + "/bin/java", "-cp", System.getProperty("java.class.path"), "com.pflager.redbook.hello" }); 
		while (true) {
			int next = helloProcess.getErrorStream().read();
			if (next == -1) {
				break;
			}
			System.out.print((char)next);
			// TODO: wait until there has been no output for 1 second, then exit.
		}
		System.out.println();
		// Capture its client area as a png

		// Kill it.
		helloProcess.destroy();
		// Compare the two png's and if they don't match, fail the test.
		
		
		fail("Not yet implemented");
	}

}
