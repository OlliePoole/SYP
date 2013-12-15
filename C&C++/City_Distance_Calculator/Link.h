#include "City.h"

/**
 * Link class
 * Provides the links for the linked list
 * @author Ollie, George, JJ
 **/
class Link {

Link* nextLink;
City* currentObject;

public:

	/**
 	 * Default Constuctor
	 **/
	Link();

	/**
 	 * directs the link pointer to the next link in the list
 	 * @param Link		The previous link
	 **/
	void setNextLink(Link* previous);

	/**
 	 * returns a pointer to the next link in the list
	 **/
	Link* getNextLink();
	
	/**
 	 *  sets the object pointer to a City
 	 * @param City		The City to be set
	 **/
	void setCurrentObject(City* current);

	/**
 	 * returns a pointer to the referenced city
	 **/
	City* getCurrentObject();


};
