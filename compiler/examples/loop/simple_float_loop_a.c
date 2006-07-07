/*
 *
 @LICENSE@
 */


float N=1;
float P = 0;
float K[100], L[100], M[100], O[100];
void run() {
  int i;
  int I=6 ;
  for (i = 0; i < I; i++) {
    N=  (K[i]*L[i] + M[i]*N)*O[i];
    P = N + P;
  }
}
