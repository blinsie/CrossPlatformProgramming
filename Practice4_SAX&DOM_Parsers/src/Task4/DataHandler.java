package Task4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

public class DataHandler extends DefaultHandler {

    private static DataSheet datasheet = null;
    private Data tmpData = null;
    private boolean isX, isY;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document Parsing Process ...");
        if (datasheet == null) {
        datasheet = new DataSheet();
        datasheet.setName("New DataSheet");
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\tПолучена структура данных");
        System.out.println("\t"+datasheet.toString());
        System.out.println("End Document Parsing Process ...");
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes attrs) throws SAXException {
        if (qName.equals("data")) {
            tmpData = new Data();
            if (attrs.getLength() > 0) {
                tmpData.setDate(attrs.getValue(0));
            }
        } else if (qName.equals("x")) {
            isX = true;
        } else if (qName.equals("y")) {
            isY = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("x")) {
            isX = false;
        } else if (qName.equals("y")) {
            isY = false;
        } else if (qName.equals("data")) {
            datasheet.setDate(tmpData);
            tmpData = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (isX) {
            tmpData.setX(Double.parseDouble(str));
        } else if (isY) {
            tmpData.setY(Double.parseDouble(str));
        }
    }

    public static DataSheet CopyDataSheet() {
        DataSheet new_ds = new DataSheet();
        new_ds.CopyDataSheet(DataHandler.datasheet);
        return new_ds;
    }

    public static void parsingBySAX(SAXParserFactory factory, InputStream fileName) {

        try {
            //SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DataHandler();

            saxParser.parse(fileName, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public static Schema validateXMLByXSD(InputStream xml, File xsd) throws SAXException, IOException {
        Schema schema;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory sFfactory = SchemaFactory.newInstance(language);
        schema = sFfactory.newSchema(xsd);

        Validator validator = schema.newValidator();
        Source source = new StreamSource(xml);

        try {
            validator.validate(source);
            System.out.println(xml.toString() + " is valid by validator.");
        }
        catch (SAXException ex) {
            System.out.println(xml.toString() + " is not valid by validator because ");
            System.out.println(ex.getMessage());
            return null;
        }
        return schema;
    }

    public static void main(String[] args) throws IOException, SAXException {
        SAXParserFactory factory1 = SAXParserFactory.newInstance();
        File schema = new File("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task3\\data.xsd");

        System.out.println("VALIDATION BY SCHEMA: ");

        factory1.setSchema(validateXMLByXSD(new FileInputStream("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task1\\data.xml"), schema));
        factory1.setValidating(true);
        factory1.setNamespaceAware(true);

        parsingBySAX(factory1, new FileInputStream("C:\\Users\\User_Asus\\Desktop\\Practice4\\src\\Task1\\data.xml"));

    }

}
