package org.pflager.redbook;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("JGlut AllTests Suite")
@SelectClasses({
	// Test_com_pflager_redbook_aaindex.class,		// Example 6-4
	Test_com_pflager_redbook_aapoly.class,			// Example 6-5
	Test_com_pflager_redbook_aargb.class,			// Example 6-3
	Test_com_pflager_redbook_accanti.class,			// Example 10-4
	Test_com_pflager_redbook_accpersp.class,		// Example 10-2
	//Test_com_pflager_redbook_accpersp2.class,		// Example 10-3
	Test_com_pflager_redbook_alpha.class,			// Example 6-1
	Test_com_pflager_redbook_alpha3D.class,			// Example 6-2
	Test_com_pflager_redbook_bezcurve.class,		// Example 12-1
	Test_com_pflager_redbook_bezmesh.class,			// Example 12-3
	Test_com_pflager_redbook_bezsurf.class,			// Example 12-2
	Test_com_pflager_redbook_checker.class,			// Example 9-1
	Test_com_pflager_redbook_clip.class,			// Example 3-5
	Test_com_pflager_redbook_colormat.class,		// Example 5-9
	Test_com_pflager_redbook_cube.class,			// Example 3-1
	Test_com_pflager_redbook_dof.class,				// Example 10-5
	Test_com_pflager_redbook_double_.class,			// Example 1-3
	Test_com_pflager_redbook_drawf.class,			// Example 8-1
	//Test_com_pflager_redbook_feedback.class,		// Example 13-7
	Test_com_pflager_redbook_fog.class,				// Example 6-6
	Test_com_pflager_redbook_fogindex.class, 		// Example 6-7   	seems strange
	//Test_com_pflager_redbook_font.class, 			// Example 8-2
	Test_com_pflager_redbook_hello.class, 			// Example 1-2
	Test_com_pflager_redbook_image.class,			// Example 8-3
	//Test_com_pflager_redbook_image1.class,		// Example 8-4
	Test_com_pflager_redbook_light.class,			// Example 5-1
	Test_com_pflager_redbook_lines.class,			// Example 2-5
	//Test_com_pflager_redbook_list.class,			// Example 7-2
	Test_com_pflager_redbook_material.class,		// Example 5-8
	//Test_com_pflager_redbook_mipmap.class,		// Example 9-4
	Test_com_pflager_redbook_model.class,			// Example 3-2
	Test_com_pflager_redbook_movelight.class,		// Example 5-6
	//Test_com_pflager_redbook_pickdepth.class,		// Example 13-6
	//Test_com_pflager_redbook_picksquare.class,	// Example 13-3
	Test_com_pflager_redbook_planet.class,			// Example 3-6
	// Test_com_pflager_redbook_polyoff.class,		// Example 6-8
	Test_com_pflager_redbook_polys.class,			// Example 2-6
	//Test_com_pflager_redbook_quadric.class,		// Example 11-4
	Test_com_pflager_redbook_robot.class,			// Example 3-7
	Test_com_pflager_redbook_scene.class,
	//Test_com_pflager_redbook_select.class,		// Example 13-2
	Test_com_pflager_redbook_smooth.class,			// Example 4-1
	Test_com_pflager_redbook_stencil.class, 		// Example 10-1		behaves oddly.
	//Test_com_pflager_redbook_stroke.class, 		// Example 7-5
	//Test_com_pflager_redbook_surface.class, 		// Example 12-5
	Test_com_pflager_redbook_teapots.class,
	//Test_com_pflager_redbook_tess.class,			// Example 11-1
	//Test_com_pflager_redbook_tess1.class,			// Example 11-3
	Test_com_pflager_redbook_texbind.class,			// Example 9-5
	//Test_com_pflager_redbook_texgen.class,		// Example 9-6
	Test_com_pflager_redbook_texsub.class,			// Example 9-3
	//Test_com_pflager_redbook_texturesurf.class,	// Example 12-4
	// Test_com_pflager_redbook_torus.class,		// Example 7-1
	//Test_com_pflager_redbook_trim.class,			// Example 12-6
	Test_com_pflager_redbook_unproject.class,		// Example 3-8
	Test_com_pflager_redbook_varray.class,			// Example 2-9
	//Test_com_pflager_redbook_wrap.class,			// 
	})
public class AllTests {
}
