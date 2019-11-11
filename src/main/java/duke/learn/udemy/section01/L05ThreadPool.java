/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class PoolProcessor implements Runnable {

    private int number;

    public PoolProcessor(int number) {
	this.number = number;
    }

    @Override
    public void run() {
	System.out.println("Starting process: " + number);
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println("Completed process: " + number);
    }
}

/**
 * @author Kazi
 *
 */
public class L05ThreadPool {

    public static void main(String[] args) {
	ExecutorService executor = Executors.newFixedThreadPool(6);
	for (int i = 0; i < 10; i++) {
	    executor.submit(new PoolProcessor(i));
	}
	executor.shutdown();
	System.out.println("All tasks submitted");
	try {
	    executor.awaitTermination(10, TimeUnit.SECONDS);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println("All processes terminated");

    }

}
