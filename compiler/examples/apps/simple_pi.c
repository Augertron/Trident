/*
 *
 @LICENSE@
 */


//#include <math.h>

/*

Wallis' Formula
The Area of the Quadrant of a Circle Before Integral Calculus

John Wallis (1616-1703) discovered a method for calculating the value
of by finding the area under the quadrant of a circle. Some evidence
suggests that a similar method was also used in Japan in the late 17th
Century. A quadrant of a unit circle has the area of pi/4. By finding
that area, one can find the value of pi. 

*/

extern float pi;

void run() {
  int span = 1000000;
  float delta = 1.0 / span;
  int i = 0;
  float x = 0.0;
  pi = 0.0;

  for(i = 0; i < span; i++) {
    x = i * delta;
    pi = pi + (float)4.0*delta*(float)((float)sqrt((float)(1 - (x*x))));
  }

}
