package org.dsa.realestate;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

public class RealEstate
{
// The list of house information
	private static SortedList list = new SortedList();
	// Text fields
	private static JTextField lotText; // Lot number field
	private static JTextField firstText; // First name field
	private static JTextField lastText; // Last name field
	private static JTextField priceText; // Price field
	private static JTextField feetText; // Square feet field
	private static JTextField bedText; // Number of bedrooms field
	// Status Label
	private static JLabel statusLabel; // Label for status info
	// Display information about parameter house on screen

private static void displayHouse(ListHouse house){
	lotText.setText(Integer.toString(house.getLotNumber()));
	firstText.setText(house.getFirstName());
	lastText.setText(house.getLastName());
	priceText.setText(Integer.toString(house.getPrice()));
	feetText.setText(Integer.toString(house.getSquareFeet()));
	bedText.setText(Integer.toString(house.getbedRooms()));
}
// Returns current screen information as a ListHouse
private static ListHouse getHouse()
{
	String lastName;
	String firstName;
	int lotNumber;
	int price;
	int squareFeet;
	int bedRooms;
	lotNumber = Integer.parseInt(lotText.getText());
	firstName = firstText.getText();
	lastName = lastText.getText();
	price = Integer.parseInt(priceText.getText());
	squareFeet = Integer.parseInt(feetText.getText());
	bedRooms = Integer.parseInt(bedText.getText());
	ListHouse house = new ListHouse(lastName, firstName, lotNumber, price,
	squareFeet, bedRooms);
	return house;
}
// Clears house information from screen
private static void clearHouse()
{
	lotText.setText("");
	firstText.setText(""); 
	lastText.setText("");
	priceText.setText("");
	feetText.setText("");
	bedText.setText("");
}
// Define a button listener
private static class ActionHandler implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	// Listener for the button events
	{
	ListHouse house;
	
	//reset button
	if (event.getActionCommand().equals("Reset")){ // Handles Reset event
		list.reset();
		if (list.lengthIs() == 0)
			clearHouse();
		else{
			house = (ListHouse)list.getNextItem();
			displayHouse(house);
			}
		statusLabel.setText("List reseted");
	}
	else
	
		//next button
	if (event.getActionCommand().equals("Next")){ 
		if (list.lengthIs() == 0)
			statusLabel.setText("No records in List");
		else{
			house = (ListHouse)list.getNextItem();
			displayHouse(house);
			statusLabel.setText("Next Record displayed");
			}
	}
	else
		
	//add button
	if (event.getActionCommand().equals("Add")){
	try{
		house = getHouse();
		if (list.isAvelable(house))
		statusLabel.setText("Lot Number Already Avelable");
		else{
	list.insert(house);
	statusLabel.setText("Record Added to the List");
	}
	}
	catch (NumberFormatException badHouseData){
	//validation
	statusLabel.setText("Number? " + badHouseData.getMessage());
	}
	}
	else
		
	//delete button
	if (event.getActionCommand().equals("Delete")){ 
	try{
	house = getHouse();
	if (list.isAvelable(house)){
	list.delete(house);
	statusLabel.setText("Record Deleted");
	}
	else
	statusLabel.setText("Lot number is Not Found");
	}
	catch (NumberFormatException badHouseData){
	// validation
	statusLabel.setText("Number? " + badHouseData.getMessage());
	}
	}
	else
		
	//clear button
	if (event.getActionCommand().equals("Clear")){ 
		clearHouse();
		statusLabel.setText("No of Record(s) in List :"+list.lengthIs());
		}
	else
		
		//find button
	if (event.getActionCommand().equals("Find")){ // Handles Find event
	int lotNumber;
	try{
	lotNumber = Integer.parseInt(lotText.getText());
	house = new ListHouse("", "", lotNumber, 0, 0, 0);
	if (list.isAvelable(house)){
	house = (ListHouse)list.getElement(house);
	displayHouse(house);
	statusLabel.setText("Record found");
	}
	else
	statusLabel.setText("Record not found");
	}
	catch (NumberFormatException badHouseData)
	{
	// validation
	statusLabel.setText("Number? " + badHouseData.getMessage());
	}
	}
	}
}
public static void main(String args[]) throws IOException
{
	ListHouse house;
	char command;
	int length;
	JLabel blankLabel; // To use up one frame slot
	JLabel lotLabel; // Labels for input fields
	JLabel firstLabel;
	JLabel lastLabel;
	JLabel priceLabel;
	JLabel feetLabel;
	JLabel bedLabel;
	JButton reset; // Reset button
	JButton next; // Next button
	JButton add; // Add button
	JButton delete; // Delete button
	JButton clear; // Clear button
	JButton find; // Find button
	ActionHandler action; // Declare listener
	// Declare/Instantiate/Initialize display frame
	final JFrame displayFrame = new JFrame();
	displayFrame.setTitle("Real Estate");
	displayFrame.setResizable(false);
	displayFrame.setSize(450,500);
	displayFrame.addWindowListener(new WindowAdapter() // handle window
// closing
{
		
		
public void windowClosing(WindowEvent event)
{
	ListHouse house;
	displayFrame.dispose(); // Close window
	try
	{
	// Store info from list into house file
	HouseFile.rewrite();
	list.reset();
	int length = list.lengthIs();
	for (int counter = 1; counter <= length; counter++)
	{
	house = (ListHouse)list.getNextItem();
	HouseFile.putToFile(house);
	}
	HouseFile.close();
	}
	catch (IOException fileCloseProblem)
	{
	System.out.println("Exception raised concerning the house info file "
	+ "upon program termination");
	}
	System.exit(0); // Quit the program
	}
	});
	// Instantiate content pane and information panel
	Container contentPane = displayFrame.getContentPane();
	JPanel infoPanel = new JPanel();
	// Instantiate/initialize labels, and text fields
	statusLabel = new JLabel("", JLabel.CENTER);
	statusLabel.setBorder(new LineBorder(Color.BLUE));
	blankLabel = new JLabel("");
	lotLabel = new JLabel("Lot Number: ", JLabel.LEFT);
	lotText = new JTextField("", 15);
	firstLabel = new JLabel("First Name: ", JLabel.LEFT);
	firstText = new JTextField("", 15);
	lastLabel = new JLabel("Last Name: ", JLabel.LEFT);
	lastText = new JTextField("", 15);
	priceLabel = new JLabel("Price: ", JLabel.LEFT);
	priceText = new JTextField("", 15);
	feetLabel = new JLabel("Square Feet: ", JLabel.LEFT);
	feetText = new JTextField("", 15);
	bedLabel = new JLabel("Number of Bedrooms: ", JLabel.LEFT);
	bedText = new JTextField("", 15);
	blankLabel  = new JLabel("Status:", JLabel.CENTER);
	blankLabel.setBorder(new LineBorder(Color.BLUE));
	// Instantiate/register buttons
	reset = new JButton("Reset");
	next = new JButton("Next");
	add = new JButton("Add");
	delete = new JButton("Delete");
	clear = new JButton("Clear");
	find = new JButton("Find");
	// Instantiate/register button listeners
	action = new ActionHandler();
	reset.addActionListener(action);
	next.addActionListener(action);
	add.addActionListener(action);
	delete.addActionListener(action);
	clear.addActionListener(action);
	find.addActionListener(action);
	// Load info from house file into list
	HouseFile.reset();
	while (HouseFile.moreHouses())
	{
	house = HouseFile.getNextHouse();
	list.insert(house);
	}
	// If possible insert info about first house into text fields
	list.reset();
	if (list.lengthIs() != 0)
	{
	house = (ListHouse)list.getNextItem();
	displayHouse(house);
	}
	// Update status
	statusLabel.setText("No of Record(s) in List :"+list.lengthIs());
	// Add components to frame
	infoPanel.setLayout(new GridLayout(10,2,10,10));
	infoPanel.setBackground(Color.WHITE);
	
	infoPanel.add(lotLabel);
	infoPanel.add(lotText);
	infoPanel.add(firstLabel);
	infoPanel.add(firstText);
	infoPanel.add(lastLabel);
	infoPanel.add(lastText);
	infoPanel.add(priceLabel);
	infoPanel.add(priceText);
	infoPanel.add(feetLabel);
	infoPanel.add(feetText);
	infoPanel.add(bedLabel);
	infoPanel.add(bedText);
	infoPanel.add(blankLabel);
	infoPanel.add(statusLabel);
	
	infoPanel.add(add);
	infoPanel.add(delete);
	infoPanel.add(next);
	infoPanel.add(find);
	infoPanel.add(clear);
	infoPanel.add(reset);
	
	// Set up and show the frame
	contentPane.add(infoPanel);
	displayFrame.show();
}
}
