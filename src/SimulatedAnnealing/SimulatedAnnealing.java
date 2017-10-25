package SimulatedAnnealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {

	private int[][] matrizDistancias;
	private List<CidadesV> matrizCidades;
	private double temperaturaN;
	private Solution firstSolution;
	private Solution currentSolution;
	private Solution nextSolution;
	private Solution bestSolution;
	private int positivo;
	private int negativo;
	private double alfa;
	private int n_iter;



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
		Collections.shuffle(first);
		int cost = 0;
		for(int i = 0; i<first.size()-1; i++) {	

			cost += matrizDistancias[first.get(i)][(first.get(i) + 1)];

		}
		cost+= matrizDistancias[first.size()-1][first.get(0)];

		Collections.sort(first , new DistComparator<Integer>(matrizCidades));

		//System.out.println(matrizCidades.get(first.get(0)).sort + " " + matrizCidades.get(first.get(1)).sort);

		double temperaturaInicial =  Math.sqrt((matrizCidades.get(first.size()-1).getX() - matrizCidades.get(first.get(0)).getX())^2 
				+ (matrizCidades.get(first.size()-1).getY() - matrizCidades.get(first.get(0)).getY())^2);

		firstSolution = new Solution(first, cost , 0, temperaturaInicial);

		currentSolution = firstSolution;
		
		bestSolution = currentSolution;
		

		//TODO numero de iterações do problema 
		 // acho que é isto
	}
	
	public Solution algSimulatedAnnealing() {
		for(int n=1; n < n_iter; n++) {
			nextSolution = vizinho();
			int d = nextSolution.getCost() - currentSolution.getCost();
			if(d<0) {
				currentSolution = nextSolution;
				if(currentSolution.getCost() < bestSolution.getCost()) 
					bestSolution = currentSolution;
			}else {
				if(Math.exp(-d/temperaturaN) > 0.9)
					currentSolution = nextSolution;
					
			}
		}
			
			if(criterioParagem(n_iter)) {
				return bestSolution;
			}
			n_iter = fatorial(bestSolution.getSolution().size())/2;
			temperaturaN = decaimento(temperaturaN);
			return algSimulatedAnnealing();
			
	}
			
		


	//reduz e retorna a temperatura TODO
	private double decaimento(double temperatura) {
		return alfa * temperatura;
	}

	//se n_it dado for maior ou igual ao numero de iterações inicial do problema então podemos parar 
	//ou então, outra solução, é descer a temperatura até zero 
	private boolean criterioParagem(int n_it ) {
		if(n_it >= n_iter)
			return true;
		else
			return false;
	}

	private int fatorial(int num) {
		for (int i = 1; i <= num; i++) {
			num = num*i;
		}
		return num;
	}



	private Solution vizinho() {

		Random randomG = new Random();

		int p1 = randomG.nextInt(currentSolution.getSolution().size());
		int p2 = randomG.nextInt(currentSolution.getSolution().size());

		while (p1 == p2 || Math.abs(p1 - p2) <= 1) {
			p2 = randomG.nextInt(currentSolution.getSolution().size());
		}
	



		List<Integer> newSolution = new ArrayList<Integer>();
		int counter = 0;
		int v1 = 0; 
		int v2 = 0;
		for (int s = 0; s < currentSolution.getSolution().size(); s++) {
			if(p1<p2) {
				v2=p2;
				v1=p1;
			}else {
				v1=p2;
				v2=p1;
			}
			if (s <= v1 || s > v2) {
				newSolution.add(s, currentSolution.getSolution().get(s));

			} else {
				newSolution.add(s, currentSolution.getSolution().get(v2 - counter));

				counter++;
			}
		}
		int v2_2 = v2 + 1;
		if (v2_2 >= currentSolution.getSolution().size()) {
			v2_2 = 0;
		}
		List<Integer> aux = currentSolution.getSolution();

		int costD = matrizDistancias[aux.get(v1)][aux.get(v2)] + matrizDistancias[aux.get(v1 + 1)][aux.get(v2_2)]
				- matrizDistancias[aux.get(v1)][aux.get(v1 + 1)] - matrizDistancias[aux.get(v2)][aux.get(v2_2)];

		Solution vizinho = new Solution(newSolution, currentSolution.getCost() + costD, (positivo + negativo + 1), temperaturaN);
		return vizinho;

		
		//positivo
		//negativo
		//counter geral
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
