/*
 *
 @LICENSE@
 */


//#include <math.h>
#define SWAP(a,b) temp=(a);(a)=(b); (b)=temp
#define TINY 0.000000000000001
#define EPS  0.000000000000000000000000000001
#define pi 3.1415

/*int flip_bits(int input, int len)
{
  int output = 0;
  int mask1 = 1;
  int mask2 = len >> 1;

  for(;mask2; ) {
     //printf("mask1 is %d mask2 %d\n", mask1, mask2);
     //printf("input %x mask2 %d\n", input, mask2);
     //printf("input & mask2 %x\n", input & mask2);
    if((input & mask2)) output |= mask1;
     //printf("output %x\n", output);
    mask1 = mask1 << 1;
    mask2 = mask2 >> 1;
  }

  return output;
}*/


/*

This was inspirec by readings from:

Press, W. H., Teukolsky, S. A., Vetterling, W. T., and Flannery, B. P. 1997,
Numerical Recipes in C The Art of Scientific Computing Second Edition.
(Cambridge University Press, New York, NY, USA.

*/

extern float data[1024];
extern float cosTheta[10][10];
extern float sinTheta[10][10];


void run() {
  int isign;
  int i; 
  float temp;
  
//look up tables for sin and cos
/*cosTheta[1][0] = 1;
cosTheta[1][1] = 0.999995293809576;
cosTheta[1][2] = 0.999981175282601;
cosTheta[1][3] = 0.999957644551964;
cosTheta[1][4] = 0.999924701839145;
cosTheta[1][5] = 0.999882347454213;
cosTheta[1][6] = 0.999830581795823;
cosTheta[1][7] = 0.999769405351215;
cosTheta[1][8] = 0.999698818696204;
cosTheta[1][9] = 0.999618822495179;
cosTheta[2][0] = 1;
cosTheta[2][1] = 0.999981175282601;
cosTheta[2][2] = 0.999924701839145;
cosTheta[2][3] = 0.999830581795823;
cosTheta[2][4] = 0.999698818696204;
cosTheta[2][5] = 0.999529417501093;
cosTheta[2][6] = 0.99932238458835;
cosTheta[2][7] = 0.999077727752645;
cosTheta[2][8] = 0.998795456205172;
cosTheta[2][9] = 0.998475580573295;
cosTheta[3][0] = 1;
cosTheta[3][1] = 0.999957644551964;
cosTheta[3][2] = 0.999830581795823;
cosTheta[3][3] = 0.999618822495179;
cosTheta[3][4] = 0.99932238458835;
cosTheta[3][5] = 0.998941293186857;
cosTheta[3][6] = 0.998475580573295;
cosTheta[3][7] = 0.997925286198596;
cosTheta[3][8] = 0.99729045667869;
cosTheta[3][9] = 0.996571145790555;
cosTheta[4][0] = 1;
cosTheta[4][1] = 0.999924701839145;
cosTheta[4][2] = 0.999698818696204;
cosTheta[4][3] = 0.99932238458835;
cosTheta[4][4] = 0.998795456205172;
cosTheta[4][5] = 0.998118112900149;
cosTheta[4][6] = 0.99729045667869;
cosTheta[4][7] = 0.996312612182778;
cosTheta[4][8] = 0.995184726672197;
cosTheta[4][9] = 0.993906970002356;
cosTheta[5][0] = 1;
cosTheta[5][1] = 0.999882347454213;
cosTheta[5][2] = 0.999529417501093;
cosTheta[5][3] = 0.998941293186857;
cosTheta[5][4] = 0.998118112900149;
cosTheta[5][5] = 0.997060070339483;
cosTheta[5][6] = 0.99576741446766;
cosTheta[5][7] = 0.994240449453188;
cosTheta[5][8] = 0.99247953459871;
cosTheta[5][9] = 0.990485084256457;
cosTheta[6][0] = 1;
cosTheta[6][1] = 0.999830581795823;
cosTheta[6][2] = 0.99932238458835;
cosTheta[6][3] = 0.998475580573295;
cosTheta[6][4] = 0.99729045667869;
cosTheta[6][5] = 0.99576741446766;
cosTheta[6][6] = 0.993906970002356;
cosTheta[6][7] = 0.9917097536691;
cosTheta[6][8] = 0.989176509964781;
cosTheta[6][9] = 0.986308097244599;
cosTheta[7][0] = 1;
cosTheta[7][1] = 0.999769405351215;
cosTheta[7][2] = 0.999077727752645;
cosTheta[7][3] = 0.997925286198596;
cosTheta[7][4] = 0.996312612182778;
cosTheta[7][5] = 0.994240449453188;
cosTheta[7][6] = 0.9917097536691;
cosTheta[7][7] = 0.988721691960324;
cosTheta[7][8] = 0.985277642388941;
cosTheta[7][9] = 0.981379193313755;
cosTheta[8][0] = 1;
cosTheta[8][1] = 0.999698818696204;
cosTheta[8][2] = 0.998795456205172;
cosTheta[8][3] = 0.99729045667869;
cosTheta[8][4] = 0.995184726672197;
cosTheta[8][5] = 0.99247953459871;
cosTheta[8][6] = 0.989176509964781;
cosTheta[8][7] = 0.985277642388941;
cosTheta[8][8] = 0.98078528040323;
cosTheta[8][9] = 0.975702130038529;
cosTheta[9][0] = 1;
cosTheta[9][1] = 0.999618822495179;
cosTheta[9][2] = 0.998475580573295;
cosTheta[9][3] = 0.996571145790555;
cosTheta[9][4] = 0.993906970002356;
cosTheta[9][5] = 0.990485084256457;
cosTheta[9][6] = 0.986308097244599;
cosTheta[9][7] = 0.981379193313755;
cosTheta[9][8] = 0.975702130038529;
cosTheta[9][9] = 0.969281235356549;



sinTheta[1][0] = -0;
sinTheta[1][1] = -0.00306795676296598;
sinTheta[1][2] = -0.00613588464915448;
sinTheta[1][3] = -0.00920375478205982;
sinTheta[1][4] = -0.0122715382857199;
sinTheta[1][5] = -0.0153392062849881;
sinTheta[1][6] = -0.0184067299058048;
sinTheta[1][7] = -0.0214740802754695;
sinTheta[1][8] = -0.0245412285229123;
sinTheta[1][9] = -0.0276081457789657;
sinTheta[2][0] = -0;
sinTheta[2][1] = -0.00613588464915448;
sinTheta[2][2] = -0.0122715382857199;
sinTheta[2][3] = -0.0184067299058048;
sinTheta[2][4] = -0.0245412285229123;
sinTheta[2][5] = -0.0306748031766366;
sinTheta[2][6] = -0.0368072229413588;
sinTheta[2][7] = -0.0429382569349408;
sinTheta[2][8] = -0.049067674327418;
sinTheta[2][9] = -0.0551952443496899;
sinTheta[3][0] = -0;
sinTheta[3][1] = -0.00920375478205982;
sinTheta[3][2] = -0.0184067299058048;
sinTheta[3][3] = -0.0276081457789657;
sinTheta[3][4] = -0.0368072229413588;
sinTheta[3][5] = -0.0460031821309146;
sinTheta[3][6] = -0.0551952443496899;
sinTheta[3][7] = -0.0643826309298575;
sinTheta[3][8] = -0.0735645635996674;
sinTheta[3][9] = -0.0827402645493757;
sinTheta[4][0] = -0;
sinTheta[4][1] = -0.0122715382857199;
sinTheta[4][2] = -0.0245412285229123;
sinTheta[4][3] = -0.0368072229413588;
sinTheta[4][4] = -0.049067674327418;
sinTheta[4][5] = -0.0613207363022086;
sinTheta[4][6] = -0.0735645635996674;
sinTheta[4][7] = -0.0857973123444399;
sinTheta[4][8] = -0.0980171403295606;
sinTheta[4][9] = -0.110222207293883;
sinTheta[5][0] = -0;
sinTheta[5][1] = -0.0153392062849881;
sinTheta[5][2] = -0.0306748031766366;
sinTheta[5][3] = -0.0460031821309146;
sinTheta[5][4] = -0.0613207363022086;
sinTheta[5][5] = -0.0766238613920315;
sinTheta[5][6] = -0.0919089564971327;
sinTheta[5][7] = -0.107172424956809;
sinTheta[5][8] = -0.122410675199216;
sinTheta[5][9] = -0.137620121586486;
sinTheta[6][0] = -0;
sinTheta[6][1] = -0.0184067299058048;
sinTheta[6][2] = -0.0368072229413588;
sinTheta[6][3] = -0.0551952443496899;
sinTheta[6][4] = -0.0735645635996674;
sinTheta[6][5] = -0.0919089564971327;
sinTheta[6][6] = -0.110222207293883;
sinTheta[6][7] = -0.128498110793793;
sinTheta[6][8] = -0.146730474455362;
sinTheta[6][9] = -0.16491312048997;
sinTheta[7][0] = -0;
sinTheta[7][1] = -0.0214740802754695;
sinTheta[7][2] = -0.0429382569349408;
sinTheta[7][3] = -0.0643826309298575;
sinTheta[7][4] = -0.0857973123444399;
sinTheta[7][5] = -0.107172424956809;
sinTheta[7][6] = -0.128498110793793;
sinTheta[7][7] = -0.149764534677322;
sinTheta[7][8] = -0.170961888760301;
sinTheta[7][9] = -0.192080397049892;
sinTheta[8][0] = -0;
sinTheta[8][1] = -0.0245412285229123;
sinTheta[8][2] = -0.049067674327418;
sinTheta[8][3] = -0.0735645635996674;
sinTheta[8][4] = -0.0980171403295606;
sinTheta[8][5] = -0.122410675199216;
sinTheta[8][6] = -0.146730474455362;
sinTheta[8][7] = -0.170961888760301;
sinTheta[8][8] = -0.195090322016128;
sinTheta[8][9] = -0.21910124015687;
sinTheta[9][0] = -0;
sinTheta[9][1] = -0.0276081457789657;
sinTheta[9][2] = -0.0551952443496899;
sinTheta[9][3] = -0.0827402645493757;
sinTheta[9][4] = -0.110222207293883;
sinTheta[9][5] = -0.137620121586486;
sinTheta[9][6] = -0.16491312048997;
sinTheta[9][7] = -0.192080397049892;
sinTheta[9][8] = -0.21910124015687;
sinTheta[9][9] = -0.245955050335795; */
  int output;
  int mask1;
  int mask2;
  
  //do bit swapping:
  for(i=0; i<1024; i+=2) {
     //printf("bit swap is %x %x\n", i/2, flip_bits(i/2, nn));
    output = 0;
    mask1 = 1;
    mask2 = 1024 >> 1;

    for(;mask2; ) {
      if((i/2 & mask2)) output |= mask1;
      mask1 = mask1 << 1;
      mask2 = mask2 >> 1;
    }
    SWAP(data[i], data[2*output]);
    SWAP(data[i+1], data[2*output+1]);
  }

  /*for(i=0; i<nn*2; i+=2) {
     printf("data is %f +i%f\n", data[i], data[i+1]);
  }*/
  
  //FFT:
  int n = 512;
  int depth = 2;
  int j, m;
  float tempr, tempi;
  
  while(depth < n) {
    for(i = 0; i < depth; i+=2) {
      for(j = i; j <= n; j+=depth) {
      
        m = j + 2*depth;
	tempr = data[m]*cosTheta[depth][i] - data[m+1]*sinTheta[depth][i];
        tempi = data[m+1]*cosTheta[depth][i] + data[m]*sinTheta[depth][i];
        
	data[m+1] = data[j] - tempr;
	data[m+1] = data[j+1] - tempi;
	data[j] += tempr;
	data[j+1] += tempi;
	
      }
      
    }
    depth *= 2;
  
  }

  /*for(i=0; i<nn*2; i+=2) {
     printf("data is %f +i%f\n", data[i], data[i+1]);
  }*/
}
