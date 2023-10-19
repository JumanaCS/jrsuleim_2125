- jrsuleim_2125 
- Bonus 
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

The inner loop, that cycles over values of j, in reality should look like this:

for (int j= i+1; j < numAtoms; j++)


Bonus Answer:
If the inner loop on line 5 is changed to for (int j = i + 1; j < numAtoms; j++), then the time complexity of the algorithm will be reduced to O(N^2/2). This is because the loop will only iterate over the values of j that are greater than i, thus reducing the number of iterations of the inner loop by half.



