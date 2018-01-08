#include <cstdio>
#include <cstdarg> //used with va_ variables; allows variable number of parameters
#include <iostream> //allows cout
#include <string> //imports string library
using namespace std;


#include "redman_interface.h"

using namespace cazako;

#pragma region objects
//structures when only having data memebers
//a structure [same as class, but defaults to public]
//struct Colors { 
//	//data members [default to public]
////private: //causes errors because members become private
//	char * c1;
//	char * c2;
//	char * c3;
//};


//class is the same as a structure, but members default to private
//use classes when there are function members
class Colors {
	//data members [default to private]
public: //without this, there would be errors below because they default to private
	char * c1;
	char * c2;
	char * c3;
};
#pragma endregion

//overloaded non-member function
//useful when you want it to work with both a + b and b + a
Redman operator +(const Redman & lhs, const Redman & rhs) {
	return Redman(lhs.getNum() + rhs.getNum());
}

int main(int argc, char ** argv) {

	int i = 76;
	Redman erik; //object created from class.

	//Redman eric(erik); // clone through constructor
	const Redman eric = erik; //clone through operator

	//constructor with parameters
	const Redman adam("Adam", "Enter the Darkness", "Darksoul Sword");



	Colors test = { "Red", "Yellow", "Blue" }; //object from structure

	Colors *pointer = &test;

	//accessing structure members through an object
	printf("The best color is %s\n\
The okay color is %s\n\
The worst color is %s\n\n", \
test.c1, test.c2, test.c3);

	//accessing structure members through an object pointer
	printf("The best color is %s\n\
The okay color is %s\n\
The worst color is %s\n\n",\
pointer->c1, pointer->c2, pointer->c3);// -> used to access pointer members

	erik.setNum(i);
	//stone.setNum(42); //does not work because eric is a const object
	printf("Value: %d\n", erik.getNum());
	printf("Value: %d\n", eric.getNum()); //same as erik, but a const version, only works if getNum() has const after function name
	

	printf("Erik address: %p\n", &erik);
	printf("Eric address: %p\n\n", &eric);

	string title =(string) erik + " the Red";

	printf("erik + eric: %d\n", erik + eric);
	printf("erik + \"the Red\": %s\n", title.c_str());
	printf("erik - eric: %d\n", erik - eric);
	const string * fusionName = erik * adam;
	printf("erik X eric: %s\n\n", (*fusionName).c_str());


	erik.say();
	erik.attack();

	eric.say();
	eric.attack();

	adam.say();
	adam.attack();

	puts("");

	puts("allocating space for a Redman object");
	//new replaces malloc()
	Redman * terry = new (nothrow) Redman; 

	//(nothrow) returns a null pointer instead of throwing an exception
	//if statement below tests for null pointer
	if (terry == nullptr) {
		puts("failed to create a new Redman :(");
		return 1;
	}
	delete terry; //calls destructor and re-claims space, replaces free()

	puts("Redman object has been deleted");

	//creating an array of objects
	Redman * redArray = new (nothrow) Redman[6]; //creating 6 new redman objects in an array
	if (redArray == nullptr) {
		puts("failed to create a new Redman :(");
		return 1;
	}
	delete[] redArray; //delete must mach the type of new, as in an array version of delete!!

	puts("Redman array has been deleted\n");

	try {
		Redman x("error", "", "error");
		x.say();
	}
	catch (exception & e) {
		printf("Redman x: %s\n\n",e.what());
	}


	return 0;
}