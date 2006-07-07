/*
 *
 @LICENSE@
 */



extern float a,b,c,d;

void run() { 
  if (a < b) {
    a = c;
  } else {
    a = d;
  }

  if ( b < a) {
    b = c;
  } else {
    b = d;
  }

  c = d;
}
