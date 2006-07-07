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
  float span = 20.0;
  float delta = 1.0 / span;
  float i = 0.0;
  float x = 0.0;
  pi = 0.0;

  for(i = 0.0; i < span; i += 1.0) {
    x = i * delta;
    pi = pi + (float)4.0*delta*(sqrtf(1.0 - (x*x)));
  }

}
