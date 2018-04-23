package com.mg.testing;

public class TestRunnable {

	public static void main(String[] args) {

		Runnable r = () -> {
			System.out.println("Here");
		};

		Thread t = new Thread(r);
		t.start();
	}
}
