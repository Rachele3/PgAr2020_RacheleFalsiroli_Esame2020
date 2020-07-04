package it.unibs.ing.progettoarnaldo.esame2020;

import it.unibs.ing.progettoarnaldo.mylib.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Mazzo {

    private ArrayList<Carta> mazzo_di_carte;

    /**  COSTRUTTORE  */
    public Mazzo()
    {
        this.mazzo_di_carte = new ArrayList<>();
        costruisciMazzo();
        mischiaMazzo();
    }


    /**
     * METODO costruisciMazzo.
     * Genera il mazzo ciclando sui 4 colori e costruendo le carte numerate da 0 a 9.
     */
    private void costruisciMazzo()
    {
        //Crea un mazzo da 80 carte con i 4 colori disponibili
        for (int n = 0; n < 2; n++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    switch (i) {
                        case 0:
                            mazzo_di_carte.add(new Carta(Colore.BLU, j));
                            break;
                        case 1:
                            mazzo_di_carte.add(new Carta(Colore.ROSSE, j));
                            break;
                        case 2:
                            mazzo_di_carte.add(new Carta(Colore.GIALLE, j));
                            break;
                        case 3:
                            mazzo_di_carte.add(new Carta(Colore.VERDI, j));
                            break;
                        default: throw new IndexOutOfBoundsException();
                    }
                }
            }
        }

    }


    /**  Mischia il mazzo di carte  */
    public void mischiaMazzo() {
        Collections.shuffle(mazzo_di_carte);
    }


    /**
     * METODO estrai.
     * Si occupa di estrarre una carta casualmente.
     *
     * @return Carta estratta
     */
    public Carta pesca()
    {
        int estratto = EstrazioniCasuali.estraiIntero(0, mazzo_di_carte.size());
        return mazzo_di_carte.remove(estratto);
    }

    public void rigeneraMazzo(Stack<Carta> scarti){
        while(scarti.empty()){
            mazzo_di_carte.add(scarti.pop());
        }
        mischiaMazzo();
    }

    public int size(){
        return mazzo_di_carte.size();
    }

}
