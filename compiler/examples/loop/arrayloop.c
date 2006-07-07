/*
 *
 @LICENSE@
 */


//extern float a[5], b, c;
extern float a[5], b[5], c;

void run() {
  int i;
  
  for(i=0;i<c;i++)
  {
    //should not have an edge from 
    //astore to getelementptr:
    //a[i]= b + 5;
    //should see connection between 
    //aload and astore of array a:
    //a[i]= a[i] + 5;
    //should not see connection between 
    //aload and astore of array a:
    //a[i]= a[((int)(i+b))] + 5;
    //should see connection between 
    //aload and astore of array a:
    //(not yet implemented)
    a[i]= a[i-1] + 5;
  }
}
