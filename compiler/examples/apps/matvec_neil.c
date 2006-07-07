
#define NROWS 10

/* WARNING: the descriptions were infered by Neil Steiner and may be incorrect */
extern const int nrows;             // number of rows in Y to process

extern const int n_pack;            // number of packed elements per row in A and X
extern const double X_vec[NROWS];   // Compressed Sparse Row input matrix
extern const double A_pack[NROWS];  // non-packed transformation matrix
extern const int ja_pack[NROWS];    // column indexes into X_vec

extern const int nrows_xtra;        // number of extra rows in Y to process
extern const int row_xtra[NROWS];   // indexes of extra rows in Y to process
extern const double A_xtra[NROWS];  // Compressed Sparse Row extra input matrix
extern const int ja_xtra[NROWS];    // column indexes into X_vec
extern const int ia_xtra[NROWS];    // number of packed elements per row in A_xtra and A_xtra

extern double Y_vec[NROWS];         // non-packed result matrix


void run()
{

	/* declare local pointers and variables */
	double* Y = Y_vec;
	const double* X = X_vec;
	const double* A = A_pack;
	const int* ja = ja_pack;
	int i;
	int n;

	if (n_pack==7) {
		for (n=0; n<nrows; n++) {
			*Y = A[0]*X[ja[0]] + A[1]*X[ja[1]] + A[2]*X[ja[2]]
			   + A[3]*X[ja[3]] + A[4]*X[ja[4]] + A[5]*X[ja[5]]
			   + A[6]*X[ja[6]];
			A+=7; ja+=7;
			Y++;
		}
	} else if (n_pack==5) {
		for (n=0; n<nrows; n++) {
			*Y = A[0]*X[ja[0]] + A[1]*X[ja[1]] + A[2]*X[ja[2]]
			   + A[3]*X[ja[3]] + A[4]*X[ja[4]];
			A+=5; ja+=5;
			Y++;
		}
	} else {
		for (n=0; n<nrows; n++) {
			*Y = 0;
			for (i=0; i<n_pack; i++) {
				*Y += (*A) * X[(*ja)];
				A++; ja++;
			}
			Y++;
		}
	}

	/* Process extra portion of sparse Matrix-Vector multiply */
	A = A_xtra;			/* Initialize temp pointers */
	ja = ja_xtra;
	for (n=0; n<nrows_xtra; n++) {
		Y = Y_vec + row_xtra[n];
		for (i=ia_xtra[n]; i<ia_xtra[n+1]; i++) {
			*Y += (*A) * X[(*ja)];
			A++; ja++;
		}
	}

}


