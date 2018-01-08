#include <cstdio>
#include <cstdarg> //used with va_ variables; allows variable number of parameters
#include <iostream> //allows cout
#include <string> //imports string library
#include <exception>
#include <cmath>
using namespace std;

//template function
//auto deduces return type
//decltype necessary to inform what auto should be
//"->" = trailing return
//decltype has enough information to derive return type. 
//is new in C++11
template <typename lhsT, typename rhsT>
auto tf(lhsT & lhs, rhsT & rhs) -> decltype (lhs + rhs) { return lhs + rhs; }

//template <class T> T maxof(T a, T b) older standard of writing a template, typename is less ambiguous 

template <typename T> T maxof(T a, T b) {
	return (a > b ? a : b);
}

//template class is generic
template <typename T> 
class Generic {
	T _item; //this is a template class, it can hold any type of item
public:
	explicit Generic(T x);
	Generic() {}
	T & item();
};

template <typename T>
Generic<T>::Generic(T x) :_item(x) {}
	
template <typename T>
T & Generic<T>::item() {
	return _item;
}

int main(int argc, char ** argv) {
	printf("max is : %d\n", maxof<int>(7, 9));
	printf("max is : %d\n", maxof(11, 9)); //template argument list not necessary here
	printf("max is : %s\n", maxof<const char *>("blue", "red")); //this compares addresses, not value
	printf("max is : %s\n", maxof<string>("blue", "red").c_str()); //this compares the actual strings
	printf("max is : %s\n\n", maxof("blue", "red"));//the assumed type is <const char *> and compares addresses

	Generic<int> gene(76);
	Generic<const char *> genec("Remember");
	Generic<string> genes("Red");

	printf("template is holding : %d\n\n", gene.item());

	decltype(gene) y; //duplicates the type, calls default constructor of the type

	printf("type of y is : %s\n", typeid(y).name());

	auto z = tf(genec.item(), genes.item());

	printf(" z is : %s\n", z.c_str());
	printf("type of z is : %s\n", typeid(z).name());

	return 0;
}