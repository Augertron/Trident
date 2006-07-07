/*
 *
 @LICENSE@
 */


extern int i;
extern float N, P;
extern  float K[]; 
extern  float L[];
extern  float M[];
extern  float O[];
void run() {
  N=  (K[i]*L[i] + M[i]*N)*O[i];
  P = N + P;
}
