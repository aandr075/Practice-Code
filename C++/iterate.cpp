#include <cstdio>
#include <iostream>
using namespace std;

int main(int argc, char ** argv) {
	/*int a[] = { 1,2,3,4,5 };
	
	for (int i = 1; i < 6; i++) {
		printf("element %d is %d\n", i, a[i - 1]);
	}*/

	char * s = "red!";

	for (char * cp = s; *cp; cp++) {
		printf("%c\n", *cp);
	}

	return 0;
}