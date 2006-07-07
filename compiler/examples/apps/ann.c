/*
 *
 @LICENSE@
 */


/*

modelling a neuron was too complex, so here's a simple artificial neural network.
This is just a one layer, linear network

There are 500 inputs and 100 outputs and 1000 training data points and 1000 
data points for final analysis.

*/

extern float trainingData[500][1000];
extern float targetOuts[100][1000];

extern float inputData[500][1000];
extern float outputData[100][1000];
//usually these start out random:
extern float weights[100][500];
extern float learningRate;

void run() {
  learningRate = 0.01;
  //train the network:
  int j;
  for(j=0;j<1000;j++) {
    int i;
    for(i=0;i<100;i++) {
      int n;
      float out = 0;
      for(n=0;n<500;n++) {
	out += trainingData[n][j] * weights[i][n];

      }

      for(n=0;n<500;n++) {
	//update weight based on error:
	weights[i][n] = weights[i][n] + learningRate * trainingData[n][j]*
                	(targetOuts[i][j]-out);
      }

    }
  }
  
  //use the trained network to analyze data:
  for(j=0;j<1000;j++) {
    int i;
    for(i=0;i<100;i++) {
      int n;
      float out = 0;
      for(n=0;n<500;n++) {
	out += inputData[n][j] * weights[i][n];

      }
      outputData[i][j] = out;

    }
  }


}
