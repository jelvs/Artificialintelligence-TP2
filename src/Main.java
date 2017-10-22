import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Trocar local do ficheiro
		String nomeFicheiro = "/Users/TiagoSousa/Documents/workspace/IA-TP2/src/Distancias.txt";
		File ficheiroDistancias = new File(nomeFicheiro);
		String linha = null;
		int nlinhas = 0;
		int[][] matrizDistancias = new int[21][21];
	
		if(ficheiroDistancias.exists()) {
			try {
				FileReader leFicheiro = new FileReader(nomeFicheiro);
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
			
			for (int i = 0; i < matrizDistancias.length; i++) {
				for (int j = 0; j < matrizDistancias.length; j++) {
					System.out.print(matrizDistancias[i][j] + "\t");
				}
				System.out.println();
			}
		}
			
		
		
	}
	

}
