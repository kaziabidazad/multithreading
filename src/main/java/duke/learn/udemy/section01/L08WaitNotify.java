/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.Scanner;

class WaitNotify {
    public void produce() {
	synchronized (this) {
	    System.out.println(Thread.currentThread().getName() + "=>Prodcuer thread running");
	    try {
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println(Thread.currentThread().getName() + "=>Producer resumed");
	}
    }

    public void consume() {
	Scanner scanner = new Scanner(System.in);
	synchronized (this) {
	    System.out.println(Thread.currentThread().getName() + "=>Waiting for return key");
	    scanner.nextLine();
	    System.out.println(Thread.currentThread().getName() + "=>Return key pressed");
	    scanner.close();
	    notify();

	}
    }
}

/**
 * @author Kazi
 *
 */
public class L08WaitNotify {

    public static void main(String[] args) {
	WaitNotify wn = new WaitNotify();
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		wn.produce();
	    }
	}).start();
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		wn.consume();
	    }
	}).start();

    }

}
