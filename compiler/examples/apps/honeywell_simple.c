extern int inimg[4*8];
extern int outing[4*8];
extern int occ[32];

void run() {
int k;
int j;
int total=4;

  for (k=0; k<4; k++)
  {
    for (j=8/2; j<8; j++)
      outing[j]=
	(int)(32*(occ[inimg[j]])/total);
  }

}
