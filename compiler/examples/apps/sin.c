/*
 *
 @LICENSE@
 */


//#include <math.h>
#define TINY 0.000000000000001
#define EPS  0.000000000000000000000000000001
/*

These two methods were inspired by reading from:

Press, W. H., Teukolsky, S. A., Vetterling, W. T., and Flannery, B. P. 1997,
Numerical Recipes in C The Art of Scientific Computing Second Edition.
(Cambridge University Press, New York, NY, USA.

*/
		       

extern double theta;
extern double f0;

/*
this estimates tan using a continued fraction representation:

tan x = x / 1-(x^2/ (3- x^2/(5-...
*/
double tan(double theta) {

  double a, b, f, C, D, delta;
  int j = 0;
  b = 1;
  f = TINY;
  C = f;
  D = 0;
  
  do {
    j++;
    if(j==1)
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

  return f;

}

//this estimates the sin function
void run() {

  double t, f;
  
  t = tan(theta/2);
  
  f = 2*t/(1+t*t);
  f0 = f;
  
}

