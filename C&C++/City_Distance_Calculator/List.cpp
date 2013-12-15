/**
 * Function Definitions
 * @author Ollie, George, JJ
 **/

#include "List.h"
#include "City.h"

List::List(){
	headPointer = NULL;
}

List::~List(){}


void List::addObject(City* objectIn){
	Link* tempLink = new Link;

	tempLink -> setNextLink(headPointer);
	tempLink -> setCurrentObject(objectIn);
	headPointer = tempLink;

}

Link* List::getHeadpointer() {
	return headPointer;
}

City* List::searchList(string n, string c)
{
	bool found = false;
	Link* tempLink;
	
	for(tempLink = headPointer; tempLink != NULL; tempLink = tempLink -> getNextLink())
	{

		if(tempLink->getCurrentObject()->getName() == n && tempLink->getCurrentObject()->getCountry() == c)
		{
			cout << tempLink->getCurrentObject()->getName();
			cout << tempLink->getCurrentObject()->getCountry();
			found = true;
			break;
		}
		
	}
	if (found)
	{
		return tempLink->getCurrentObject();
	}
	else
	{
		City *city2 = new City();
		return city2;
	}
}


bool List::removeObject(string name, string country)
{
	City* cityFound;

	bool found = false;
	if(headPointer == NULL){
		found = false;
	}
	else{
		Link* tempLink;

		if((headPointer -> getCurrentObject()->getName()) == name && headPointer->getCurrentObject()->getCountry() == country)
		{
			tempLink = headPointer;
			headPointer = headPointer -> getNextLink();
			cityFound = tempLink -> getCurrentObject();
			delete tempLink;
			found = true;
		}
		else{
			Link* temp2 = headPointer;
			for(tempLink = headPointer -> getNextLink(); tempLink != NULL; tempLink = tempLink -> getNextLink()){
				if((tempLink -> getCurrentObject()->getName()) == name && tempLink->getCurrentObject()->getCountry() == country){
					temp2 -> setNextLink(tempLink -> getNextLink());
					cityFound = tempLink -> getCurrentObject();
					delete tempLink;
					tempLink = temp2;
					found = true;
				}
				else{
					temp2 = tempLink;
				}
			}
		}
	}

	return found;
}
