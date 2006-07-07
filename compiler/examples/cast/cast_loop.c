/*
 *
 @LICENSE@
 */


/*
  This tests loop casts.  For an unknown reason, (perhaps C standard ?)
LLVM likes to cast loop index variables into uints.  If you actually 
use them, then it changes it back into an int.  This is a simple example
to test that.

*/


extern float a;

void run() {
  
  int i;
  for(i=0; i<1000; i++) {
    a = a + i;
  }

}


    
