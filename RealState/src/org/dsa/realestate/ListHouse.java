package org.dsa.realestate;

//house details
public class ListHouse implements Listable
{
	private String lastName;
	private String firstName;
	private int lotNumber;
	private int price;
	private int squareFeet;
	private int bedRooms;
	
//constructor
public ListHouse(String lastName, String firstName, int lotNumber,int price, int squareFeet, int bedRooms ){
	this.lastName = lastName;
	this.firstName = firstName;
	this.lotNumber = lotNumber;
	this.price = price;
	this.squareFeet = squareFeet;
	this.bedRooms = bedRooms;
}

//create a copy of house
public Listable copy(){
	ListHouse result = new ListHouse(lastName, firstName, lotNumber, price,squareFeet, bedRooms);
	return result;
}

//compare houses using lot number
public int compareTo(Listable otherListHouse){
	ListHouse other = (ListHouse)otherListHouse;
	return (this.lotNumber - other.lotNumber);
}
// get methods
public String getLastName(){
return lastName;
}

public String getFirstName(){
return firstName;
}

public int getLotNumber(){
return lotNumber;
}

public int getPrice(){
return price;
}

public int getSquareFeet(){
return squareFeet;
}

public int getbedRooms(){
return bedRooms;
}
}