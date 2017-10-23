import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import SimulatedAnnealing.Cidades;
import SimulatedAnnealing.SimulatedAnnealing;

public class Main {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String nomeFicheiro = "/Users/Silvia/Desktop/IA/Praticas/Praticas eclipse/AulaPratica 2/src/Distancias.txt";
		int[][] matrizDistancias = new int[21][21];
		
		matrizDistancias = setMatrizDistancias(nomeFicheiro, matrizDistancias);
		
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
		
		SimulatedAnnealing alg = new SimulatedAnnealing(matrizDistancias, cidadesValues);

	/*	for(int i: cidadesValues)
			System.out.println(i); */
		
		System.out.println(alg.temperaturaInicial());
			
	}
	
	private static int[][] setMatrizDistancias(String nomeFicheiro, int[][] matrizDistancias) {
		
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
		
		return matrizDistancias;
	}


}
