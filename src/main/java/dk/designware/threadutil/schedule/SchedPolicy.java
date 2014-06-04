package dk.designware.threadutil.schedule;

public interface SchedPolicy {

	public static final int SCHED_OTHER = 0;
	public static final int SCHED_FIFO = 1;
	public static final int SCHED_RR = 2;
	public static final int SCHED_BATCH = 3;
	public static final int SCHED_IDLE = 5;
	
	public static final int SCHED_RESET_ON_FORK = 0x40000000;
	
}
