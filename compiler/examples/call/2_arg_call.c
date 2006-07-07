/*
 *
 @LICENSE@
 */


#include <math.h>

extern double x;
extern double y;
extern int *nptr;
extern double res;

void foo() {
  res = frexp (x+y, nptr);
}
