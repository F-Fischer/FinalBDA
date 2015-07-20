package com.usuarioslogin.general;

import java.util.regex.Pattern;

public class TestRegexp {
	public static void main(String[] args) {
		
		String input = "^/dist/.*";
		String output = input.replaceAll("^/.*?/", "/");
		System.out.println(output);
		
		if(Pattern.matches(input, "/dist/blablablabla"))
			System.out.println("JOYA");
	}

}
