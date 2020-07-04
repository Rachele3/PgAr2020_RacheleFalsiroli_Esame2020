package it.unibs.ing.progettoarnaldo.esame2020;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Statistica {

    private static final String path = "src/main/java/resources/Statistiche.xml";

    public void printStatVittorie() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        HashMap<String,Integer> classifica = new HashMap<String,Integer>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(path);
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//Statistiche/Partita/NumeroCarte[@nomeGiocatore]");
        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nl.getLength(); i++)
        {
            Node currentItem = nl.item(i);
            String nome = currentItem.getAttributes().getNamedItem("nomeGiocatore").getNodeValue();
            int nCarte;
            nCarte = Integer.parseInt(currentItem.getFirstChild().getNodeValue());
            if(classifica.containsKey(nome)){
                if (nCarte == 0){
                    int vittorie = classifica.get(nome) + 1;
                    classifica.put(nome,vittorie);
                }
            } else {
                if (nCarte == 0){
                    classifica.put(nome,1);
                } else {
                    classifica.put(nome,0);
                }
            }
        }

        Map<String, Integer> sorted;

        sorted = classifica.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println("Classifica per numero di vittorie: " + sorted);
    }

    public void printStatMediaCarte() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        HashMap<String,Float> classifica = new HashMap<String,Float>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(path);
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//Statistiche/Partita/NumeroCarte[@nomeGiocatore]");
        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nl.getLength(); i++)
        {
            Node currentItem = nl.item(i);
            String nome = currentItem.getAttributes().getNamedItem("nomeGiocatore").getNodeValue();
            float nCarte = Integer.parseInt(currentItem.getFirstChild().getNodeValue());
            if(classifica.containsKey(nome)){
                float tmp = classifica.get(nome);
                classifica.put(nome,tmp + nCarte);
            } else {
                classifica.put(nome,nCarte);
            }
        }

        expr = xpath.compile("//Statistiche/Partita");
        nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        int numeroPartite = nl.getLength();

        classifica.replaceAll((k, v) -> classifica.get(k) / numeroPartite);

        Map<String, Float> sorted;

        sorted = classifica.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));

        System.out.println("Classifica per media di carte in mano: " + sorted);
    }

    public void printStatPartita(String id) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException  {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(path);
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//Statistiche/Partita[@id]");
        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nl.getLength(); i++) {
            Node currentItem = nl.item(i);
            String find = currentItem.getAttributes().getNamedItem("id").getNodeValue();
            if(id.equals(find)){
                System.out.println("Partita con codice " + find);
                NodeList giocatori = currentItem.getChildNodes();
                for (int j = 0; j < giocatori.getLength(); j++) {
                    Node g = giocatori.item(j);
                    if(g.getNodeType() == Node.ELEMENT_NODE) {
                        String nome = g.getAttributes().getNamedItem("nomeGiocatore").getNodeValue();
                        int nCarte = Integer.parseInt(g.getFirstChild().getNodeValue());
                        System.out.println("-----------------------------");
                        System.out.println("Nome giocatore: " + nome);
                        System.out.println("Numero carte a fine partita: " + nCarte);
                        System.out.println("-----------------------------");
                    }
                }
                break;
            }
        }
    }

    public void printPartite() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException  {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(path);
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//Statistiche/Partita[@id]");
        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        System.out.println("------------- PARTITE TROVATE ------------");
        for (int i = 0; i < nl.getLength(); i++) {
            Node currentItem = nl.item(i);
            String find = currentItem.getAttributes().getNamedItem("id").getNodeValue();
            System.out.println(i + ". code = " + find);
        }
        System.out.println("------------------------------------------\n");
    }
}
