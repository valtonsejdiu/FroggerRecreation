public class Main {

	public static void main(String[] args) {
		Frog f = new Frog(300,601,48);
		PlayerStats g = new PlayerStats();
		AllObstacles a=new AllObstacles();
		ControllKeys c = new ControllKeys(f);
		Gui gui =new Gui(f,a,c,g);
		GameController game = new GameController(a,f,g,gui);
		game.run();
	}
}
