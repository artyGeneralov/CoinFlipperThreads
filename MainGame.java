package assig3_1;
/*
 * Coin-Flip
 * Authors:
 * Artyom Generalov 319525671
 * Itamar Abir 208273169
 * 
 * */
public class MainGame {

	public static void main(String[] args) {
		
		
		GamePlay game = new GamePlay();
		Gamer gamer_1 = new Gamer(game);
		Gamer gamer_2 = new Gamer(game);
		
		Thread gamer_1_thread = new Thread(gamer_1);
		Thread gamer_2_thread = new Thread(gamer_2);
		
		gamer_1_thread.start();
		gamer_2_thread.start();
		
		while(gamer_1_thread.isAlive() || gamer_2_thread.isAlive()) {
		}
		if(gamer_1.getScore() > gamer_2.getScore())
			System.out.println("Player 1 wins");
		else if (gamer_1.getScore() > gamer_2.getScore())
			System.out.println("Player 2 wins");
		else
			System.out.println("Tie");
	}
}
