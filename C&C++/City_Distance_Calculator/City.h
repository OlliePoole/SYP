//Header file --> Function Declarations

#include <iostream>
#include <string>

using namespace std;

#ifndef City_H
#define City_H
#define PI 3.14159265358979323846

/**
 * City Class
 * Used to store information about a specific city
 * @author Ollie, George, JJ
 **/
class City {

public:
	/**
 	 * Default Constructor
	 **/
	City();

	/**
 	 * Overload Constructor
 	 * @param string 	The name of the city
 	 * @param string	The country the city is in
 	 * @param double	The lattitude
 	 * @param double	The longditute
	 **/
	City(string, string, double, double);

	/**
 	 * Destructor
	 **/
	~City();

	/**
 	 * returns the name
 	 * @return 	The name of the city
	 **/
	string getName() const;
	
	/**
 	 * returns the country
 	 * @return 	The country the city is in
	 **/
	string getCountry() const;

	/**
 	 * returns the latitude in radians
 	 * @return 	The latitude in radians
	 **/
	double getLatitude() const;

	/**
 	 * returns the latitude in degrees
 	 * @return 	The latitude in degrees
	 **/
	double getLatitude2() const;
	
	/**
 	 * returns the longditude in radians
 	 * @return	The longditude in radians
	 **/
	double getLongditude() const;
	
	/**
 	 * returns the longditude in degrees
 	 * @return 	The longditude in degrees
	 **/
	double getLongditude2() const;
	
	/**
 	 * sets the name of city
 	 * @param string 	name of city
	 **/
	void setName(string);

	/**
 	 * sets the country that the city is in
 	 * @param string	country city is in
	 **/
	void setCountry(string);

	/**
 	 * sets latitude of country
 	 * @param double	latitude of country
	 **/
	void setLatitude(double);

	/**
 	 * sets longditude of country
 	 * @param double	longditude of country
	 **/
	void setLongditude(double);


	/**
 	 * Overloaded operator used in comparing two cities
 	 * @param City		The city to be compared
 	 **/
	int operator == (const City& city);

private:
	//Member variables
	string name; //name of the city
	string country; //country the city is in
	double latitude; //latitude of the city
	double longditude; //longditude of the city

	City* next;

};


#endif
