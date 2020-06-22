package it.polito.tdp.food.model;

public class TipoDiPorzione {
	
	private String nome;

	public TipoDiPorzione(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "TipoDiPorzione [nome=" + nome + "]";
	}
	
	

}
