package dk.designware.threadutil.schedule;

import java.util.List;

public class UtilTest {

	private static final int noThreads = 3;
	
	public static void main(String[] args) throws InterruptedException {
		long sleepMs = (args != null && args.length > 0)?Long.parseLong(args[0]):0;
		
		System.out.println("Native process id: " + Util.getNativeProcessId());
		System.out.println(
				"Main thread: name: " + Thread.currentThread().getName() + 
				", java-Thread-id: " + Thread.currentThread().getId() + 
				", native-thread-id (LWP) = " + Util.getNativeThreadId());
        
        Thread[] threads = new Thread[noThreads];
        for (int i = 0; i < noThreads; i++) {
        	threads[i] = new Thread(new SlowLWPAndSleepRunnable(sleepMs, i==0));
        	threads[i].start();
        }
        List<Integer> threadIds = Util.getNativeThreadIds();
        System.out.println("Total list of native-thread-ids running under current process");
        for (Integer threadId : threadIds) {
        	System.out.println("  " + threadId);
        }
        try {
        	System.out.println("Now sleeping for " + sleepMs + " ms. Giving you a chance to inspect e.g. using 'top -H -p " + Util.getNativeProcessId() +"'");
			Thread.sleep(sleepMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        for (int i = 0; i < noThreads; i++) {
        	threads[i].join();
        }
	}
	
	private static class SlowLWPAndSleepRunnable implements Runnable {
		
		private long sleepMs;
		private boolean realTime;
		
		private SlowLWPAndSleepRunnable(long sleepMs, boolean realTime) {
			this.sleepMs = sleepMs;
			this.realTime = realTime;
		}

		public void run() {
			String additionalText = "";
			if (realTime) {
				SchedParam sp = new SchedParam();
				sp.schedPriority = 99;
				Util.setScheduler(Util.getNativeThreadId(), SchedPolicy.SCHED_FIFO, sp);
				additionalText = ", running realtime priority";
			}
			System.out.println(
					"Thread: name: " + Thread.currentThread().getName() + 
					", java-Thread-id: " + Thread.currentThread().getId() + 
					", native-thread-id (LWP) = " + Util.getNativeThreadId() +
					additionalText);
			try {
				Thread.sleep(sleepMs);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
