package com.mg.testing;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

//2 3 1 2 1 3 3 3 2 4
//0 2 5 3 4 6 6 6 8 4

//2 1 1 1 1 3 3 3 2 4
//0 1 2 3 3 5 5 5 6 4

//2 1 1 1 1 3 3 3 2 4
//0 1 2 5 3 5 5 5 6 4

//1 1 1 1 1 3 3 3 2 4 1 1 1 1 1 1
//0 1 2 5 3 5 5 5 6 4 10 10 0 0 0 3

//2 1 1 0 1 0 0 0 0 0 
//0 1 2 5 3 5 5 5 6 4

//2 4 4 2 4
//1 4 1 6 4

//50
//1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1

//30
//1 2 5 4 8 7 5 2 1 4 5 2 5 4 6 9 8 7 5 2 1 4 5 2 4 2 1 2 1 5
//0 0 0 0 2 5 4 1 7 8 5 4 6 3 2 1 4 1 0 0 0 1 4 2 4 2 1 5 1 2

/*
7
1 2 3 2 3 1 1
1 2 3 3 1 6 6
 
 6
 
18
1000 2000 1000 3000 4000 5000 1000 2000 1000 3000 4000 5000 1000 2000 1000 3000 4000 5000
1 1 0 2 0 8 5 4 8 4 5 2 1 5 6 8 7 4
 */
public class BobTheBear {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String salmons = scanner.nextLine();
		String lenths = scanner.nextLine();
		String time = scanner.nextLine();

		scanner.close();

		Integer salmonsNumber = Integer.parseInt(salmons);
		String[] salmonLenths = lenths.split(" ");
		String[] salmontime = time.split(" ");
		Integer finalSalmonsUsed = 0;

		if (salmonLenths.length == salmonsNumber && salmontime.length == salmonsNumber && salmonsNumber >= 1
				&& salmonsNumber <= 1000) {
			for (int i = 0; i < salmonLenths.length; i++) {
				if ("0".equalsIgnoreCase(salmonLenths[i])) {
					System.out.println(finalSalmonsUsed);
					return;
				}
			}

			ArrayList<Integer> headList = new ArrayList<>();
			ArrayList<Integer> tailList = new ArrayList<>();

			for (int i = 0; i < salmonsNumber; i++) {
				headList.add(Integer.parseInt(salmontime[i]));
				tailList.add(Integer.parseInt(salmonLenths[i]) + Integer.parseInt(salmontime[i]));
			}

			Integer firstHead = Collections.min(headList);
			Integer lastTail = Collections.max(tailList);

			Map<Integer, ArrayList<Integer>> salmonsMap = new HashMap<>();

			for (int t = firstHead; t <= lastTail; t++) {

				ArrayList<Integer> salmonsUsed = new ArrayList<>();
				for (int i = 0; i < salmonsNumber; i++) {
					if (t >= headList.get(i) && t <= tailList.get(i))
						salmonsUsed.add(i + 1);
				}
				salmonsMap.put(t, salmonsUsed);
			}

			Integer maxSalmonsAtATimeKey = Collections
					.max(salmonsMap.entrySet(), Comparator.comparingInt(entry -> entry.getValue().size())).getKey();

			Map<Integer, ArrayList<Integer>> salmonsHigherMap = salmonsMap.entrySet().stream()
					.filter(entry -> entry.getValue().size() == salmonsMap.get(maxSalmonsAtATimeKey).size())
					.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

			final Integer maxSalmonsUsed = salmonsMap.get(maxSalmonsAtATimeKey).size();
			ArrayList<Integer> max = new ArrayList<>();

			for (Map.Entry<Integer, ArrayList<Integer>> salmonEntry : salmonsHigherMap.entrySet()) {

				Map<Integer, ArrayList<Integer>> salmonsLowerMap = salmonsMap.entrySet().parallelStream()
						.filter(entry -> entry.getValue().size() < salmonsMap.get(maxSalmonsAtATimeKey).size())
						.map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), new ArrayList<>(e.getValue())))
						.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

				Integer innerMapMaxSalmons = 0;
				if (salmonsLowerMap.size() > 0) {
					Map<Integer, ArrayList<Integer>> salmonsLowerInnerMap = salmonsLowerMap;

					salmonsLowerInnerMap.entrySet()
							.forEach(entry -> entry.getValue().removeAll(salmonEntry.getValue()));

					innerMapMaxSalmons = Collections.max(salmonsLowerInnerMap.entrySet(),
							Comparator.comparingInt(entry -> entry.getValue().size())).getValue().size();

				}
				max.add(maxSalmonsUsed + innerMapMaxSalmons);
			}
			finalSalmonsUsed = Collections.max(max);
		}
		System.out.println(finalSalmonsUsed);
	}

}
