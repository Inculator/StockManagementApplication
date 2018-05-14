package com.mg.testing;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

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
2

3 100.0
1 10 5 5
5 10 5 5
8 10 5 5
0 1 2


3 100.0
1 10 5 20
5 10 5 20
8 10 5 20

0 1 2
*/

public class FloodInJungle {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String firstEntry = scanner.nextLine();
		String[] firstLine = firstEntry.split(" ");
		Integer noOfTrees = Integer.parseInt(firstLine[0]);
		Double totalCapacity = Double.parseDouble(firstLine[1]);

		SortedSet<Integer> treesUsedSet = new TreeSet<>();
		Map<Integer, Tree> treeMap = new HashMap<>();

		for (int i = 0; i < noOfTrees; i++) {
			String[] treeLine = scanner.nextLine().split(" ");
			if (Integer.parseInt(treeLine[2]) >= 0 && Integer.parseInt(treeLine[2]) <= 15
					&& Integer.parseInt(treeLine[3]) >= 1 && Integer.parseInt(treeLine[3]) <= 200)
				treeMap.put(i, new Tree(Integer.parseInt(treeLine[0]), Integer.parseInt(treeLine[1]),
						Integer.parseInt(treeLine[2]), Integer.parseInt(treeLine[3])));
			else {
				scanner.close();
				System.out.println("-1");
				return;
			}
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

			Double totalEuclidieanThreshold = 0.0;

			for (Map.Entry<Integer, Tree> treeRemainingEntry : treeRemainingMap.entrySet()) {
				flag = 0;
				if (treeRemainingEntry.getValue().getMaxMonkey() > treeRemainingEntry.getValue().getThreshold()) {
					flag = 1;
					break;
				} else {
					Tree remainTree = treeRemainingEntry.getValue();
					totalEuclidieanThreshold = distance(new Point(tree.getXi(), tree.getYi()),
							new Point(remainTree.getXi(), remainTree.getYi()));
					if (totalEuclidieanThreshold > totalCapacity) {
						flag = 1;
						break;
					}
				}
			}

			if (flag == 1)
				continue;

			treesUsedSet.add(treeEntry.getKey());

		}

		if (treesUsedSet.isEmpty())
			System.out.println("-1");
		else {
			String treesUsed = "";
			for (Integer tree : treesUsedSet)
				treesUsed = treesUsed + tree + " ";
			System.out.println(treesUsed);
		}
		System.exit(0);
	}

	static double distance(Point point1, Point point2) {
		double xDiff = point1.x - point2.x;
		double xSqr = Math.pow(xDiff, 2);

		double yDiff = point1.y - point2.y;
		double ySqr = Math.pow(yDiff, 2);

		return Math.sqrt(xSqr + ySqr);
	}
}

class Point {

	Integer x;
	Integer y;

	Point(Integer x, Integer y) {
		this.x = x;
		this.y = y;
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

	public Integer getYi() {
		return yi;
	}

	public Integer getMaxMonkey() {
		return maxMonkey;
	}

	public Integer getThreshold() {
		return threshold;
	}

}