
/* -------------------------------------------------------------- */
/* written by James Peery, LANL                                   */
/* -------------------------------------------------------------- */

/* Initial scalar implementation of a heavily simplified 
 * system simulation that performs advection in 1-D passes
 *
 * 	F(new) = (F(old)*v(old) + sum(F'dv)) / (v(new))
 *
 * Our simple particle system will consist of:
 *
 *	1) An array of old values for each cell (F_old[])
 *	2) An array of old volume for each cell (volume_old[])
 *	3) An array of volume changess from left cell (delta_volume_left[])
 *	3) An array of volume changess from right cell (delta_volume_right[])
 *	3) An array of advection  values from left cell (F_prime_left[])
 *	3) An array of advection values from right cell (F_prime_right[])
 *	3) An array of new volumes for each cell (volume_new[])
 *	3) An array of new values for each cell (F_new[])
 *
 */

//#include "advection.h"
#define CELLS 60
#define END_OF_TIME 100


extern float F_old[CELLS];
extern float old_volume[CELLS];
extern float delta_volume_left[CELLS];
extern float delta_volume_right[CELLS];
extern float F_prime_left[CELLS];
extern float F_prime_right[CELLS];	
extern float new_volume[CELLS];
extern float F_new[CELLS];

void run()
{
  int i;
  float delta_F;
  int dt = 1;
  int time;
  int last_cell = CELLS-1;

// initialize the cells

  for (i=0; i<CELLS; i++) {
      F_old[i] = 1.0;
      old_volume[i] = 5.0;
      delta_volume_left[i] = 0.1;
      delta_volume_right[i] = -0.1;
   }

  // For each step in time

  for (time=0; time<END_OF_TIME; time += dt) {

    // For each Cell use a simple donar cell approach for the quantity in the advection volume

    F_prime_right[last_cell] = 1.0; 
    for (i=1; i<CELLS; i++) {
      if(delta_volume_left[i] > 0.0) 
	F_prime_left[i] = F_old[i-1];
      else 
	F_prime_left[i] = F_old[i];
    }

    for (i=0; i<last_cell; i++) {
      if(delta_volume_right[i] > 0.0) 
	F_prime_right[i] = F_old[i+1];
      else 
	F_prime_right[i] = F_old[i];
    }

   // Set an inflow bc on the left and a reflective bc on the right

    if(delta_volume_left[0] > 0.0) 
	F_prime_left[0] = 5.0; 
    else 
	F_prime_left[0] = F_old[0];
    F_prime_right[last_cell] = F_old[last_cell]; 


   // update each cell based on the flux in and out of each cell

    for (i=0; i<CELLS; i++) {
      new_volume[i] = old_volume[i] + delta_volume_left[i] + delta_volume_right[i];
      delta_F = F_prime_left[i]*delta_volume_left[i] + F_prime_right[i]*delta_volume_right[i];
      if(new_volume[i]) {
        F_new[i] = (F_old[i]*old_volume[i] + delta_F) / new_volume[i];
      }
      else {
        F_new[i] = 0.0;
     }
      F_old[i] = F_new[i];
      old_volume[i] = new_volume[i];
    }
  }

 // printf("time = %i\n", time);
  //for (i=0; i<CELLS; i++) {
  //  printf("Cell = %i volume = %f value = %f\n", i, new_volume[i], F_new[i]);
  //}

  return (0);
}
