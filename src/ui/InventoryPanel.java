package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InventoryPanel extends JPanel {
	private JButton loadButton;
	private JButton showButton;
	private JSONArray inventory;
	
	public InventoryPanel() {
		setLayout(new FlowLayout());
		// creating buttons
		loadButton = new JButton("Load Inventory");
		showButton = new JButton("Show Inventory");
		// add to panel
		add(loadButton);
		add(showButton);
		// action listeners
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadInventory();
			}
		});
		
		showButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showInventory();
			}
		});
	}
	
	// loading inventory from JSON file
	private void loadInventory() {
		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(new FileReader("inventory.json"));
			inventory = (JSONArray) data.get("product_info");
			JOptionPane.showMessageDialog(this, "Loaded Successfully!");
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error loading inventory: " + e.getMessage());
		}
	}
	
	// to display inventory in new frame
	private void showInventory() {
		if(inventory == null) {
			JOptionPane.showMessageDialog(this, "Load the inventory first");
			return;
		}
		JFrame frame = new JFrame("Product Inventory");
		frame.setLayout(new BorderLayout());
		JTextArea inventoryText = new JTextArea(20, 40);
		inventoryText.setEditable(false);
		StringBuilder displayText = new StringBuilder();
		displayText.append(String.format("%-10s %-15s %-10s %-20s\n", "Code", "Name", "Price", "Description"));
		
		for(Object object : inventory) {
			JSONObject product = (JSONObject) object;
			displayText.append(String.format(
	                "%-10s %-15s %-10.2f %-20s\n",
	                product.get("product_code"),
	                product.get("product_name"),
	                (double) product.get("price"),
	                product.get("description")
	            ));
		}
		
		inventoryText.setText(displayText.toString());
		frame.add(new JScrollPane(inventoryText), BorderLayout.CENTER);
		// close button
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		frame.add(closeButton, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

}