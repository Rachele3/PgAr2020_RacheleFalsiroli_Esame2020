package it.unibs.ing.progettoarnaldo.esame2020;

public enum Colore {

	 BLU(0), ROSSA(1), GIALLA(2), VERDE(3);

    private final int value;
   
    Colore(int value) {
    	this.value = value;
    }

    public int getValue() {
    	return value;
    }

}
