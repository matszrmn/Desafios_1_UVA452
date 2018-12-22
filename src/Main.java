import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;


public class Main {
	public static int maiorCaminho = 0;
	public static Map pesos;
	public static Map arestas;
	
	
	public static void maiorCaminho(char atual, int acumulado) {
		acumulado += (int) pesos.get(atual);
		
		LinkedList<Character> vizinhos = (LinkedList<Character>) arestas.get(atual);
		if(vizinhos.size()==0) {
			if(acumulado > maiorCaminho) maiorCaminho = acumulado;
			return;
		}
		for(Character vizinho : vizinhos) {
			maiorCaminho(vizinho, acumulado);
		}
	}
	public static void main(String[] args) {
		Scanner scDoc = new Scanner(System.in);
		int casos = scDoc.nextInt();
		if(casos==0) {
			System.out.println(0);
			System.exit(0);
		}
		char nome;
		int peso;
		
		char atualAux;
		char anteriorAux;
		String anteriores;
		
		String listaAnteriores;
		LinkedList<Character> listaAux;
		
		LinkedList<Character> nos;
		LinkedList<Object[]> arestasQueDevemSerInseridas;
		
		String linha;
		if(scDoc.hasNextLine()) scDoc.nextLine();
		if(scDoc.hasNextLine()) scDoc.nextLine();
		
		for(int casoAtual=0; casoAtual<casos; casoAtual++) {
			pesos = new HashMap<Character, Integer>();
			arestas = new HashMap<Character, LinkedList<Character>>();
			nos = new LinkedList<Character>();
			arestasQueDevemSerInseridas = new LinkedList<Object[]>();
			
			linha = scDoc.nextLine();
			
			while(linha!=null && !linha.trim().equals("")) {
				nome = linha.charAt(0);
				linha = linha.substring(2);
				if(linha.contains(" ")){
					peso = Integer.parseInt(linha.substring(0, linha.indexOf(" ")));
					anteriores = linha.substring(linha.indexOf(" ")+1).trim();
					Object[] registro = {anteriores, nome};
					arestasQueDevemSerInseridas.add(registro);
				}
				else {
					peso = Integer.parseInt(linha.trim());
				}
				nos.add(nome);
				pesos.put(nome, peso);
				arestas.put(nome, new LinkedList<Character>());
				
				if(scDoc.hasNextLine()) linha = scDoc.nextLine();
				else break;
			}
			for(Object[] obj : arestasQueDevemSerInseridas) {
				listaAnteriores = (String) obj[0];
				atualAux = (Character) obj[1];
				for(int i=0; i<listaAnteriores.length(); i++) {
					anteriorAux = listaAnteriores.charAt(i);
					listaAux = (LinkedList<Character>)arestas.get(anteriorAux);
					if(listaAux != null) listaAux.add(atualAux);
				}
			}
			for(Character noAtual : nos) { //Maior caminho comecando a partir de cada no
				maiorCaminho(noAtual, 0);
			}
			if(casoAtual != casos-1) {
				System.out.println(maiorCaminho);
				System.out.println();
			}
			else System.out.println(maiorCaminho); //Ultima execucao

			maiorCaminho = 0;
		}
		scDoc.close();
	}
}
