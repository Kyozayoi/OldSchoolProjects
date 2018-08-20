/*
By Anthony Shih Secret#82
11/17/2014
CS49c Section 2
Homework 8
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
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
                fprintf(fpo, "%s\n", comment);
                break;
            case 'D': //One of the adjustments needed for HW8
                op1 = pop();
                push(op1);
                push(op1);
                break;
            case CALCULUS:
                CalcMath(s, fpo);
                break;
            case STORAGE:
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
                fprintf(fpo, "error: unknown command %sHI\n", s);
                break;
        }
    }
    return 0;
}

void CalcMath(char s[], FILE *fpo) {
    if (strcmp(s, "sin") == 0) {
        push(sin(pop()));
    }
    if (strcmp(s, "cos") == 0) {
        push(cos(pop()));
    }
    if (strcmp(s, "tan") == 0) {
        push(tan(pop()));
    }
    if (strcmp(s, "asin") == 0) {
        push(asin(pop()));
    }
    if (strcmp(s, "acos") == 0) {
        push(acos(pop()));
    }
    if (strcmp(s, "atan") == 0) {
        push(atan(pop()));
    }
    if (strcmp(s, "exp") == 0) {
        push(exp(pop()));
    }
    if (strcmp(s, "log") == 0) {
        push(log(pop()));
    }
    if (strcmp(s, "pow") == 0) {
        double op1 = pop();
        double op2 = pop();
        push(pow(op1, op2));
    }
    if (strcmp(s, "sqrt") == 0) {
        push(sqrt(pop()));
    } else {
        fprintf(fpo, "error: unknown command %s\n", s);
    }
}

void Store(char s[], FILE *fpo) {
    int storages0, storages1, storages2,
            storages3, storages4, storages5,
            storages6, storages7, storages8, storages9; //Added this variable for cases 'S' and 'R'.
    double storage0, storage1, storage2,
            storage3, storage4, storage5,
            storage6, storage7, storage8, storage9; //Added this variable for cases 'S' and 'R'.
    if (strcmp(s, "S0") == 0) {
        storage0 = pop();
        storages0++;
    }
    if (strcmp(s, "R0") == 0) {
        if (storages0 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage0);
        }
    }
    if (strcmp(s, "S1") == 0) {
        storage1 = pop();
        storages1++;
    }
    if (strcmp(s, "R1") == 0) {
        if (storages1 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage1);
        }
    }
    if (strcmp(s, "S2") == 0) {
        storage2 = pop();
        storages2++;
    }
    if (strcmp(s, "R2") == 0) {
        if (storages2 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage2);
        }
    }
    if (strcmp(s, "S3") == 0) {
        storage3 = pop();
        storages3++;
    }
    if (strcmp(s, "R3") == 0) {
        if (storages3 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage3);
        }
    }
    if (strcmp(s, "S4") == 0) {
        storage4 = pop();
        storages4++;
    }
    if (strcmp(s, "R4") == 0) {
        if (storages4 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage4);
        }
    }
    if (strcmp(s, "S5") == 0) {
        storage5 = pop();
        storages5++;
    }
    if (strcmp(s, "R5") == 0) {
        if (storages5 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage5);
        }
    }
    if (strcmp(s, "S6") == 0) {
        storage6 = pop();
        storages6++;
    }
    if (strcmp(s, "R6") == 0) {
        if (storages6 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage6);
        }
    }
    if (strcmp(s, "S7") == 0) {
        storage7 = pop();
        storages7++;
    }
    if (strcmp(s, "R7") == 0) {
        if (storages7 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage7);
        }
    }
    if (strcmp(s, "S8") == 0) {
        storage8 = pop();
        storages8++;
    }
    if (strcmp(s, "R8") == 0) {
        if (storages8 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage8);
        }
    }
    if (strcmp(s, "S9") == 0) {
        storage9 = pop();
        storages9++;
    }
    if (strcmp(s, "R9") == 0) {
        if (storages9 == 0) {
            fprintf(fpo, "error: nothing stored\n");
        } else {
            push(storage9);
        }
    } else {
        fprintf(fpo, "error: unknown command %s here \n", s);
    }
}



