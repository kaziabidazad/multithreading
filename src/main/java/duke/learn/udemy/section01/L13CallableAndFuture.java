/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Kazi
 *
 */
public class L13CallableAndFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

	ExecutorService executor = Executors.newFixedThreadPool(10);
	Future<String> result = executor.submit(() -> {
	    return "fdf";
	});
	System.out.println(result.get());
    }

}
