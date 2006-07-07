/*
 *
 @LICENSE@
 */


/*
  Without working to hard, it is very easy to creat casts between floats
  and doubles.  These casts, although implemented, are not cheap.  We need
  to be able to handle them when necessar and possible warn when they 
  should not be there.


*/


extern double b;
extern float a;

void run() {

  a = b + 1;
  b = a + 2;

}


    
