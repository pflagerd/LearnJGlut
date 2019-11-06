package org.pflager.redbook;
	import static org.junit.jupiter.api.Assertions.*;

	import org.junit.jupiter.api.Test;
import org.pflager.redbook.kvs.bezmesh;

	@SuppressWarnings("serial")
	public class Test_com_pflager_redbook_bezmesh  extends ImageCompareJNA {

		@Test
		void test() throws Throwable {
			assertTrue(captureRedbookReferencePng("bezmesh"));
			RunNewProcess(bezmesh.class.getName());
			boolean CompareImage = CompareImageSec("bezmesh");
			super.finalize();
			assertTrue(CompareImage);
		}
}