package assig3_1;
/*
 * Coin-Flip
 * Authors:
 * Artyom Generalov 319525671
 * Itamar Abir 208273169
 * 
 * */
public class Gamer implements Runnable {
	
	private final long SLEEP_TIME = 1000;
	private final int MAX_FLIPS = 10;
	
	private int goodFlipsCounter; // Score
	private int flipCounter; // How many flips ( 10 max!! )
	GamePlay gp; // context
	
	public Gamer(GamePlay gp) {
		this.gp = gp;
	}
	
	
	public int getScore() {
		return this.goodFlipsCounter;
	}
	
	// play()
	public void run() {
		
		while(flipCounter < MAX_FLIPS) {
			synchronized(gp) {
			boolean result = gp.flipCoin();
			if(result == true)
				goodFlipsCounter++;
			
			System.out.println(result);
			}
			flipCounter++;
			try {
				Thread.sleep(SLEEP_TIME);
			}catch(InterruptedException e) {};
		}
		
		
	}
}
