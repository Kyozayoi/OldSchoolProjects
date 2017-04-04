/*
By Anthony Shih Secret#82
10/15/2014
CS49c Section 2
Homework 5
*/

#include <stdio.h>
#include <string.h>
#include <ctype.h>

int notPalindrome(char *x)
{
    int i, ln, rn, c, fl; //This is where I'm initializing the various int variables I will be using.
    fl = (strlen(x)); //fl is the FinalLength of the string, kinda weird but I didn't want to use l for length.
    c = 0; //c is the expected answer. I wanted it to be count, but it just sorted ended up as the answer instead.
    ln = 0; //This is the "left" number, or the leftmost entry of the string.
    rn = fl - 1; //This is the "right" number, or the rightmost entry of the string.
    while(ln < rn) //The while loop goes through the palindrome by incrementing ln up and incrementing rn down until they pass each other.
    {
    while(isalpha(x[ln]) == 0) //The next two while loops checks for empty spaces and simply increments the place in the string to get to a alphabetical character.
    {
        ln++;
    }
    while(isalpha(x[rn]) == 0)
    {
        rn--;
    }
    if(toupper(x[ln]) != toupper(x[rn])) //This code actually checks where the Palindrome ends. If the left value and the right value aren't the same, then I make c into rn.
    {
        c = rn;
    }
    ln++;
    rn--;
    }
    return c;//If c never changed, then it is 0 and the string is a palindrome. Otherwise, the above if statement would've changed c to the appropriate number where the palindrome stops.
}