package Task2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DataHandler extends DefaultHandler {

    private boolean isX, isY;
    private double sumX, sumY, sumX2, sumXY, t;
    private static double k;
    private static double b;
    private int num;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document Parsing Process ...");
        sumX = 0; sumY = 0; sumX2 = 0; sumXY = 0; t = 0; num = 0;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document Parsing Process ...");
        num /= 2;
        k = (sumXY - sumX * sumY / num) / (sumX2 - sumX * sumX / num);
        b = sumY / num - k * sumX / num;
        System.out.println("k: " + k + "\t" + "b: " + b);
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes attrs) throws SAXException {
        System.out.println("Начало обработки элемента: " + qName);
        if (qName.equals("x")) {
            isX = true;
        } else if (qName.equals("y")) { isY = true;
        }
        if (attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++)
                System.out.println("\t" + attrs.getLocalName(i) + ": " +
                        attrs.getValue(i));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Конец обработки элемента: " + qName);
        if (qName.equals("x")) {
            isX = false; num += 1;
        } else if (qName.equals("y")) {
            isY = false;
            t = 0;
            num += 1;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (str.trim().length() > 0)
            System.out.println("\tЗначение: " + str);
        double tmp = 0;
        if (isX) {
            tmp = Double.parseDouble(str); sumX += tmp;
            sumX2 += tmp*tmp; t = tmp;
        } else if (isY) {
            tmp = Double.parseDouble(str); sumY += tmp;
            t = t * tmp; sumXY += t;
        }
    }

    public static double getCopyK () {
        double new_k = k;
        return new_k;
    }

    public static double getCopyB () {
        double new_b = b;
        return new_b;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        InputStream xmlInput = new FileInputStream("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task1\\data.xml");

        try {
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DataHandler();
            saxParser.parse(xmlInput, handler);
        }
        catch (SAXException e) { e.printStackTrace(); }
        catch (ParserConfigurationException e) { e.printStackTrace(); } catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

    }

}

