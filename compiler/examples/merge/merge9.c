/*
 *
 @LICENSE@
 */


extern float a,b,i;

void run() {
  i = 1.0;
  
  if (a < 0.0) {
    i = 2.0;
    if (b < 1.0) 
      i = 3.0;
    else
      i = 4.0;
  } else {
    i = 5.0;
    if (b > 1.0) 
      i = 6.0;
    else
      i = 7.0;
  }
  i = 8.0;

}

