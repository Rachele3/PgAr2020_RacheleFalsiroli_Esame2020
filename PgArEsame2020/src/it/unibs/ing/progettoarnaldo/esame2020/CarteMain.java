package it.unibs.ing.progettoarnaldo.esame2020;

import it.unibs.ing.progettoarnaldo.mylib.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * INIZIO PROGRAMMA
 * @author Rachele
 *
 */
public class CarteMain {

    private static final String SALUTO_INIZIALE = "BENVENUTO A UNO - GI - OH!";
    private static final String SALUTO_FINALE = "\nGRAZIE E ARRIVEDERCI!";

    private static final String RICHIESTA_INI = "Cosa vuoi fare?\n"
            + "1. Gioca nuova partita\n"
            + "2. Vedi statistiche\n"
            + "3. Esci\n";

    public static void main(String[] args) {
        boolean exit = false;

        System.out.println(SALUTO_INIZIALE);
        while(!exit) {
            int scelta = InputDati.leggiIntero(RICHIESTA_INI, 1, 3);
            switch (scelta) {
                case 1:
                    IniziaUnoGiOh gioco = new IniziaUnoGiOh();
                    gioco.iniziaPartita();
                    break;
                case 2:
                    Statistica s = new Statistica();
                    try {
                        s.printPartite();
                        s.printStatMediaCarte();
                        s.printStatVittorie();
                        String code = InputDati.leggiStringa("Digita il codice di una partita per visionarla o E per tornare indietro\n");
                        while(!code.equals("E")){
                            s.printStatPartita(code);
                            code = InputDati.leggiStringa("Digita il codice di una partita per visionarla o E per tornare indietro\n");
                        }
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (XPathExpressionException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    exit = true;
            }
        }

        System.out.println(SALUTO_FINALE);

    }
}
