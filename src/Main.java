import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<String> conjuntoV = new ArrayList<>();
		ArrayList<Aresta> conjuntoE = new ArrayList<>();
		
		int op = 0 ;
		 //Inserção de daods manual
		while(op!=2) {
			
			conjuntoV.add(JOptionPane.showInputDialog("Insira o label do vertice:"));
			
			op = Integer.parseInt(JOptionPane.showInputDialog("Deseja continuar inserindo vertices? \n1 - SIM \n2 - NÃO"));
			
		}
		
		op = 0;
		
		while(op!=2) {
			
			Aresta a = new Aresta();
			
			a.origem = (JOptionPane.showInputDialog("Insira o vertice origem:"));
			a.destino = (JOptionPane.showInputDialog("Insira o vertice destino:"));
			a.distancia = (Integer.parseInt(JOptionPane.showInputDialog("Insira a distância da aresta:")));
			
			conjuntoE.add(a);
			
			op = Integer.parseInt(JOptionPane.showInputDialog("Deseja continuar inserindo vertices? \n1 - SIM \n2 - NÃO"));
			
		}
		
		/* PARA TESTES DO ALGORITMO - dados já inseridos
		conjuntoV.add("a");
		conjuntoV.add("b");
		conjuntoV.add("c");
		conjuntoV.add("d");
		conjuntoV.add("e");
		conjuntoV.add("f");
		conjuntoV.add("g");
		conjuntoV.add("h");
		conjuntoV.add("i");
		
		Aresta a1 = new Aresta();
		a1.origem = "a";
		a1.destino = "b";
		a1.distancia = 4;
		conjuntoE.add(a1);
		
		Aresta a2 = new Aresta();
		a2.origem = "a";
		a2.destino = "h";
		a2.distancia = 8;
		conjuntoE.add(a2);
		
		Aresta a3 = new Aresta();
		a3.origem = "b";
		a3.destino = "c";
		a3.distancia = 8;
		conjuntoE.add(a3);
		
		Aresta a4 = new Aresta();
		a4.origem = "b";
		a4.destino = "h";
		a4.distancia = 11;
		conjuntoE.add(a4);
		
		Aresta a5 = new Aresta();
		a5.origem = "c";
		a5.destino = "d";
		a5.distancia = 7;
		conjuntoE.add(a5);
		
		Aresta a6 = new Aresta();
		a6.origem = "c";
		a6.destino = "f";
		a6.distancia = 4;
		conjuntoE.add(a6);
		
		Aresta a7 = new Aresta();
		a7.origem = "c";
		a7.destino = "i";
		a7.distancia = 2;
		conjuntoE.add(a7);
		
		Aresta a8 = new Aresta();
		a8.origem = "d";
		a8.destino = "e";
		a8.distancia = 9;
		conjuntoE.add(a8);
		
		Aresta a9 = new Aresta();
		a9.origem = "d";
		a9.destino = "f";
		a9.distancia = 14;
		conjuntoE.add(a9);
		
		Aresta a10 = new Aresta();
		a10.origem = "e";
		a10.destino = "f";
		a10.distancia = 10;
		conjuntoE.add(a10);
		
		Aresta a11 = new Aresta();
		a11.origem = "f";
		a11.destino = "g";
		a11.distancia = 2;
		conjuntoE.add(a11);
		
		Aresta a13 = new Aresta();
		a13.origem = "g";
		a13.destino = "h";
		a13.distancia = 1;
		conjuntoE.add(a13);
		
		Aresta a14 = new Aresta();
		a14.origem = "g";
		a14.destino = "i";
		a14.distancia = 6;
		conjuntoE.add(a14);
		
		Aresta a15 = new Aresta();
		a15.origem = "h";
		a15.destino = "i";
		a15.distancia = 7;
		conjuntoE.add(a15);
		*/
		
		JOptionPane.showMessageDialog(null, Kruskal(conjuntoV, conjuntoE));

	}
	
	
	static public String Kruskal(ArrayList<String> conjuntoV,ArrayList<Aresta> conjuntoE) {
		
		ArrayList<Conjunto> conjuntos = new ArrayList<>(); // lista dos conjuntos de cada aresta
		String arvoreGeradora = "Arestas da Árvore Geradora: ";
		int custo = 0;
		
		for(int i=0; i<conjuntoV.size();i++) { //Gera um conjunto para cada aresta
			
			Conjunto conjuntoDoVertice =  new Conjunto(); //Cria os conjuntos dos vertice
			conjuntoDoVertice.conjunto.add(conjuntoV.get(i)); //adiciona o label do vertice no seu conjunto
			conjuntos.add(conjuntoDoVertice); //adiciona o conjunto do vertice à lista de conjuntos
			} 
		
		ArrayList<Aresta> arestasOrdenadas = new ArrayList<>(); //lista das arestas já ordenadas em ordem crescente por distância
		ArrayList<Aresta> aux = new ArrayList<>(); //lista auxiliar
		
		aux = conjuntoE; //A lista de arestas será atribuida a lista auxiliar
		Aresta aAux = new Aresta();
		
		while(!aux.isEmpty()) { // enquanto a lista auxiliar não estiver vazia
			
			aAux = ordenacao(aux); //Chama o método ordenacao(aux) que recebe a lista auxiliar por parametro e devolve a aresta de menor distância para ser atribuida a aresta auxiliar
			
			for(int i=0;i<aux.size();i++) { //percore a lista auxiliar
				if(aAux == aux.get(i)) { //verifica a aresta de menor distância
					arestasOrdenadas.add(aux.get(i)); //adiciona a aresta menor que encontro na lsita de arestas de distancia em ordem crescente
					aux.remove(i); //remove a aresta de menor distância da lista auxiliar, para quando o método ordenacao(aux) rodar novamente, achar a próxima aresta de menor valor na lista auxiliar
					}
			}
		}
		
		boolean verfOrigem = false;
		boolean verfDestino = false;
		
		Conjunto conjuntoDaOrigem = new Conjunto(); //conjuntos que serão usados para auxiliar
		Conjunto conjuntoDoDestino = new Conjunto();
		Conjunto u = new Conjunto(); //conjunto para as unioes
		
		for(int i=0;i<arestasOrdenadas.size();i++) { // percorrer as aresta em ordem crescente
			for(int j=0; j<conjuntos.size();j++) {  // percorrer a lista de conjuntos
				for(int k=0; k<conjuntos.get(j).conjunto.size();k++) { //percorre o conjunto do vertice
					if(conjuntos.get(j).conjunto.get(k).equals(arestasOrdenadas.get(i).origem)) { //verifica os valores dentro do conjunto
						verfOrigem = true; //se o valor dentro do conjunto for igual a origem, o vertice de origem pertence e este conjunto
					}
						
					if(conjuntos.get(j).conjunto.get(k).equals(arestasOrdenadas.get(i).destino)) {
						verfDestino = true; //se o valor dentro do conjunto for igual ao destino, o vertice de destino pertence e este conjunto
					}
				}	
				
				//se o vertice de origem e destino estão no mesmo conjunto
				//ambas verificações estarão true
				//neste caso, nada será feito, do mesmo modo se ambas estiverem false
				if(verfOrigem == true && verfDestino == false) { //Verifica se o conjunto é do vertice origem e se o destino também não está nele, caso esteja, temos um ciclo
					arvoreGeradora += "(" + arestasOrdenadas.get(i).origem + "," + arestasOrdenadas.get(i).destino + ")"; //Se não houver ciclo, adiciona a aresta a árvore geradora
					custo += arestasOrdenadas.get(i).distancia; //calcula o custo da árvore
				
					conjuntoDaOrigem = conjuntos.get(j); //indica que este conjunto é do vertice origem
					
				}else {
					if(verfOrigem == false && verfDestino == true) { //caso seja o conjunto do vertice destino
		
						conjuntoDoDestino = conjuntos.get(j);//Apenas indica que este conjunto é do vertice destino
					}
				}
				
				//reinicia as variaveis
				verfOrigem = false; 
				verfDestino = false;  
			}
			
			if(conjuntoDaOrigem!=null && conjuntoDoDestino!=null) { //se houver um conjunto de origem e destino
				u = uniao(conjuntoDaOrigem, conjuntoDoDestino); //Faz a unia dos dois
				conjuntos.add(u); //adiciona na lista de conjuntos
				conjuntos.remove(conjuntoDaOrigem); //remove o conjunto origem (para não haver duplicidade)
				conjuntos.remove(conjuntoDoDestino); //remove o conjunto destino (para não haver duplicidade)
			}
			
			//reinicia as variaveis
			conjuntoDaOrigem = null;
			conjuntoDoDestino = null;
			
			/* PARA TESTES DO ALGORITMO - verifica cada modificação que ocorre na lista de conjuntos e suas uniões
			String conjuntoUniao = "Conjuntos - (";
			for(int x=0;x<conjuntos.size();x++) {
				conjuntoUniao += "{";
				for(int y=0;y<conjuntos.get(x).conjunto.size();y++) {
					conjuntoUniao += conjuntos.get(x).conjunto.get(y);
				}
				
				conjuntoUniao += "} ";
			}
			
			JOptionPane.showMessageDialog(null, conjuntoUniao + ")");
			 */
		}
		
		/* PARA TESTES DO ALGORITMO - Mostra os conjuntos iniciais e a lista de arestas ordenadas crescentemente pela distância
		String conj = "Conjuntos - (";
		for(int i=0;i<conjuntos.size();i++) {
			conj += "{";
			for(int j=0;j<conjuntos.get(i).conjunto.size();j++) {
				conj += conjuntos.get(i).conjunto.get(j);
			}
			
			conj += "} ";
		}
		
		String ordem = "Ordem das crescente das arestas - (";
		for(int i=0;i<arestasOrdenadas.size();i++) {
			ordem+=arestasOrdenadas.get(i).distancia + " ";
		}
		
		JOptionPane.showMessageDialog(null, conj + ")");
		JOptionPane.showMessageDialog(null, ordem + ")");
		
		*/
		
		return arvoreGeradora + "\nCusto da árvore: " + custo;
		
	}
	
	static public Aresta ordenacao (ArrayList<Aresta> aux) { //Achar a aresta de menor valor da lista auxiliar e retorna-la
		
		int menor = 99999999;
		Aresta aMenor =  new Aresta(); //aresta com menor valor
		
		for(int i=0;i<aux.size();i++) {
			if(aux.get(i).distancia < menor) { // se a aresta for menor que o menor
				menor = aux.get(i).distancia; //atribui a distância menor
				aMenor = aux.get(i); // fica com a aresta menor
			}
		}
		
		return aMenor;
		
	}
	
	static public Conjunto uniao(Conjunto conjuntoOrigem, Conjunto conjuntoDestino){
		
		Conjunto uniao = new Conjunto();
		
		//Pega o conjunto do vertice origem e uni com o conjunto do vertice destino
		for(int i=0;i<conjuntoOrigem.conjunto.size(); i++) {
			uniao.conjunto.add(conjuntoOrigem.conjunto.get(i));
		}
		
		for(int i = 0;i<conjuntoDestino.conjunto.size();i++) {
			uniao.conjunto.add(conjuntoDestino.conjunto.get(i));
		}
		
		return uniao; //retorna o conjunto originado da união
	}

}
