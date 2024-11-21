package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoicePanel extends JPanel {
	// Below are all the textfield variables
	private JTextField tax;
	private JTextField cityState;
	private JTextField discount;
	private JTextField beforeTaxTotalField;
	private JTextField taxedTotalField;
	private JTextField discountTotalField;
	private JTextField grandTotalField;
	private JCheckBox discountCheckbox;
	private double salesTax = 8.25;
	private double discountPerc = 0.0;
	private double beforeTaxTotal = 0.0;
	private double taxedTotal = 0.0;
	private double discountTotal = 0.0;
	private double grandTotal = 0.0;

	// constructor
	public InvoicePanel() {
		// ex of 8 rows and 2 columns with spacing
		setLayout(new GridLayout(8, 2, 5, 5));
		
		// for sales tax
		add(new JLabel("Sales Tax (%):"));
		tax = new JTextField(String.valueOf(salesTax));
		tax.setEditable(false);
		add(tax);
		
		// for city and state
		add(new JLabel("City, State:"));
		cityState = new JTextField("San Jose, CA");
		cityState.setEditable(false);
		add(cityState);
		
		// discount %
		add(new JLabel("Discount (%):"));
		discount = new JTextField("0.0");
		discount.setEditable(false);
		add(discount);
		
		// discount checkbox
		add(new JLabel("Enable Discount:"));
		discountCheckbox = new JCheckBox();
		add(discountCheckbox);
		
		// total before tax
		add(new JLabel("Total before Tax:"));
		beforeTaxTotalField = new JTextField(String.format("%.2f", beforeTaxTotal));
		beforeTaxTotalField.setEditable(false);
		add(beforeTaxTotalField);
		
		// total with tax
		add(new JLabel("Total with Tax:"));
		taxedTotalField = new JTextField(String.format("%.2f", taxedTotal));
		taxedTotalField.setEditable(false);
		add(taxedTotalField);
		
		// discounted total
		add(new JLabel("Discounted Total:"));
		discountTotalField = new JTextField(String.format("%.2f", discountTotal));
		discountTotalField.setEditable(false);
		add(discountTotalField);
		
		// overall amount
		add(new JLabel("Grand Total:"));
		grandTotalField = new JTextField(String.format("%.2f", grandTotal));
		grandTotalField.setEditable(false);
		add(grandTotalField);
		
		// action listener for checkbox
		discountCheckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(discountCheckbox.isSelected()) {
					discountPerc = 10.0; 
					discount.setText(String.valueOf(discountPerc));
				} else {
					discountPerc = 0.0;
					discount.setText(String.valueOf(discountPerc));
				}
				updateTotals();
			}
		});
		updateTotals();
	}
	
	// to update total (dynamically)
	private void updateTotals() {
		taxedTotal = beforeTaxTotal + (beforeTaxTotal * salesTax / 100);
		if(discountCheckbox.isSelected()) {
			discountTotal = taxedTotal - (taxedTotal * discountPerc / 100);
		} else {
			discountTotal = taxedTotal;
		}
		grandTotal = discountTotal;
		
		beforeTaxTotalField.setText(String.format("%.2f", beforeTaxTotal));
        taxedTotalField.setText(String.format("%.2f", taxedTotal));
        discountTotalField.setText(String.format("%.2f", discountTotal));
        grandTotalField.setText(String.format("%.2f", grandTotal));
	}
	
	// to set the "before tax" total
	public void setBeforeTaxTotal(double total) {
		beforeTaxTotal = total;
		updateTotals();
	}
}
