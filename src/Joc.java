//obiect ce va fi decorat
// face parte din design patternul Decorator
public class Joc implements IJoc {
	public Fereastra createGame() {
		// LENGH VARIABUL, POT SA PUN CATE PATRATE VREAU
		return new Fereastra("Fluturi", 14);
	}
}
