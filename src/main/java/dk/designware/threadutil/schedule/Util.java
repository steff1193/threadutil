package dk.designware.threadutil.schedule;

public class Util {

    public static native int getNativeThreadId();

    static {
        System.loadLibrary("DesignWareThreadUtilScheduleUtil");
    }
    
}
