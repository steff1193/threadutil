package dk.designware.threadutil.schedule;

public class UtilTest {

	public static void main(String[] args) {
		int tid = Util.getNativeThreadId();
        System.out.println("TID=" + tid);
	}

}
