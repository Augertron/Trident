#define N 200000
#define P 16

extern short int x[N], pat[P];
extern char y[N];

void run() {

  int i, j;
  
  for(i=0; i<N-P+1; i++) {
    y[i]=1;
    for (j=0; j<P; j++)
      if(pat[j] != x[i+j]) y[i] = 0;
  }

}
