package assig3_1;

import java.util.Random;

/*
 * Coin-Flip
 * Authors:
 * Artyom Generalov 319525671
 * Itamar Abir 208273169
 * 
 * */
public class GamePlay {

	private static int threadNum;

	private boolean coin_available; // Is the coin available?
	private int roundsCounter; // Number of passed rounds
	private Judge judge;
	private Thread judge_thread;
	

	public GamePlay() {
		judge = new Judge(this);
		judge_thread = new Thread(judge);
		judge_thread.start();
	}

	public synchronized void makeCoinAvail(boolean val) {
		this.coin_available = val;
		if (val == true)
			notifyAll();
	}

	public boolean flipCoin() {
		try {
			System.out.println(threadNum + " is waiting for a coin ");
			wait();
		} catch (InterruptedException e) {
		}

		int current_num;
		synchronized (this) {
			System.out.println(threadNum + " is flipping a coin ");
			judge_thread.interrupt();
			makeCoinAvail(false);
			roundsCounter++;
			Random rnd = new Random();
			current_num = rnd.nextInt(10) % 2;
			makeCoinAvail(true);
			judge_thread = new Thread(judge);
			judge_thread.start();
			notifyAll();
		}
		System.out.println(threadNum++ + " is dead");
		return current_num == 0 ? false : true;
	}
	


	// Returns the current number of rounds
	public synchronized int getNumOfRounds() {
		return roundsCounter;
	}

	public synchronized boolean getAvail() {
		return this.coin_available;
	}

}
