package it.unibs.ing.progettoarnaldo.esame2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import it.unibs.ing.progettoarnaldo.mylib.*;


public class Mazzo {

	private static final int CARTA_BLU = 1;
	private static final int CARTA_ROSSA = 2;
	private static final int CARTA_GIALLA = 3;
	private static final int CARTA_VERDE = 4;
	
	private ArrayList<Carta> mazzoCarte = new ArrayList<Carta>(80);
	private Stack<Carta> carteScartate = null;
	
	
	/**  COSTRUTTORE  */
	public Mazzo() 
	{
		this.mazzoCarte = costruisciMazzo();
		this.carteScartate = new Stack<Carta>();
	}

	
	/**
	 * METODO costruisciMazzo.
	 * Genera il mazzo ciclando sui 4 colori e costruendo le carte numerate da 0 a 9.
	 * @return lista di carte colorate e numerate
	 */
	private ArrayList<Carta> costruisciMazzo ()
	{
		ArrayList<Carta> lista = new ArrayList<Carta>();
	
		Carta c = new Carta();
	
		do {
			for (int j = 1; j <= 4; j++) // ciclo sul numero di colori possibili
			{
				switch (j) 
				{ 
					case CARTA_BLU:
						for (int i = 0; i <= 9; i++) 
						{
							c = new Carta (Colore.BLU, i);
							lista.add(c);
						}
						break;
						
					case CARTA_ROSSA:
						for (int i = 0; i <= 9; i++) 
						{
							c = new Carta (Colore.ROSSA, i);
							lista.add(c);
						}
						break;
						
					case CARTA_GIALLA:
						for (int i = 0; i <= 9; i++) 
						{
							c = new Carta (Colore.GIALLA, i);
							lista.add(c);
						}
						break;
						
					case CARTA_VERDE:
						for (int i = 0; i <= 9; i++) 
						{
							c = new Carta (Colore.VERDE, i);
							lista.add(c);
						}
						break;
						
				}
			}
			
		} while(lista.size()< 80);
		
		return lista;
	}
	
	
	/**  Mischia il mazzo di carte  */
	public void mischiaMazzo() {
	    Collections.shuffle(mazzoCarte);
	}
	
	
	/**
	 * METODO estrai.
	 * Si occupa di estrarre una carta casualmente.
	 * @return Carta estratta
	 */
	public Carta estrai() 
	{
		int estratto = EstrazioniCasuali.estraiIntero(0, mazzoCarte.size());
		return mazzoCarte.get(estratto);
	}


	public Carta aggiungiCartaScartata()
	{
		Carta c = estrai();
		this.carteScartate.push(c);
		return c;
	}
	
	public ArrayList<Carta> getMazzo_di_carte() {
		return mazzoCarte;
	}
	
}