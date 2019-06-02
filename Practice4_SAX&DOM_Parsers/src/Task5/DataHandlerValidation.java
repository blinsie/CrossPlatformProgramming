package Task5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataHandlerValidation {

    private static void stepThrough(Node start) {
        if (start instanceof Element) {
            System.out.println(((Element) start).getTagName() + " = " + start.getNodeValue() + "(" + start.getTextContent() + ")");
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

    public static void main(String[] args) throws SAXException, IOException, TransformerException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        File xmlFile = new File("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task1\\data.xml");
        Document doc = null;

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            ErrorHandler handler = new MyErrorHandler();
            db.setErrorHandler(handler);
            doc = db.parse(xmlFile);
        } catch (
                ParserConfigurationException e) {
            e.printStackTrace();
        } catch (
                SAXException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        Schema schema = null;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        schema = factory.newSchema(new File("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task3\\data.xsd"));

        Validator validator = schema.newValidator();
        validator.validate(new DOMSource(doc));

        System.out.println(" WITH SET ELEMENT\n");
        DataSheet ds = new DataSheet(doc);
        ds.setX(0, 1.2);
        Element new_el = ds.newElement("30.03.2019", 8.8, 8.88);
        ds.addElement(new_el);

        Element root = doc.getDocumentElement();
        stepThrough(root);

        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        File newXMLFile = new File("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task5\\outFile.xml");
        FileOutputStream fos = new FileOutputStream(newXMLFile);
        StreamResult result = new StreamResult(fos);
        transformer.transform(source, result);

    }
}
