package Task6;

import Task4.DataSheet;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static Task2.DataHandler.getCopyB;
import static Task2.DataHandler.getCopyK;
import static Task4.DataHandler.CopyDataSheet;
import static Task4.DataHandler.parsingBySAX;

public class DocCreater {

    public static void main(String[] args) throws FileNotFoundException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        parsingBySAX(factory, new FileInputStream("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task1\\data.xml"));

        DataSheet datasheet = CopyDataSheet();
        datasheet.setName("DataSheet For DocCreator");
        //System.out.println(datasheet.toString());

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

// root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Analyser");
            doc.appendChild(rootElement);

// DataTable element (Analyser)
            Element dtable = doc.createElement("DataTable");
            rootElement.appendChild(dtable);
// line element (Analyser)
            Element line = doc.createElement("line");
            rootElement.appendChild(line);
//attrl for line
            Attr attrl = doc.createAttribute("b");
            attrl.setValue(String.valueOf(getCopyB()));
            line.setAttributeNode(attrl);
            attrl = doc.createAttribute("k");
            attrl.setValue(String.valueOf(getCopyK()));
            line.setAttributeNode(attrl);

// datapoint elements
            for (int i = 0; i < datasheet.getSize(); i++) {
                Element dpoint = doc.createElement("datapoint");
                dtable.appendChild(dpoint);

                Attr at = doc.createAttribute("date");
                at.setValue(datasheet.getDate(i).getDate());
                dpoint.setAttributeNode(at);

                Element x = doc.createElement("x");
                Element y = doc.createElement("y");

                x.setTextContent(String.valueOf(datasheet.getDate(i).getX()));
                y.setTextContent(String.valueOf(datasheet.getDate(i).getY()));

                dpoint.appendChild(x);
                dpoint.appendChild(y);
            }

// write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING,
                    "Windows-1251"); transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task6\\outFileSax.xml"));

// Output to console for testing
            //StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) { pce.printStackTrace();
        } catch (TransformerException tfe) { tfe.printStackTrace();
        }
    }
}
