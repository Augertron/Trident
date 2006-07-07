/*
 *
 @LICENSE@
 */


extern int i;
extern float N, P;
extern  float K[1]; 
extern  float L[1];
extern  float M[1];
extern  float O[1];
void run() {
  N=  (K[i]*L[i] + M[i]*N)*O[i];
  P = N + P;
}
