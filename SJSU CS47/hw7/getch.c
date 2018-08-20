#include <stdio.h>
#include "calcf.h"

#define BUFSIZE 100

char buf[BUFSIZE];
int bufp = 0;

int getch(FILE *fpi) {
    return (bufp > 0) ? buf[--bufp] : getc(fpi);
}

void ungetch(int c) {
    if (bufp >= BUFSIZE) {
       printf("ungetch: too many characters\n");
    } else {
        buf[bufp++] = c;
    }
}
