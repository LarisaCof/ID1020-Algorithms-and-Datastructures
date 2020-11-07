// Author: Larisa Cof
// Date: 2020-09-27
//
// Assignment:
// Write a simple filter to clean a text,
// i.e. to remove all characters that are not
// alphabetic, blank or newline - replacing
// every such character by a blank to keep
// the number of characters constant to
// the original text.

#include <stdio.h>
#include <ctype.h>

int main ()
{

char character = getchar(); // reads single character from input stream

while (character != EOF) // go through the entire input
{
    if (isalpha(character) == 0) // isalpha returns zero if the character is not A-Z
    {
        putchar(' '); // if input is not a ASCII-character, replace it by a blank

      } else {
          putchar(character); // print out the ASCII-character

        }
      character = getchar(); // read next
    }
}

// EXAMPLE EXECUTION
// Larisas-MacBook-Pro-778:LAB3 larisacof$ gcc -o LAB3A1 LAB3A1.c
// Larisas-MacBook-Pro-778:LAB3 larisacof$ ./LAB3A1
// Hej ja5g hete72r La'ri-sa
// Hej ja g hete  r La ri sa
