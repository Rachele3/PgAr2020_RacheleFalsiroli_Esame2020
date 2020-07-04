package it.unibs.ing.progettoarnaldo.esame2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import it.unibs.ing.progettoarnaldo.mylib.*;


public class IniziaUnoGiOh {

	private static final String RICHIESTA_NUMERO_GIOCATORI = "Quanti giocatori vogliono partecipare?\n"
			+ "1. Un solo giocatore\n"
			+ "2. Due giocatori\n"
			+ "3. Tre giocatori\n"
			+ "4. Quattro giocatori\n"
			+ "Inserire opzione scelta: ";
	
	private static final String FINE_PARTITA = "\nIl giocatore %s ha vinto, perchè ha terminato le carte del suo mazzo.";
	
	private static final String MSG_INIZIALE = "\nINIZIA LA PARTITA!";
	private static final String RICHIESTA_NOME = "Inserire nome giocatore: ";
	
	private Mazzo mazzo;
	private Stack<Carta> carteScartate = new Stack<Carta>();
	private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();

	
	public IniziaUnoGiOh() {
		this.mazzo = new Mazzo();
	}

	
	 /**
     * METODO iniziaPartita.
     * Si occupa di gestire la partita con i turni.
     */
    public void iniziaPartita()
    {
        System.out.println(MSG_INIZIALE);

        int numeroGiocatori = InputDati.leggiIntero(RICHIESTA_NUMERO_GIOCATORI, 1, 4);

        // LISTA GIOCATORI
        for (int i = 0; i < numeroGiocatori; i++)
        {
            Giocatore g = new Giocatore(InputDati.leggiStringaNonVuota(RICHIESTA_NOME));
            giocatori.add(g);
        }

        // VIENE SCELTO ORDINE DI PARTENZA
        Collections.shuffle(giocatori);
        for (Giocatore g: giocatori) {
            //pesca 7 carte dal mazzo
            g.init(mazzo);
        }

        while(!isFinita())
        {
        	Carta cartaEstratta = new Carta();
        	
        	for (Giocatore g : giocatori) 
            {
                if (mazzo.size() == 0)
                    mazzo.rigeneraMazzo(carteScartate);
               
                g.isMyTurn = true;
                System.out.println("Ora è il turno del giocatore: " + g.getNome());
               
    			mazzo.mischiaMazzo();
    			cartaEstratta = mazzo.pesca();                
    			
    			while (g.isMyTurn)
    			{
                    //stampa le mosse possibili poi numerale e con uno switch scegli cosa fare
//    				g.scartaCarta(v, cartaEstratta);
//    				if (cartaGiocatore != null)
//    					cartaEstratta = cartaGiocatore;
//                }
                
	
	    			if (InputDati.yesOrNo("\nVuoi vedere la situazione dei giocatori? ")) {
	    				for (int i = 0; i < giocatori.size(); i++)
	    					System.out.println(giocatori.get(i).toString());
	    			}
    			}
            } // for
        } // while
        
        stampaClassifica(giocatori);
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
	
	
	/**
	 * METODO isFinita
	 * Determina se continuare il gioco oppure no. Termina quando un giocatore ha finito le sue carte.
	 * @param elencoGiocatori
	 * @return true se è finita.
	 */
	private boolean isFinita()
    {
        for (Giocatore giocatore : giocatori) 
        {
            if (giocatore.numeroCarte() == 0) 
            {
                System.out.println(String.format(FINE_PARTITA, giocatore.getNome()));
                return true;
            }
        }
        return false;
    }
	
	
	private void stampaClassifica (ArrayList<Giocatore> elencoGiocatori) 
	{
		System.out.println("\nECCO LA CLASSIFICA FINALE");
		Map<Integer, Giocatore> classifica = new TreeMap<Integer, Giocatore>();
		
		for (int i = 0; i < elencoGiocatori.size(); i++)
			classifica.put(elencoGiocatori.get(i).numeroCarte(), elencoGiocatori.get(i));
		
		for(Integer key : classifica.keySet()) {
			System.out.println(classifica.get(key));
		}
	}
	
	
}
