/*
 *
 @LICENSE@
 */


extern int l, li, le;
extern float delxl, delyl, rhse, rhsl, det, absdt, dtinv, xi, yi, x1l, y1l, x2l, y2l, sqlnl, ssq, ex, ey;

int run() {
  l = 1;
  li = 0;
  le = 2;;
  delxl = -0.04273843765258789062500000000000;
  delyl = -0.1641389876604080200195312500000;
  rhse = -0.09348050504922866821289062500000;
  rhsl = 0.16900083422660827636718750000000;
  det = 0;
  absdt = 0.16741591691970825195312500000000;
  dtinv = 5.97314786911010742187500000000000;
  xi = 0;
  yi = 0;
  x1l = 0.98561590909957885742187500000000;
  y1l = -0.16900081932544708251953125000000;
  x2l = 0.94287747144699096679687500000000;
  y2l = -0.33313980698585510253906250000000;
  sqlnl = 0.02876818180084228515625000000000;
  ssq = 0.14609050750732421875000000000000;
  ex = -0.99561953544616699218750000000000;
  ey = 0.09349746257066726684570312500000;
  const float epsdet0 = 0.00000000010000000133514319600181; 
  if(l != le)
  {
    /*
       delxl   = readonly->delx   [l];
       delyl   = readonly->dely   [l];
       rhsl    = readonly->rhs    [l];
     */
    /*c*/
    /*c  compute intersection points*/
    /*c*/
    det  = ex*delyl - ey*delxl;
    absdt= fabs(det);
    if(absdt <= epsdet0)
      det=  epsdet0;
    dtinv= 1.0/det;
    xi     = dtinv * (delxl*rhse - ex*rhsl);
    yi     = dtinv * (delyl*rhse - ey*rhsl);
    /*c*/
    /*c  test for intersection between surface endpoints*/
    /*c*/
    /*
       x1l     = readonly->x1     [l];
       y1l     = readonly->y1     [l];
       x2l     = readonly->x2     [l];
       y2l     = readonly->y2     [l];
       sqlnl   = readonly->sqln   [l];
     */
    ssq  = (xi - x1l)*(xi - x1l) + (xi - x2l)*(xi - x2l)
      + (yi - y1l)*(yi - y1l) + (yi - y2l)*(yi - y2l);
    if(ssq <= sqlnl)
    {
      li= l;
      /*      break; */
    }
    return(li);
  }
}
