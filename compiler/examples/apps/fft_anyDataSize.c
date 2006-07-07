/*
 *
 @LICENSE@
 */


//#include <math.h>
#define SWAP(a,b) temp=(a);(a)=(b); (b)=temp
#define TINY 0.000000000000001
#define EPS  0.000000000000000000000000000001
#define pi 3.1415
/*

The top two methods were inspired by reading from:

Press, W. H., Teukolsky, S. A., Vetterling, W. T., and Flannery, B. P. 1997,
Numerical Recipes in C The Art of Scientific Computing Second Edition.
(Cambridge University Press, New York, NY, USA.

*/
		       


/*
this estimates tan using a continued fraction representation:

tan x = x / 1-(x^2/ (3- x^2/(5-...
*/
double tan(double theta) {

  double a, b, f, C, D, delta;
  int j = 0;
  b = 1;
  f = TINY;
  C = f;
  D = 0;
  
  do {
    j++;
    if(j==1)
      a = theta;
    else
      a = theta*theta;
      
    D = b + a * D;
    if(fabs(D)<TINY)
      D = TINY; 
    C = b + a / C;
    if(fabs(C)<TINY)
      C = TINY; 
    
    D = 1/D;
    delta = C*D;
    
    f = f*delta;
    
    b+=2;
  
  } while(fabs(delta-1) < EPS);

  return f;

}

//this estimates the sin function
double sin(double theta) {

  double t, f;
  
  t = tan(theta/2);
  
  f = 2*t/(1+t*t);

  return f;

}

int flip_bits(int input, int len)
{
  int output = 0;
  int mask1 = 1;
  int mask2 = len >> 1;

  for(;mask2; ) {
    if((input & mask2)) output |= mask1;
    mask1 = mask1 << 1;
    mask2 = mask2 >> 1;
  }

  return output;
}


/*

This was inspired by readings from:

Press, W. H., Teukolsky, S. A., Vetterling, W. T., and Flannery, B. P. 1997,
Numerical Recipes in C The Art of Scientific Computing Second Edition.
(Cambridge University Press, New York, NY, USA.

*/

//actually data has nn*2 elements, but how can I declare it so?

/*

this calculates the FFT of data.  nn is the number of elements in the array and
MUST be a power of two.  data is actually 2*nn long, where each even indexed
element contains the real part of a piece of data and each odd indexed element,
the imaginary part.
*/
extern unsigned long nn;
extern float data[];
void run() {
  int isign;
  int i; 
  float temp;
  float theta, sinTheta, cosTheta;
  float alpha, beta, phi, sinPhi;
  
  
  //do bit swapping:
  for(i=0; i<nn; i+=2) {
     //printf("bit swap is %x %x\n", i/2, flip_bits(i/2, nn));
    SWAP(data[i], data[2*flip_bits(i/2, nn)]);
    SWAP(data[i+1], data[2*flip_bits(i/2, nn)+1]);
  }

  /*for(i=0; i<nn*2; i+=2) {
     printf("data is %f +i%f\n", data[i], data[i+1]);
  }*/
  
  //FFT:
  int n = nn/2;
  int depth = 1;
  int j, m;
  float tempr, tempi, cosThetaTmp;
  
  while(depth < n) {
    cosTheta = 1;
    sinTheta = 0;
    phi = 2*pi/depth;
    sinPhi = sin(phi/2);
    alpha = 2 * sinPhi * sinPhi;
    beta = sin(phi);
    for(i = 0; i < depth*2; i+=2) {
      for(j = i; j <= n; j+=depth) {
      
        m = j + 2*depth;
	tempr = data[m]*cosTheta - data[m+1]*sinTheta;
        tempi = data[m+1]*cosTheta + data[m]*sinTheta;
        
	data[m+1] = data[j] - tempr;
	data[m+1] = data[j+1] - tempi;
	data[j] += tempr;
	data[j+1] += tempi;
	
      }
      
      /*
      using this method to determine cos and sin of theta speeds up the 
      algorithm by allowing them to be evaluated with only multiplies and adds
      there are only two sin evaluations necessary and they are in the outer
      loop.
      if you have a trig function following a linear sequence such that:
      
      Theta = Theta0 + n * sigma
      
      where n = 0,1,2,...
      
      then,
      
      cos(theta+sigma)=cos(theta) - [alpha*cos(theta)+beta*sin(theta)]
      sin(theta+sigma)=sin(theta) - [alpha*sin(theta)-beta(cos(theta)]
      
      where
      
      alpha = 2*sin^2(sigma/2)
      beta = sin(sigma)
      
      in our case, Theta0 = 0, and sigma = (2 * pi / N)*y where y varies between
      0 and N, where N is the number of inputs into the FFT.
      
      */
      cosThetaTmp = cosTheta;
      cosTheta = cosTheta - (alpha*cosTheta + beta*sinTheta);
      sinTheta = sinTheta - (alpha*sinTheta - beta*cosThetaTmp);
    }
    depth *= 2;
  
  }

  /*for(i=0; i<nn*2; i+=2) {
     printf("data is %f +i%f\n", data[i], data[i+1]);
  }*/
}
