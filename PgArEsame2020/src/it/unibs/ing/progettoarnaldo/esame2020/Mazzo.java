package it.unibs.ing.progettoarnaldo.esame2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import it.unibs.ing.progettoarnaldo.mylib.*;

public class Mazzo {
	
	private ArrayList<Carta> mazzoCarte;

    /**  COSTRUTTORE  */
    public Mazzo()
    {
        this.mazzoCarte = new ArrayList<Carta>();
        costruisciMazzo();
        mischiaMazzo();
    }


    /**
     * METODO costruisciMazzo.
     * Genera il mazzo ciclando sui 4 colori e costruendo le carte numerate da 0 a 9.
     */
    private void costruisciMazzo() throws IndexOutOfBoundsException
    {
        // Crea un mazzo da 80 carte con i 4 colori disponibili
        for (int n = 0; n < 2; n++) 
        {
            for (int i = 0; i < 4; i++) 
            {
                for (int j = 0; j < 10; j++) 
                {
                    switch (i) 
                    {
                        case 0:
                            mazzoCarte.add(new Carta(Colore.BLU, j));
                            break;
                            
                        case 1:
                            mazzoCarte.add(new Carta(Colore.ROSSA, j));
                            break;
                            
                        case 2:
                            mazzoCarte.add(new Carta(Colore.GIALLA, j));
                            break;
                            
                        case 3:
                            mazzoCarte.add(new Carta(Colore.VERDE, j));
                            break;
                            
                        default: 
                        	throw new IndexOutOfBoundsException();
                    } // switch
                } // for j
            } // for i
        } // for n
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
    public Carta pesca()
    {
        int estratto = EstrazioniCasuali.estraiIntero(0, mazzoCarte.size());
        return mazzoCarte.remove(estratto);
    }

    public void rigeneraMazzo(Stack<Carta> scarti)
    {
        while(scarti.empty())
            mazzoCarte.add(scarti.pop());
   
        mischiaMazzo();
    }

    public int size(){
        return mazzoCarte.size();
    }

    public ArrayList<Carta> getMazzo_di_carte() {
        return mazzoCarte;
    }
	
}