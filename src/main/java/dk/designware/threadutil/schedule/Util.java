package dk.designware.threadutil.schedule;

public class Util {

	/**
	 * @return The thread-id (LWP id) for the OS-thread running current Java-thread
	 */
    public static native int getNativeThreadId();

    static {
        System.loadLibrary("DesignWareThreadUtilScheduleUtil");
    }
    
}
