package it.unibs.ing.progettoarnaldo.esame2020;

import it.unibs.ing.progettoarnaldo.mylib.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class IniziaUnoGiOh {

    private Mazzo mazzo;
    private Stack<Carta> carteScartate = new Stack<Carta>();
    private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
    private int id;

    private static final String RICHIESTA_NUMERO_GIOCATORI = "Quanti giocatori vogliono giocare?\n"
            + "1. Un solo giocatore\n"
            + "2. Due giocatori\n"
            + "3. Tre giocatori\n"
            + "4. Quattro giocatori\n"
            + "Inserire opzione scelta: ";

    private static final String FINE_PARTITA = "\nIl giocatore %s ha vinto, perch� ha terminato le carte del suo mazzo.";

    private static final String MSG_INIZIALE = "\nINIZIA LA PARTITA!";
    private static final String RICHIESTA_NOME = "\nInserire nome giocatore: ";

    public IniziaUnoGiOh(){
        id = EstrazioniCasuali.estraiIntero(0,1000000);
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

        // Ogni giocatore pesca 7 carte
        for (Giocatore g: giocatori) {
            //pesca 7 carte dal mazzo
            g.init(mazzo);
        }

        // SCOMMETTIAMO SULLE CARTE
        for (Giocatore g: giocatori) {
            System.out.println("Ora è il turno del giocatore: " + g.getNome());
            int mossa = InputDati.leggiIntero(".....", 0, 1);
            if (mossa == 0){
                g.scommetti();
            }
        }
        carteScartate.push(mazzo.pesca());

        // Inizia la partita
        while(!isFinita()){
            for (Giocatore g: giocatori) {
                if (mazzo.size() == 0){
                    Carta c = carteScartate.pop();
                    mazzo.rigeneraMazzo(carteScartate);
                    carteScartate.push(c);
                }

                g.isMyTurn = true;
                System.out.println("Ora è il turno del giocatore: " + g.getNome());
                printUltimaCarta();
                g.printMano();

                int min = 0;
                while (g.isMyTurn){
                    //print mosse
                    int mossa = InputDati.leggiIntero(".....", min, 2);
                    switch (mossa){
                        case 0:
                            g.pescaCarta(mazzo);
                            g.printMano();
                            min = 1;
                            break;
                        case 1:
                            int numeroCarta = InputDati.leggiIntero(".....", 0, g.numeroCarte());
                            g.scartaCarta(numeroCarta,carteScartate);
                            break;
                        case 2:
                            g.passaIlTurno();
                            break;
                        default: throw new IndexOutOfBoundsException();
                    }
                }
                if (g.numeroCarte() == 0) break;
            }
        }

        try {
            salvaPartita();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }


    private void printUltimaCarta(){
        System.out.println("----------------- ULTIMA CARTA GIOCATA ------------------");
        System.out.println(carteScartate.peek().toString());
        System.out.println("---------------------------------------------------------");
    }

    private void salvaPartita() throws ParserConfigurationException, IOException, SAXException {

        String filepath = "src/main/java/resources/Statistiche.xml";

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(filepath);
        Node root = doc.getFirstChild();

        Element partita = doc.createElement("Partita");
        root.appendChild(partita);
        Attr attr = doc.createAttribute("id");
        attr.setValue("" + id);
        partita.setAttributeNode(attr);
        for (Giocatore g:giocatori) {
            Element numeroCarte = doc.createElement("NumeroCarte");
            partita.appendChild(numeroCarte);
            Attr nome = doc.createAttribute("nomeGiocatore");
            attr.setValue(g.getNome());
            numeroCarte.setAttributeNode(nome);
            numeroCarte.appendChild(doc.createTextNode("" + g.numeroCarte()));
        }
    }

    private boolean isFinita()
    {
        for (Giocatore giocatore : giocatori) {
            if (giocatore.numeroCarte() == 0) {
                System.out.println(String.format(FINE_PARTITA, giocatore.getNome()));
                return true;
            }
        }
        return false;
    }
}
