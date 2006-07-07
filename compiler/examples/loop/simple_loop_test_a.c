/*
 *
 @LICENSE@
 */


extern int a, b, c, d, e;

void run() {
  //a = 5;
  b = 6;
  
  for(e=0; e < 10; e++) {
    d = e;
    a = c + a;
  }
  
  for(e = 0; e < 10; e++) {
    d = a + b;
    c = a + b;
  }
}



