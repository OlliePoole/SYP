// Function definitions

/**
 * @author Ollie, George, JJ
 * 
 **/
 

#include "City.h"

City::City() {
	longditude = 0.0;
	latitude = 0.0;

}

City::City(string n, string c, double la, double lo):name(n), country(c), latitude(la), longditude(lo), next(NULL) {
}


City::~City(){
	
}


string City::getName() const{
	return name;
}


string City::getCountry() const{
	return country;
}


double City::getLatitude() const{
	return latitude * PI / 180;
}


double City::getLatitude2() const{
	return latitude;

}


double City::getLongditude() const{
	return longditude * PI / 180;
}


double City::getLongditude2() const{
	return longditude;
}


void City::setName(string newName){
	name = newName;
}


void City::setCountry(string newCountry) {
	country = newCountry;
}


void City::setLatitude(double newLatitude) {
	latitude = newLatitude;
}


void City::setLongditude(double newLongditude) {
	longditude = newLongditude;
}



int City::operator == (const City& city){

	if(name == city.getName() && country == city.getCountry())
	{
		return 1;
	}
	else
	{
		return 0;
		
	}
}
