// Design pattern decorator adaug in plus functionalitatea de a mai rasari o fereastra noua pe
// care vom afisa diferite informatii

public class DecoratorJoc implements IJoc {
	private final IJoc game;

	// primim in constructor Clasa concreta joc
	public DecoratorJoc(IJoc game) {
		this.game = game;
	}

	// in metoda din interfata ce trebuie implementata, apelez FereastraScor.getInstance()
	// SINGLETONE!!!
	public Fereastra createGame() {
		FereastraScor.getINSTANCE();
		return game.createGame();
	}
}
