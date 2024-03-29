package SimulatedAnnealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {
	
	private int[][] matrizDistancias;
	private List<CidadesV> matrizCidades;
	private double temperaturaInicial;
	private double temperatura;
	private Solution firstSolution;
	private Solution currentSolution;
	private int positivo;
	private int negativo;
	
	
	
	public SimulatedAnnealing() {
		
		matrizCidades = new ArrayList<CidadesV>(21);
	}
	

	public void addCidades(int c, int x, int y) {
		
		matrizCidades.add(new CidadesV(c,x,y));
	}
	

	public void setMatrizDistancias(int[][] matrizDistancias2) {
		
		this.matrizDistancias = matrizDistancias2;	
	}
	
	
	//temperatur ainicial = raiz quadrada distancia ultimo para o  primeiro formaula distancia
	
	public void setFirstSolution(List<Integer> first) {
		int cost = 0;
		for(int i = 0; i<first.size()-1; i++) {	
			
			cost += matrizDistancias[first.get(i)][(first.get(i) + 1)];
			
		}
		cost+= matrizDistancias[first.size()-1][first.get(0)];
		
		Collections.sort(first , new DistComparator<Integer>(matrizCidades));
		
		//System.out.println(matrizCidades.get(first.get(0)).sort + " " + matrizCidades.get(first.get(1)).sort);
		
		temperaturaInicial =  Math.sqrt((matrizCidades.get(first.size()-1).getX() - matrizCidades.get(first.get(0)).getX())^2 
				+ (matrizCidades.get(first.size()-1).getY() - matrizCidades.get(first.get(0)).getY())^2);
		
		firstSolution = new Solution(first, cost , 0, temperaturaInicial);
		
		currentSolution = firstSolution;
	}
	/*
	public void runAlgorithm(List<CidadesV> list) {
		for(n=1; n < list.size(); n++)
			
	}*/



	
	
	
	private Solution vizinho() {
		
		Random randomG = new Random();
	
		int p1 = randomG.nextInt(currentSolution.getSolution().size());
		int p2 = randomG.nextInt(currentSolution.getSolution().size());
		int first;
		int second;
		
		/*
		 * mudar
		 */
		while (p1 == p2 || Math.abs(p1 - p2) <= 1) {
			p2 = randomG.nextInt(currentSolution.getSolution().size());
		}
		if (p1 > p2) {
			second = p1;
			first = p2;
		} else {
			second = p2;
			first = p1;
		}
		
		

		List<Integer> newSolution = new ArrayList<Integer>();
		int counter = 0;
		for (int s = 0; s < currentSolution.getSolution().size(); s++) {
			if (s <= first || s > second) {
				newSolution.add(s, currentSolution.getSolution().get(s));

			} else {
				newSolution.add(s, currentSolution.getSolution().get(second - counter));

				counter++;
			}
		}
		int newSecond = second + 1;
		if (newSecond >= currentSolution.getSolution().size()) {
			newSecond = 0;
		}
		List<Integer> aux = currentSolution.getSolution();

		int dif = matrizDistancias[aux.get(first)][aux.get(second)] + matrizDistancias[aux.get(first + 1)][aux.get(newSecond)]
				- matrizDistancias[aux.get(first)][aux.get(first + 1)] - matrizDistancias[aux.get(second)][aux.get(newSecond)];

		Solution vizinho = new Solution(newSolution, currentSolution.getCost() + dif, (positivo + negativo + 1), temperatura);
		//nao vai ser a temperatura inicial, mas a nova temperatura que vamos ter de fazer
		return vizinho;
	
	}

	
	
	/*
	List<Integer> input;
	public void printCidades() {
		Collections.sort(firstSolution , new DistComparator<Integer>(matrizCidades));
		for(int i = 0; i<21;i++) {
			
			System.out.println("id: "+ i + " x: " + matrizCidades.get(i).getX() + " y: " + matrizCidades.get(i).getY());
			
		}
		
	}*/
	
}
