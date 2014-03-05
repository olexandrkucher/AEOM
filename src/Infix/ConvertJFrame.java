package Infix;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;

public class ConvertJFrame {

	private TextField infix;
	private TextField result;
	private byte fromTo = 0;
	
	public static void main(String[] args) {
		ConvertJFrame cJF = new ConvertJFrame();
		JFrame f = new JFrame("Infix And Postfix");
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridLayout(5,1));
		
		f.add(cJF.createPanel(f), BorderLayout.NORTH);
		f.add(cJF.radioPanel(f), BorderLayout.NORTH);
		f.add(cJF.createButton(f), BorderLayout.NORTH);
		f.add(cJF.resultPanel(f), BorderLayout.NORTH);
		
		f.setVisible(true);
	}
	
	/**
	 * Method creates JPanel with one TextFields for string with infix notation 
	 * @param f - active JFrame
	 */
	
	private JPanel createPanel(JFrame f){
		
		JPanel createPanel = new JPanel();
		createPanel.setLayout(new GridLayout(2, 1));
		
		infix = new TextField(5);
		createPanel.add(new JLabel("Input notation"));
		createPanel.add(infix);

		return createPanel;
	}
	
	/**
	 * Method creates JPanel with one TextFields for string with postfix notation 
	 * @param f - active JFrame
	 */
	
	private JPanel resultPanel(JFrame f){
		
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new GridLayout(2, 1));
		
		result = new TextField(1);
		resultPanel.add(new JLabel("Out notation"));
		resultPanel.add(result);

		return resultPanel;
	}
	
	/**
	 * Method creates JPanel with one JRadioButton for case action
	 * @param f - active JFrame
	 */
	
	private JPanel radioPanel(JFrame f){
		ButtonGroup group = new ButtonGroup();
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(2, 1));

		JRadioButton infixToPostfix = new JRadioButton();
		radioPanel.add(new JLabel("From Infix to Postfix"));
		radioPanel.add(infixToPostfix);
		infixToPostfix.setSelected(true);
		JRadioButton postfixToInfix = new JRadioButton();
		radioPanel.add(new JLabel("From Postfix to Infix"));
		radioPanel.add(postfixToInfix);
		group.add(infixToPostfix);
		group.add(postfixToInfix);
		
		infixToPostfix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fromTo = 0;
			}
		});
		postfixToInfix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fromTo = 1;
			}
		});
		
		return radioPanel;
	}
	
	/**
	 * Method creates JPanel with one JButton for converting
	 * @param f - active JFrame
	 */
	
	private JPanel createButton(JFrame f){
		JPanel createButton = new JPanel();
		createButton.setLayout(new FlowLayout());
		
		JButton fromInfixToPostfix = new JButton("Convert");
		createButton.add(fromInfixToPostfix);

		fromInfixToPostfix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Infix in = new Infix();
				if(infix.getText().length() != 0){
					if(fromTo == 0){
						result.setText(in.InfixToPostfix(infix.getText()));
					} else if(fromTo == 1){
						result.setText(in.PostfixToInfix(infix.getText()));
					}
				} else {
					JOptionPane.showMessageDialog(null,	"TextField is empty", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		return createButton;
	}
}