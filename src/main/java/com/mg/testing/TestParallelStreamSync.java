package com.mg.testing;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class TestParallelStreamSync {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10000; i++)
			list.add(i);

		// Instant start = Instant.now();
		// list.forEach(e -> process(e));
		// Instant end = Instant.now();
		// Duration timeElapsed = Duration.between(start, end);
		// System.out.println("NORMAL:" + timeElapsed.toMillis());

		Instant start1 = Instant.now();
		list.parallelStream().forEach(e -> new TestParallelStreamSync().process(e));
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		Thread t1 = new Thread(() -> forkJoinPool.submit(() -> {
		}).invoke());

		t1.start();
		Instant end1 = Instant.now();
		Duration timeElapsed1 = Duration.between(start1, end1);
		System.out.println("PARALLEL:" + timeElapsed1.toMillis());
	}

	public synchronized void process(Integer i) {
		// synchronized (this) {
		for (int j = 0; j < 999999; j++)
			i = j + 1;
		for (int j = 0; j < 999999; j++)
			i = j + 1;
		for (int j = 0; j < 999999; j++)
			i = j + 1;
		for (int j = 0; j < 999999; j++)
			i = j + 1;
		// }
	}
}
