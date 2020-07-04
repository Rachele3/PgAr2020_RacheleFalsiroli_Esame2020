package it.unibs.ing.progettoarnaldo.esame2020;


public class Carta {
	
	private Colore colore;
	private int numero;
	
	public Carta(Colore colore, int numero) {
		this.colore = colore;
		this.numero = numero;
	}

	
	public Colore getColore() {
		return colore;
	}

	public int getNumero() {
		return numero;
	}


	@Override
	public String toString() {
		return "Carta [colore = " + colore.name() + ", numero = " + numero + "]";
	}

	
}
