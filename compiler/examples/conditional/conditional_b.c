/*
 *
 @LICENSE@
 */



extern int a, b, c, d;

void run() {
  int local_a = 0;

  if (a == 0) {
    local_a = 1;
    if (b == 0) {
      b = 2;
      if (c == 0) {
	c = 3;
	if (d == 0) {
	  d = 4;
	} else {
	  d = 5;
	}
      } else {
	c = 6;
      }
    } else {
      b = 7;
    }
  } else {
    a = 8;
  }

  if (local_a == 0) {
    a = 1; 
    if( b == 0 ) { 
      b = 2; 
      if( c == 0 ) { 
	c = 3; 
	if( d == 0 ) { 
	  d = 4; 
	} else { 
	  d = 5; 
	} 
      } else { 
	c = 6; 
      } 
    } else { 
      b = 7; 
    } 
  } else { 
    a = 8;
  } 
}  

  
