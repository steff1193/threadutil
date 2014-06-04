package dk.designware.threadutil.schedule;

public class Util {

	/**
	 * @return The process-id for the OS-process parenting OS-thread running current Java-thread
	 */
    public static native int getNativeProcessId();

	/**
	 * @return The thread-id (LWP id) for the OS-thread running current Java-thread
	 */
    public static native int getNativeThreadId();

    static {
        System.loadLibrary("DesignWareThreadUtilScheduleUtil");
    }
    
}
