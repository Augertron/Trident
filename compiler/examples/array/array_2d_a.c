/*
 *
 @LICENSE@
 */


extern float N, P;
extern int i;
extern float K[3][4]; 
extern float L[3][4];
extern float M[3][4];
extern float O[3][4];
void run() {
  N=  (K[1][i]*L[i][3] + M[1][i]*N)*O[i][0];
  P = N + P;
}
