package com.pflager.redbook.kvs;
	import static org.junit.jupiter.api.Assertions.*;

	import org.junit.jupiter.api.Test;

	import com.pflager.redbook.kvs.bezmesh;

	@SuppressWarnings("serial")
	public class Test_com_pflager_redbook_bezmesh  extends ImageCompareJNA {

		@Test
		void test() throws Throwable {
			assertTrue(CaptureCImage("bezmesh"));
			RunNewProcess(bezmesh.class.getName());
			boolean CompareImage = CompareImageSec("bezmesh");
			super.finalize();
			assertTrue(CompareImage);
		}
}