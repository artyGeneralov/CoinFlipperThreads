package assig3_1;

/*
 * Coin-Flip
 * Authors:
 * Artyom Generalov 319525671
 * Itamar Abir 208273169
 * 
 * */
public class Judge implements Runnable {

	private final long TIME_TO_SLEEP = 500;
	private GamePlay gp; // Receiving the context (the GamePlay object that holds the coin)
	private boolean position;// holds the next judge position

	public Judge(GamePlay gp) {
		this.gp = gp;
	}

	public void run() {
			while (gp.getNumOfRounds() < gp.MAX_ROUNDS && !Thread.interrupted()) {
				try {
					Thread.sleep(TIME_TO_SLEEP); // sleep
				} catch (InterruptedException e) {}
				position = !position;
				gp.makeCoinAvail(position);
			}
		}

}
