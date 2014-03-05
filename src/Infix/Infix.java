package Infix;

import java.util.Stack;

public class Infix {

	/**
	 * Method converts from infix notation to postfix
	 * @param sb - input string
	 * @return output (result) string
	 */
	
	public String InfixToPostfix(String sb){
		Stack<Character> stack = new Stack<>();
		String outStr = "";
		for(int i = 0; i < sb.length(); i++){
			char ch = sb.charAt(i);
			if(Character.isDigit(ch) || Character.isLetter(ch)){
				outStr += ch;
			} else if(ch == '('){
				stack.add(ch);
			} else if(ch == ')'){
				while(stack.peek() != '('){
					outStr += stack.pop();
				}
				stack.pop();
			} else if(ch == '+' || ch == '-' || ch == '*' || ch =='/' || ch == '^'){
				if(stack.isEmpty()){
					stack.add(ch);
				} else {
					if(prior(ch) > prior(stack.peek())){
						stack.push(ch);
					} else { 
						while((stack.size() != 0) && (prior(stack.peek()) >= prior(ch))){
							outStr += stack.pop();
						}
						stack.push(ch);
					}
				}
			} 
		}
		while(stack.size() != 0){
			outStr += stack.pop();
		}
		return outStr;
	}
	
	/**
	 * Method converts from postfix notation to infix
	 * @param sb - input string
	 * @return output (result) string
	 */
	
	public String PostfixToInfix(String sb){
		Stack<String> stack = new Stack<>();
		for(int i = 0; i < sb.length(); i++){
			char ch = sb.charAt(i);
			if(Character.isDigit(ch) || Character.isLetter(ch)){
				stack.push(ch + "");
			} else if(ch == '+' || ch == '-' || ch == '*' || ch =='/' || ch == '^'){
				String ch1 = stack.pop();
				String ch2 = "";
				if(!stack.isEmpty()){
					ch2 = stack.pop();
				}
				if (prior(getOperation(ch1)) < prior(ch) && ch1.length() > 1 && ch2.length() == 1) {
					stack.push(ch2 + ch + "(" + ch1 + ")");
				} else if(prior(getOperation(ch2)) < prior(ch) && ch2.length() > 1 && ch1.length() == 1){
					stack.push("(" + ch2 + ")" + ch + ch1);
				} else if(ch1.length() > 1 && ch2.length() > 1){
					stack.push("(" + ch2 + ")" + ch + "(" + ch1 + ")");
				} else {
					stack.push(ch2 + ch + ch1);
				}
			}
		}
		return stack.peek();
	}
	
	/**
	 * Method determines the priority of operations
	 * @param ch - operation
	 * @return the priority
	 */
	
	private int prior(final char ch) {
		switch (ch) {
			case '^':
				return 4;
			case '*':
				return 3;
			case '/':
				return 3;
			case '-':
				return 2;
			case '+':
				return 2;
			case '(':
				return 1;
		}
		return 0;
	}

	/**
	 * Method get first operation in string
	 * @param str string with operations
	 * @return character - operation
	 */
	
	private char getOperation(String str){
		char ch;
		if(str.length() > 1){
			ch = str.charAt(1);
		} else {
			ch = str.charAt(0);
		}
		return ch;
	}
}
