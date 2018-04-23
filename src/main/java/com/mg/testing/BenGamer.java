package com.mg.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BenGamer {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Integer levelCount = scanner.nextInt();
		Integer weaponCount = scanner.nextInt();
		Integer listLevelCount = levelCount;

		ArrayList<Levels> list = new ArrayList<>(levelCount);

		while (listLevelCount > 0) {
			list.add(new Levels(scanner.next()));
			listLevelCount--;
		}

		Map<Integer, Boolean> map = new HashMap<>();

		Collections.sort(list);

		list.forEach(level -> System.out.println(level.getLevel()));

		list.forEach(level -> {
			Boolean flag = true;

			if (level.getLevel().length() != weaponCount)
				flag = false;

			map.put(0, flag);
		});

		Double coinsUsed = 0.0;

		if (map.get(0)) {

			if (levelCount >= 1 && weaponCount >= 1 && levelCount <= 20 && weaponCount <= 20) {

				List<Integer> weaponsUsed = new ArrayList<>(weaponCount);

				for (Levels levels : list) {

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
					} else
						break;
				}
			}
		}
		System.out.println(coinsUsed.intValue());
	}

}

class Levels implements Comparable<Levels> {

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
			int decimal1 = getBinaryValue(Integer.parseInt(localLevel.getLevel()));
			int decimal2 = getBinaryValue(Integer.parseInt(this.level));
			if (decimal1 > decimal2)
				return 1;
			else if (decimal1 < decimal2)
				return -1;
			else
				return 0;
		}
	}

	private static int getBinaryValue(int n) {
		int decimal = 0, p = 0;

		while (n != 0) {
			decimal += n % 10 * Math.pow(2, p);
			n = n / 10;
			p++;
		}
		return decimal;
	}

	public String getLevel() {
		return level;
	}

}
