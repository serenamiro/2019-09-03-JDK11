package it.polito.tdp.food.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Model2 {
	
	private FoodDao2 dao;
	public Graph<String, DefaultWeightedEdge> grafo;
	private List<String> tipiDiPorzione;
	
	List<String> parziale;
	List<String> bestCammino = null;
	private int pesoBestCammino = 0;
	
	public Model2() {
		this.dao = new FoodDao2();
	}
	
	public void creaGrafo(int calorie) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.tipiDiPorzione = this.dao.getTipoDiPorzioneByCalorie(calorie);
		
		Graphs.addAllVertices(this.grafo, tipiDiPorzione);
		
		List<Adiacenza2> list = dao.getAdiacenze();
		
		for(Adiacenza2 a : list) {
			if(this.grafo.vertexSet().contains(a.getTipo1()) && this.grafo.vertexSet().contains(a.getTipo2())){
				Graphs.addEdge(this.grafo, a.getTipo1(), a.getTipo2(), a.getPeso());
			}
		}
		
		System.out.format("Grafo creato con %d vertici e %d archi\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<String> getVertici(){
		return this.tipiDiPorzione;
	}
	
	public List<VerticiVicini> getVerticiVicini(String partenza){
		List<String> vicini = Graphs.neighborListOf(this.grafo, partenza);
		List<VerticiVicini> res = new ArrayList<>();
		for(String s : vicini) {
			int peso = (int) this.grafo.getEdgeWeight(this.grafo.getEdge(partenza, s));
			VerticiVicini vv = new VerticiVicini(s, peso);
			res.add(vv);
		}
		return res;
	}
	
	public String getVerticiViciniConPeso(String partenza) {
		List<VerticiVicini> res = this.getVerticiVicini(partenza);
		String s = "Nodi direttamente connessi:\n\n";
		for(VerticiVicini vv : res) {
			s += vv.getTipoDiPorzione()+" "+vv.getPeso()+"\n";
		} 
		return s;
	}
	
	public List<String> calcolaCammino(String partenza, int N){
		parziale = new ArrayList<String>();
		parziale.add(partenza);
		
		cerca(parziale, 1, N); // parziale, livello, termine
		return bestCammino;
	}

	private void cerca(List<String> parziale, int livello, int N) {
		// CASO TERMINALE
		if(parziale.size() == N+1) {
			int peso = calcolaPeso(parziale);
			if(peso>pesoBestCammino) {
				pesoBestCammino = peso;
				bestCammino = new ArrayList<>(parziale);
			}
			return;
		}
		
		List<String> vicini = Graphs.neighborListOf(this.grafo, parziale.get(livello-1));
		for(String s : vicini) {
			if(!parziale.contains(s)) {
				parziale.add(s);
				cerca(parziale, livello+1, N);
				parziale.remove(parziale.size()-1);
			}
		}
	}

	private int calcolaPeso(List<String> parziale2) {
		
		int pesoTotale = 0;
		
		for(int i = 0; i<parziale2.size()-1; i++) {
			String here = parziale2.get(i);
			String next = parziale2.get(i+1);
			int peso = (int) this.grafo.getEdgeWeight(this.grafo.getEdge(here, next));
			pesoTotale += peso;
		}
		
		return pesoTotale;
	}
	
	public int getPesoMax() {
		return pesoBestCammino;
	}
	
}