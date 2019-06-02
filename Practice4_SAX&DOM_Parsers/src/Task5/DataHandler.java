package Task5;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DataHandler {

    public static void getSelectInfa(Document doc) {
        NodeList nl1 = doc.getDocumentElement().getElementsByTagName("x");
        NodeList nl2 = doc.getDocumentElement().getElementsByTagName("y");
        if (nl1.getLength() == nl2.getLength()) {
            for (int i = 0; i < nl1.getLength(); i++) {
                System.out.println(nl1.item(i).getNodeName() + " "
                        + nl1.item(i).getTextContent() + "\t"
                        + nl2.item(i).getNodeName() + " "
                        + nl2.item(i).getTextContent());
            }
        }
    }

    private static void stepThrough(Node start) {
        if (start instanceof Element) {
            System.out.println(((Element) start).getTagName() + " = " + start.getNodeValue());
            NamedNodeMap startAttr = start.getAttributes();
            for (int i = 0; i < startAttr.getLength(); i++) {
                Node attr = startAttr.item(i);
                System.out.println("Attribute: " + attr.getNodeName() + " = "
                        + attr.getNodeValue());
            }
        }
        for (Node child = start.getFirstChild();
             child != null;
             child = child.getNextSibling()) {
            stepThrough(child);
        }
    }


    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        File xmlFile = new File("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task1\\data.xml");
        Document doc = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getSelectInfa(doc);

        Element rootElement = doc.getDocumentElement();
        System.out.println(rootElement.toString());

        NodeList nodes = rootElement.getChildNodes();
        System.out.println("Root element: " + rootElement.getNodeName());
        // список имен дочерних элементов и их содержимого
        System.out.println("Child elements: ");
        stepThrough(rootElement);


        System.out.println("SET ELEMENT");
        DataSheet ds = new DataSheet(doc);
        ds.setX(0, 1.2);
        Element new_el = ds.newElement("30.03.2019", 8.8, 8.88);
        ds.addElement(new_el);

        System.out.println(ds.getX(0));

        System.out.println("\nПолученное дерево объектов NODES:\n");

        for(int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node instanceof Element) {
                Element child = (Element) node;
                //String attribute = child.getAttribute("width");
                NodeList x_elements = ((Element) node).getElementsByTagName("x");
                //System.out.println("X_ELEMENTS:   " + x_elements.item(i));
                System.out.println(child.toString());
                stepThrough(child);
            }
        }

    }

}
