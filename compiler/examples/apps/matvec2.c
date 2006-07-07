/*
 *
 @LICENSE@
 */


#include <stdlib.h>
#include <stdio.h>

/*
	Solve Y=A*X for a sparse system.
	Matrix A is split into a regular 7pt or 5pt part in ELPACK format
	nrows										rank of the system
	Y(nrows)								Result vector Y
	X(nrows)								Input vector X
	ELPACKed part:
		A_pack(n_pack*nrows)	packed coefficients - all coeffs then by rows
		ja_pack(n_pack*nrows)	packed indices into vector X
*/
#define NROWS 150000
#define N_PACK 7

extern int nrows;	      // number of rows in Y to process
extern int n_pack;	      // number of packed elements per row in A and X
extern const double X[NROWS];   // Compressed Sparse Row input matrix
extern const double A_pack[NROWS];  // non-packed transformation matrix
extern const int ja_pack[NROWS];    // column indexes into X_vec
extern double Y[NROWS];         // non-packed result matrix

/*void
split_matvec(const double * const A_pack,	// ELPACKed portion of sparse matrix A 
	     const int * const ja_pack,	// indices into vector X 
	     //const int nrows,	// N = rank of the system 
	     const double * const X,	// Input vector X 
	     double Y[])*/	// Result vector Y = A*X 
void run()
{
  n_pack = N_PACK;
  nrows = NROWS;
  int n;

  /* Process regular ELPACKed pack portion of the sparse Matrix-Vector multiply */
  /* Assume width of ELPACK part of A is 7 */
    for (n=0; n<nrows; n++) {
      Y[n] = A_pack[n*n_pack] * X[ja_pack[n*n_pack]] +
	A_pack[n*n_pack+1] * X[ja_pack[n*n_pack+1]] +
	A_pack[n*n_pack+2] * X[ja_pack[n*n_pack+2]] +
	A_pack[n*n_pack+3] * X[ja_pack[n*n_pack+3]] +
	A_pack[n*n_pack+4] * X[ja_pack[n*n_pack+4]] +
	A_pack[n*n_pack+5] * X[ja_pack[n*n_pack+5]] +
	A_pack[n*n_pack+6] * X[ja_pack[n*n_pack+6]];
    }

}

