extern int a;
extern char b;
extern unsigned int c;
extern unsigned char d;

void run() {

  if(a>b) {
    b=-5;
    a=b;
    c=b;
    d=b;
  }
  else if(a>c) {
    b=5;
    a=b;
    c=b;
    d=b;
  }
  else if(a>d) {
    a=260;
    b=a;
    c=a;
    d=a;
  }
  else if(b>c) {
    a=-260;
    b=a;
    c=a;
    d=a;
  }
  else if(b>d) {
    a=-260;
    b=a;
    c=a;
    d=a;
  }
  else if(c>d) {
    a=-260;
    b=a;
    c=a;
    d=a;
  }

}
