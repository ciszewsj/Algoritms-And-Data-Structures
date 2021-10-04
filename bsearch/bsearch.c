#include <stdio.h>
int bsearch(int* num, int n, int toFind){
	int l = 0;
	int p = n - 1;
        int mid;
	while (l <= p){
		mid = l + (p - l) / 2;
		if(num[mid] == toFind) {
			return mid;
		} 
		else if (num[mid] > toFind) {
			p = mid  - 1;
		}
		else {
			l = mid + 1;
		}
	}
	return -1;
}

int main(int argc, char** argv){
	int num[10];
	num[0] = 0;
	num[1] = 1;
	num[2] = 23;
	num[3] = 25;
	num[4] = 44;
	num[5] = 46;
	num[6] = 70;
	num[7] = 100;
	num[8] = 2137;
	num[9] = 99999;
	printf("%d == 9\n", bsearch(num, 10, 99999));
	printf("%d == 0\n", bsearch(num, 10, 0));
	printf("%d == 5\n", bsearch(num, 10, 46));
	printf("%d == -1\n", bsearch(num, 10, 99));
	return 0;
}

