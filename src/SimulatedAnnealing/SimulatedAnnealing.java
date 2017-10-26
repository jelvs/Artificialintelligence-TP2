package SimulatedAnnealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {

	private int[][] matrizDistancias;
	private List<CidadesV> matrizCidades;
	private double temperaturaN;
	private double temperaturaInicial;
	private double temperaturaBest;
	private int pCounter;
	private int nCounter;
	private int gCounter;
	private double alfa;
	private int n_iter;
	private long time;
	private long startTime;
	
	private Solution firstSolution;
	private Solution currentSolution;
	private Solution nextSolution;
	private Solution bestSolution;
	



	public SimulatedAnnealing() {

		matrizCidades = new ArrayList<CidadesV>(21);
		alfa = 0.90;
	}


	public void addCidades(int c, int x, int y) {

		matrizCidades.add(new CidadesV(c,x,y));
	}


	public void setMatrizDistancias(int[][] matrizDistancias2) {

		this.matrizDistancias = matrizDistancias2;	
	}


	//temperatur ainicial = raiz quadrada distancia ultimo para o  primeiro formaula distancia

	public void setFirstSolution(List<Integer> first) {
		startTime = System.currentTimeMillis();	
		//Collections.shuffle(first);
		int cost = 0;
		for(int i = 0; i<first.size()-1; i++) {	

			cost += matrizDistancias[first.get(i)][(first.get(i) + 1)];

		}
		cost+= matrizDistancias[first.size()-1][first.get(0)];

		//Collections.sort(first , new DistComparator<Integer>(matrizCidades));

		//System.out.println(matrizCidades.get(first.get(0)).sort + " " + matrizCidades.get(first.get(1)).sort);

		temperaturaInicial =  Math.sqrt((matrizCidades.get(first.size()-1).getX() - matrizCidades.get(first.get(0)).getX())^2 
				+ (matrizCidades.get(first.size()-1).getY() - matrizCidades.get(first.get(0)).getY())^2);
		

		firstSolution = new Solution(first, cost , 0, temperaturaInicial);
		System.out.println("first solution : " + firstSolution.getSolution());


		currentSolution = firstSolution;
		
		bestSolution = currentSolution;
		
	
		

	}
	
	public Solution algSimulatedAnnealing() {
		
		n_iter = fatorial(bestSolution.getSolution().size())/2;


		for(int n = 1; n < n_iter; n++) {
			System.out.println("current Cost : " + currentSolution.getCost());
			System.out.println("currentSolution:"  +currentSolution.getSolution());
			nextSolution = vizinho();

			System.out.println("bestSolution : " + bestSolution.getSolution());
			System.out.println("nextSolution : " + nextSolution.getSolution());


			System.out.println("bestSolutionCost :" + bestSolution.getCost());
			System.out.println("nextSolutionCost : " + nextSolution.getCost());
			
			int d = nextSolution.getCost() - currentSolution.getCost();
			if(d<0) {
				currentSolution = nextSolution;
				if(currentSolution.getCost() < bestSolution.getCost()) 
					bestSolution = currentSolution;
			}else {
				if(Math.exp(-d/temperaturaN) > 0.9)
					currentSolution = nextSolution;
					pCounter++;
					
			}
		}
		
			n_iter = fatorial(bestSolution.getSolution().size())/2;
			
		//	if(criterioParagem(n_iter)) {
				
			if(temperaturaN <= 0.5) {
				temperaturaBest = temperaturaN;
				return bestSolution;
			}
	
			temperaturaN = decaimento(temperaturaN);
			gCounter ++;
			nCounter++;
			time += System.currentTimeMillis() - startTime;
			return algSimulatedAnnealing();
			
	}
			
		


	//reduz e retorna a temperatura TODO
	private double decaimento(double temperatura) {
		return alfa * temperatura;
	}

	//se n_it dado for maior ou igual ao numero de iteraÃ§Ãµes inicial do problema entÃ£o podemos parar 
	//ou entÃ£o, outra soluÃ§Ã£o, Ã© descer a temperatura atÃ© zero 
	private boolean criterioParagem(int n_it ) {
		if(n_it >= n_iter)
			return true;
		else
			return false;
	}

	private int fatorial(int num) {
		int n = num;
		for (int i = 1; i <= num; i++) {
			n = n * i;
		}
		return n;
	}
	
	public void printResults() {
		System.out.println("Melhor Solução: ");
		iterSolution(bestSolution.getSolution().iterator());
		System.out.println();
		System.out.println("Temperatura = " + temperaturaBest);
		System.out.println("Pior Solução: ");
		iterSolution(firstSolution.getSolution().iterator());
		System.out.println();
		System.out.println("Temperatura = " + temperaturaN);
		System.out.println("Primeira Solução: ");
		iterSolution(firstSolution.getSolution().iterator());
		System.out.println();
		System.out.println("Temperatura = " + temperaturaInicial);
		System.out.println("Ultima Solução: ");
		iterSolution(currentSolution.getSolution().iterator());
		System.out.println();
		System.out.println("Temperatura = " + temperaturaN);
		
		System.out.println("Numero total de iterações: " + n_iter); //ou o counter 
		System.out.printf("Runtime (ms): " + this.time);
		
	}
	
	private void iterSolution(Iterator<Integer> it) {
		System.out.print("[ ");
		while(it.hasNext()) 
			System.out.print(it.next() + " ");
		System.out.print("]");
	}
	
	



	private Solution vizinho() {

		Random randomG = new Random();
		int v1 = 0; 
		int v2 = 0;
		int p1 = randomG.nextInt(currentSolution.getSolution().size());
		int p2 = randomG.nextInt(currentSolution.getSolution().size());

		while (p1 == p2 || Math.abs(p1 - p2) <= 1) {
			p2 = randomG.nextInt(currentSolution.getSolution().size());
		}

		if(p1<p2) {
			v2=p2;
			v1=p1;
		}else {
			v1=p2;
			v2=p1;
		}
		System.out.println("v1 : " + v1);
		System.out.println("v2 : " + v2);

		
		List<Integer> newSolution = new ArrayList<Integer>();
		int counter = 0;
		
		for (int s = 0; s < currentSolution.getSolution().size(); s++) {
			
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
		List<Integer> c = currentSolution.getSolution();
		System.out.println("current : " + c);

		int costD = matrizDistancias[c.get(v1)][c.get(v2)] + matrizDistancias[c.get(v1 + 1)][c.get(v2_2)]
				- matrizDistancias[c.get(v1)][c.get(v1 + 1)] - matrizDistancias[c.get(v2)][c.get(v2_2)];

		Solution vizinho = new Solution(newSolution, currentSolution.getCost() + costD, (pCounter + nCounter + 1), temperaturaN);
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
