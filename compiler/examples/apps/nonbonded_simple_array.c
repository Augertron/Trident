#define numAtoms 7

extern float rx[numAtoms], ry[numAtoms], rz[numAtoms];
extern float force[numAtoms];

void run() {
  int i, j;
  float rmag;
  for(i=0; i<numAtoms; i++) {
    force[i] = 0;
  }
  for(i=0; i<numAtoms; i++) {
    for(j=0; j<numAtoms; j++) {
      if(i!=j) {
        rmag = sqrtf((rx[i]-rx[j])*(rx[i]-rx[j]) + 
	             (ry[i]-ry[j])*(ry[i]-ry[j]) +
	             (rz[i]-rz[j])*(rz[i]-rz[j]));
        force[i]+=(48/(rmag*rmag))*(1/(rmag*rmag*rmag*rmag*rmag*rmag *
	                               rmag*rmag*rmag*rmag*rmag*rmag) - 
				    1/(2*rmag*rmag*rmag*rmag*rmag*rmag));
      }
    }
  }

}
