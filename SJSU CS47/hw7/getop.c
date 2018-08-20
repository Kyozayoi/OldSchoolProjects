#include <stdio.h>
#include <ctype.h>
#include "calcf.h"
#define CALCULUS '1'
#define STORAGE '2'
int getch(FILE *fpi);
void ungetch(int);

int getop(char s[], FILE *fpi) {
    int i = 0;
    int c;
    while ((s[0] = c = getch(fpi)) == ' ' || c == '\t')
        ;
    s[1] = '\0';
    if (islower(c)) {
        while (islower(s[++i] = c = getch(fpi))) 
            ;
        s[i] = '\0';    
        return CALCULUS;
    }
    if (c == 'S' || c == 'R') {
        while (isdigit(s[++i] = c = getch(fpi)))
            ;
        s[i] = '\0';
        return STORAGE;
    }
    if (!isdigit(c) && c != '.') {
        return c;
    }
    i = 0;
    if (isdigit(c))
        while (isdigit(s[++i] = c = getch(fpi)))
            ;
    if (c == '.')
        while (isdigit(s[++i] = c = getch(fpi)))
            ;
    s[i] = '\0';
    if (c != EOF)
        ungetch(c);
    return NUMBER;
}

            /*   s[j] = c;
            j++;
            c = getch(fpi);
        
        s[j + 1] = '\0';*/