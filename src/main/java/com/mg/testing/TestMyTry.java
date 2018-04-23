package com.mg.testing;

public class TestMyTry {

	public static void main(String[] args) {
		int decimal = getBinaryValue(11001);
		System.out.println(decimal);
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

}
