/*
 *
 @LICENSE@
 */


//#include <math.h>

/*

Euclidean distance between two points in three space.
*/

extern float x1[5],x2[5],y1[5],y2[5],z1[5],z2[5],dist[5];

// really you should use pow(blah,2);  There is no ^ for powers in c.

void run() {
  
  int i;
  for(i=0; i<5; i++) {
    dist[i] = sqrtf(((x2[i]-x1[i])*(x2[i]-x1[i])) + 
                    ((y2[i]-y1[i])*(y2[i]-y1[i])) + 
		    ((z2[i]-z1[i])*(z2[i]-z1[i])));
  }

}
