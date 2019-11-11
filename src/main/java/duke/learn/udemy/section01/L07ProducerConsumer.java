/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Kazi
 *
 */
public class L07ProducerConsumer {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10000);

    public static void producer() {
	Random random = new Random();
	while (true) {
	    try {
		queue.put(random.nextInt(10000));
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    private static void consumer() throws InterruptedException {
	Random random = new Random();
	while (true) {
	    // Thread.sleep(1);
	    // just to add some time
	    // if (random.nextInt(10) == 0) {
	    int val = queue.take();
	    System.out.println(
		    Thread.currentThread().getName() + "=> Consumed : " + val + " ; Queue size: " + queue.size());
	    // }
	}
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	Thread t1 = new Thread(new Runnable() {
	    @Override
	    public void run() {
		producer();
	    }
	});
	Thread t2 = new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
	Thread t3 = new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	;
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
	t1.start();
	t2.start();
	t3.start();
    }

}
