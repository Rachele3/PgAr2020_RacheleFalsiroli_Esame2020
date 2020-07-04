package it.unibs.ing.progettoarnaldo.esame2020;

import java.util.ArrayList;

public class Giocatore {

	private static final String DESCRIZIONE = "Il giocatore %s ha ancora %d carte nel suo mazzo";
	
	private final String nome;
    private ArrayList<Carta> mieCarte = new ArrayList<>();
    public boolean isMyTurn; // per comodità, per poterlo usare al di fuori da questa classe

    /**
     * COSTRUTTORE
     * @param nome
     */
    public Giocatore(String nome) {
        this.nome = nome.toUpperCase();
        this.isMyTurn = false;
    }

    //Si mette il vincolo che ogni giocatore abbia nome univoco.
    public int hashCode() {
    	return this.nome.hashCode();
    }
    
    
    /**
     * METODO creaMioMazzo.
     * Ad inizio parita il giocatore pesca 7 carte dal mazzo
     */
    public void init(Mazzo mazzo)
    {
        for (int i = 0; i < 7; i++)
           this.mieCarte.add(mazzo.pesca());
    }


    public void scartaCarta(int v, Carta ultimaCarta)
    {
        Carta c = this.mieCarte.get(v);

        // Scarto la carta se è dello stesso colore oppure ha uguale numero della carta sulla pila delle carte scartate
        if (c.getColore().equals(ultimaCarta.getColore())  ||  c.getNumero() == ultimaCarta.getNumero()) 
        {
        	System.out.println("\t" + this.nome + " hai scartato: " + c.toString());
            this.mieCarte.remove(c);
            this.isMyTurn = false;
        } 
        else
            System.out.println("Questa carta non può essere scartata. Numero o colore errato.");
    }

    public void scartaCartaAPiacere (int numCartaScelta) {
    	 this.mieCarte.remove(this.mieCarte.get(numCartaScelta));
    }
    
    public void pescaCarta(Mazzo mazzo) {
       this.mieCarte.add(mazzo.pesca());
    }

    public void passaIlTurno(){
        isMyTurn = false;
    }

    
    public void scommetti(){
    	
    }

    public int numeroCarte() {
        return this.mieCarte.size();
    }

    public String toString() {
        return String.format(DESCRIZIONE, this.nome.toUpperCase(), numeroCarte());
    }
    
    public void visualizzaCarte() {
    	for (int i = 0; i < numeroCarte(); i++)
    		System.out.println("0. " + this.mieCarte.get(i));
    }

    public String getNome() {
        return nome;
    }

	public ArrayList<Carta> getMieCarte() {
		return mieCarte;
	}
    
}