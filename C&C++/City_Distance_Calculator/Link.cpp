#include "Link.h"
#include <stdlib.h>

using namespace std;

/**
  * Implementations of the Link.h file
  * All comments are in the Link.h file
  * @author Ollie, George, JJ
 **/
Link::Link()
{
	nextLink = NULL;
	currentObject = NULL;
}


void Link::setNextLink(Link* next){
	nextLink = next;
}


Link* Link::getNextLink(){
	return nextLink;
}


void Link::setCurrentObject(City* current){
	currentObject = current;
}


City* Link::getCurrentObject(){
	return currentObject;
}
