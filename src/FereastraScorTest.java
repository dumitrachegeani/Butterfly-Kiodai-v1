import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FereastraScorTest {
	FereastraScor fereastraScor;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		fereastraScor = FereastraScor.getINSTANCE();
	}

	@Test
	public void cresteScorul() {
		fereastraScor.incrementScore();
		fereastraScor.incrementScore();
		fereastraScor.incrementScore();
		fereastraScor.incrementScore();
		fereastraScor.incrementScore();
		fereastraScor.incrementScore();

		assertEquals(6, fereastraScor.score);
	}

}