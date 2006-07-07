/*
 *
 @LICENSE@
 */


//extern int a, i[256];

void run(int *a, int *b, int *c) {
  *c = 5;
  if (*a < *b) 
    *a = *b;

  *c = 6;
}

