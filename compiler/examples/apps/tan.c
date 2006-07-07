/*
 *
 @LICENSE@
 */


//#include <math.h>
#define TINY 0.000000000000001
#define EPS  0.000000000000000000000000000001
/*

This method was inspired by reading from:

Press, W. H., Teukolsky, S. A., Vetterling, W. T., and Flannery, B. P. 1997,
Numerical Recipes in C The Art of Scientific Computing Second Edition.
(Cambridge University Press, New York, NY, USA.

*/
		       

extern double theta;
extern double f;

/*
this estimates tan using a continued fraction representation:

tan x = x / 1-(x^2/ (3- x^2/(5-...
*/
void run() {

  double a, b, C, D, delta;
  int j = 0;
  b = 1;
  f = TINY;
  C = f;
  D = 0;
  
  do {
    j++;
    if(j==(int)1)
      a = theta;
    else
      a = theta*theta;
      
    D = b + a * D;
    if(fabs(D)<TINY)
      D = TINY; 
    C = b + a / C;
    if(fabs(C)<TINY)
      C = TINY; 
    
    D = 1/D;
    delta = C*D;
    
    f = f*delta;
    
    b+=2;
  
  } while(fabs(delta-1) < EPS);


}
