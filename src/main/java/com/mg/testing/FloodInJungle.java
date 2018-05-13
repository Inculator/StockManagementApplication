package com.mg.testing;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*3 100.0
1 10 5 5
5 10 5 1
8 10 5 4
-1

3 100.0
1 10 5 5
5 10 5 1
8 10 5 5
1

3 100.0
1 10 5 5
5 10 5 5
8 10 5 4
1

3 100.0
1 10 5 5
5 10 5 5
8 10 5 5
3

3 40.0
1 10 5 5
5 10 5 1
8 10 5 5
1

3 40.0
1 10 5 5
5 10 5 5
8 10 5 4
-1*/

public class FloodInJungle {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String firstEntry = scanner.nextLine();
		String[] firstLine = firstEntry.split(" ");
		Integer noOfTrees = Integer.parseInt(firstLine[0]);
		Double totalCapacity = Double.parseDouble(firstLine[1]);

		Integer treesUsed = 0;

		Map<Integer, Tree> treeMap = new HashMap<>();

		for (int i = 0; i < noOfTrees; i++) {
			String[] treeLine = scanner.nextLine().split(" ");
			treeMap.put(i, new Tree(Integer.parseInt(treeLine[0]), Integer.parseInt(treeLine[1]),
					Integer.parseInt(treeLine[2]), Integer.parseInt(treeLine[3])));
		}
		scanner.close();

		for (Map.Entry<Integer, Tree> treeEntry : treeMap.entrySet()) {
			Integer flag = 0;
			Tree tree = treeEntry.getValue();

			Map<Integer, Tree> treeRemainingMap = new HashMap<>();
			for (Map.Entry<Integer, Tree> treeEntry1 : treeMap.entrySet()) {
				if (treeEntry.getKey() != treeEntry1.getKey())
					treeRemainingMap.put(treeEntry1.getKey(), treeEntry1.getValue());
			}

			for (Map.Entry<Integer, Tree> treeRemainingEntry : treeRemainingMap.entrySet()) {
				if (treeRemainingEntry.getValue().getMaxMonkey() > treeRemainingEntry.getValue().getThreshold()
						|| treeRemainingEntry.getValue().getMaxMonkey() < treeRemainingEntry.getValue()
								.getThreshold()) {
					flag = 1;
					break;
				}
			}

			if (flag == 1)
				continue;

			Double totalThreshold = 0.0;
			for (Map.Entry<Integer, Tree> treeRemainingEntry : treeRemainingMap.entrySet()) {
				Tree remainTree = treeRemainingEntry.getValue();
				totalThreshold = totalThreshold
						+ Math.abs(remainTree.getXi() - tree.getXi()) * remainTree.getThreshold();
			}

			if (totalThreshold <= totalCapacity)
				treesUsed = treesUsed + 1;
		}

		if (treesUsed == 0)
			System.out.println("-1");
		else
			System.out.println(treesUsed);

		System.exit(0);
	}
}

class Tree {
	private Integer xi;
	private Integer yi;
	private Integer maxMonkey;
	private Integer threshold;

	public Tree(Integer xi, Integer yi, Integer maxMonkey, Integer threshold) {
		this.xi = xi;
		this.yi = yi;
		this.maxMonkey = maxMonkey;
		this.threshold = threshold;
	}

	public Integer getXi() {
		return xi;
	}

	public void setXi(Integer xi) {
		this.xi = xi;
	}

	public Integer getYi() {
		return yi;
	}

	public void setYi(Integer yi) {
		this.yi = yi;
	}

	public Integer getMaxMonkey() {
		return maxMonkey;
	}

	public void setMaxMonkey(Integer maxMonkey) {
		this.maxMonkey = maxMonkey;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

}