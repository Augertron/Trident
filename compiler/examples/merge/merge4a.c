/*
 *
 @LICENSE@
 */


extern int a, i[256];

void run() {
  i[0] = 1;
  
  if (a < 0) 
    if (i[5] == 5)
      a = 1;

  // oops ? :)
  i[257] = 3;
}

