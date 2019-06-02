package xmlpac;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DataSheetToXML {

    public static void saveXMLDoc(Document doc, String fileName) {
// write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING,
                    "Windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource((Node) doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static Document createDataSheetDOM(DataSheet dataSheet) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = docBuilder.newDocument();
// root element DataTable
            Element rootElement = doc.createElement("datasheet");
            doc.appendChild(rootElement);

// datapoint elements
            for (int i = 0; i < dataSheet.size(); i++) {
                Element dpoint = doc.createElement("data");
                rootElement.appendChild(dpoint);

                Attr at = doc.createAttribute("date");
                at.setValue(dataSheet.getDateItem(i).getDate());
                dpoint.setAttributeNode(at);

                Element x = doc.createElement("x");
                Element y = doc.createElement("y");

                x.setTextContent(String.valueOf(dataSheet.getDateItem(i).getX()));
                y.setTextContent(String.valueOf(dataSheet.getDateItem(i).getY()));

                dpoint.appendChild(x);
                dpoint.appendChild(y);
            }

            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
