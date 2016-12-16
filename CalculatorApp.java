package caculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorApp extends JPanel implements ActionListener {
	private JTextField display = new JTextField("");
	private JTextField display1 = new JTextField("");
	private JTextField display2 = new JTextField("");
	private int result = 0;
	private String newTxt = null;

	private String operator = "=";
	private boolean calculating = true;

	public CalculatorApp() {

		int i;
		JPanel btnPanel = new JPanel();
		String digits = "7894561230+-=";
		JButton btn;

		setLayout(new BorderLayout());
		display.setEditable(false);
		add(display, "North");
		btnPanel.setLayout(new GridLayout(5, 3));
		add(btnPanel, "Center");

		for (i = 0; i < digits.length(); i++) {
			btn = new JButton(digits.substring(i, i + 1));
			btn.addActionListener(this);
			btnPanel.add(btn);
		}
		btn = new JButton("BS");
		btn.setActionCommand("delete");
		btn.addActionListener(this);
		btnPanel.add(btn);
		btn = new JButton("Clear");
		btn.setActionCommand("deleteall");
		btn.addActionListener(this);
		btnPanel.add(btn);
	}

	public void actionPerformed(ActionEvent event) {
		String cmdStr = event.getActionCommand();
		if ('0' <= cmdStr.charAt(0) && cmdStr.charAt(0) <= '9') {
			display2.setText(cmdStr);
			if (calculating) {
				display.setText(display.getText() + cmdStr);
			} else
				display.setText(display.getText() + cmdStr);
			calculating = false;
		} else if (cmdStr.equals("delete")) {
			newTxt = display.getText();
			if (newTxt.length() > 0) {
				newTxt = newTxt.substring(0, newTxt.length() - 1);
				display.setText(newTxt);
			}
		} else if (cmdStr.equals("deleteall")) {
			newTxt = setText();
			display.setText(newTxt);
			display1.setText(newTxt);
			display2.setText(newTxt);
			operator = "=";
			result = 0;
		} else {
			calculating = true;
			if (calculating) {
				if (!cmdStr.equalsIgnoreCase("=")) {
					if ((display.getText() == null) || (display.getText().contentEquals(""))) {
						display.setText("0");
					}
					int x = Integer.parseInt(display.getText());
					if ((!operator.equalsIgnoreCase("=")) && (!cmdStr.equalsIgnoreCase("+"))) {
						operator = "-";
						x = 0;
					} else if ((!operator.equalsIgnoreCase("=")) && (!cmdStr.equalsIgnoreCase("-"))) {
						operator = "+";
						x = 0;
					}
					calculate(x);
					if ((operator.equalsIgnoreCase(cmdStr)) && operator.equalsIgnoreCase("+")) {
						operator = "+";
						display.setText(display2.getText());
					}
					if ((operator.equalsIgnoreCase(cmdStr)) && operator.equalsIgnoreCase("-")) {
						operator = "-";
						display.setText(cmdStr + display2.getText());
					}
					operator = "=";
					calculate(Integer.parseInt(display.getText()));
					if (display.getText().equalsIgnoreCase("0")) {
						display.setText(cmdStr);
					} else {
						display.setText(display.getText() + cmdStr);
					}
					operator = cmdStr;

				} else {
					if ((operator.equalsIgnoreCase("+")) && (cmdStr.equalsIgnoreCase("="))) {
						int x = Integer.parseInt(display2.getText());
						calculate(x);
					} else if ((operator.equalsIgnoreCase("-")) && (cmdStr.equalsIgnoreCase("="))) {
						int x = Integer.parseInt(display2.getText());
						calculate(x);
					} else {

						int x = Integer.parseInt(display.getText());
						result = Integer.parseInt(display1.getText());
						calculate(x);
					}

				}
			}
		}
	}

	private void calculate(int number) {
		if (operator.equals("+"))
			result += number;
		else if (operator.equals("-"))
			result -= number;
		else if (operator.equals("="))
			result = number;
		display.setText("" + result);
		display1.setText("" + result);
	}

	private String setText() {
		return null;
	}

	public static void main(String[] args) {
		JPanel calculator = new CalculatorApp();
		JFrame frame = new JFrame("Calculator Keypad");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(calculator);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
