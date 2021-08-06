public class Main {
	public static void main(String[] args) {

		// imbracam obiectul intr un decorator ce va face lucruri suplimentare
		IJoc game = new Joc();
		game = new DecoratorJoc(game);
		game.createGame();
	}
}
