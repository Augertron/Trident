/*
 *
 @LICENSE@
 */


#include <math.h>

extern double x;
extern double y;
extern double res;

void foo() {
  res = sqrt (pow(x, y) + y);
}
