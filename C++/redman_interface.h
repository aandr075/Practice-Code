//normally, 3 separate files
//class definition in a header file (interface)
//implemented methods in a separate file
//driver in its own file.


#include <cstdio>
#include <cstdarg> //used with va_ variables; allows variable number of parameters
#include <iostream> //allows cout
#include <string> //imports string library
using namespace std;


//include guard
#ifndef REDMAN
#define REDMAN

//namespaces can be used to differentiate between classes with the same name from different libraries.
namespace cazako{ //creates a namespace necessary for using Redman class

class Redman { //a class is a definition
	//default is private for members
	//private: //this is not necessary since it is default
	int number;
	string name;
	string phrase;
	string weapon;
	string bye;

	//Redman(); //making the default constructor private means it cannot be used, must use parameters


public: // public is not default, and thus must be explicit
	Redman(); //default constructor with no value
	explicit Redman(int num); //explicit keyword prevents implicit conversion, such as char 'x' into int (120)
	Redman(const string & name, const string & phrase, const string & weapon, int number = 0/* default value for input*/ );

	Redman(const Redman &); //copy constructor
	Redman & operator = (const Redman &); //copy operator, overloads operator

	//int operator + (const Redman &) const;
	int operator - (const Redman &) const;
	const string * operator * (const Redman &) const;

	operator std::string() const;

	~Redman(); //destructor


	void setNum(const int value); //normally declare method, and define outside
	//const allows you to use method with const objects
	int getNum() const; //method can be implemented inside class definition too
	int getNum(); //non const version [mutable]

	void say() const;
	void attack() const;
};

};
#endif

