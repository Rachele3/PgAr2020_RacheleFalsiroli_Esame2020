package it.unibs.ing.progettoarnaldo.esame2020;

import java.util.ArrayList;
import java.util.Stack;

public class Giocatore {

    private static final String DESCRIZIONE = "\nIl giocatore %s ha ancora %d carte nel suo mazzo\n";

    private final String nome;
    private ArrayList<Carta> mieCarte = new ArrayList<>();
    public boolean isMyTurn;

    public Giocatore(String nome) {
        this.nome = nome;
        isMyTurn = false;
    }

    /**
     * METODO creaMioMazzo.
     * Ad inizio parita il giocatore pesca 7 carte dal mazzo
     */
    public void init(Mazzo mazzo)
    {
        for (int i = 0; i < 7; i++) {
            mieCarte.add(mazzo.pesca());
        }
    }


    public void scartaCarta(int v, Stack<Carta> ultimaCarta)
    {
        Carta c = mieCarte.get(v);

        // Scarto carta se � dello stesso colore oppure ha uguale numero della carta sulla pila delle carte scartate
        if (c.getColore().equals(ultimaCarta.peek().getColore())  ||  c.getNumero() == ultimaCarta.peek().getNumero()) {
            System.out.println("Hai scartato: " + c.toString());
            mieCarte.remove(c);
            ultimaCarta.push(c);
            isMyTurn = false;
        } else {
            System.out.println("Questa carta non può essere scartata. Numero o colore errato.");
        }
    }

    public void pescaCarta(Mazzo mazzo){
        mieCarte.add(mazzo.pesca());
    }

    public void passaIlTurno(){
        isMyTurn = false;
    }

    public void printMano(){
        System.out.println("----------------- CARTE IN MANO ------------------");
        for (int i = 0; i < mieCarte.size(); i++) {
            System.out.println(i + ". " + mieCarte.get(i).toString());
        }
        System.out.println("--------------------------------------------------");
    }

    //TODO
    public void scommetti(){}

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
