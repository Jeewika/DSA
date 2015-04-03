package org.dsa.realestate;



public class SortedList extends ListADT{

//constructor	
public SortedList(int maxItems){
	super(maxItems);
}

//default constructor with 50 elements
public SortedList(){
	super(50);
}

//return true if a element with key is found
public boolean isAvelable (Listable item){
	int matchResult;
	int midPoint;
	int first = 0;
	int last = numberOfElements - 1;
	boolean moreToSearch = (first <= last);
	boolean found = false;
	while (moreToSearch && !found){
		midPoint = (first + last) / 2;
		matchResult = item.compareTo(list[midPoint]);
		if (matchResult == 0)
			found = true;
		// item is less than element at location
		else if (matchResult < 0) {
			last = midPoint - 1;
			moreToSearch = (first <= last);
		}
		// item is greater than element at location
		else{ 
			first = midPoint + 1;
			moreToSearch = (first <= last);
		}
	}
	return found;
}

//return element with same key of object
public Listable getElement (Listable object){
	int compareResult;
	int first = 0;
	int last = numberOfElements - 1;
	int midPoint = (first + last) / 2;
	boolean found = false;
	while (!found)
	{
		midPoint = (first + last) / 2;
		compareResult = object.compareTo(list[midPoint]);
		if (compareResult == 0)
			found = true;
		else if (compareResult < 0) // item is less than element at location
			last = midPoint - 1;
		else // item is greater than element at location
			first = midPoint + 1;
	}
	return list[midPoint].copy();
}

//insert into list
public void insert (Listable Object){
	int location = 0;
	boolean moreToSearch = (location < numberOfElements);
	while (moreToSearch){
		if (Object.compareTo(list[location]) < 0) // item is less
			moreToSearch = false;
		else{ // item is more
			location++;
			moreToSearch = (location < numberOfElements);
		}
	}
	for (int index = numberOfElements; index > location; index--)
		list[index] = list[index - 1];
		list[location] = Object.copy();
		numberOfElements++;
}
public void delete (Listable item)
// Deletes the element that matches item from this list
{
int location = 0;
while (item.compareTo(list[location]) != 0)
location++;
for (int index = location + 1; index < numberOfElements; index++)
list[index - 1] = list[index];
numberOfElements--;
}
}
