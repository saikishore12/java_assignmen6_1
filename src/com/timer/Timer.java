package com.timer;

public class Timer extends Thread {
	protected int m_rate = 100;

	private int m_length;

	private int m_elapsed;

	public Timer(int length) {

		m_length = length;

		m_elapsed = 0;
	}

	public synchronized void reset() {
		m_elapsed = 0;
	}

	public void run() {
		// Keep looping
		for (;;) {

			try {
				Thread.sleep(m_rate);
			} catch (InterruptedException ioe) {
				continue;
			}

			synchronized (this) {

				m_elapsed += m_rate;

				if (m_elapsed > m_length) {

					timeout();
				}
			}

		}
	}

	public void timeout() {
		System.err.println("Network timeout occurred.... terminating");
		System.exit(1);
	}

	public static void main(String[] args) {
		Timer timer = new Timer(100);
		timer.start();

		timer.reset();
	}
}