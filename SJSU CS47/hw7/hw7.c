
#include <stdio.h>

/* test program for reverse Polish calculator */
int main(int argc, char *argv[])
{
	FILE *fpi, *fpo;
	
	fpi = stdin;
        printf("Input my value: \n");
	if (argc > 1) {
		fpi = fopen(argv[1], "r");
		if (fpi == NULL) {
			printf("%s failed to open\n", argv[1]);
			return -1;
		}
	}
	
	fpo = stdout;
	if (argc > 2) {
		fpo = fopen(argv[2], "w");
		if (fpo == NULL) {
			printf("%s failed to open\n", argv[2]);
			return -1;
		}
	}

	RPNCalc(fpi, fpo);

	return 0;
}