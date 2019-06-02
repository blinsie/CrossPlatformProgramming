package Task5;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {

    public void error(SAXParseException ex) throws SAXException { System.err.println("Error:	" + ex);
        System.err.println("line = " + ex.getLineNumber() + "	col = "
                + ex.getColumnNumber());
    }
    public void fatalError(SAXParseException ex) throws SAXException { System.err.println("Fatal Error:	" + ex); System.err.println("line = " + ex.getLineNumber() + "	col = "
            + ex.getColumnNumber());
    }

    public void warning(SAXParseException ex) throws SAXException { System.err.println("Warning:	" + ex);
        System.err.println("line = " + ex.getLineNumber() + "	col = "
                + ex.getColumnNumber());
    }

}
