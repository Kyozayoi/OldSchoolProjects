/*
By Anthony Shih Secret#82
11/26/2014
CS49c Section 2
Homework 9
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct List { //This is my struct
    unsigned int data;
    char name[16];
    char grade;
    struct List *next;
};

void insertList(struct List *lptr, unsigned int idata, char *iname, char igrade) {
    struct List *student = (struct List *) malloc(sizeof (struct List)); //First I allocate some space for the incoming structure data.
    student->data = idata; //Put all the relevant info into the struct.
    strcpy(student->name, iname);
    student->grade = igrade;
    while (lptr->next != NULL) { //This will be how I iterate through the list.
        if (lptr->next->data > idata && idata > lptr->data) { //Once I get to this point
            break;
        }
        lptr = lptr->next;
    }
    student->next = lptr->next; //I place the struct in between the ones I stopped at.
    lptr->next = student;
}

void printList(FILE *fpo, struct List *lptr) { //Pretty simple, iterate through the list
    lptr = lptr->next; //Avoid the first and last node.
    while (lptr->next != NULL) { 
        fprintf(fpo, "%09u \t%s \t%c\n", lptr->data, lptr->name, lptr->grade);
        lptr = lptr->next;
    }
}

void freeList(struct List *lptr) { //Same thing, iterate through the list.
    lptr = lptr->next; //Avoid the first and last node
    struct List *temp = (struct List *) malloc(sizeof (struct List)); //I wasn't sure if we need to make a temp
    temp = lptr->next; //But I did to be safe.
    while (temp != NULL) { 
        free(lptr); //Free the current node
        lptr = temp;
        temp = lptr->next; //Move on to the next node
    }
}