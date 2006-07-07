/*
 *
 @LICENSE@
 */


extern int i, j;
extern float N, P;
extern  float K[10];
extern  float L[10];
void run() {
  N=  (K[i]*L[j] + K[j]*N)*L[i];
  P = N + P + K[i+j];
}
