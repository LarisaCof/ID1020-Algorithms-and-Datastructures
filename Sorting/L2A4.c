// Implement a function in C which takes an
// array of integers (both positive and negative)
// and orders the elements in the array so that all
// negative elements come before the positive.
// You are not allowed to sort the array - only collect
// all negative values first. The algorithm should
// only use O(1) extra memory (i.e. be in-place
// Wikipedia: In-place algorithm (Länkar till en externa sida.)
// Länkar till en externa sida.)
// Author: Larisa Cof
// Date: 2020-09-19

#include <stdio.h>

int main () {
// read length of array from stdin
printf("Enter length: ");
int arraylength;
scanf("%d", &arraylength);

printf("Enter all values: ");

int array[arraylength]; // fixed length
int index = 0;
int input;
int firstpos = 0; // first position in the array
int lastpos = arraylength - 1; // last position in the array

while (index < arraylength) {

		scanf("%d", &input); // get input

    if (input < 0) // if negative, fill the array from the front
    {
      array[firstpos] = input;
      firstpos++; // next
    }
    else { // if positive, fill the array from the back
        array[lastpos] = input;
        lastpos--; // next
      }
    index++; // number of elements
}

// Print the array
	int j = 0;
  for(j = 0; j < arraylength; j++)
    {
          printf("[");
          printf("%d", array[j]);
          printf("] ");
        }
}

// EXAMPLE EXECUTION

// Larisas-MacBook-Pro-778:Desktop larisacof$ gcc -o L2A4 L2A4.c
// Larisas-MacBook-Pro-778:Desktop larisacof$ ./L2A4
// Enter length: 5
// Enter all values: 2
// 6
// 4
// -6
// -2
// [-6] [-2] [4] [6] [2]
