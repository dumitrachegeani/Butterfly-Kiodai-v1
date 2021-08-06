import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class CaleTest {
	Fereastra fereastra;
	JLabel labelCareExista;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		fereastra = new Fereastra("Test", 4);
		labelCareExista = fereastra.getLabels()[1][1];
	}

	@Test
	public void gasesteLabelCareNuExista() {
		Coordonate raspuns = Cale.searchLabelsCoord(new JLabel());
		assertEquals(raspuns, null, "Raspuns incorect!");
	}

	@Test
	public void gasesteLabelCareExista() {
		Coordonate raspuns = Cale.searchLabelsCoord(labelCareExista);
		assertEquals(2, raspuns.i, "Raspuns incorect!");
		assertEquals(2, raspuns.j, "Raspuns incorect!");
	}


}