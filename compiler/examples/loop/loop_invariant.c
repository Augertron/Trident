/*
 *
 @LICENSE@
 */


extern int a, b, c, d, e;

void run() {
  a = 6;
  b = 5; 
  int i;
  for(i=0; i<10; i++) {
    d = i;
    a = c + a;
  }

  for(i=0; i < 10; i++) {
    e = 5;
    if (c < d) 
      d = a + b + 5;
    else
      c = a + 2 + b;
  }
  d++;
  e++;
} 

