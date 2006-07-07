/*
 *
 @LICENSE@
 */



extern float a, b, c, d;

void run() {
  a = 1.0;
  if( b == 0.0 ) {
    b = 2.0;
    if( c == 0.0 ) {
      c = 3.0;
      if( d == 0.0 ) {
	d = 4.0;
      } else {
	d = 5.0;
      }
    } else {
      c = 6.0;
    }
  } else {
    b = 7.0;
  }
}
