/*
By Anthony Shih Secret#82
11/17/2014
CS49c Section 2
Homework 8
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "calcf.h"
#include <ctype.h>
#include "calcf.h"
//Below is the code for the Reverse Polish Calculator
//Taken from Kernighan and Ritchie
#define MAXOP 100
#define NUMBER '0'
#define CALCULUS '1'
#define STORAGE '2'

int getop(char [], FILE *fpi);
void push(double);
double pop(void);
int getch(FILE *fpi);
void ungetch(int);
void Store(char s[], FILE *fpi);
void CalcMath(char s[], FILE *fpo);
double storages[10];

int RPNCalc(FILE *fpi, FILE *fpo) {
    int type;
    double op2;
    double op1;
    char comment[MAXOP];
    char s[MAXOP];
    while ((type = getop(s, fpi)) != EOF) {
        switch (type) {
            case NUMBER:
                push(atof(s));
                break;
            case '#': //One of the adjustments needed for HW8
                fgets(comment, MAXOP, fpi);
                fprintf(fpo, "#%s", comment);
                break;
            case 'D': //One of the adjustments needed for HW8
                op1 = pop();
                push(op1);
                push(op1);
                break;
            case CALCULUS: //Using a helper function for this.
                CalcMath(s, fpo);
                break;
            case STORAGE: //Using a helper function for this.
                Store(s, fpo);
                break;
            case '=': //This was from HW7
                op1 = pop();
                fprintf(fpo, "\t%.16g\n", op1);
                push(op1);
                break;
            case 'X': //This was from HW7
                op1 = pop();
                op2 = pop();
                push(op1);
                push(op2);
                break;
            case '+':
                push(pop() + pop());
                break;
            case '*':
                push(pop() * pop());
                break;
            case '-':
                op1 = pop();
                push(pop() - op1);
                break;
            case '/':
                op1 = pop();
                if (op1 != 0.0) {
                    push(pop() / op1);
                } else {
                    fprintf(fpo, "error: zero divisor\n");
                }
                break;
            case '\n': //This was from HW7
                op1 = pop();
                fprintf(fpo, "\t%.16g\n", op1);
                push(op1);
                break;
            case '0xd': //This was from HW7
                break;
            default:
                fprintf(fpo, "Error: Unknown Command: %s\n", s);
                break;
        }
    }
    return 0;
}

void CalcMath(char s[], FILE *fpo) { //This code compares the string to the various math functions.
    if (strcmp(s, "sin") == 0) { //If it matches, perform the corresponding function.
        push(sin(pop()));
    } else if (strcmp(s, "cos") == 0) {
        push(cos(pop()));
    } else if (strcmp(s, "tan") == 0) {
        push(tan(pop()));
    } else if (strcmp(s, "asin") == 0) {
        push(asin(pop()));
    } else if (strcmp(s, "acos") == 0) {
        push(acos(pop()));
    } else if (strcmp(s, "atan") == 0) {
        push(atan(pop()));
    } else if (strcmp(s, "exp") == 0) {
        push(exp(pop()));
    } else if (strcmp(s, "log") == 0) {
        push(log(pop()));
    } else if (strcmp(s, "pow") == 0) {
        double op1 = pop();
        double op2 = pop();
        push(pow(op2, op1));
    } else if (strcmp(s, "sqrt") == 0) {
        push(sqrt(pop()));
    } else { //If its any other combination of lower case letters, print an error.
        fprintf(fpo, "Error: Unknown Command: %s\n", s);
    }
}

void Store(char s[], FILE *fpo) { //Same thing with the CalcMath function, but with S/R 0-9
    if (strcmp(s, "S0") == 0) {
        storages[0] = pop(); //Popping into another array to store it easier.
    } else if (strcmp(s, "R0") == 0) {
        push(storages[0]); //Don't need 9 variables all called storage.
    } else if (strcmp(s, "S1") == 0) {
        storages[1] = pop();
    } else if (strcmp(s, "R1") == 0) {
        push(storages[1]);
    } else if (strcmp(s, "S2") == 0) {
        storages[2] = pop();
    } else if (strcmp(s, "R2") == 0) {
        push(storages[2]);
    } else if (strcmp(s, "S3") == 0) {
        storages[3] = pop();
    } else if (strcmp(s, "R3") == 0) {
        push(storages[3]);
    } else if (strcmp(s, "S4") == 0) {
        storages[4] = pop();
    } else if (strcmp(s, "R4") == 0) {
        push(storages[4]);
    } else if (strcmp(s, "S5") == 0) {
        storages[5] = pop();
    } else if (strcmp(s, "R5") == 0) {
        push(storages[5]);
    } else if (strcmp(s, "S6") == 0) {
        storages[6] = pop();
    } else if (strcmp(s, "R6") == 0) {
        push(storages[6]);
    } else if (strcmp(s, "S7") == 0) {
        storages[7] = pop();
    } else if (strcmp(s, "R7") == 0) {
        push(storages[7]);
    } else if (strcmp(s, "S8") == 0) {
        storages[8] = pop();
    } else if (strcmp(s, "R8") == 0) {
        push(storages[8]);
    } else if (strcmp(s, "S9") == 0) {
        storages[9] = pop();
    } else if (strcmp(s, "R9") == 0) {
        push(storages[9]);
    } else { //If its another combination, print an error.
        fprintf(fpo, "Error: Unknown Command: %s\n", s);
    }
}

int getop(char s[], FILE *fpi) {
    int i = 0;
    int c;
    while ((s[0] = c = getch(fpi)) == ' ' || c == '\t')
        ;
    s[1] = '\0';
    if (islower(c)) { //Checking if c is a lower case letter.
        i = 0;
        while (islower(s[++i] = c = getch(fpi)))
            ; //Getch the rest of the string to s
        s[i] = '\0';
        if (c != EOF) {
            ungetch(c);
        }
        return CALCULUS; //Send it to CalcMath for further checking.
    }
    if (isupper(c)) { //Checking if c is an upper case letter.
        i = 0;
        char b = getch(fpi);
        if (isdigit(b)) { //Checks if a number follows the first one.
            s[i++] = c;
            s[i++] = b;
            s[i] = '\0';
            if (c == 'R' || c == 'S') { //If it starts with S or R, send to Storage
                return STORAGE;
            }
        } else //else, return the letter to return an error.
            return c;
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
