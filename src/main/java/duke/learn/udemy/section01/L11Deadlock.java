/**
 * 
 */
package duke.learn.udemy.section01;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private int balance = 10000;

    public void deposit(int amount) {
	balance += amount;
    }

    public void withdraw(int amount) {
	balance -= amount;
    }

    public int getBalance() {
	return balance;
    }

    public static void transfer(Account ac1, Account ac2, int amount) {
	ac1.withdraw(amount);
	ac2.deposit(amount);
    }
}

class DeadlockRunner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void accuireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
	while (true) {
	    boolean gotFirstLock = false;
	    boolean gotSecondLock = false;
	    try {
		gotFirstLock = firstLock.tryLock();
		gotSecondLock = secondLock.tryLock();
	    } finally {
		if (gotFirstLock && gotSecondLock)
		    return;

		if (gotFirstLock)
		    firstLock.unlock();
		if (gotSecondLock)
		    secondLock.unlock();
	    }
	    Thread.sleep(10);
	}
    }

    public void firstRun() throws InterruptedException {
	try {
	    accuireLocks(lock1, lock2);
	    Random random = new Random();
	    for (int i = 0; i < 1000; i++) {
		Account.transfer(acc1, acc2, random.nextInt(100));
	    }
	} finally {
	    lock1.unlock();
	    lock2.unlock();
	}
    }

    public void secondRun() throws InterruptedException {
	try {

	    accuireLocks(lock2, lock1);
	    Random random = new Random();
	    for (int i = 0; i < 1000; i++) {
		Account.transfer(acc2, acc1, random.nextInt(100));
	    }
	} finally {
	    lock1.unlock();
	    lock2.unlock();
	}
    }

    public void finised() {
	System.out.println("account 1 balance = " + acc1.getBalance());
	System.out.println("account 2 balance = " + acc2.getBalance());
	System.out.println("Total Balance = " + (acc1.getBalance() + acc2.getBalance()));
    }
}

/**
 * @author Kazi
 *
 */
public class L11Deadlock {

    public static void main(String[] args) throws InterruptedException {
	DeadlockRunner runner = new DeadlockRunner();
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
	Thread t3 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    runner.firstRun();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
	t1.start();
	t2.start();
	t3.start();
	t1.join();
	t2.join();
	t3.join();
	runner.finised();
    }
}
