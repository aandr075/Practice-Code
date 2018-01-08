#include <cstdio> //angle brackets tells the preprocessor to search file system using PATH
#include "func1.h"
#include <cstdarg> //used with va_ variables; allows variable number of parameters
#include <iostream> //allows cout
#include <string> //imports string library

//defining macros
#define COLOR "Red" // creates a macro
#define DIVR(a, b) (a / b),(a % b) //macro with input parameters
//#define FOO //commenting this line out will change result of NUMBER macro

#include "conditional.h" //quotes to include specific header file

#ifndef NUMBER //if NUMBER is not defined
#define NUMBER 76 //convention is to create macro in ALL CAPS
#endif //ends the if statement

using namespace std;

int main(int argc, char ** argv) {
	const int number = 76; // actual way to make a constant, preferred
	int x = 9;
	int y = 7;
	int a[] = { DIVR(x,y) };
	//backslashes allow code to continue on following line
	int b[] = { \
		x / y, \
		x % y \
	};
	printf("Color: %s\n", COLOR); //COLOR is not a variable, and is replaced with "Red" before compiliation
	printf("Number: %d\n", NUMBER); //macro version
	printf("Number: %d\n", number); //constant variable version *preferred*
	printf("Macro: %d R %d\n", DIVR(x,y));
	printf("Quotient: %d \n", a[0]);
	printf("Remainder: %d\n", a[1]); 
	printf("Remainder: %d\n", x%y);
	printf("Remainder: %d\n", b[0]); 
	printf("Remainder: %d\n", b[1]);
	return 0;
}