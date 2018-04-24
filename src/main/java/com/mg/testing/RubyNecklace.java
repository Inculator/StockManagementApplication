package com.mg.testing;

/* 
 * Enter your code here. Read input from STDIN. Print your output to STDOUT. 
 * Your class should be named CandidateCode.
 */

import java.io.*;
import java.util.*;
public class RubyNecklace {
	public static void main(String args[] ) throws Exception {


		Scanner scanner = new Scanner(System.in);

		Integer blue = scanner.nextInt();
		Integer red = scanner.nextInt();
		Integer yellow = scanner.nextInt();
		Integer green = scanner.nextInt();

		Integer totalRuby = blue + red + yellow + green;

		String currentRuby = "";

		List<String> necklace = new ArrayList<>();

		if(totalRuby >0 && blue <=2000 && red<=2000 && yellow<=2000 && green<=2000){
			if(blue != 0){
				currentRuby = "b";
				necklace.add("b");
				blue = blue-1;
				totalRuby = totalRuby -1;
			}
			else if(blue == 0 && red !=0){
				currentRuby = "r";
				necklace.add("r");
				red = red-1;
			}
			else if(blue == 0 && red ==0 && green!=0){
				currentRuby = "g";
				necklace.add("g");
				green = green-1;
			}
			else if(blue == 0 && red ==0 && green == 0 && yellow!=0){
				currentRuby = "y";
				necklace.add("y");
				yellow = yellow-1;
			}
		}
		totalRuby = totalRuby -1;
		boolean flag = false;

		if(!"".equalsIgnoreCase(currentRuby)){
			while(totalRuby >= 0){

				if("b".equalsIgnoreCase(currentRuby)){
					if(blue!=0){
						necklace.add("b");
						currentRuby = "b";
						blue = blue-1;
					}
					else if(red != 0){
						necklace.add("r");
						currentRuby = "r";
						red = red-1;
					}
					else
						flag = true;
				}

				else if("r".equalsIgnoreCase(currentRuby)){
					if(green!=0){
						necklace.add("g");
						currentRuby = "g";
						green = green-1;
					}
					else if(yellow != 0){
						necklace.add("y");
						currentRuby = "y";
						yellow = yellow-1;
					}
					else
						flag = true;
				}

				else if("y".equalsIgnoreCase(currentRuby)){
					if(blue!=0){
						necklace.add("b");
						currentRuby = "b";
						blue = blue-1;
					}
					else if(red != 0){
						necklace.add("r");
						currentRuby = "r";
						red = red-1;
					}
					else
						flag = true;
				}

				else if("g".equalsIgnoreCase(currentRuby)){
					if(green!=0){
						necklace.add("g");
						currentRuby = "g";
						green = green-1;
					}
					else if(yellow != 0){
						necklace.add("y");
						currentRuby = "y";
						yellow = yellow-1;
					}
					else
						flag = true;
				}

				if(flag)
					break;
				totalRuby = totalRuby - 1;
			}
		}
		System.out.println(necklace.size());

	}
}


