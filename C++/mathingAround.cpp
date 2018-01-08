#include <cstdio>
#include <cstdarg> //used with va_ variables; allows variable number of parameters
#include <iostream> //allows cout
#include <string> //imports string library
#include <exception>
#include <cmath>

using namespace std;

int main(int argc, char ** argv) {
	__int64 init = 123456789128675429;
	int illions[6];
	int place = 5;
	printf("initial value: %I64d\n", init);

	while (init > 999) {
		int lastThree = init % 1000;
		printf("last three digits: %d\n", lastThree);
		illions[place] = lastThree;
		place--;

		init = init / 1000;
		printf("shifted value: %I64d\n", init);
	}
	illions[place] = init;

	printf("last three digits: %d\n", init);

	if (illions[0]) { printf("%d quadrillion, ", illions[0]); }
	if (illions[1]) { printf("%d trillion, ", illions[1]); }
	if (illions[2]) { printf("%d billion, ", illions[2]); }
	if (illions[3]) { printf("%d million, ", illions[3]); }
	if (illions[4]) { printf("%d thousand, ", illions[4]); }
	if (illions[5]) { printf("%d", illions[5]); }

	return 0;
}