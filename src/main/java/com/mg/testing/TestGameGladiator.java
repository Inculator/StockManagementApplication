package com.mg.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestGameGladiator {

	public static void main(String[] args) {

		Integer levelCount = 8;
		Integer weaponCount = 4;

		ArrayList<String> list = new ArrayList<>();
		list.add("0101");

		String levels[] = list.toArray(new String[0]);

		Map<String, Double> coinsCount = new HashMap<>();

		String levelCombinations = "";
		for (int i = 0; i < levelCount; i++) {
			levelCombinations = levelCombinations + i;
		}
		Set<String> permutationSet = TestGameGladiator.generatePerm(levelCombinations);
		// permutationSet.forEach(value -> System.out.println(value));

		permutationSet.forEach(value -> {
			List<Integer> weaponsUsed = new ArrayList<>();
			Double coinsUsed = 0.0;
			char[] ch = value.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				Integer innerCoinCount = 0;
				String level = ch[i] + "";
				level = levels[Integer.parseInt(level)];
				char[] weaponChar = level.toCharArray();
				for (int j = 0; j < weaponChar.length; j++) {
					if (weaponChar[j] == '1' && !weaponsUsed.contains(j)) {
						weaponsUsed.add(j);
						innerCoinCount = innerCoinCount + 1;
					}
				}
				coinsUsed = coinsUsed + Math.pow(innerCoinCount, 2);
			}
			coinsCount.put(value, coinsUsed);

		});

		Double min = Collections.min(coinsCount.values());
		System.out.println(min.intValue());
	}

	public static Set<String> generatePerm(String input) {
		Set<String> set = new HashSet<String>();
		if (input == "")
			return set;

		Character a = input.charAt(0);

		if (input.length() > 1) {
			input = input.substring(1);

			Set<String> permSet = generatePerm(input);

			for (String x : permSet) {
				for (int i = 0; i <= x.length(); i++) {
					set.add(x.substring(0, i) + a + x.substring(i));
				}
			}
		} else {
			set.add(a + "");
		}
		return set;
	}
}
