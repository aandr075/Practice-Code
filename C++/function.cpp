#include <cstdio>
#include <cstdarg> //used with va_ variables; allows variable number of parameters
#include <iostream> //allows cout
#include <string> //imports string library
#include "func1.h" //common way to include c++ function d
using namespace std;

//function declairations
const char * prompt();
int jump(const char *);

#pragma region Function tests
//function tests

//void func1(); //forward declaration, necessary to define function after calling
//commonly done with a header file

void func2(int  & i) { // &i means parameter is passed by reference
	i += 23; //will change original value of variable passed into func2
	printf("F2: Value: %d\n", i);
}

void func2() { //overload func2
	int i = 1;
	printf("F2: Value: %d\n", i);
}

void func3(const int  & i) { // declaring parameter as a const does not allow changes to variable
	//i += 23; //error trying to change a const
	printf("F3: Value: %d\n", i);
}

void func3() { //overload func3
	printf("F3: Value: %d\n", 23);
}

void func4(const string  & fs) { // passing by reference

	printf("F4: Value: %s\n", fs.c_str());
}

void func4() { // overload

	string s = "Red";
	string & fs = s;
	printf("F4: Value: %s\n", fs.c_str());
}

void func5(const string  * fs) { // old method of passing pointer, more common in C

	printf("F5: Value: %s\n", fs->c_str());
}

void func5() { // overload
	string s = "Erik";
	string * fs = &s;
	printf("F5: Value: %s\n", fs->c_str());
}

void func6() { // static scoping
	static int i = 5; //persistent storage exists longer than function
	//auto int i = 3; //used to be how auto storage was done, but is the default and repurposed.
	printf("F6: Value1: %d\n", ++i);
	printf("F6: Value2: %d\n", i++);
}

int func7(int i) { //return int

	printf("F7: Value2: %d\n", i++);
	return i; //return on stack, cannot return large objects
}

void func7() { //overload
	int i = 1;
	printf("F7: Value2: %d\n", i++);
}

const string & func8() { //returns a reference to the string, const prevents editing string outside of function
	static string s = "Remember to return the RED!"; //creates a string in static memory
	return s; //returns reference to static memory location
}

//void func8(){ cannot overload functions without changing parameters
//	string s = "Remember to return the RED!";
//	puts(s);
//}

void func9() {
	puts("F9:!!!");
}
//functions end  
#pragma endregion

#pragma region overload operator
class Test {
	int x;
public:
	Test(const int &a) : x(a) {}
	const int & value() const { return x; }
};

const string * operator + (const Test & lhs, const Test & rhs) {
	puts("operator + for test class");
	static string s = (to_string(lhs.value()) + to_string(rhs.value())).c_str();
	return &s;
}
#pragma endregion

#pragma region Variable parameters
int summation(const int count, ...) { //variable number of arguments, count is # of args

	va_list ap; // arguments listed as ....
	int i;
	int sum = 0;

	va_start(ap, count); //
	for (i = 0; i < count; i++) {
		sum += va_arg(ap, int); //grabs next var from va_list and declares the type
	}
	va_end(ap);//ends va_list
	return sum;
}

int message(const char * fmt, ...) {
	va_list ap;
	va_start(ap, fmt);
	int rc = vfprintf(stdout, fmt, ap);
	puts("");
	va_end(ap);
	return rc;
}
#pragma endregion

#pragma region recursion
unsigned long int sigma(unsigned long int n) { //recursive function
	if (n < 1) return 0;
	return sigma(n - 1) + n;
}
#pragma endregion


void(*funcs[])() = { func1, func2, func3, func4, func5,  func6, func7, func9, nullptr };


int main(int argc, char ** argv) {

	int x = 10;
	string s = "Remember the Red";
	Test red(7);
	Test man(6);
	//void(*fp)() = func9; //pointer to a function, return type must match function being assigned. parenthesis are necessary around *fp.
	//void(*fp)() = &func9; //& is optional, works the same regardless
	puts("This is main()");

	//used for jump menu
	//while (jump(prompt()));
	//puts("\nDone.");

	//func3(x);
	//func4(s);
	//func5(&s);
	//func6();
	//func6();
	//printf("Returned: %d\n",func7(x));
	//fp(); //calls function through pointer
	//(*fp)(); //works the same as fp()
	//printf("Returned: %s\n", func8().c_str());
	//printf("main: Int: %d\nString: %s\n", x, s.c_str());
	//printf("main: Test Addition: %s\n", red + man);

	//message("This is a message\n"); //created printf substitute
	//message("Average: %d\n", summation(4, 42, 33, 15, 7));

	printf("sigma from 0 to %d is: %ld\n",x, sigma(x));

	return 0;
}

#pragma region jump Functions

const char * prompt() {
	puts("Choose an option:");
	puts("1. Function 1()");
	puts("2. Function 2()");
	puts("3. Function 3()");
	puts("4. Function 4()");
	puts("5. Function 5()");
	puts("6. Function 6()");
	puts("7. Function 7()");
	puts("8. Function 9()");
	puts("Q. Quit.");
	printf(">> ");

	fflush(stdout);
	const int buffsz = 16;
	static char response[buffsz];
	fgets(response, buffsz, stdin);

	return response;
}

int jump(const char * rs) {
	char code = rs[0];
	if (code == 'q' || code == 'Q') return 0;

	// count the length of the funcs array
	int func_length = 0;
	while (funcs[func_length] != NULL) func_length++;

	int i = (int)code - '0';   // convert ASCII numeral to int
	i--;        // list is zero-based
	if (i < 0 || i >= func_length) {
		puts("invalid choice");
		return 1;
	}
	else {
		funcs[i]();
		return 1;
	}

}
#pragma endregion


void func1()
{
	puts("f1: Remember the RED!");
}