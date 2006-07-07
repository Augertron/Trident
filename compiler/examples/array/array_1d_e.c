/*
 *
 @LICENSE@
 */


extern int i,j,k,l,m;
extern float N, P;
extern  float J[20000];
extern  float K[500000]; 
extern  float L[500000];
extern  float M[500000];
extern  float O[500000];

void run() {
  N=  ((K[i]*L[j] + M[k]*N)*O[l])+J[m];
  P = N + P;
}
