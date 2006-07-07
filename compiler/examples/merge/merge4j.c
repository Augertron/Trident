/*
 *
 @LICENSE@
 */


extern int c,b,d;

void run() {
  int i = c;
  int a = c;
  i = c;
  if (a < 0) 
    if (i == 5)
      a = 1;

  i = b;

  if (a < 1) 
    a = 2;
  
  i = a;

  c = i;
}

