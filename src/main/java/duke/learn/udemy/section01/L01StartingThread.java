/**
 * 
 */
package duke.learn.udemy.section01;

/**
 * @author Kazi
 *
 */

class MyThread extends Thread {
    @Override
    public void run() {
	System.out.println("Starting my thread");
	for (int i = 0; i < 9; i++) {
	    try {
		Thread.sleep(200);
		System.out.println(Thread.currentThread().getName() + " => loop " + i + "done");
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
}

class Runner implements Runnable {

    public void run() {
	System.out.println("Starting my runner");
	for (int i = 0; i < 9; i++) {
	    try {
		Thread.sleep(300);
		System.out.println(Thread.currentThread().getName() + " => loop " + i + "done");
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

}

public class L01StartingThread {

    public static void main(String[] args) {
	// MyThread t1 = new MyThread();
	// MyThread t2 = new MyThread();
	// t1.start();
	// t2.start();
	// Using runnable
	Thread t1 = new Thread(new Runner());
	Thread t2 = new Thread(new Runner());
	t1.start();
	t2.start();
	// Using anonymous class
	new Thread(new Runnable() {

	    public void run() {
		System.out.println("Starting my runner");
		for (int i = 0; i < 9; i++) {
		    try {
			Thread.sleep(300);
			System.out.println(Thread.currentThread().getName() + " => loop " + i + "done");
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	    }
	}).start();
	;
	// Using java8 lambda
	new Thread(() -> {
	    System.out.println("Starting my lambda");
	    for (int i = 0; i < 9; i++) {
		try {
		    Thread.sleep(300);
		    System.out.println(Thread.currentThread().getName() + " => loop " + i + "done");
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}).start();
	;
    }

}
