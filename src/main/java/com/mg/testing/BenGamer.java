package com.mg.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BenGamer {

	private static Integer levelCounter = 0;
	private static Double coinsUsed = 0.0;
	private static String reverse = "";
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Integer levelCount = scanner.nextInt();
		Integer weaponCount = scanner.nextInt();
		Integer listLevelCount = levelCount;
		levelCounter = levelCount;
		ArrayList<Levels> list = new ArrayList<>(levelCount);
		HashMap<Integer, Integer> countMaxMap = new HashMap<>();

		while (listLevelCount > 0) {
			list.add(new Levels(scanner.next()));
			listLevelCount--;
		}

		Map<Integer, Boolean> map = new HashMap<>();

		list.forEach(level -> {
			Boolean flag = true;
			if (level.getLevel().length() != weaponCount)
				flag = false;
			map.put(0, flag);
		});

		if (map.get(0) && (levelCount >= 1 && weaponCount >= 1 && levelCount <= 20 && weaponCount <= 20)) {
			List<Integer> weaponsUsed = new ArrayList<>(weaponCount);
			Collections.sort(list);
			recursiveCoinsCount(weaponCount, list, weaponsUsed);
			countMaxMap.put(1, coinsUsed.intValue());

			coinsUsed = 0.0;
			weaponsUsed.clear();
			levelCounter = levelCount;
			reverse = "1";
			Collections.sort(list);
			recursiveCoinsCount(weaponCount, list, weaponsUsed);
			countMaxMap.put(2, coinsUsed.intValue());
		}
		System.out.println(Collections.min(countMaxMap.values()));
	}

	private static void recursiveCoinsCount(Integer weaponCount, ArrayList<Levels> list,
			List<Integer> weaponsUsed) {
		reverse = "";
		ArrayList<Levels> innerList = new ArrayList<>();
		ArrayList<Levels> finalInnerLevelList = new ArrayList<>();

		list.forEach(level-> innerList.add(level));

		Levels levels = list.get(0);
		levelCounter --;
		innerList.remove(levels);

		if (levels.getLevel().length() == weaponCount) {
			Double innerCoinsUsed = 0.0;
			String level = levels.getLevel();
			char[] weaponChar = level.toCharArray();

			for (int j = 0; j < weaponChar.length; j++) {
				if (weaponChar[j] == '1' && !weaponsUsed.contains(j)) {
					weaponsUsed.add(j);
					innerCoinsUsed = innerCoinsUsed + 1;
				}
			}
			coinsUsed = coinsUsed + Math.pow(innerCoinsUsed, 2);
		}

		for(Levels level1: innerList)
		{
			char[] chLevel = level1.getLevel().toCharArray();
			for (int i = 0; i < chLevel.length; i++)
				if (chLevel[i] == '1' && weaponsUsed.contains(i))
					chLevel[i] = '0';
			finalInnerLevelList.add(new Levels(String.valueOf(chLevel)));
		}
		Collections.sort(finalInnerLevelList);

		while(levelCounter>0)
			recursiveCoinsCount(weaponCount, finalInnerLevelList, weaponsUsed);
	}

	static class Levels implements Comparable<Levels> {

		String level;

		public Levels(String level) {
			this.level = level;
		}

		@Override
		public int compareTo(Levels localLevel) {
			int countOf1InLocal = 0;
			int countOf1InClass = 0;

			char[] ch = localLevel.getLevel().toCharArray();
			for (int i = 0; i < ch.length; i++)
				if (ch[i] == '1')
					countOf1InLocal = countOf1InLocal + 1;

			char[] chClass = this.level.toCharArray();
			for (int i = 0; i < chClass.length; i++)
				if (chClass[i] == '1')
					countOf1InClass = countOf1InClass + 1;

			if (countOf1InClass > countOf1InLocal)
				return 1;
			else if (countOf1InClass < countOf1InLocal)
				return -1;
			else {
				int decimal1 = Integer.valueOf(localLevel.getLevel(), 2);
				int decimal2 = Integer.valueOf(this.level, 2);

				if("".equalsIgnoreCase(reverse)){
					if (decimal1 > decimal2)
						return 1;
					else if (decimal1 < decimal2)
						return -1;
				} else if("1".equalsIgnoreCase(reverse)){
					if (decimal1 < decimal2)
						return 1;
					else if (decimal1 > decimal2)
						return -1;
				}
				return 0;
			}
		}

		public String getLevel() {
			return level;
		}

	}
}
