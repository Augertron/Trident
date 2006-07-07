/*
 *
 @LICENSE@
 */


//#include <math.h>

/*

Euclidean distance between two points in three space.
*/

extern float x1,x2,y1,y2,z1,z2,dist;

// really you should use pow(blah,2);  There is no ^ for powers in c.

void run() {
  dist = sqrtf(((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)) + ((z2-z1)*(z2-z1)));

}
