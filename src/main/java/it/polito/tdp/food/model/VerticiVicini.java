package it.polito.tdp.food.model;

public class VerticiVicini {
	
	private String tipoDiPorzione;
	private int peso;
	
	public VerticiVicini(String tipoDiPorzione, int peso) {
		super();
		this.tipoDiPorzione = tipoDiPorzione;
		this.peso = peso;
	}

	public String getTipoDiPorzione() {
		return tipoDiPorzione;
	}

	public void setTipoDiPorzione(String tipoDiPorzione) {
		this.tipoDiPorzione = tipoDiPorzione;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	

}
