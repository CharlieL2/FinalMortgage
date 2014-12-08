package pikas;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;



public class GUI extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// values for the fields
	private double grossIncome = 56000, monthlyDebt = 300, intRate = 6, term = 30, downPay = 0, housePay = 840, housePayPlus = 1380, maxPay = 840, amtMort = 140104.96 ;
	
	// labels for textfields
	private JLabel grossIncomeLabel, monthlyDebtLabel, intRateLabel, termLabel, downPayLabel, housePayLabel, housePayPlusLabel, maxPayLabel, amtMortLabel;
	
	// strings for labels
	private static String grossIncomeString = "Gross Income", monthlyDebtString = "Monthly Debt", 
			intRateString = "Annual Interest Rate (%)", termString =
			"Number of Terms", downPayString = "Downpayment (Optional)",
			housePayString = "Housing Payment Only", housePayPlusString = "Houseing Payment + Other Obligations",
			maxPayString = "Maximum Monthly Payment", amtMortString = "Max Finacable Mortgage";
	
	// the text fields for data entry
	private JFormattedTextField grossIncomeField, monthlyDebtField, intRateField, termField, downPayField, housePayField, housePayPlusField, maxPayField, amtMortField;
	
	// formats
	private NumberFormat currencyFormat, intRateFormat;
	public GUI() {
		super(new BorderLayout());
		setUpFormats();
		
		// create labels
		grossIncomeLabel = new JLabel(grossIncomeString);
		monthlyDebtLabel = new JLabel(monthlyDebtString);
		intRateLabel = new JLabel(intRateString);
		termLabel = new JLabel(termString);
		downPayLabel = new JLabel(downPayString);
		housePayLabel = new JLabel(housePayString);
		housePayPlusLabel = new JLabel(housePayPlusString);
		maxPayLabel = new JLabel(maxPayString);
		amtMortLabel = new JLabel(amtMortString);

		// Create the text fields and set them up.
		grossIncomeField = new JFormattedTextField(currencyFormat);
		grossIncomeField.setValue(new Double(grossIncome));
		grossIncomeField.setColumns(10);
		grossIncomeField.addActionListener(this);

		monthlyDebtField = new JFormattedTextField(currencyFormat);
		monthlyDebtField.setValue(new Double(monthlyDebt));
		monthlyDebtField.setColumns(10);
		monthlyDebtField.addActionListener(this);

		intRateField = new JFormattedTextField(intRateFormat);
		intRateField.setValue(new Double(intRate));
		intRateField.setColumns(10);
		intRateField.addActionListener(this);

		termField = new JFormattedTextField();
		termField.setValue(new Double(term));
		termField.setColumns(10);
		termField.addActionListener(this);

		downPayField = new JFormattedTextField(currencyFormat);
		downPayField.setValue(new Double(downPay));
		downPayField.setColumns(10);
		downPayField.addActionListener(this);

		housePayField = new JFormattedTextField(currencyFormat);
		housePayField.setValue(new Double(housePay));
		housePayField.setColumns(10);
		housePayField.setEditable(false);
		
		housePayPlusField = new JFormattedTextField(currencyFormat);
		housePayPlusField.setValue(new Double(housePayPlus));
		housePayPlusField.setColumns(10);
		housePayPlusField.setEditable(false);
		
		maxPayField = new JFormattedTextField(currencyFormat);
		maxPayField.setValue(new Double(maxPay));
		maxPayField.setColumns(10);
		maxPayField.setEditable(false);
		
		amtMortField = new JFormattedTextField(currencyFormat);
		amtMortField.setValue(new Double(amtMort));
		amtMortField.setColumns(10);
		amtMortField.setEditable(false);
		
		// Tell accessibility tools about label/textfield pairs.
		grossIncomeLabel.setLabelFor(grossIncomeField);
		monthlyDebtLabel.setLabelFor(monthlyDebtField);
		intRateLabel.setLabelFor(intRateField);
		termLabel.setLabelFor(termField);
		downPayLabel.setLabelFor(downPayField);
		housePayLabel.setLabelFor(housePayField);
		housePayPlusLabel.setLabelFor(housePayPlusField);
		maxPayLabel.setLabelFor(maxPayField);
		amtMortLabel.setLabelFor(amtMortField);
		
		//Lay out the labels in a panel.
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(grossIncomeLabel);
        labelPane.add(monthlyDebtLabel);
        labelPane.add(intRateLabel);
        labelPane.add(termLabel);
        labelPane.add(downPayLabel);
        labelPane.add(housePayLabel);
        labelPane.add(housePayPlusLabel);
        labelPane.add(maxPayLabel);
        labelPane.add(amtMortLabel);
        
        //Layout the text fields in a panel.
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(grossIncomeField);
        fieldPane.add(monthlyDebtField);
        fieldPane.add(intRateField);
        fieldPane.add(termField);
        fieldPane.add(downPayField);
        fieldPane.add(housePayField);
        fieldPane.add(housePayPlusField);
        fieldPane.add(maxPayField);
        fieldPane.add(amtMortField);
        
        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);

	}

	public void actionPerformed(ActionEvent e) {
		
		grossIncome = ((Number)grossIncomeField.getValue()).doubleValue();
		monthlyDebt = ((Number)monthlyDebtField.getValue()).doubleValue();
		intRate = ((Number)intRateField.getValue()).doubleValue() / 100;
		term = ((Number)termField.getValue()).doubleValue();
		downPay = ((Number)downPayField.getValue()).doubleValue();
		
		Engine values = new Engine(grossIncome, monthlyDebt, intRate, term, downPay);
		
		housePayField.setText((String.valueOf(values.getHousePay())));
		
		housePayPlusField.setText(String.valueOf(currencyFormat.format(values.getHousePayPlus())));
		maxPayField.setText(String.valueOf(currencyFormat.format(values.getMaxPay())));
		amtMortField.setText(String.valueOf(currencyFormat.format(values.getAmtMort())));
		
		
	}
	
	private void setUpFormats() {
		Locale US = new Locale("en", "US");
		
		currencyFormat = NumberFormat.getCurrencyInstance(US);
		currencyFormat.setMaximumFractionDigits(2);
		
        intRateFormat = NumberFormat.getNumberInstance(US);
        intRateFormat.setMinimumFractionDigits(1);

    }
	
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Investment Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new GUI());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
            UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }

}
