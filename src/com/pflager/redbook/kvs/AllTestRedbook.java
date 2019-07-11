package com.pflager.redbook.kvs;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("JGlut AllTests Suite")
@SelectClasses({
	Test_com_pflager_redbook_aargb.class,
	Test_com_pflager_redbook_alpha.class,
	Test_com_pflager_redbook_alpha3D.class,
	Test_com_pflager_redbook_bezcurve.class,
	Test_com_pflager_redbook_bezsurf.class,
	Test_com_pflager_redbook_checker.class,
	Test_com_pflager_redbook_clip.class,
	Test_com_pflager_redbook_colormat.class,
	Test_com_pflager_redbook_cube.class,
	Test_com_pflager_redbook_double_.class,
	Test_com_pflager_redbook_fog.class,
	Test_com_pflager_redbook_fogindex.class,
	Test_com_pflager_redbook_hello.class,
	Test_com_pflager_redbook_image.class,
	Test_com_pflager_redbook_light.class,
	Test_com_pflager_redbook_lines.class,
	Test_com_pflager_redbook_material.class,
	Test_com_pflager_redbook_model.class,
	Test_com_pflager_redbook_movelight.class,
	Test_com_pflager_redbook_planet.class,
	Test_com_pflager_redbook_polys.class,
	Test_com_pflager_redbook_robot.class,
	Test_com_pflager_redbook_scene.class,
	Test_com_pflager_redbook_smooth.class,
	Test_com_pflager_redbook_teapots.class,
	Test_com_pflager_redbook_unproject.class,
	Test_com_pflager_redbook_varray.class,
	Test_com_pflager_redbook_drawf.class,
	})
public class AllTestRedbook {
}
