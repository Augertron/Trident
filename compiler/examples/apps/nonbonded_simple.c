#define numAtoms 7

extern float rx0, rx1, rx2;
extern float ry0, ry1, ry2;
extern float rz0, rz1, rz2;
extern float force0, force1, force2;

void run() {
  float rmag;

force0 = 0;
force1 = 0;
force2 = 0;


  rmag = sqrtf((rx0-rx1)*(rx0-rx1) + 
	       (ry0-ry1)*(ry0-ry1) +
	       (rz0-rz1)*(rz0-rz1)); 

  force0+=(48/(rmag*rmag))*(1/(rmag*rmag*rmag*rmag*rmag*rmag *
			       rmag*rmag*rmag*rmag*rmag*rmag) - 
			    1/(2*rmag*rmag*rmag*rmag*rmag*rmag));

  rmag = sqrtf((rx0-rx2)*(rx0-rx2) + 
	       (ry0-ry2)*(ry0-ry2) +
	       (rz0-rz2)*(rz0-rz2)); 

  force0+=(48/(rmag*rmag))*(1/(rmag*rmag*rmag*rmag*rmag*rmag *
			       rmag*rmag*rmag*rmag*rmag*rmag) - 
			    1/(2*rmag*rmag*rmag*rmag*rmag*rmag));

  rmag = sqrtf((rx1-rx0)*(rx1-rx0) + 
	       (ry1-ry0)*(ry1-ry0) +
	       (rz1-rz0)*(rz1-rz0)); 

  force1+=(48/(rmag*rmag))*(1/(rmag*rmag*rmag*rmag*rmag*rmag *
			       rmag*rmag*rmag*rmag*rmag*rmag) - 
			    1/(2*rmag*rmag*rmag*rmag*rmag*rmag));

  rmag = sqrtf((rx1-rx2)*(rx1-rx2) + 
	       (ry1-ry2)*(ry1-ry2) +
	       (rz1-rz2)*(rz1-rz2)); 

  force1+=(48/(rmag*rmag))*(1/(rmag*rmag*rmag*rmag*rmag*rmag *
			       rmag*rmag*rmag*rmag*rmag*rmag) - 
			    1/(2*rmag*rmag*rmag*rmag*rmag*rmag));

  rmag = sqrtf((rx2-rx0)*(rx2-rx0) + 
	       (ry2-ry0)*(ry2-ry0) +
	       (rz2-rz0)*(rz2-rz0)); 

  force2+=(48/(rmag*rmag))*(1/(rmag*rmag*rmag*rmag*rmag*rmag *
			       rmag*rmag*rmag*rmag*rmag*rmag) - 
			    1/(2*rmag*rmag*rmag*rmag*rmag*rmag));

  rmag = sqrtf((rx2-rx1)*(rx2-rx1) + 
	       (ry2-ry1)*(ry2-ry1) +
	       (rz2-rz1)*(rz2-rz1)); 

  force2+=(48/(rmag*rmag))*(1/(rmag*rmag*rmag*rmag*rmag*rmag *
			       rmag*rmag*rmag*rmag*rmag*rmag) - 
			    1/(2*rmag*rmag*rmag*rmag*rmag*rmag));

}
