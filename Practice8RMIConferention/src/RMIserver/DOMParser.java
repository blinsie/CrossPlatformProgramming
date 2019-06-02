package RMIserver;

import RMIserver.User;
import RMIserver.UserHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class DOMParser {

    public static Document parse(String nameFile) throws SAXException, ParserConfigurationException, IOException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
        Schema schema = schemaFactory.newSchema(new File("src/xml/schema.xsd"));

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setSchema(schema);
        builderFactory.setValidating(false);
        builderFactory.setNamespaceAware(true);
        builderFactory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

        return documentBuilder.parse(new File(nameFile));
    }

    public static void transformDocumentToData(Document document, UserHandler dataParticipant) throws RemoteException {
        dataParticipant.clear();
        Element rootElement = document.getDocumentElement();
        NodeList nodes = rootElement.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element participant = (Element) nodes.item(i);

            Element name = (Element) participant.getFirstChild();
            String strName = ((Text) name.getFirstChild()).getData().trim();

            Element surname = (Element) name.getNextSibling();
            String strSurname = ((Text) surname.getFirstChild()).getData().trim();

            Element organization = (Element) surname.getNextSibling();
            int strOrganization = Integer.parseInt(((Text) organization.getFirstChild()).getData().trim());

            dataParticipant.addUser(new User(strName, strSurname, strOrganization));
        }
    }

    public static Document transformDataToDocument(UserHandler dataParticipant) throws ParserConfigurationException, IllegalAccessException {
        Document document = createDocument();
        ArrayList<User> participants = dataParticipant.getUsers();

        Element rootElement = document.createElement("RegisteredConferees");
        for (User participant : participants) {
            Element conferee = document.createElement("Participant");

            Class cls = participant.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    Element element = document.createElement(field.getName());
                    element.setTextContent(field.get(participant).toString());
                    conferee.appendChild(element);
                }
            }
            rootElement.appendChild(conferee);
        }
        document.appendChild(rootElement);
        return document;
    }

    private static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.newDocument();
    }

}
