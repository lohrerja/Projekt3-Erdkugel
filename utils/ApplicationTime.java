package utils;

import Projekt3.Constants;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationTime extends Thread {
	//time in ms
	public double timeSinceStart = 0;
	public long currentTime = 0;
	public long formerTime = 0;
	private double timeScale = Constants.TIMESCALE;
	private final AtomicBoolean isPaused = new AtomicBoolean(true);
	private final AtomicBoolean running = new AtomicBoolean(true);

	public ApplicationTime() {
	}

	@Override
	public void run() {

		formerTime = System.currentTimeMillis();
		while (running.get()) {
			currentTime = System.currentTimeMillis();
			if (!isPaused.get()) {
				timeSinceStart += (currentTime - formerTime) * timeScale;
			}
			formerTime = currentTime;
		}
	}

	//returns the current timer in milliseconds
	public double getTime() {
		return timeSinceStart;
	}

	//returns the current time in seconds
	public double getTimeInSeconds() {
		return timeSinceStart / 1000;
	}

	// change the timer speed (1 = realtime)
	public void changeTimeScaling(double newValue) {
		timeScale = newValue;
	}

	public void pauseTime() {
		while (true) {
			if (isPaused.compareAndSet(isPaused.get(), true))
				System.out.println("Application Time is paused");
			return;
		}
	}

	public void continueTime() {
		while (true) {
			if (isPaused.compareAndSet(isPaused.get(), false))
				System.out.println("Application time continues");
			return;
		}
	}

	public void resetTime() {
		// stop and reset time
		if (isPaused.compareAndSet(isPaused.get(), true))
			this.timeSinceStart = 0;
			System.out.println("Application Time is reset");
	}
}
