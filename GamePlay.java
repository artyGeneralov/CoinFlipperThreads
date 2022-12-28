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

	public final int MAX_ROUNDS = 6;

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

	public synchronized boolean flipCoin() {
		try {
			System.out.println(Thread.currentThread().getName() + " is waiting for a coin ");
			wait();
		} catch (InterruptedException e) {
		}

		int current_num;
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + " is flipping a coin ");
			makeCoinAvail(false);
			roundsCounter++;
			Random rnd = new Random();
			current_num = rnd.nextInt(10) % 2;
			makeCoinAvail(true);
			notifyAll();
		}
		return current_num == 0 ? false : true;
	}
	

	// Returns the current number of rounds
	public synchronized int getNumOfRounds() {
		return roundsCounter;
	}

}
