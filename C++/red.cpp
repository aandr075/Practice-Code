#include <cstdio>
#include <cstdarg> //used with va_ variables; allows variable number of parameters
#include <iostream> //allows cout
#include <string> //imports string library
#include <exception>
using namespace std;

#include "redman_interface.h"
//exceptions

class E : public exception {
	const char * msg = nullptr;
	E() {};
public:
	E(const char * s) throw() : msg(s) {}
	const char * what() const throw() { return msg; }
};




//implementation of class methods
//<return type> <class name>::<function name>(<parameters>) {}

//constructors
cazako::Redman::Redman() :name("Erik"), phrase("Remember the Red!"), bye("Enjoy the Movie!"), weapon("Red Katana"), number(0) {
	puts("default constructor\n");
};

cazako::Redman::Redman(int num) : name("Erik"), phrase("Remember the Red!"), bye("Enjoy the Movie!"), weapon("Red Katana"), number(num) {
	puts("default constructor\n");
};


//member functions

using cazako::Redman; //allows all the following "Redman" uses to not be preceeded by "cazako::"
//using namespace cazako; //alternate to the line above
Redman::Redman(const string & name, const string & phrase, const string & weapon, int number )
	: name(name), phrase(phrase), bye("Enjoy the Movie!"), weapon(weapon),number(number) {
	puts("constructor with arguments\n");
	if (name.length() == 0 || phrase.length() == 0 || weapon.length() == 0) {
		throw E("Don't make a Redman with empty parameters!!!");
	}
};

Redman::Redman(const Redman & x) {
	puts("copy constructor\n");
	name = "clone of " + x.name;
	phrase = x.phrase;
	bye = x.bye;
	weapon = "fake " + x.weapon;
	number = 14;
};

Redman::~Redman() {//destructor (dtor)
	printf("dtor: %s\n\t-%s\n",bye.c_str(),name.c_str());
};

//int Redman::operator + (const Redman & x) const { // overloaded plus operator
//	return getNum() + x.getNum();
//}

int Redman::operator - (const Redman & x) const { // overloaded minus operator
	return getNum() - x.getNum();
};

const string * Redman::operator * (const Redman & x) const { // overloaded multiply operator = fusion
	static string fusionName;
	int lenthis = name.length();
	int lenthat = x.name.length();

	fusionName = name.substr(0, (lenthis / 4))  + x.name.substr(lenthat / 4, lenthat / 2) + name.substr(lenthis * 3 / 4);


	return &fusionName;

};

Redman & Redman::operator=(const Redman & x) {
	puts("assignment operator");
	if (this != &x) {
		name = "clone of " + x.name;
		phrase = x.phrase;
		weapon = "fake " + x.weapon;
		bye = x.bye;
		number = 67;
	}
	return *this;
};

//member methods

int Redman::getNum() const {
	puts("const getter");
	return number;
};

int Redman::getNum() {
	puts("mutable getter");
	printf("'This': %p\n", this);
	return number;
};

void Redman::say() const {
	printf("%s\n", phrase.c_str());
};

void Redman::attack() const {
	printf("%s attacks with a %s\n", name.c_str(), weapon.c_str());
};

void Redman::setNum(const int value) { // method implementation outside of class definition
	number = value;
};



//conversion operator overloads
//the to_String auto conversion tool
//can add Redman to a string (may need to cast)
Redman::operator std::string () const {
	return std::string(name);
};