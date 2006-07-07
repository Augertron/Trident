/*
 *
 @LICENSE@
 */


extern float N, P;
extern int i;
extern float K[3][4][5]; 
extern float L[3][4][5];
extern float M[3][4][5];
extern float O[3][4][5];
void run() {
  N=  (K[1][2][i]*L[i][2][3] + M[1][i][2]*N)*O[i][0][2];
  P = N + P;
}
