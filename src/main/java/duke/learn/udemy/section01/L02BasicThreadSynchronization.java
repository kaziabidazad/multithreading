/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.Scanner;

class Processor extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
	while (running) {
	    System.out.println("Hello from thread " + Thread.currentThread().getName());
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    public void shutdown() {
	running = false;
    }
}

/**
 * @author Kazi
 *
 */
public class L02BasicThreadSynchronization {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Processor p1 = new Processor();
	p1.start();
	Scanner scanner = new Scanner(System.in);
	scanner.nextLine();
	p1.shutdown();
	scanner.close();
    }

}
