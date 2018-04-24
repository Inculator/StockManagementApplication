package com.mg.testing;

public class TestMyTry {

	public static void main(String[] args) {
		//		int decimal = getBinaryValue(Long.parseLong("11111111000011010100"));
		//		System.out.println(decimal);

		System.out.println(Integer.valueOf("11111111000011010100", 2));
		System.out.println(Integer.valueOf("11110101011111010100", 2));
		System.out.println(Integer.valueOf("10101010101010101010", 2));
		System.out.println(Integer.valueOf("11110101011111010100", 2));
		System.out.println(Integer.valueOf("10101010101010101010", 2));
		System.out.println(Integer.valueOf("11111111111111010100", 2));
		System.out.println(Integer.valueOf("10101010101010101010", 2));
		System.out.println(Integer.valueOf("11110101011100010100", 2));
		System.out.println(Integer.valueOf("10101010101010101010", 2));
		System.out.println(Integer.valueOf("11110111100001010100", 2));
		System.out.println(Integer.valueOf("10101100101010101010", 2));
		System.out.println(Integer.valueOf("11110101011111010100", 2));
		System.out.println(Integer.valueOf("10101010101010101010", 2));
		System.out.println(Integer.valueOf("11111111000011010100", 2));
		System.out.println(Integer.valueOf("10101010000010101010", 2));
		System.out.println(Integer.valueOf("11110101011111010100", 2));
		System.out.println(Integer.valueOf("10000011100010101010", 2));
		System.out.println(Integer.valueOf("11110101111111010100", 2));
		System.out.println(Integer.valueOf("10101010101010101010", 2));
		System.out.println(Integer.valueOf("11110101100111010100", 2));
		System.out.println(Integer.valueOf("10111110001010101010", 2));

	}

	private static int getBinaryValue(Long n) {
		int decimal = 0, p = 0;

		while (n != 0) {
			decimal += n % 10 * Math.pow(2, p);
			n = n / 10;
			p++;
		}
		return decimal;
	}

}
