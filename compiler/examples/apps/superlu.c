/*
 *
 @LICENSE@
 */


void dpanel_bmod_inner(
    int lptr,        /* Points to the row subscripts of a supernode */
    int nsupc,
    int * xlsub,
    int fsupc,
    int irow,
    int * lsub,
    double *dense_col,  /* dense[] for a column in the panel */
    double ukj,
    double *lusup,
    int luptr) {
  int i;
  for (i = lptr + nsupc; i < xlsub[fsupc+1]; i++) {
    irow = lsub[i];
    dense_col[irow] -= ukj * lusup[luptr];
    ++luptr;
  }
  return;
}

