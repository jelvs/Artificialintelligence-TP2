package SimulatedAnnealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {

	private static final int MAX_ITER= 200;
	private int[][] matrizDistancias;
	private List<CidadesV> matrizCidades;
	private double temperaturaN;
	private double temperaturaInicial;
	private double temperaturaBest;
	private int pCounter;
	private int nCounter;
	private double alfa;
	private int n_iter;
	private long time;
	private long startTime;
	
	private Solution firstSolution;
	private Solution currentSolution;
	private Solution nextSolution;
	private Solution bestSolution;
	



	public SimulatedAnnealing() {

		matrizCidades = new ArrayList<CidadesV>(27);
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

		temperaturaInicial =  Math.sqrt(((matrizCidades.get(first.size()-1).getX() - matrizCidades.get(first.get(0)).getX()))^2 
				+ ((matrizCidades.get(first.size()-1).getY() - matrizCidades.get(first.get(0)).getY()))^2);
		

		firstSolution = new Solution(first, cost , 0, temperaturaInicial);
		System.out.println("first solution : " + firstSolution.getSolution());


		currentSolution = firstSolution;
		
		bestSolution = currentSolution;
		
	
		

	}
	
	public void setFirstShuffleSolution(List<Integer> first) {
		startTime = System.currentTimeMillis();	
		Collections.shuffle(first);
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
	
	public void setFirstCompSolution(List<Integer> first) {
		startTime = System.currentTimeMillis();	
		//Collections.shuffle(first);
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
		System.out.println("first solution : " + firstSolution.getSolution());


		currentSolution = firstSolution;
		
		bestSolution = currentSolution;
		
	
		

	}
	
	public Solution algSimulatedAnnealing(int num) {
		
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
				if(Math.exp(-d/temperaturaN) > alfa)
					currentSolution = nextSolution;
					pCounter++;
					
			}
		}
		
			//n_iter = fatorial(bestSolution.getSolution().size())/2;
			
			
					if(num == 1) {
						if(n_iter/pCounter <= 0.2 ) {
							temperaturaBest = temperaturaN;
							time += System.currentTimeMillis() - startTime;
							return bestSolution;
						}
							
					}
						
					if(num == 2) { //TEMPERATURA
						if(temperaturaN <= 0.5) {
							temperaturaBest = temperaturaN;
							time += System.currentTimeMillis() - startTime;
							return bestSolution;
						}
					}
					
					if(num == 3) { //numero de iteracoes
						if(n_iter >= MAX_ITER ) {
							temperaturaBest = temperaturaN;
							time += System.currentTimeMillis() - startTime;
							return bestSolution;
						}
					}
					
			temperaturaN = decaimento(temperaturaN);
			
			nCounter++;
			
			return algSimulatedAnnealing(num);
			
	}
			
		


	//reduz e retorna a temperatura TODO
	private double decaimento(double temperatura) {
		return alfa * temperatura;
	}


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
		List<Integer> updateSolution = new ArrayList<Integer>();
		int counter = 0;
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

		
		
		
		
		for (int s = 0; s < currentSolution.getSolution().size(); s++) {
			
			if (s <= v1 || s > v2) {
				updateSolution.add(s, currentSolution.getSolution().get(s));

			} else {
				updateSolution.add(s, currentSolution.getSolution().get(v2 - counter));

				counter++;
			}
		}
		int v2_2 = v2 + 1;
		if (v2_2 >= currentSolution.getSolution().size()) {
			v2_2 = 0;
		}
		List<Integer> cList = currentSolution.getSolution();
		System.out.println("current : " + cList);

		int costD = matrizDistancias[cList.get(v1)][cList.get(v2)] + matrizDistancias[cList.get(v1 + 1)][cList.get(v2_2)]
				- matrizDistancias[cList.get(v1)][cList.get(v1 + 1)] - matrizDistancias[cList.get(v2)][cList.get(v2_2)];

		Solution vizinho = new Solution(updateSolution, currentSolution.getCost() + costD, (pCounter + nCounter + 1), temperaturaN);
		return vizinho;

		
		
	}



}
