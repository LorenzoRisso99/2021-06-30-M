package it.polito.tdp.genes.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private Graph<Integer, DefaultWeightedEdge> grafo;
	
	private GenesDao dao;
	
	private double pesoAlto;
	private double pesoBasso;
	
	public Model() {
		dao = new GenesDao();
	}
	
	public void creaGrafo() {
		
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, dao.getVertici());
		
		for(Adiacenza a : dao.getArchi()) {
			Graphs.addEdgeWithVertices(this.grafo, a.getC1(), a.getC2(), a.getExpressionCorr());
			
			if(a.getExpressionCorr() > pesoAlto) {
				pesoAlto = a.getExpressionCorr();
			}
			
			if(a.getExpressionCorr() < pesoBasso) {
				pesoBasso = a.getExpressionCorr();
			}
			
			
		}
		
		System.out.println("grafo creato");
		System.out.println("Vertici : " + this.grafo.vertexSet().size());
		System.out.println("Archi : " + this.grafo.edgeSet().size());
		System.out.println("Peso basso : ");
		
		
	}

	private int numP = 0;
	private int numM = 0;

	public void check(Double num) {
		numM = 0;
		numP = 0;
		for (DefaultWeightedEdge e : grafo.edgeSet()) {
			if (grafo.getEdgeWeight(e) > num) {
				numP++;
			}
			if (grafo.getEdgeWeight(e) < num) {
				numM++;
			}
		}
		System.out.println("Minori: " + numM);
		System.out.println("Maggiori: " + numP);
	}

	public int getNumP() {
		return numP;
	}

	public int getNumM() {
		return numM;
	}

	public double getPesoAlto() {
		return pesoAlto;
	}

	public double getPesoBasso() {
		return pesoBasso;
	}

}