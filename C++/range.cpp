#include <cstdio>
#include <iostream>
using namespace std;

int main(int argc, char ** argv) {
	//int a[] = { 1,2,3,4,5 };
	string a = "Remember The RED!";
	for (char i : a){
		if (i == 0) break;
		printf("%c\n", i);
	}

	return 0;
}