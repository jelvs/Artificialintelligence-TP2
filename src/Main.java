import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import SimulatedAnnealing.Cidades;
import SimulatedAnnealing.SimulatedAnnealing;

public class Main {

	private static SimulatedAnnealing simulated = new SimulatedAnnealing();

	public static void main(String[] args) throws NumberFormatException, IOException {

		// TODO Auto-generated method stub

		String nomeFicheiroD = "/Users/TiagoSousa/Documents/workspace/IA-TP2/src/Distancias.txt";
		String nomeFicheiroC = "/Users/TiagoSousa/Documents/workspace/IA-TP2/src/cidades-xy.txt";

		int[][] matrizDistancias = new int[21][21];


		setMatrizDistancias(nomeFicheiroD, matrizDistancias);
		setMatrizCidades(nomeFicheiroC);

		setFirstSolution();
		
		

		//simulated.printCidades();

		String[] cidadesVisitadas = {"A", "S", "D"};
		int[] cidadesValues = new int[cidadesVisitadas.length];


		int current = 0;
		for(Cidades cid1: Cidades.values()) {
			for(String cid2: cidadesVisitadas ) {
				if(cid2.equals(cid1.getName())) {
					cidadesValues[current] = cid1.getValue();
					current++;
				}

			}
		}





	}

	private static void setFirstSolution() throws NumberFormatException, IOException {
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
	
		simulated.setFirstSolution(firstList);

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
