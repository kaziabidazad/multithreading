/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.LinkedList;
import java.util.Random;

class WaitNotifyProducerConsumer {
    private LinkedList<Integer> list = new LinkedList<>();
    private Object mutex = new Object();
    private static final int limit = 10;

    public void produce() throws InterruptedException {
	int value = 0;
	while (true) {
	    synchronized (mutex) {
		while (list.size() == limit) {
		    mutex.wait();
		}
		list.add(++value);
		mutex.notify();
	    }
	}
    }

    public void consume() throws InterruptedException {
	Random random = new Random();
	while (true) {
	    synchronized (mutex) {
		while (list.size() == 0)
		    mutex.wait();
		System.out.print(Thread.currentThread().getName() + "=>List size: " + list.size());
		int value = list.removeFirst();
		System.out.println("   Consumed: " + value);
		mutex.notify();
	    }
	    // sleeping for a random amount of time
	    // Thread.sleep(random.nextInt(1000));
	}
    }
}

/**
 * @author Kazi
 *
 */
public class L09LowLevelSync {

    /**
     * @param args
     */
    public static void main(String[] args) {
	WaitNotifyProducerConsumer wn = new WaitNotifyProducerConsumer();
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    wn.produce();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    wn.consume();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    wn.consume();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();

    }

}
