package FromDecToBinDouble;
public class DoubleFromDecToBin {

	/**
	 * Method parse double value and return fraction part
	 * @param number - number for parsing
	 * @return - fraction part
	 */
	
	public double fromDecToStrFract(double number){
		String numberStr = "" + number;
		int index = numberStr.indexOf('.');
		String fractNum = null;
		if(index > 0){
			fractNum = "0" + numberStr.substring(index);
		}
		double dNum = Double.parseDouble(fractNum);
		return dNum;
	}
	
	/**
	 * Method parse double value and return integer part
	 * @param number - number for parsing
	 * @return - integer part
	 */
	
	public int fromDecToStrInt(double number){
		String numberStr = "" + number;
		int index = numberStr.indexOf('.');
		String intNum = null;
		if(index > 0){
			intNum = numberStr.substring(0, index);
		}
		int dNum = Integer.parseInt(intNum);
		return dNum;
	}
	
	/**
	 * Method converts integer part from dec into bin 
	 * @param dec - integer part in dec
	 * @return binary value
	 */
	
	public String decIntToBinary(int dec){
		String binary = "";
		int tmp;
		do{
			tmp = dec % 2;
			dec = dec/2;
			binary = tmp + binary;
		} while(dec != 0);
		return binary;
	}
	
	/**
	 * Method converts fraction part from dec into bin
	 * @param dec - fraction part in dec
	 * @param max - maximum number of digits after the decimal point
	 * @return binary value
	 */
	
	public String decFractToBinary(double dec, int max){
		String binary = "";
		int count = 0;
		do{
			dec *= 2;
			if(dec < 1){
				binary += 0;
			} else {
				binary += 1;
			}
			dec = fromDecToStrFract(dec);
		} while(dec != 0 && ++count < max);
		return binary;
	}
}
