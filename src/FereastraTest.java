import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FereastraTest {
	Fereastra fereastra;

	@Test
	public void fereastra100() {
		fereastra = new Fereastra("Test", 100);
		assertEquals(fereastra.getLength(), 100, "Corect");
	}

	// OUT OF MEMORY!
//	@Test
//	public void fereastra1000() {
//		fereastra = new Fereastra("Test", 10000);
//	}

//	@Test
//	public void fereastra10000() {
//		fereastra = new Fereastra("Test", 10000);
//	}
}