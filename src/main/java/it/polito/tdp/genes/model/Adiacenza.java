package it.polito.tdp.genes.model;

public class Adiacenza {
	
	private int c1 ;
	private int c2 ;
	private Double expressionCorr ;
	
	public Adiacenza(int c1, int c2, Double expressionCorr) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.expressionCorr = expressionCorr;
	}

	public int getC1() {
		return c1;
	}

	public void setC1(int c1) {
		this.c1 = c1;
	}

	public int getC2() {
		return c2;
	}

	public void setC2(int c2) {
		this.c2 = c2;
	}

	public Double getExpressionCorr() {
		return expressionCorr;
	}

	public void setExpressionCorr(Double expressionCorr) {
		this.expressionCorr = expressionCorr;
	}
	
	
	
	

}
