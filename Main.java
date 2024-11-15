package ui;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Cashier App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		InventoryPanel inventoryPanel = new InventoryPanel();
		frame.add(inventoryPanel);
		frame.setVisible(true);
		
	}
}
