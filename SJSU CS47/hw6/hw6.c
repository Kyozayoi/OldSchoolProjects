/*
By Anthony Shih Secret#82
10/27/2014
CS49c Section 2
Homework 6
*/

#include <math.h>;
#include <stdlib.h>;
#include <stdio.h>;

int bits(int n, unsigned int start, unsigned int pattern, char *fn) {
    FILE* myFile = NULL; //Initializing my File pointer.
    myFile = fopen(fn, "w"); //Opening my File, setting it to write.
    int k;
    int c = 0; //My counter.
    unsigned int template = start; //This is where I'll append the bits.
    unsigned int pat = pattern >> 1; //This is the variable for the pattern.
    unsigned int first;
    unsigned int tbn = 1; //The Bit Number, the bit that'll be appended.
    do { //I do the first set of bit appending, 
        first = pat & template; //This is the set of bits I'm looking at.
        tbn = first % 2; //Sets tbn as the last bit, 0 or 1.
        for (k = 1; k < n; k++) { //The for loop XOR the other bits of first.
            first = first >> 1;
            tbn = tbn ^ (first % 2);
        } //After XOR, add it to template.
        template = (template << 1) | tbn; //Keeping track of my bit, tbn.
        fprintf(myFile, "%d", tbn); //Printing it to the file.
        template = template << (32 - n);
        template = template >> (32 - n); //Set template to the last n bits.
        c++; //Increment C.
    } while (template != start); //If template and start don't match, keep going
    fclose(myFile); //Closing the file.
    return c; //Return how many times it appended.
}