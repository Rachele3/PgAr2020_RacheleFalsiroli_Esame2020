package it.unibs.ing.progettoarnaldo.esame2020;

import java.util.ArrayList;

public class Giocatore {

	private static final String DESCRIZIONE = "\nIl giocatore %s ha ancora %d carte nel suo mazzo\n";
	
	private String nome;
	private ArrayList<Carta> mieCarte = new ArrayList<Carta>(7);
	
	public Giocatore(String nome) {
		this.nome = nome;
		this.mieCarte = creaMioMazzo();  // ogni giocatore ha inizialmente 7 carte
	}

	/**
	 * METODO creaMioMazzo.
	 * Genera il mazzo del giocatore
	 * @return lista di carte del giocatore estratte dal mazzo
	 */
	private ArrayList<Carta> creaMioMazzo() 
	{
		ArrayList<Carta> mioMazzo = new ArrayList<Carta>();
		Mazzo mazzo = new Mazzo();
		
		for (int i = 0; i < 7; i++) {
			mioMazzo.add(mazzo.estrai());
		}
			
		return mioMazzo;
	}
	
	
	public Carta scartaCarta()
	{	
		Carta c = null;
		Mazzo mazzo = new Mazzo();
		Carta cartaPescata = mazzo.aggiungiCartaScartata();
		
		// Scarto carta se è dello stesso colore oppure ha uguale numero della carta sulla pila delle carte scartate
		if (c.getColore().equals(cartaPescata.getColore())  ||  c.getNumero() == cartaPescata.getNumero()) {  
			System.out.println("Hai scartato: " + c.toString());
			this.mieCarte.remove(c);
		}
		
		else {// pesca carta dal mazzo, vedo se è compatibile altrimenti passo il turno
			cartaPescata = mazzo.estrai();
			if (c.getColore().equals(cartaPescata.getColore())  ||  c.getNumero() == cartaPescata.getNumero()) {  
				System.out.println("Hai scartato: " + c.toString());
				this.mieCarte.remove(c);
			}
			else {
				System.out.println("Spiacente, non puoi scartare nessuna carta! Passi il turno");
				return null;
			}
		}
		
		return c;
	}
	
	
	public int numeroCarte() {
		return this.mieCarte.size();
	}
	
	
	public String toString() {
		return String.format(DESCRIZIONE, this.nome.toUpperCase(), numeroCarte());
	}

	public String getNome() {
		return nome;
	}
	
	
}
