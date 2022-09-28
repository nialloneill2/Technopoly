
package test.datahandlertest;
import datahandler.Initialiser;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InitialiserTester {

	@Test
	void testInit() {
		Initialiser init = Initialiser.getInit();
		assertNotNull(init);
	}

}
