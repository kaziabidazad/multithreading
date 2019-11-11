/**
 * 
 */
package duke.learn.udemy.section01;

class Counter {
    private volatile int count = 0;

    public synchronized void increment() {
	count++;
    }

    void doWork() {
	Thread t1 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		for (int i = 0; i < 30000; i++) {
		    increment();
		}
	    }
	});
	Thread t2 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		for (int i = 0; i < 30000; i++) {
		    increment();
		}
	    }
	});
	Thread t3Thread = new Thread(new Runnable() {

	    @Override
	    public void run() {
		for (int i = 0; i < 30000; i++) {
		    increment();
		}
	    }
	});
	t3Thread.start();
	t1.start();
	t2.start();
	try {
	    t1.join();
	    t2.join();
	    t3Thread.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println("count = " + count);
    }
}

/**
 * @author Kazi
 *
 */
public class L03SynchronizedKeyword {

    public static void main(String[] args) {
	Counter counter = new Counter();
	counter.doWork();
    }

}
