package com.mg.testing;

public class IntefaceTest implements Interface1, Interface2 {

	@Override
	public void show() {
		System.out.println("ABC");
	}

	public static void main(String[] args) {
		Interface1 i = new IntefaceTest();
		i.show();
		new IntefaceTest().show();
	}
}
