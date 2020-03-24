package assignment4;
//Linh Nguyen
import java.util.Arrays;

import algs4.Stack;
import algs4.StdIn;
import algs4.StdOut;
import week3inclass.ArrayStack;


public class ParseXML {
	public static void main(String[] args) {
		StdOut.print("Enter the name of the XML file: ");//start with "data/", end with .xml
		String uinput = StdIn.readLine();
		StdIn.fromFile(uinput);
		String ftext = StdIn.readAll();
		String[] stoken = ftext.split("\n");
		
		Stack<String> stack= new Stack<String>();
		
		StdOut.println("stack: "+stack);
		for(String t: stoken) {
			StdOut.println("current token: "+t);
			String tagName = tagName(t);
			StdOut.println("tagname check: "+tagName);////sanity check
			StdOut.println("isTag: "+isTag(t));
			StdOut.println("isWord: "+isWord(t));////sanity check
			if(isOpeningTag(t) == true) {///////
				//if token is opening tag
				stack.push(tagName);
			}
			else if(isClosingTag(t)==true) {
				//if token is closing tag
				String errorMESS = "This closing tag has no matching opening tag.";
				if(stack.isEmpty()==true) {
					StdOut.println(errorMESS);
					StdOut.println("closing tag: "+t);
					//StdOut.println("stack: "+stack);////////sanity check
					//exit the program here
					System.exit(0);
				}
				
				String matchCheck = stack.pop();
				if(!(tagName.equals(matchCheck))) {
					StdOut.println(errorMESS);
					//StdOut.println("matchCheck: "+matchCheck);/////////sanitycheck
					StdOut.println("closing tag: "+t);
					//exit program
					System.exit(0);
				}
			}//end of else if
		}//end of for loop
		if(stack.isEmpty() == false) {
			StdOut.println("This opening tag has no matching closing tag.");
			StdOut.println("opening tag: "+stack);
			
		}
		else {
			StdOut.println("This XML is correctly formatted");
		}
	}//end of main
	
	public static boolean isTag(String token) {
		if(token.contains("<") && token.contains(">")==true) {
			if(token.charAt(0)=='<') {
				return true;
			}
		}
		return false;
	}
	public static boolean isWord(String token) {
		if(token.charAt(0) != '<') {		
			return true;
		}
		return false;
	}
	public static boolean isClosingTag(String token) {
		if(token.charAt(1) =='/' && isTag(token) ==true) {
			return true;
		}
		return false;
	}
	public static boolean isOpeningTag(String token) {
		if(token.charAt(1) !='/' && isTag(token)==true) {
			return true;
		}
		return false;
	}
	public static String tagName(String token) {
		if(isTag(token)==true) {
			char[] f = token.toCharArray();
			String tn = "";
			for(int i = 0; i<f.length;i++) {
				boolean charCheck = Character.isAlphabetic(f[i]);
				if(charCheck==true) {
					tn+=f[i];
				}
			}
			return tn;
		}
		return null;	
	}
}
