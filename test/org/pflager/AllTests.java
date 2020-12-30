package org.pflager;

import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteDisplayName("LearnJGlut AllTests Suite")
@SuiteClasses({
	org.pflager.redbook.AllTests.class
})
public class AllTests {}