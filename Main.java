package ui;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Cashier App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		InventoryPanel inventoryPanel = new InventoryPanel();
		frame.add(inventoryPanel);
		InvoicePanel invoicePanel = new InvoicePanel();
		frame.add(invoicePanel);
		frame.setVisible(true);
		
	}
}
// trying to see if commits 
//
//
//

