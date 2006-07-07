/*
 *
 @LICENSE@
 */


extern int i;

void run(int a,int b, int c,int d) {
  a = 2;
  i = c;
  if (a < 0) 
    if (i == 5)
      a = 1;

  i = b;

  if (a < 1) 
    a = 2;
  
  i = a;

}

