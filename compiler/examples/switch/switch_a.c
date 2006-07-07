/*
 *
 @LICENSE@
 */



extern int a, b;

void run() {
  if (a > b)
    a = b;

  switch (a) {
  case 1:
    switch (b) {
    case 1:
      b = a * 2;
      break;
    }
    break;
  case 0:
    b = a + 4;
    break;
  case 5:
    b = a * 5;
  case 2:
    if (b < a)
      b = a - 3;
    break;
  default:
    a = b;
  }
}


    
