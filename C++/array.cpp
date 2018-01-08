#include <cstdio>
#include <iostream>
using namespace std;

int main(int argc, char ** argv) {
	char s[] = { 'a','b', 'c','d', 0 };
	for (char c : s ) {
		if (c == 0) break;
		printf("char is: %c\n", c);
	}
	

	return 0;
}