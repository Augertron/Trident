#define N 128

extern char image1[N][N], image2[N][N], 
            skeleton1[N][N], skeleton2[N][N],
	    im_in[N][N], im_out[N][N], skel_in[N][N], skel_out[N][N],
	    array[N][N], array2[N][N];

static inline char Fmin(char array2[N][N], int x, int y) __attribute__((always_inline));
static inline char Fmin(char array[N][N], int  x, int y) {
  char min = 126;
  int i, j;
  for(i=0; i<x; i++) {
    for(j=0; j<y; j++) {
      if(!(((i==0)&&(j==0))||
          ((i==0)&&(j==y))||
	  ((i==x)&&(j==0))||
	  ((i==x)&&(j==y)))) {
        if(array[i][j]<min)
	  min=array[i][j];
      }
    }
  }
  return min;
}

static inline char Fmax(char array2[N][N], int x, int y) __attribute__((always_inline));
static inline char Fmax(char array2[N][N], int x, int y) {
  char max = -126;
  int i, j;
  for(i=0; i<x; i++) {
    for(j=0; j<y; j++) {
      if(!(((i==0)&&(j==0))||
          ((i==0)&&(j==y))||
	  ((i==x)&&(j==0))||
	  ((i==x)&&(j==y)))) {
        if(array[i][j]>max)
	  max=array2[i][j];
      }
    }
  }
  return max;
}


static inline int skeleton_iter(char im_in[N][N], char im_out[N][N],
                  char skel_in[N][N], char skel_out[N][N]) __attribute__((always_inline));

static inline int skeleton_iter(char im_in[N][N], char im_out[N][N],
                  char skel_in[N][N], char skel_out[N][N]){
  int pixel_on = 0;
  int x, y;
  for (x=0; x<N+2; x++) {
    for(y=0; y<N+2; y++) {
      if(x<N && y<N)
        im_out[x][y] = ((int)y<2 || (int)y>=N-2 || x<2 || x>=N-2)
	              ? (char)0 : (char)Fmin(im_in,x,y);
      if(x>=2 && y>=2) {
        int filter = ((int)y<4 || (int)y>=N || x<4 || x>=N)
	             ? 0 : Fmax(im_out,x-2,y-2);
	int pixel = (int)im_in[x-2][y-2] - filter;
	skel_out[x-2][y-2] = (int)skel_in[x-2][y-2] | pixel;
	if (pixel==255) pixel_on = 1;
      }
    }
  }
  return pixel_on;
}


void run() {
  int pixel_on = 1;
  int count = 0;
  while(pixel_on) {
    count++;
    if(count % 2 ==1)
      pixel_on = skeleton_iter(image1, image2, skeleton1, skeleton2);
    else
      pixel_on = skeleton_iter(image2, image1, skeleton2, skeleton1);
  }
}
