#include <cstdio>
#include <iostream>
using namespace std;

int main(int argc, char ** argv) {
	int a[] = { 1,2,3,4,5 };
	int i = 1;

	do {
		printf("element %d is %d!\n", i, a[i-1]);
		i++;
	} while (i < 0); //while (i < 6);


	/*while (i < 6) {
		printf("element %d is %d!\n", i, a[i-1]);
		i++;
	}*/

	return 0;
}