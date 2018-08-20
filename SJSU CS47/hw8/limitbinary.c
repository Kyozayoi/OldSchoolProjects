
#include <stdio.h>
#include <stdbool.h>
#include <limits.h>

lim(int s, int max, char t)
{
    bool Signed = false;
    bool Max = false;
     
if(s == 0)
{
    Signed = false;
}    
else if(s != 0)
{
    Signed = true;
}
    
if(max == 0)
{
    Max = false;
}    
else if(max != 0)
{
    Max = true;
}

switch(t)
{
case 'c':
    if(Signed)
    {
        if(Max)
        {
            return CHAR_MAX;
        }
        else
        {
            return CHAR_MIN;
        }
    }
    else
    {
        if(Max)
        {
            return UCHAR_MAX;
        }
        else
        {
            return 0;
        }
    }
break;
case 's':
    if(Signed)
    {
        if(Max)
        {
            return SHRT_MAX;
        }
        else
        {
            return SHRT_MIN;
        }
    }
    else
    {
        if(Max)
        {
            return USHRT_MAX;
        }
        else
        {
            return 0;
        }
    }
break;
case 'd':
    if(Signed)
    {
        if(Max)
        {
            return INT_MAX;
        }
        else
        {
            return INT_MIN;
        }
    }
    else
    {
        if(Max)
        {
            return UINT_MAX;
        }
        else
        {
            return 0;
        }
    }
break;
case 'l':
    if(Signed)
    {
        if(Max)
        {
            return LONG_MAX;
        }
        else
        {
            return LONG_MIN;
        }
    }
    else
    {
        if(Max)
        {
            return ULONG_MAX;
        }
        else
        {
            return 0;
        }
    }
break;
case 'z':
    if(Signed)
    {
        if(Max)
        {
            return LLONG_MAX;
        }
        else
        {
            return LLONG_MIN;
        }
    }
    else
    {
        if(Max)
        {
            return ULLONG_MAX;
        }
        else
        {
            return 0;
        }
    }
break;
}
}

binomial(int n, int m)
{
    int difference = (n - m);
    int array1Size = n - difference;
    int array2Size = m;
    int array1[array1Size];
    int array2[array2Size];
    int Numb1 = 1;
    int Numb2 = 1;
    int i, j;
    int FinNumb;
    
if(n < 0 || m < 0)
{
return -1;
}
    for(int i = 0; i < array1Size; i++)
    {
        for(int j = n; j > array1Size; j--)
        {
            array1[i] = j;
        }
    }
    for(int i = 0; i < array2Size; i++)
    {
        for(int j = 0; j < array2Size; j++)
        {
            array2[i] = j;
        }
    }
    for(int i = 0; i < array1Size; i++)
    {
        for(int j = 0; j < array2Size; j++)
        {
            if(array1[i] % array2[j] == 0)
            {
                array1[i] = array1[i] / array2[j];
                array2[j] = 1;
            }
        }
    }
    for(int i = 0; i < array1Size; i++)
    {
        Numb1 = Numb1 * array1[i];
    }
    for(int j = 0; j < array2Size; i++)
    {
        Numb2 = Numb2 * array2[j];
    }
    FinNumb = GCD(Numb1, Numb2);
    return FinNumb;
}

int GCD(int a, int b)
{
 int greaternumber;
 int smallernumber;
 int newsmallernumber;
 //This checks if a and b are already 0
 if(a == 0 || b == 0)
 {
  return -1;
 }
 //The next two if statements check if a and b are negative
 if(a < 0)
 {
  a = -a;
 }
 if(b < 0)
 {
  b = -b;
 }
 //The next two if statements compare a and b to see which one is the larger number
 if(a > b)
 {
   greaternumber = a;
   smallernumber = b;
 }
 else
 {
   greaternumber = b;
   smallernumber = a;	
 }
 //This while loop does Euclid's Algorithm
 while(smallernumber > 0)
 {
   newsmallernumber = greaternumber % smallernumber;
   greaternumber = smallernumber;
   smallernumber = newsmallernumber;
 }
 //After the while loop, return what came out as the GCD
 return greaternumber;
}
