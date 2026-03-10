/**********************************************************************
 * From Arnold Gosling and Homes chapter 6 -- a simple program that
 * prints PING and PONG at different rates
 * Not a great way to do threads: can only inherit simgly -- and the
 * inherits all the Thread functionality - possibly overkill?
 * @author Garret
 */
public class PingPong extends Thread {
	private String word; // what word to print
	private int delay; // how long to pause

	public PingPong(String whatToSay, int delayTime) {
		word = whatToSay;
		delay = delayTime;
	}

	public void run() {
		try {
			for (;;) {
				System.out.print(word + " ");
				Thread.sleep(delay); // wait until next time
			}
		} catch (InterruptedException e) {
			return; // end this thread
		}
	}

	public static void main(String[] args) {
		new PingPong("ping", 33).start(); // 1/30 second
		new PingPong("PONG", 100).start(); // 1/10 second
	}
}