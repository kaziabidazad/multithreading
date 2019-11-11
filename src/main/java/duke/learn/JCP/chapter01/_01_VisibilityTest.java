/**
 * 
 */
package duke.learn.JCP.chapter01;

/**
 * @author Kazi
 *
 */
public class _01_VisibilityTest {

    static boolean ready;
    static int number;

    /**
     * @param args
     */
    public static void main(String[] args) {
	new ReaderThread().start();
	number = 43;
	ready = true;
	// int i = 0;
	// while (i < 1000)
	// i++;
	// number = 90;
    }

    private static class ReaderThread extends Thread {
	@Override
	public void run() {
	    while (!ready)
		Thread.yield();
	    System.out.println(number);

	}
    }

}
