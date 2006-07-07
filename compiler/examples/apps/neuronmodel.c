/*
 *
 @LICENSE@
 */


/*

This is an implementation of a Hodgkin-Huxley Model of a neuron.  This model 
simulates the current over the cell membrane over time as a result of leakage 
current, sodium current and potassium current through their respective channels.  
Neuronal firing is controlled by the behaviour of the potassium and sodium 
channels. These channels open and close to allow sodium or potassium ions to 
flow through, and change the voltage inside the neuron.  This flow creates the 
pulse and brings the neuron back to its resting potential after firing.  
This model is based on this equation:

im = gL(V-EL) + gK(n^4)(V-EK)+gNa(m^3)h(V-ENa)

where im is the current flowing through the cell membrane, V is the voltage 
across the membrane, which varies with time, gL is the conductance for the 
leakage current (which is a non-voltage dependant current), EL is a neutral 
potential at which no leakage current will flow, gK is the conductance for
potassium ions, EK, the neutral potential where no potassium ions will flow, gNA
is the sodium conductance, and ENa the potential where no sodium will flow.  
All of these are constants except V and im.  But n, m, and h are voltage 
dependant, and they represent the probabilities of the sodium or potassium
channels being open.  The potassium channel has one gate on it, whose probability
of being open is n.  The sodium channel has one main gate which must first open,
whose probability of being open is m, and a secondary gate which can close 
and deactivate the channel and the probability that deactivation has not occured
is h.  The equations for n, m, and h are below.

first, they all vary based on the equations:

tauY(V) dy/dt = y00(V) - y

tauY(V) = 1/(alphaY(V) + betaY(V))

Y00(V) = alphaY(V)/(alphaY(V) + betaY(V))

where Y is either n, m or h.

for n,

alphan(V) = 0.01(V+55)/(1-expTbl(-0.1(V+55)))
betan(V) = 0.125expTbl(-0.0125(V+65))

for m

alpham(V) = 0.1(V+40)/(1-expTbl(-0.1(V+40)))
betam(V) = 4expTbl(-0.0556(V+65))

for h,

alphah(V) = 0.07expTbl(-0.05(V+65))
betah(V) = 1/(1+expTbl(-0.1(V+35)))

However, since we cannot use the expTbl function, I will instead assume there 
exists a lookup table for each alhpa and beta depending on V.

New voltages can be calculated for each time step using:

v(t + deltaT) = v00 + (v(t)-v00)expTbl(-deltaT/tauV)
where 
v00 = (sum over i(giEi) + Ie/A) / (sum over i of gi)
tauV = cm/sum over i of gi

(that is the sum of all imput impedances)

n, m, and h can be calculated similarly with 

z(t+deltaT) = z00 + (z(t)-z00)expTbl(-deltaT/tauT)

we will alternate updating voltage and n, m, and h over time.

*/

//lookup tables
extern float alphan[200], betan[200];
extern float alpham[200], betam[200];
extern float alphah[200], betah[200];
extern float expTbl[10000];

//making this external so that it can be viewed over time
extern float v;

void run() {

  float gL = 0.003; //mS/mm^2 == leakage conductance
  float EL = -54.387; //mmV == leakage equilibrium potential
  float gK = 0.36; //mS/mm^2 == potassium conductance
  float EK = -77; //mV == potassium equilibrium potential
  float gNa = 1.2; //mS/mm^2 == sodium conductance
  float ENa = 50; //mV == sodium equilibrium potential

  float cm = 10; //nF/mm^2 == capacitance per cell surface area
  
  float v00, tauv;
  v = -65; //mV a typical resting voltage for a neuron
  
  float m00, taum, m = 0.1;
  float h00, tauh, h = 0.6;
  float n00, taun, n = 0.35;
  
  float Ie = 5; //injection current
  float A = 0.1; //mm^2 total surface area of cell 
  
  int i;
  for(i = 0; i<1000; i++) {
    if(i%2==0) { //alternate updating v and m, h, and n
    
      taun = 1/(alphan[(int)v] + betan[(int)v]);
      n00 = alphan[(int)v]/(alphan[(int)v] + betan[(int)v]);
      
      n = n00 + (n - n00) * expTbl[(int)(-1.0/taun)*1000]; //I'm also assuming there's a lookup table for the expTbl function
      
      taum = 1/(alpham[(int)v] + betam[(int)v]);
      m00 = alpham[(int)v]/(alpham[(int)v] + betam[(int)v]);
      
      m = m00 + (m - m00) * expTbl[(int)(-1.0/taum)*1000]; 
      
      tauh = 1/(alphah[(int)v] + betah[(int)v]);
      h00 = alphah[(int)v]/(alphah[(int)v] + betah[(int)v]);
      
      h = h00 + (h - h00) * expTbl[(int)(-1.0/tauh)*1000]; 
      
    
    } else {
    
      v00 = ((gL * EL + gK * n*n*n*n * EK + gNa * m*m*m * h * ENa) + Ie/A) /
             (gL + gK * n*n*n*n + gNa * m*m*m * h);
    
      tauv = cm / (gL + gK * n*n*n*n + gNa * m*m*m * h);
      
      v = v00 + (v-v00) * expTbl[(int)(-1.0/tauv)*1000];
    
    }
  }
  

}
