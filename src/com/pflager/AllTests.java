package com.pflager;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("JGlut AllTests Suite")
@SelectClasses({
	Test_com_pflager_gl_glRectdv.class,
	Test_com_pflager_gl_glRectf__DDDD.class,
	Test_com_pflager_gl_glRectfv___3D_3D.class,
	Test_com_pflager_gl_glRectfv___3F_3F.class,
	Java_com_pflager_gl_glRectiv.class,
	})
public class AllTests {
}
