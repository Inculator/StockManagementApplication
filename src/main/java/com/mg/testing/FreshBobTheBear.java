package com.mg.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FreshBobTheBear {

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

		if (salmonsNumber >= 1 && salmonsNumber <= 1000 && salmonLenths.length == salmonsNumber
				&& salmontime.length == salmonsNumber) {

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

			Map<Integer, ArrayList<Integer>> salmonsMap = new HashMap<>();

			Integer firstHead = Collections.min(headList);
			Integer lastTail = Collections.max(tailList);

			for (int t = firstHead; t <= lastTail; t++) {

				ArrayList<Integer> salmonsUsed = new ArrayList<>();
				for (int i = 0; i < salmonsNumber; i++) {
					if (t >= headList.get(i) && t <= tailList.get(i))
						salmonsUsed.add(i + 1);
				}
				salmonsMap.put(t, salmonsUsed);
			}
			ArrayList<Integer> salmonsUsedList = new ArrayList<>();

			for (Map.Entry<Integer, ArrayList<Integer>> salmonTimeEntry : salmonsMap.entrySet()) {
				Integer salmonsUsed = 0;
				ArrayList<Integer> list = new ArrayList<>();
				salmonsUsed = salmonTimeEntry.getValue().size();
				for (Map.Entry<Integer, ArrayList<Integer>> salmonTimeRemainingEntry : salmonsMap.entrySet()) {
					if (salmonTimeRemainingEntry.getKey() != salmonTimeEntry.getKey()) {
						ArrayList<Integer> list1 = new ArrayList<>();
						list1 = salmonTimeRemainingEntry.getValue();
						ArrayList<Integer> list2 = new ArrayList<>();
						for (Integer i : list1) {
							if (!salmonTimeEntry.getValue().contains(i))
								list2.add(i);
						}
						list.add(list2.size());
					}
				}
				salmonsUsed = salmonsUsed + Collections.max(list);

				salmonsUsedList.add(salmonsUsed);
			}

			System.out.println(Collections.max(salmonsUsedList));
			System.exit(0);
		}
	}
}
