/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CountdownProcessor implements Runnable {

    private List<String> outputScrapper;
    private CountDownLatch countDownLatch;

    /**
     * @param outputScrapper
     * @param countDownLatch
     */
    public CountdownProcessor(List<String> outputScrapper, CountDownLatch countDownLatch) {
	super();
	this.outputScrapper = outputScrapper;
	this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
	System.out.println("Started...");
	outputScrapper.add("Countdown" + countDownLatch.getCount());
	countDownLatch.countDown();
    }

    public List<String> getOutputScrapper() {
	return outputScrapper;
    }

}

class MultiLatchWorker implements Runnable {

    private List<String> outputScrapper;
    private CountDownLatch readyThreadCounter;
    private CountDownLatch callingThreadBlocker;
    private CountDownLatch completedThreadCounter;

    /**
     * @param outputScrapper
     * @param readyThreadCounter
     * @param callingThreadBlocker
     * @param completedThreadCounter
     */
    public MultiLatchWorker(List<String> outputScrapper, CountDownLatch readyThreadCounter,
	    CountDownLatch callingThreadBlocker, CountDownLatch completedThreadCounter) {
	super();
	this.outputScrapper = outputScrapper;
	this.readyThreadCounter = readyThreadCounter;
	this.callingThreadBlocker = callingThreadBlocker;
	this.completedThreadCounter = completedThreadCounter;
    }

    @Override
    public void run() {
	System.out.println("started" + readyThreadCounter.getCount());
	readyThreadCounter.countDown();
	try {
	    callingThreadBlocker.await();
	    outputScrapper.add("Countdown" + completedThreadCounter.getCount());
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	completedThreadCounter.countDown();
    }
}

/**
 * @author Kazi
 *
 */
public class L06CountdownLatch {

    private static final int threadNum = 1200;

    public static void main(String[] args) throws InterruptedException {
	multiLatch();
	// simpleLatch();
    }

    static void multiLatch() throws InterruptedException {
	List<String> outputScrapper = new CopyOnWriteArrayList<>();
	CountDownLatch readyThreadCounter = new CountDownLatch(threadNum);
	CountDownLatch callingThreadBlocker = new CountDownLatch(1);
	CountDownLatch completedThreadCounter = new CountDownLatch(threadNum);
	List<Thread> threads = Stream.generate(() -> new Thread(
		new MultiLatchWorker(outputScrapper, readyThreadCounter, callingThreadBlocker, completedThreadCounter)))
		.limit(threadNum).collect(Collectors.toList());
	threads.forEach(Thread::start);
	readyThreadCounter.await();
	outputScrapper.add("Workers ready");
	callingThreadBlocker.countDown();
	completedThreadCounter.await();
	outputScrapper.add("Workers complete");
	outputScrapper.stream().forEach(s -> System.out.println(s));
	System.out.println(outputScrapper.size());
    }

    static void simpleLatch() {

	List<String> outputScrapper = new CopyOnWriteArrayList<>();
	CountDownLatch latch = new CountDownLatch(threadNum);
	ExecutorService executor = Executors.newFixedThreadPool(threadNum);
	for (int i = 0; i < threadNum; i++) {
	    executor.submit(new CountdownProcessor(outputScrapper, latch));
	}
	try {
	    latch.await();
	    outputScrapper.add("Latch Released");
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	executor.shutdown();
	System.out.println("Latch released");
	outputScrapper.stream().forEach(s -> System.out.println(s));
	System.out.println(outputScrapper.size());

    }

}
