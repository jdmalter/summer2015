/**
 * Illustrates a basic concurrency principle from item 66: synchronize access
 *F to shared mutable data.
 */
package concurrencysynchronization;

import java.util.concurrent.TimeUnit;

/**
 * A class with cooperative thread termination with a volatile field. Volatile
 * fields only aid inter-thread communication and not mutual exclusivity.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class StopThread {
	private static volatile boolean stopRequested;

	/**
	 * Starts and stops a background thread.
	 * 
	 * @param args
	 *            command line arguments are not used in this program.
	 * @throws InterruptedException
	 *             should not be thrown unless code is modified to halt thread
	 *             during an operation
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread0 = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!stopRequested)
					i++;
				System.out.println("i: " + i);
			}
		});
		Thread backgroundThread1 = new Thread(new Runnable() {
			public void run() {
				int j = 0;
				while (!stopRequested)
					j--;
				System.out.println("j: " + j);
			}
		});
		Thread backgroundThread2 = new Thread(new Runnable() {
			public void run() {
				int k = 0;
				while (!stopRequested)
					k++;
				System.out.println("k: " + k);
			}
		});
		Thread backgroundThread3 = new Thread(new Runnable() {
			public void run() {
				int l = 0;
				while (!stopRequested)
					l--;
				System.out.println("l: " + l);
			}
		});
		backgroundThread0.start();
		backgroundThread1.start();
		backgroundThread2.start();
		backgroundThread3.start();

		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
	}

}
