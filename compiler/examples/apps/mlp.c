/*
 *
 @LICENSE@
 */


/*

this is an example of a multilayer neural network.  It can be used to separate
non-linearly separable data or to approximate non-linear functions.

our example has 100 inputs, two layers of 8 hidden neurons, and 10 outputs

*/

extern float trainingData[500][1000];
extern float targetOuts[100][1000];

extern float inputData[500][1000];
extern float outputData[100][1000];
//usually these start out random:
extern float fstWeights[8][500];
extern float scndWeights[8][8];
extern float outWeights[100][8];
extern float learningRate;

//internal variables
extern float fstLayer[8];
extern float fstLayerSums[8];
extern float scndLayer[8];
extern float scndLayerSums[8];
extern float outputSums[100];
extern float outError[100];
extern float scndLayerError[8];

void run() {
  learningRate = 0.01;
  
  //train the network:
  int j;
  //foreach data point
  for(j=0;j<1000;j++) {
    int i;
    //foreach hidden node in the 1st layer
    for(i=0;i<8;i++) {
      int n;
      float out = 0;
      //foreach input:
      for(n=0;n<500;n++) {
	out += trainingData[n][j] * fstWeights[i][n];

      }
      
      //we need a nonlinear activation function or adding hidden layers does nothing
      fstLayerSums[i] = out;
      if(out > 1) out = 1;
      else if(out<-1) out = -1;
      
      fstLayer[i] = out;


    }

    //foreach hidden node in the second layer
    for(i=0;i<8;i++) {
      int n;
      float out = 0;
      //foreach hidden node in the first layer:
      for(n=0;n<8;n++) {
	out += fstLayer[n] * scndWeights[i][n];

      }
      
      //we need a nonlinear activation function or adding hidden layers does nothing
      scndLayerSums[i] = out;
      if(out > 1) out = 1;
      else if(out<-1) out = -1;
      
      scndLayer[i] = out;


    }

    //foreach output node
    for(i=0;i<100;i++) {
      int n;
      float out = 0;
      //foreach hidden node in the second layer:
      for(n=0;n<8;n++) {
	out += scndLayer[n] * outWeights[i][n];

      }
      
      //we need a nonlinear activation function or adding hidden layers does nothing
      outputSums[i] = out;
      if(out > 1) out = 1;
      else if(out<-1) out = -1;
      
      //scndLayer[i] = out;
      
      outError[i] = (targetOuts[i][j]-out) * (1-out*out);
      
      for(n=0;n<8;n++) {
	//update weight based on error:
	outWeights[i][n] = outWeights[i][n] + learningRate * scndLayer[n] * outError[i];
      }


    }

    //foreach hidden node in the second layer
    for(i=0;i<8;i++) {
      int n;
      float errorSum = 0;
      //foreach output:
      for(n=0;n<100;n++) {
         errorSum += outError[n]*outWeights[n][i];
      }
      scndLayerError[i] = (1-scndLayer[i]*scndLayer[i]) * errorSum;
      
      for(n=0;n<8;n++) {
	//update weight based on error:
	scndWeights[i][n] = scndWeights[i][n] + learningRate * fstLayer[n] * scndLayerError[i];
      }
    } 

    //foreach hidden node in the 1st layer
    for(i=0;i<8;i++) {
      int n;
      float errorSum = 0;
      //foreach hidden node in the 2nd layer:
      for(n=0;n<8;n++) {
         errorSum += scndLayerError[n]*scndWeights[n][i];
      }
      float fstLayerError = (1-fstLayer[i]*fstLayer[i]) * errorSum;
      
      for(n=0;n<500;n++) {
	//update weight based on error:
	fstWeights[i][n] = fstWeights[i][n] + learningRate * trainingData[n][j] * fstLayerError;
      }
    } 
  }


  
  //use the trained network to analyze data:
  //foreach data point
  for(j=0;j<1000;j++) {
    int i;
    //foreach hidden node in the 1st layer
    for(i=0;i<8;i++) {
      int n;
      float out = 0;
      //foreach input:
      for(n=0;n<500;n++) {
	out += inputData[n][j] * fstWeights[i][n];

      }
      
      //we need a nonlinear activation function or adding hidden layers does nothing
      fstLayerSums[i] = out;
      if(out > 1) out = 1;
      else if(out<-1) out = -1;
      
      fstLayer[i] = out;


    }

    //foreach hidden node in the second layer
    for(i=0;i<8;i++) {
      int n;
      float out = 0;
      //foreach hidden node in the first layer:
      for(n=0;n<8;n++) {
	out += fstLayer[n] * scndWeights[i][n];

      }
      
      //we need a nonlinear activation function or adding hidden layers does nothing
      scndLayerSums[i] = out;
      if(out > 1) out = 1;
      else if(out<-1) out = -1;
      
      scndLayer[i] = out;


    }

    //foreach output node
    for(i=0;i<100;i++) {
      int n;
      float out = 0;
      //foreach hidden node in the second layer:
      for(n=0;n<8;n++) {
	out += scndLayer[n] * outWeights[i][n];

      }
      
      //we need a nonlinear activation function or adding hidden layers does nothing
      outputSums[i] = out;
      if(out > 1) out = 1;
      else if(out<-1) out = -1;
      
      outputData[i][j] = out;


    }

  }


}
