package it.polito.tdp.food.model;

public class Adiacenza2 {
	
	private String tipo1;
	private String tipo2;
	private int peso;
	
	public Adiacenza2(String tipo1, String tipo2, int peso) {
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.peso = peso;
	}

	public String getTipo1() {
		return tipo1;
	}

	public String getTipo2() {
		return tipo2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Adiacenza [tipo1=" + tipo1 + ", tipo2=" + tipo2 + ", peso=" + peso + "]";
	}
	
	

}
