/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Worker {
    private Random random = new Random();
    private List<Integer> l1 = new ArrayList<Integer>();
    private List<Integer> l2 = new ArrayList<>();
    private Object mutex1 = new Object();
    private Object mutex2 = new Object();

    public void stageOne() {
	try {
	    Thread.sleep(1);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	synchronized (mutex1) {
	    l1.add(random.nextInt(500));
	}
    }

    public synchronized void stageTwo() {
	try {
	    Thread.sleep(1);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	synchronized (mutex2) {
	    l2.add(random.nextInt(500));
	}
    }

    public void process() {
	for (int i = 0; i < 2000; i++) {
	    stageOne();
	    stageTwo();
	}
    }

    public void doWork() {
	System.out.println("Starting to work..");
	long start = System.currentTimeMillis();
	Thread t1 = new Thread(new Runnable() {
	    @Override
	    public void run() {
		process();
	    }
	});
	Thread t2 = new Thread(new Runnable() {
	    @Override
	    public void run() {
		process();
	    }
	});
	t1.start();
	t2.start();
	try {
	    t1.join();
	    t2.join();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	long end = System.currentTimeMillis();

	System.out.println("Time taken : " + (end - start) + " ms");
	System.out.println("List1: " + l1.size() + " ,  list2:  " + l2.size());
    }
}

/**
 * @author Kazi
 *
 */
public class L04MultipleLocks {

    public static void main(String[] args) {
	Worker worker = new Worker();
	worker.doWork();
    }

}
