/*
 *
 @LICENSE@
 */


#define REAL float
#define INTEGER int
#define B_N      0x0001   /* obstacle cells adjacent to fluid cells */
#define B_S      0x0002   /* in the respective direction            */
#define B_W      0x0004   
#define B_O      0x0008 
#define B_NW     0x0005    
#define B_SW     0x0006      
#define B_NO     0x0009    
#define B_SO     0x000a
#define C_F      0x0010   /* fluid cell */

#define C_E     0x1000   /* empty celle                 */
#define C_N     0x0800   /* free surface cells          */
#define C_S     0x0400   /* adjacent to empty cells     */
#define C_W     0x0200   /* in the respective direction */
#define C_O     0x0100 
#define C_WO    0x0300
#define C_NS    0x0c00
#define C_SW    0x0600
#define C_NW    0x0a00
#define C_NO    0x0900
#define C_SO    0x0500
#define C_SWO   0x0700 
#define C_NSW   0x0e00
#define C_NWO   0x0b00
#define C_NSO   0x0d00
#define C_NSWO  0x0f00


/* Macros for POISSON, denoting whether there is an obstacle cell */
/* adjacent to some direction                                     */
#define eps_E !(FLAG[i+1][j] < C_F)
#define eps_W !(FLAG[i-1][j] < C_F)
#define eps_N !(FLAG[i][j+1] < C_F)
#define eps_S !(FLAG[i][j-1] < C_F)


#define DIM_A 200
#define DIM_B 200

extern REAL    P[DIM_A][DIM_B];
extern REAL    RHS[DIM_A][DIM_B];
extern INTEGER FLAG[DIM_A][DIM_B];
extern int     imax, jmax, itermax, ifull, p_bound;
extern REAL    delx, dely, eps, omg;
extern REAL    res[1];
extern int     retval;

/*-------------------------------------------------------------*/
/* SOR iteration for the poisson equation for the pressure     */
/*-------------------------------------------------------------*/
/*
int POISSON(REAL **P,REAL **RHS,int **FLAG,
            int imax,int jmax,REAL delx,REAL dely,
            REAL eps,int itermax,REAL omg,REAL *res,int ifull,int p_bound)
*/
void run()
{
  int i,j,iter;
  REAL rdx2,rdy2;
  REAL add,beta_2,beta_mod;
  REAL p0 = 0.0;

  rdx2 = 1./delx/delx;
  rdy2 = 1./dely/dely;
  beta_2 = -omg/(2.0*(rdx2+rdy2));

  for (i=1;i<=imax;i++)
    for (j=1;j<=jmax;j++)
      if (FLAG[i][j] & C_F)
	p0 += P[i][j]*P[i][j];

  p0 = sqrtf(p0/ifull);
  if (p0 < 0.0001)
    p0 = 1.0;

  /* SOR-iteration */
  /*---------------*/
  for (iter=1;iter<=itermax;iter++)
    {
      if (p_bound == 1)
	/* modify the equation at the boundary */
	/*-------------------------------------*/
	{
	  /* relaxation for fluid cells */
	  /*----------------------------*/
	  for (i=1;i<=imax;i+=1)
	    for (j=1;j<=jmax;j+=1)
	      /* five point star for interior fluid cells */
	      if (FLAG[i][j] == 0x001f)
                P[i][j] = (1.-omg)*P[i][j] -
		  beta_2*((P[i+1][j]+P[i-1][j])*rdx2 +
			  (P[i][j+1]+P[i][j-1])*rdy2 - RHS[i][j]);
	  /* modified star near boundary */
	      else if ((FLAG[i][j] & C_F) && (FLAG[i][j] < 0x0100)){
                beta_mod = -omg/((eps_E+eps_W)*rdx2+(eps_N+eps_S)*rdy2);
                P[i][j] = (1.-omg)*P[i][j] -
		  beta_mod*( (eps_E*P[i+1][j]+eps_W*P[i-1][j])*rdx2 +
			     (eps_N*P[i][j+1]+eps_S*P[i][j-1])*rdy2 -
			     RHS[i][j]);
              }
	  /* computation of residual */
	  /*-------------------------*/
	  *res = 0.0;
	  for (i=1;i<=imax;i++)
	    for (j=1;j<=jmax;j++)
	      if ((FLAG[i][j] & C_F) && (FLAG[i][j] < 0x0100))
		/* only fluid cells */
		/*------------------*/
                {
		  add =  (eps_E*(P[i+1][j]-P[i][j]) -
			  eps_W*(P[i][j]-P[i-1][j])) * rdx2  +
		    (eps_N*(P[i][j+1]-P[i][j]) -
		     eps_S*(P[i][j]-P[i][j-1])) * rdy2  -  RHS[i][j];
		  *res += add*add;
                }

	  *res = sqrtf((*res)/ifull)/p0;
	  /* convergence? */
	  /*--------------*/

	  if (*res<eps)
	    retval = iter;
	}

      else if (p_bound == 2)
	{
	  /* copy values at external boundary */
	  /*----------------------------------*/
	  for (i=1;i<=imax;i+=1)
	    {
	      P[i][0]      = P[i][1];
	      P[i][jmax+1] = P[i][jmax];
	    }
	  for (j=1;j<=jmax;j+=1)
	    {
	      P[0][j]      = P[1][j];
	      P[imax+1][j] = P[imax][j];
	    }
	  /* and at interior boundary cells */
	  /*--------------------------------*/
	  for (i=1;i<=imax;i+=1)
	    for (j=1;j<=jmax;j+=1)
	      if (FLAG[i][j] >=B_N && FLAG[i][j] <=B_SO)
		switch (FLAG[i][j])
		  {
		  case B_N:{  P[i][j] = P[i][j+1];                 break;}
		  case B_O:{  P[i][j] = P[i+1][j];                 break;}
		  case B_S:{  P[i][j] = P[i][j-1];                 break;}
		  case B_W:{  P[i][j] = P[i-1][j];                 break;}
		  case B_NO:{ P[i][j] = 0.5*(P[i][j+1]+P[i+1][j]); break;}
		  case B_SO:{ P[i][j] = 0.5*(P[i][j-1]+P[i+1][j]); break;}
		  case B_SW:{ P[i][j] = 0.5*(P[i][j-1]+P[i-1][j]); break;}
		  case B_NW:{ P[i][j] = 0.5*(P[i][j+1]+P[i-1][j]); break;}
		  default:                                         break;
		  }

	  /* relaxation for fluid cells */
	  /*----------------------------*/
	  for (i=1;i<=imax;i+=1)
	    for (j=1;j<=jmax;j+=1)
	      if ((FLAG[i][j] & C_F) && (FLAG[i][j] < 0x0100))
		P[i][j] = (1.-omg)*P[i][j] -
		  beta_2*((P[i+1][j]+P[i-1][j])*rdx2 +
			  (P[i][j+1]+P[i][j-1])*rdy2 - RHS[i][j]);

	  /* computation of residual */
	  /*-------------------------*/
	  *res = 0.0;
	  for (i=1;i<=imax;i++)
	    for (j=1;j<=jmax;j++)
	      if ((FLAG[i][j] & C_F) && (FLAG[i][j] < 0x0100))
		/* only fluid cells */
		/*------------------*/
		{
		  add =  (P[i+1][j]-2*P[i][j]+P[i-1][j])*rdx2+
		    (P[i][j+1]-2*P[i][j]+P[i][j-1])*rdy2-RHS[i][j];
		  *res += add*add;
		}

	  *res = sqrtf((*res)/ifull)/p0;
	  /* convergence? */
	  /*--------------*/

	  if (*res<eps)
	    retval = iter;
	}
    }
  retval = iter;
}
