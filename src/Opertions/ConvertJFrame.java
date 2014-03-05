package Opertions;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;

public class ConvertJFrame {

	private TextField directCodeFirst;
	private TextField operation;
	private TextField directCodeSecond;
	private JLabel result = new JLabel();
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

		f.add(cJF.result, BorderLayout.SOUTH);
		f.add(cJF.errorInfo1, BorderLayout.SOUTH);
		f.add(cJF.errorInfo2, BorderLayout.SOUTH);
		f.setVisible(true);
	}
	
	/**
	 * Method creates JPanel with two TextFields for binary values and one field for operation
	 * @param f - active JFrame
	 */
	
	private JPanel createPanel(JFrame f){
		
		JPanel createPanel = new JPanel();
		createPanel.setLayout(new GridLayout(3, 2));
		
		directCodeFirst = new TextField(5);
		createPanel.add(new JLabel("Value in direct code"));
		createPanel.add(directCodeFirst);
		
		operation = new TextField(5);
		createPanel.add(new JLabel("Operation"));
		createPanel.add(operation);
		
		directCodeSecond = new TextField(5);
		createPanel.add(new JLabel("Second value in direct code"));
		createPanel.add(directCodeSecond);

		return createPanel;
	}
	
	/**
	 * Method creates JPanel with one JButton for doing operations
	 * @param f - active JFrame
	 */
	
	private JPanel createButton(JFrame f){
		JPanel createButton = new JPanel();
		createButton.setLayout(new GridLayout(1, 1));
		
		JButton fromDecToBin = new JButton("Calculate");
		createButton.add(fromDecToBin);

		fromDecToBin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String first = directCodeFirst.getText();
				String second = directCodeSecond.getText();
				String op = operation.getText();
				if(first.length() == second.length() && op.length() == 1){
					int i = 0;
					boolean okFormat = true;
					while(i < first.length() && okFormat){
						if(first.charAt(i) != '0' && first.charAt(i) != '1' 
								&& second.charAt(i) != '0' && second.charAt(i) != '1'){
							okFormat = false;
							errorInfo1.setText("ERROR! Numbers must contain only '0' and '1'.");
							errorInfo2.setText("");
						}
						i++;
					}
					if (okFormat) {
						if(first.length() % 4 != 0){
							for(int k = 0; k < first.length()%4; k++){
								first = '0' + first;
								second = '0' + second;
							}
						}
						ArithmeticOperations ao = new ArithmeticOperations();
						errorInfo1.setText("");
						errorInfo2.setText("");
						switch (op) {
						case "+":
							result.setText(ao.addition(first, second));
							break;
						case "-":
							result.setText(ao.subtraction(first, second));
							break;
						case "*":
							result.setText(ao.boothAlgorithm(first, second));
							break;
						case "/":
							result.setText(ao.divide(first, second));
							break;
						default:

							break;
						}
					}
				} else {
					errorInfo1.setText("ERROR! Numbers must be the same length.");
					errorInfo2.setText("Operation must be: +, -, *, /.");
				}
				
			}
		});
		return createButton;
	}
}