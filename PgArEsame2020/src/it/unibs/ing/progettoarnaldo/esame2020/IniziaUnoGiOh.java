package it.unibs.ing.progettoarnaldo.esame2020;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import it.unibs.ing.progettoarnaldo.mylib.*;


public class IniziaUnoGiOh {

	private static final String RICHIESTA_NUMERO_GIOCATORI = "Quanti giocatori vogliono giocare?\n"
			+ "1. Un solo giocatore\n"
			+ "2. Due giocatori\n"
			+ "3. Tre giocatori\n"
			+ "4. Quattro giocatori\n"
			+ "Inserire opzione scelta: ";
	
	private static final String FINE_PARTITA = "\nIl giocatore %s ha vinto, perchè ha terminato le carte del suo mazzo.";
	
	private static final String MSG_INIZIALE = "\nINIZIA LA PARTITA!";
	private static final String RICHIESTA_NOME = "\nInserire nome giocatore: ";
	

	/**
	 * METODO iniziaPartita.
	 * Si occupa di gestire la partita con i turni.
	 */
	public void iniziaPartita()
	{
		IniziaUnoGiOh gioco = new IniziaUnoGiOh();
		Mazzo mazzo = null;
		ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();

		System.out.println(MSG_INIZIALE);
		
		int numeroGiocatori = InputDati.leggiIntero(RICHIESTA_NUMERO_GIOCATORI, 1, 4);
		
		// LISTA GIOCATORI
		for (int i = 0; i < numeroGiocatori; i++) 
		{
			Giocatore g = null;
			g = new Giocatore(InputDati.leggiStringaNonVuota(RICHIESTA_NOME));
			giocatori.add(g);
			
		}
		
		// VIENE SCELTO ORDINE DI PARTENZA
		Map <Integer, Giocatore> ordineTurni = new TreeMap<Integer, Giocatore>();
		ordineTurni = gioco.iniziaTu(giocatori);
		
		
		do 
		{
			// corpo della partita
			// ... 

		} while(gioco.isFinita(giocatori) == false);
	
	}
	
	
	/*Per decidere chi inizia a giocare i giocatori lanciano un dado a 6 facce con numeri da 1 a 6
	(estremi inclusi), chi otterrà il numero più alto inizia il turno. Nel caso uscisse lo stesso numero a
	entrambi i giocatori il dado verrà rilanciato nuovamente da essi.*/
	
	private Map<Integer, Giocatore> iniziaTu (ArrayList<Giocatore> elencoGiocatori) 
	{
		Map<Integer, Giocatore> infoGiocatore = new TreeMap<Integer, Giocatore>();
		
		for (int i = 0; i < elencoGiocatori.size(); i++) 
		{
			int numeroEstrattoDado = EstrazioniCasuali.estraiIntero(1, 6);
			infoGiocatore.put(numeroEstrattoDado, elencoGiocatori.get(i));
		}
			
		return infoGiocatore;	
	}
	
	
	
	private boolean isFinita(ArrayList<Giocatore> elencoGiocatori) 
	{
		boolean finita = false;
		for (int i = 0; i < elencoGiocatori.size(); i++) {
			if (elencoGiocatori.get(i).numeroCarte() == 0) {
				System.out.println(String.format(FINE_PARTITA, elencoGiocatori.get(i).getNome()));
				return true;
			}
		}
		return finita;
	}
}
