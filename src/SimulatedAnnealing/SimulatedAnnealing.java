package SimulatedAnnealing;

public class SimulatedAnnealing {
	
	private int[][] matrizDistancias;
	private int[] cidadesValues;

	public SimulatedAnnealing( int[][] matrizDistancias, int[] cidadesValues) {
		this.matrizDistancias = matrizDistancias;
		this.cidadesValues = cidadesValues;
	}
	
	public int temperaturaInicial() {
		int max = 0;
		
		for(int i: cidadesValues) {
			for(int j: cidadesValues) {
				if(max <= delta(i, j)) {
					System.out.println(delta(i,j));
					max = delta(i, j);
				}
					
			}
		}
		
		return max;
	}
	
/*
	private double generateInitialTemperature() {
		return (Link.maxDistances() - Link.minDistances());
	}
*/
	//TODO
	private int delta(int i, int j) {
		return matrizDistancias[i][j] + matrizDistancias[i+1][j+1] 
				- matrizDistancias[i][i+1] - matrizDistancias[j][j+1];

	}
}
