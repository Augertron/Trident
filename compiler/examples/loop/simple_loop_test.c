/*
 *
 @LICENSE@
 */




extern int a, b, c, d;

void run() {
  //a = 5;
  b = 6;
  
  int i;
  for(i=0; i<10; i++) {
    d = i;
    a = c + a;
  }
  
  for(i = 0; i < 10; i++) {
    d = a + b;
    c = a + b;
  }
}



