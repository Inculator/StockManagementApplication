package com.mg.testing;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskGame {

	public static void main(String[] args) {

		String A = "A";
		String B = "B";
		String H = "H";

		Integer days = 5;
		Integer holidays = 2;

		String[] iterationArray = null;

		HashMap<String, String> map = new HashMap<>();

		for(int i= 1;i<= days; i++){
			iterationArray[i] = A;
			if(i>1){
				if(iterationArray[i-1].equalsIgnoreCase(A))
					iterationArray[i] = B;
				if(iterationArray[i-1].equalsIgnoreCase(B))
					iterationArray[i] = H;
			}
		}


	}
}
