#include <cstdio>
#include <cstdarg> //used with va_ variables; allows variable number of parameters
#include <iostream> //allows cout
#include <string> //imports string library
#include <exception>
#include <cmath>
#include <cstdint>
using namespace std;

//can give numbers a constant name.
enum color { RED = 76,BLUE = 2,YELLOW = 14 }; //alternative to preprocessor constants

typedef long int bigRed_t; //alias for the type long int, _t is customary for a typedef

void func(void) { //void as a parameter means no arguments, same as (). necessary in C, but not C++
	puts("this is a void function");
	return; //cannot return value
}

string func(int num) { //function returns a string
	return string("you have input the number: " +  to_string(num));
}

string func(const char * s) { //function returns a string
	return string( s);
}

//bitfields
struct prefs {
	bool likesMusic : 1; //declares number of bits the value takes up
	bool hasRed : 1;
	bool isRed : 1;
	bool remembersRed : 1;
	unsigned int number : 32; //max of 32 bits, because int are 32 bits
	//unsigned int number2 : 32; //if this is included, size of prefs is 96 bits instead of 64 bits
};


int main(int argc, char ** argv) {

	/*CV qualifiers
	const: read-only
	volatile: may be changed by another process [external source] [default]
	mutable: used on data member to make it writable from a const member function

	*/

	/*Storage Duration
	static: global scope
	register: stored in processor register [suggestion]
	extern: defined in seperate translation unit and linked to code
	[auto]: default local scope
	*/

	auto msg = func(YELLOW); //auto automatically assigns a type.


	struct prefs erik;
	erik.likesMusic = true;
	erik.hasRed = false;
	erik.isRed = true;
	erik.remembersRed = true;
	erik.number = RED;



	bigRed_t x = RED; //puts int an int
	bigRed_t y = 76L; //assigns a long int
	bigRed_t z = 76LL; //assigns a long long int
	bigRed_t octel = 076; // assigns octal value
	bigRed_t hex = 0x76; //assigns a hex value
	bigRed_t bin = 0b1001100; //assigns a hex value

	float f = 5e2;
	double df;
	long double ldf;
	float rounding = 0.1 + 0.1 + 0.1;
	double rounding2 = 0.1 + 0.1 + 0.1;

	
		//printf("\a");//makes a little bell sound
	

	//char c[] = "Remember the RED";
	char c[] = "Remember" //auto concatinates, same as line above
		" the"
		" RED";


	printf("76 as int: %ld\n", x);
	printf("76 as long int: %ld \n", y);
	printf("76 as long long int: %ld \n", z);
	printf("76 as octel: %ld \n", octel);
	printf("76 as hex: %ld \n", hex);
	printf("76 in binary: %ld \n\n", bin); //works in C14 compiler


	printf("5e2 as float: %f \n", f);
	printf("5e2 with 2 significant digits: %1.2f \n", f); //prints with 2 significant digits
	printf("0.1 + 0.1 + 0.1 as a float: %1.20f \n", rounding); 
	printf("0.1 + 0.1 + 0.1 as a double: %1.20f \n\n", rounding2);

	//testing float addition
	if (rounding == 0.3) {
		puts("float addition success");
		puts("0.1 + 0.1 + 0.1 == 0.3\n");
	}
	else {
		puts(" float rounding error!");
		puts("0.1 + 0.1 + 0.1 != 0.3\n");
	}

	//testing double addition
	if (rounding2 == 0.3) {
		puts("double addition success");
		puts("0.1 + 0.1 + 0.1 == 0.3\n");
	}
	else {
		puts(" double rounding error!");
		puts("0.1 + 0.1 + 0.1 != 0.3\n");
	}

	printf("char size: %ld bits\n", sizeof(char) * 8);
	printf("short int size: %ld bits\n", sizeof(short int) * 8);
	printf("short int size: %ld bits\n", sizeof(short) * 8); //int can be exempted
	printf("int size: %ld bits\n", sizeof(int) * 8);
	printf("long int size: %ld bits\n", sizeof(long int) * 8); //32 bit on PC, 64 bit on Mac
	printf("long  int size: %ld bits\n", sizeof(long ) * 8); //int can be exempted
	printf("long  int size: %ld bits\n", sizeof(bigRed_t) * 8); //typedef alias for long int, works the same
	printf("long long int size: %ld bits\n", sizeof(long long int) * 8);
	printf("long long int size: %ld bits\n\n", sizeof(long long ) * 8); //int can be exempted


	printf("int8_t size: %ld bits\n", sizeof(int8_t) * 8);
	printf("int16_t size: %ld bits\n", sizeof(int16_t) * 8);
	printf("int32_t size: %ld bits\n", sizeof(int32_t) * 8);
	printf("int64_t size: %ld bits\n\n", sizeof(int64_t) * 8);

	printf("float size: %ld bits\n", sizeof(f) * 8);
	printf("double size: %ld bits\n", sizeof(df) * 8);
	printf("long double size: %ld bits\n\n", sizeof(ldf) * 8);

	puts(c);
	printf("size of \"Remember the RED\": %ld bits\n", sizeof(c));
	for (unsigned int i = 0; c[i]; i++) {
		printf("%03d: %c\n", i, c[i]);
	}



	printf("\nint size: %ld bits\n", sizeof(int) * 8);
	printf("prefs size: %ld bits\n\n", sizeof(erik) * 8); //how data stored in memory determined by architecture.
	

	printf("message: %s \n", msg.c_str());
	printf("type of message: %s \n\n", typeid(msg).name());
	
	printf(func(NULL).c_str()); //works on PC because NULL is defined as a 0
	printf(func(0).c_str()); // same as previous line
	//printf(func((void *)0).c_str()); //causes errors with func
	printf(func(nullptr).c_str()); //keyword introduced in C++11 to solve problem of previous line
	//printf(func(0LL).c_str()); //will not work, as it can be applied to both parameter types for overloaded func()
	
	return 0;
}