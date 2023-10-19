- jrsuleim_2125 
- HW1 
- Sep 04, 2022

 1	public double calculateEnergy(double[][] coords, double[] eps, int numAtoms) {
 2		double energySum = 0.0;
 3             	double r0 = 1.2;
 4             	for (int i = 0; i < numAtoms-1; i++) {
 5                    for (int  j = 0; j < numAtoms; j++) {
 6                       double distance = Math.sqrt( Math.pow(coords[i][0] - coords[j][0], 2 ) + 
 7                                                    Math.pow(coords[i][1] - coords[j][1], 2 ) + 
 8                                                    Math.pow(coords[i][2] - coords[j][2], 2 )    );
 9                       double term2 = Math.pow( (r0/distance), 12 ) ;
10                       double term1 = Math.pow( (r0/distance),  6) ;
11                       double epsilon = Math.sqrt( eps[i] * eps[i] + eps[j] * eps[j]);
12                       energySum = energySum + (4.0 * epsilon * (term1 - 2.0 * term2) );
13                  }
14             }
15            return energySum;
16     }

Answer:

- given: 
  . Math.pow cost 8+3*p
  . Math.sqrt cost 12

- We start at the inter loop which is line 6
- 1 + 8 + 3 * 2 = 15
- Lines 6-8: are 15 + 15 + 15 + 2 = 47
- Add Math.sqrt 12 + 47 + 1 = 60

- Line 9 1 + 8 + 3 * 12 +1(the assignment) = 1 + 8 + 36 + 1 = 46
- Line 10 1+ 8 + 3 * 6 + 1(the assignment) = 1 + 8 + 18 + 1 = 28
- Line 11 2 + 1 + 12 + 1(the assignment) = 16 
- Line 12 3 + 1 + 1 + 1(the assignment) = 6
- 60 + 46 + 28 + 16 +6 = 156 ticks inside the loop

- The loop happens N times
- Line 5 1(the initialization) + 156(N) + (N + 1)(the condition) + N = 156N + 2 cost of inner 

- Outer Loop: 
- Line 4 158N + 2 + 2N(the condition) + 1(the initialization) + N - 1(the incrementation) 
  . 158N^2 + 2N - 158N - 2 +2N + 1 + N - 1
  . 158N^2 - 153N - 2 + 1(from Line 2) + 1(from Line 3) + 1(from Line 15) = 158N^2 - 153 + 1 = 0(N^2)


 




