package org.dsa.realestate;

public abstract class ListADT{
	
	protected int numberOfElements; // Number of elements on this list
	protected int currentPosition; // Current position for iteration
	protected Listable[] list;  //Element list 
	
	//constructor
public ListADT(int maxItems){
	numberOfElements = 0;
	list = new Listable[maxItems];
}


//Returns the number of elements on this list
public int lengthIs(){
	return numberOfElements;
}

//returns list is full or not
public boolean isFull(){
	return (list.length == numberOfElements);
}
//returns true if element with key on list;
public abstract boolean isAvelable (Listable item);

//adds item to into list
public abstract void insert (Listable item);

//returns element with the same key as item
public abstract Listable getElement(Listable item);


//deletes the element 
public abstract void delete (Listable item);

//set current position to loop through this list
public void reset(){
	currentPosition = 0;
}

//returns next element of list
public Listable getNextItem (){
	Listable next = list[currentPosition];
	if (currentPosition == numberOfElements-1)
		currentPosition = 0;
	else
		currentPosition++;
	return next.copy();
}
}