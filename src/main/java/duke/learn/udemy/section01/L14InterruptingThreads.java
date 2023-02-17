/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.Random;

/**
 * @author Kazi
 *
 */
public class L14InterruptingThreads {

    public static void run() {
	for (int i = 0; i < 1e9; i++) {
	    Math.sin(new Random().nextDouble());
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException e) {
		System.out.println("Inside interrupted exception" + e.getMessage());
	    }
	}
    }

    public static void main(String[] args) throws InterruptedException {

	Thread t1 = new Thread(() -> run());
	t1.start();
	System.out.println("Thread started");
	// Thread.sleep(2000);
	t1.interrupt();
	t1.join();

	System.out.println("Finished");
    }

}
