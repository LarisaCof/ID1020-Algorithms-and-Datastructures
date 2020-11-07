#include <stdio.h>

void iterate () {

char itarray[10]; // skapar en array med fixt antal (10)
itarray[0] = getchar(); // läser från stdin
int i = 0;
int count = 0;

//så länge senaste karaktären inte är en nyrad
for (i = 1; itarray[i-1] != '\n'; i++) {
  itarray[i] = getchar(); // fyll alla platser, getchar minns senaste --> nästa vid nytt anrop
  count++; // antal element i vektorn

}

int j = count;
for (j = count; j >= 0; j--) {
  putchar(itarray[j]); // skriver ut i omvänd ordning

}
}

//_____________________________________________________________

void recurse () {
char c = getchar(); // sparar det som skrivs i från stdin i stack

if (c != '\n') // allt utom enter
  recurse(); // hämta nästa
  putchar(c); // läser från toppen (LIFO) och skriver stdin
}

main () {

  printf("Enter characters: ");
  iterate();
  //recurse();
}
