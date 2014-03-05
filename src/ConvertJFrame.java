import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;

public class ConvertJFrame {

	private TextField doubleDec;
	private TextField maxCount;
	private int max;
	private JLabel doubleBin = new JLabel();
	private JLabel errorInfo1 = new JLabel();
	private JLabel errorInfo2 = new JLabel();
	
	public static void main(String[] args) {
		ConvertJFrame cJF = new ConvertJFrame();
		JFrame f = new JFrame("Sort And Search");
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridLayout(5,1));
		
		f.add(cJF.createPanel(f), BorderLayout.NORTH);
		f.add(cJF.createButton(f), BorderLayout.NORTH);

		f.add(cJF.doubleBin, BorderLayout.SOUTH);
		f.add(cJF.errorInfo1, BorderLayout.SOUTH);
		f.add(cJF.errorInfo2, BorderLayout.SOUTH);
		f.setVisible(true);
	}
	
	/**
	 * Method creates JPanel with one TextFields for double value in dec
	 * @param f - active JFrame
	 */
	
	private JPanel createPanel(JFrame f){
		
		JPanel createPanel = new JPanel();
		createPanel.setLayout(new GridLayout(2, 2));
		
		doubleDec = new TextField(5);
		createPanel.add(new JLabel("Value in 10"));
		createPanel.add(doubleDec);
		
		maxCount = new TextField(5);
		createPanel.add(new JLabel("Max digits after the point"));
		createPanel.add(maxCount);

		return createPanel;
	}
	
	/**
	 * Method creates JPanel with one JButton for transform dec value to bin
	 * @param f - active JFrame
	 */
	
	private JPanel createButton(JFrame f){
		JPanel createButton = new JPanel();
		createButton.setLayout(new GridLayout(1, 1));
		
		JButton fromDecToBin = new JButton("Transform");
		createButton.add(fromDecToBin);

		fromDecToBin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				double dec = setDoubleValue(doubleDec);
				max = setIntValue(maxCount);
				double tmp = dec;
				if (dec > 0 && dec < 10000000 && max > 0 && max < 30) {
					DoubleFromDecToBin to = new DoubleFromDecToBin();
					String intNum = to.decIntToBinary(to.fromDecToStrInt(dec));
					String fractNum = to.decFractToBinary(to.fromDecToStrFract(dec), max);
					if(!"0".equals(fractNum)){
						doubleBin.setText(tmp + " (10) = " + intNum +"."+ fractNum + " (2)");
					} else {
						doubleBin.setText(tmp + " (10) = " + intNum + " (2)");
					}
					errorInfo1.setText("");
					errorInfo2.setText("");
				} else {
					doubleBin.setText("");
					errorInfo1.setText("ERROR! Please, input correct value in fields.");
					errorInfo2.setText("Duoble positive value in first and [1; 29] in second field.");
				}
			}
		});
		return createButton;
	}
	
	/**
	 * Method gets text from TextField and parse it as double value
	 * @param field - name of TextField 
	 * @return double number
	 */
	
	private double setDoubleValue(TextField field){
		double value = -1;
		String str = field.getText();
		try{
			value = Double.parseDouble(str);
		} catch(NumberFormatException e){}
		return value;
	}
	
	/**
	 * Method gets text from TextField and parse it as integer value
	 * @param field - name of TextField 
	 * @return integer number
	 */
	
	private int setIntValue(TextField field){
		int value = -1;
		String str = field.getText();
		try{
			value = Integer.parseInt(str);
		} catch(NumberFormatException e){}
		return value;
	}
}