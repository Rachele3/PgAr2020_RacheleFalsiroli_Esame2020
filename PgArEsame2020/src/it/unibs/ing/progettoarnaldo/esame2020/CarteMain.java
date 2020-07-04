package it.unibs.ing.progettoarnaldo.esame2020;


/**
 * Avvio del programma.
 * Inzia con un saluto inziale
 * @author Rachele
 *
 */


public class CarteMain {

	private static final String SALUTO_INIZIALE = "BENVENUTO A UNO - GI - OH!";
	private static final String SALUTO_FINALE = "\nGRAZIE E ARRIVEDERCI!";
	
	
	public static void main(String[] args) {
		
		System.out.println(SALUTO_INIZIALE);
		
		IniziaUnoGiOh gioco = new IniziaUnoGiOh();
		gioco.iniziaPartita();
		
		System.out.println(SALUTO_FINALE);

	}

}
