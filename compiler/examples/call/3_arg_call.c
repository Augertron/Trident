/*
 *
 @LICENSE@
 */


double bar(double b, double c, double d);
extern double x;
extern double y;
extern double res;

void foo() {
  res = bar (x + y, x, y);
}
