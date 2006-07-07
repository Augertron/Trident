/*
 *
 @LICENSE@
 */


extern float N, P;
extern int i;
float K[3][4] = {{0,1,2,3}, {4,5,6,7}, {8,9,10,11}};
float L[3][4];
float M[3][4];
float O[3][4];
void run() {
  N=  (K[1][i]*L[i][3] + M[1][i]*N)*O[i][0];
  P = N + P;
}
