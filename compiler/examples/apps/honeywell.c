extern int inimg[240*480];
extern int outing[240*480];
extern int occ[256];

void run() {
int k;
int j;
int total;

  for (k=0; k<240; k++)
  {
    for (j=480/2; j<480; j++)
      outing[j]=
	(int)(256*(occ[inimg[j]])/total);
  }

}
