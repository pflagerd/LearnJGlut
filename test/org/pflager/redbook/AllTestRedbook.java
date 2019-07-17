package org.pflager.redbook;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

import com.pflager.redbook.kvs.Test_com_pflager_redbook_aargb;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_accanti;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_accpersp;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_alpha;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_alpha3D;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_bezcurve;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_bezmesh;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_bezsurf;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_checker;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_clip;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_colormat;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_cube;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_double_;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_drawf;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_fog;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_fogindex;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_hello;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_image;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_light;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_lines;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_material;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_model;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_movelight;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_planet;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_polys;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_robot;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_scene;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_smooth;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_stencil;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_teapots;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_texsub;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_unproject;
import com.pflager.redbook.kvs.Test_com_pflager_redbook_varray;

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
	Test_com_pflager_redbook_stencil.class,
	Test_com_pflager_redbook_accpersp.class,
	Test_com_pflager_redbook_accanti.class,
	Test_com_pflager_redbook_texsub.class,
	Test_com_pflager_redbook_bezmesh.class,
	
	})
public class AllTestRedbook {
}
