package dk.designware.threadutil.schedule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Util {

	/**
	 * @return The process-id for the OS-process owning the OS-thread running current Java-thread
	 */
    public static native int getNativeProcessId();

	/**
	 * @return The thread-id (LWP) for the OS-thread running current Java-thread
	 */
    public static native int getNativeThreadId();
    
    /**
     * @param nativeProcessId A OS process-id
     * @return The list of thread-ids (LWPs) for the OS-threads owned by OS-process with process-id nativeProcessId
     */
    public static List<Integer> getNativeThreadIds(int nativeProcessId) {
    	String[] tasks = new File("/proc/" + nativeProcessId + "/task").list();
    	List<Integer> result = new ArrayList<Integer>();
    	for (String task : tasks) {
    		try {
    			result.add(Integer.parseInt(task));
    		} catch (NumberFormatException e) {
    			// ought not to happen - ignore
    		}
    	}
    	return result;
    }

    /**
     * @return The list of thread-ids (LWPs) for the OS-threads owned by OS-process owning the OS-thread running current Java-thread
     */
    public static List<Integer> getNativeThreadIds() {
    	return getNativeThreadIds(getNativeProcessId());
    }
    
    /**
     * Changes priority of the thread (LWP) on OS-level. Similar to linux sched_setscheduler/chrt
     * @param nativeThreadId The thread-id (LWP) of the thread to have its priority changed
     * @param policy The policy to use (use values from SchedPolicy)
     * @param schedParam The priority to set
     * @return The policy for the thread (a nonnegative integer). On error -1
     */
    public static native int setScheduler(int nativeThreadId, int policy, SchedParam schedParam);

    static {
        System.loadLibrary("DesignWareThreadUtilScheduleUtil");
    }
    
}
