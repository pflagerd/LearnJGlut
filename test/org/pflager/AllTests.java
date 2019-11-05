package org.pflager;

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
	Test_com_pflager_gl_glRectiv.class,
	Test_com_pflager_gl_glVertex2f__DD.class,
	Test_com_pflager_gl_glVertex2fv__3F.class,
	Test_com_pflager_gl_glRectd.class,
	})
public class AllTests {
}
