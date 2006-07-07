/*
 *
 @LICENSE@
 */


#define NROWS 10
extern const double X_vec[NROWS];
extern double Y_vec[NROWS];

void run() {
	int i = 0;
	for(i = 0;i < NROWS;i++) {
		Y_vec[i] = X_vec[i];
	}
}
