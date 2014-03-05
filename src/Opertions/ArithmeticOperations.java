package Opertions;

public class ArithmeticOperations {

	/**
	 * Method make invert number in bin
	 * @param originalNum - original number
	 * @return inverted number
	 */
	
	private String invert(String originalNum){
		StringBuffer resultNum = new StringBuffer(originalNum);
		for(int i = resultNum.length()-1; i >= 0; i--){
			char oneSimbol = resultNum.charAt(i);
			if(oneSimbol == '1'){
				resultNum.setCharAt(i, '0');
			} else if(oneSimbol == '0'){
				resultNum.setCharAt(i, '1');
			}
		}
		return new String(resultNum);
	}
	
	/**
	 * Method adds 1 to given number originalNum
	 * @param originalNum - given number for addition
	 * @return sum of two numbers originalNum and 1 (originalNum+1)
	 */
	
	private String plusOne(String originalNum){
		StringBuffer resultNum = new StringBuffer(originalNum);
		char additionalNum = '0';
		int tmpIndex = resultNum.length()-1;
		if (tmpIndex > 0) {
			char oneSimbol = resultNum.charAt(tmpIndex);
			if (oneSimbol == '1') {
				resultNum.setCharAt(tmpIndex, '0');
				additionalNum = '1';
			} else if (oneSimbol == '0') {
				resultNum.setCharAt(tmpIndex, '1');
				additionalNum = '0';
			}
			for (int i = tmpIndex - 1; i >= 0; i--) {
				oneSimbol = resultNum.charAt(i);
				if (additionalNum == '1') {
					if (oneSimbol == '1') {
						resultNum.setCharAt(i, '0');
						additionalNum = '1';
					} else if (oneSimbol == '0') {
						resultNum.setCharAt(i, '1');
						additionalNum = '0';
					}
				}
			}
		}
		return new String(resultNum);
	}
	
	/**
	 * Method adds two numbers
	 * @param firstNum - first number for addition
	 * @param secondNum - second number for addition
	 * @return - sum of two numbers (firstNum + secondNum) in bin
	 */
	
	public String addition(String firstNum, String secondNum){
		String num = new String(firstNum);
		String result = "";
		for(int i = firstNum.length()-1; i > 0; i--){
			if(secondNum.charAt(i) == '1'){
				num = plusOne(num.substring(0,i+1));
				result = num.charAt(i) + result;
			} else {
				result = num.charAt(i) + result;
			}
		}
		if(num.charAt(0) == '1'){
			if(secondNum.charAt(0) == '1'){
				result = '0' + result;
			} else if(secondNum.charAt(0) == '0'){
				result = '1' + result;
			}
		} else if(num.charAt(0) == '0'){
			if(secondNum.charAt(0) == '1'){
				result = '1' + result;
			} else if(secondNum.charAt(0) == '0'){
				result = '0' + result;
			}
		}
		return result;
	}

	/**
	 * Method subtracts one number from another
	 * @param firstNum 
	 * @param secondNum
	 * @return firstNum - secondNum
	 */
	
	public String subtraction(String firstNum, String secondNum){
		secondNum = invert(secondNum);
		secondNum = plusOne(secondNum);
		return addition(firstNum, secondNum);
	}

	/**
	 * Method realize algorithm Booth for multiply two number in bin
	 * @param firstNum 
	 * @param secondNum
	 * @return firstNum*secondNum
	 */
	
	public String boothAlgorithm(String firstNum, String secondNum){
		StringBuffer a = new StringBuffer(firstNum);
		for(int i = 0; i < firstNum.length()-1; i++){
			a.setCharAt(i, '0');
		}
		StringBuffer m = new StringBuffer(firstNum);
		StringBuffer q = new StringBuffer(secondNum);
		StringBuffer q1 = new StringBuffer("0");
		int n = firstNum.length();
		for(int i = n; i > 0; i--){
			if(q.charAt(q.length()-1) == '1' && q1.charAt(0) == '0'){
				a = new StringBuffer(subtraction(new String(a), new String(m)));
			} else if(q.charAt(q.length()-1) == '0' && q1.charAt(0) == '1'){
				a = new StringBuffer(addition(new String(a), new String(m)));
			} 
			q1.setCharAt(0, q.charAt(q.length()-1));
			for(int j = q.length()-1; j > 0; j--){
				q.setCharAt(j, q.charAt(j-1));
			}
			q.setCharAt(0, a.charAt(a.length()-1));
			for(int j = a.length()-1; j > 0; j--){
				a.setCharAt(j, a.charAt(j-1));
			}
			if(a.charAt(1) == '1'){
				a.setCharAt(0, '1');
			} else if(a.charAt(1) == '0'){
				a.setCharAt(0, '0');
			}
		}
		return new String(a) + new String(q);
	}

	/**
	 * Method realize algorithm for divide two number 
	 * @param firstNum
	 * @param secondNum
	 * @return firstNum/secondNum
	 */
	
	public String divide(String firstNum, String secondNum){
		
		StringBuffer a = new StringBuffer(firstNum);
		for(int i = 0; i < firstNum.length(); i++){
			a.setCharAt(i, '0');
		}
		StringBuffer zero = new StringBuffer(a);
		StringBuffer tmp = new StringBuffer(a);
		StringBuffer q = new StringBuffer(firstNum);
		StringBuffer m = new StringBuffer(secondNum);
		int n = firstNum.length();
		for(int i = n; i > 0; i--){
			for(int j = 0; j < a.length()-1; j++){
				a.setCharAt(j, a.charAt(j+1));
			}
			a.setCharAt(a.length()-1, q.charAt(0));
			for(int j = 0; j < q.length()-1; j++){
				q.setCharAt(j, q.charAt(j+1));
			}
			q.setCharAt(q.length()-1, '0');
			tmp  = new StringBuffer(a);
			if(a.charAt(0) == m.charAt(0)){
				a = new StringBuffer(subtraction(new String(a), new String(m)));
			} else {
				a = new StringBuffer(addition(new String(a), new String(m)));
			}
			if(a.charAt(0) == tmp.charAt(0) || (q.equals(zero) && a.equals(zero))){
				q.setCharAt(q.length()-1, '1');
			} else if(a.charAt(0) != tmp.charAt(0) && (!q.equals(zero) || !a.equals(zero))){
				q.setCharAt(q.length()-1, '0');
				a = new StringBuffer(tmp);
			}
		}
		if(firstNum.charAt(0) == secondNum.charAt(0)){
			return new String(q) + " .. " + new String(a);
		} else { 
			q = new StringBuffer(invert(new String(q)));
			q = new StringBuffer(plusOne(new String(q)));
			return new String(q) + " .. " + new String(a);
		}
	}
}