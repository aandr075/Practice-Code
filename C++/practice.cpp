#include <cstdio>
#include <iostream>
using namespace std;

int & f(int &x) { //comes in as a reference, not by value
	x++;
	return x;
}

const int & effectless(const int & x) {
	static int y = x;
	y++;
	return y;
}



int main(int argc, char ** argv) {
	int x = 7;
	int *ip = &x; //pointer to address
	int &y = x; //reference

	printf("The value of x is %d\n", x);
	printf("The address of x is %d\n", &x);
	printf("The value of y is %d\n", y);
	printf("The address of y is %d\n", &y);
	printf("The value of *ip is %d\n", *ip);
	printf("The address of ip is %d\n", &ip);
	printf("The address stored in ip is %d\n\n", ip);

	x = 73;
	printf("The value of x is %d\n", x);
	printf("The address of x is %d\n", &x);
	printf("The value of y is %d\n", y);
	printf("The address of y is %d\n", &y);
	printf("The value of *ip is %d\n", *ip);
	printf("The address of ip is %d\n", &ip);
	printf("The address stored in ip is %d\n\n", ip);

	int z = 149;
	ip = &z;
	printf("The value of x is %d\n", x);
	printf("The address of x is %d\n", &x);
	printf("The value of y is %d\n", y);
	printf("The address of y is %d\n", &y);
	printf("The value of z is %d\n", z);
	printf("The address of z is %d\n", &z);
	printf("The value of *ip is %d\n", *ip);
	printf("The address of ip is %d\n", &ip);
	printf("The address stored in ip is %d\n\n", ip);

	y = z;
	printf("The value of x is %d\n", x);
	printf("The address of x is %d\n", &x);
	printf("The value of y is %d\n", y);
	printf("The address of y is %d\n", &y);
	printf("The value of z is %d\n", z);
	printf("The address of z is %d\n", &z);
	printf("The value of *ip is %d\n", *ip);
	printf("The address of ip is %d\n", &ip);
	printf("The address stored in ip is %d\n\n", ip);


	printf("The value of x is %d\n", x);
	printf("The value of f(x) is %d\n", f(x)); //passed as reference
	printf("The value of x is %d\n", x); //side-effect is that x is altered

	printf("The value of x is %d\n", x);
	printf("The value of effectless(x) is %d\n", effectless(x)); //passed as reference
	printf("The value of x is %d\n", x); //no side effect when passed as a const reference

	puts("Remember the RED!");
	return 0;
}