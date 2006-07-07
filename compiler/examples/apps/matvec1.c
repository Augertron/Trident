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

void
split_matvec(const double * const A_pack,	/* ELPACKed portion of sparse matrix A */
	     const int * const ja_pack,	/* indices into vector X */
	     const int nrows,	/* N = rank of the system */
	     const double * const X,	/* Input vector X */
	     double Y[])	/* Result vector Y = A*X */
{
  int n;

  /* Process regular ELPACKed pack portion of the sparse Matrix-Vector multiply */
  /* Assume width of ELPACK part of A is 7 */
    for (n=0; n<nrows; n++) {
      Y[n] = A_pack[n*7] * X[ja_pack[n*7]] +
	A_pack[n*7+1] * X[ja_pack[n*7+1]] +
	A_pack[n*7+2] * X[ja_pack[n*7+2]] +
	A_pack[n*7+3] * X[ja_pack[n*7+3]] +
	A_pack[n*7+4] * X[ja_pack[n*7+4]] +
	A_pack[n*7+5] * X[ja_pack[n*7+5]] +
	A_pack[n*7+6] * X[ja_pack[n*7+6]];
    }

}

