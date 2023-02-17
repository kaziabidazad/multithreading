/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockRunner {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    private void increment() {
	for (int i = 0; i < 10000; i++)
	    count++;
    }

    public void firstRun() throws InterruptedException {
	lock.lock();
	try {
	    increment();
	} finally {
	    lock.unlock();
	}
    }

    public void secondRun() throws InterruptedException {
	lock.lock();
	try {
	    increment();
	} finally {
	    lock.unlock();
	}
    }

    public void finised() {
	System.out.println(count);
    }
}

/**
 * @author Kazi
 *
 */
public class L10RentrantLock {

    public static void main(String[] args) throws InterruptedException {
	LockRunner runner = new LockRunner();
	Thread t1 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    runner.firstRun();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
	Thread t2 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    runner.secondRun();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
	t1.start();
	t2.start();
	t1.join();
	t2.join();
	runner.finised();
    }

}
