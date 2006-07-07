/*
 *
 @LICENSE@
 */


extern int i;
extern float N[1], P[1];
extern  float K[1]; 
extern  float L[1];
extern  float M[1];
extern  float O[1];
void run() {
  N[0] =  (K[i]*L[i] + M[i]*N[0])*O[i];
  P[0] = N[0] + P[0];
}
