import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import SimulatedAnnealing.SimulatedAnnealing;

public class Main {

	private static SimulatedAnnealing simulated = new SimulatedAnnealing();
	private static final String COUNTER = "CT";
	private static final String TEMPERATURA = "T";
	private static final String N_ITER = "IT";
	private static final String SHUFFLE = "S";
	private static final String REGULAR = "R";
	private static final String COMPARATOR = "C";

	private static final int CT= 1;
	private static final int TEP= 2;
	private static final int NI = 3;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// TODO Auto-generated method stub

		String nomeFicheiroD = "/Users/TiagoSousa/Documents/workspace/IA-TP2/src/Distancias.txt";
		String nomeFicheiroC = "/Users/TiagoSousa/Documents/workspace/IA-TP2/src/cidades-xy.txt";

		int[][] matrizDistancias = new int[26][26];


		setMatrizDistancias(nomeFicheiroD, matrizDistancias);
		setMatrizCidades(nomeFicheiroC);

		Scanner in = new Scanner(System.in);
		setFirstSolution(in);
		

		System.out.println("Forma de Acabar: (CT:COUNTER, T:TEMPERATURE, IT:ITERATIONS)");
		String cmd = getCommand(in);


		switch(cmd){
		case COUNTER:
			simulated.algSimulatedAnnealing(CT);
			break;
		case TEMPERATURA:
			simulated.algSimulatedAnnealing(TEP);
			break;
		case N_ITER:
			simulated.algSimulatedAnnealing(NI);
			break;
		default:
			break;
		}
		System.out.println();
		
		in.close();

		simulated.printResults();

	}




	private static String getCommand(Scanner in) {
		String input; 
		input = in.next().toUpperCase();
		return input;
	}

	private static void setFirstSolution(Scanner in) throws NumberFormatException, IOException {
		BufferedReader first = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> firstList;
		do {

			StringTokenizer t = new StringTokenizer(first.readLine());
			int count = t.countTokens();
			firstList = new ArrayList<Integer>(count);
			while(count>0) {
				firstList.add(Integer.parseInt(t.nextToken()));
				count--;
			}

		}while(first.ready());
		
		
		System.out.println("R:REGULAR ou S:SUFFLE ou C:COMPARATOR :");
		String cmd = getCommand(in);

		
		switch(cmd){
		case REGULAR:
			simulated.setFirstSolution(firstList);
			break;
		case SHUFFLE:
			simulated.setFirstShuffleSolution(firstList);
			break;
		case COMPARATOR:
			simulated.setFirstCompSolution(firstList);
			break;
		default:
			break;
		}
	
		
	

	}

	private static void setMatrizDistancias(String nomeFicheiro, int[][] matrizDistancias) {

		File ficheiroDistancias = new File(nomeFicheiro);
		String linha = null;
		int nlinhas = 0;



		if(ficheiroDistancias.exists()) {
			try {
				FileReader leFicheiro = new FileReader(nomeFicheiro);
				@SuppressWarnings("resource")
				BufferedReader bufferFicheiro = new BufferedReader(leFicheiro);
				do {
					linha = bufferFicheiro.readLine();
					StringTokenizer st = new StringTokenizer(linha, " ");
					int numTokens = st.countTokens();					

					for(int j = 0; j < numTokens  ; j++){
						String token = st.nextToken();
						matrizDistancias[j][j] = 0;
						if( token.charAt(0) > 47 && token.charAt(0) < 58) {
							matrizDistancias[nlinhas][j-1] = Integer.parseInt(token);
							matrizDistancias[j-1][nlinhas] = Integer.parseInt(token);
						}
					}
					nlinhas++;

				}while(linha != null);

			}catch(Exception e) {

			}
		}

		simulated.setMatrizDistancias(matrizDistancias);


	}

	private static void setMatrizCidades(String nomeFicheiro) {

		File ficheiroDistancias = new File(nomeFicheiro);



		if(ficheiroDistancias.exists()) {
			try {
				FileReader leFicheiro = new FileReader(nomeFicheiro);
				@SuppressWarnings("resource")
				BufferedReader bufferFicheiro = new BufferedReader(leFicheiro);
				bufferFicheiro.readLine();
				int c = 0;
				do {

					StringTokenizer st = new StringTokenizer(bufferFicheiro.readLine());


					st.nextToken();	
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());

					simulated.addCidades(c++, x, y);

				}while(bufferFicheiro.ready());

			}

			catch(Exception e) {

			}

		}


	}


}
