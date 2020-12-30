package org.pflager;

import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteDisplayName("JGlut Redbook AllTests Suite")
@SuiteClasses({
	org.pflager.gl.AllTests.class,
	org.pflager.redbook.AllTests.class
})
public class AllTests {}