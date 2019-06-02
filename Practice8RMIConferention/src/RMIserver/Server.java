package RMIserver;

import RMIclient.Client;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.Permission;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Server {

    private static JFrame frame = new JFrame("Conferention_Server");
    private final JFileChooser fileChooser = new JFileChooser();
    private Conferention stub;
    private Registry registry;
    private Document document;
    private Registration registration;

    {
        try {
            registration = new Registration();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private JPanel rootPanel;
    private JTextArea showServer;
    private JButton startButton;
    private JButton exitButton;
    private JButton stopButton;
    private JTextField address;
    private JTextField port;
    private JButton saveButton;
    private JButton loadButton;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JTextField user_num;
    private JPanel scrollPanel;
    private JScrollPane scroll;
    private JButton refreshButton;

    public class ValidateThread implements Runnable {
        public void run() {
            while (true)
            constantDataUpdate();
        }
    }

    private JFrame getFrame() {
        return frame;
    }

    private Container getRootPanel() {
        return this.rootPanel;
    }

    public Server() {
//        PrintStream ps = System.out;
//        System.setOut(new PrintStream(new TestRedirect.StreamCapturer("STDOUT", this, ps)));

//        Thread thread = new Thread(new ValidateThread());
//        thread.start();

        System.setProperty("java.rmi.server.codebase", "file://codebase.jar");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager() {
                @Override
                public void checkConnect(String host, int port) {
                }

                @Override
                public void checkConnect(String host, int port, Object context) {
                }

                @Override
                public void checkPermission(Permission permission) {
                }
            });
        }

        exportObjectInRegister();
        user_num.setEditable(false);
        saveButton.setEnabled(false);
        stopButton.setEnabled(false);
        refreshButton.setEnabled(false);
        fileChooser.setCurrentDirectory(new File("."));

        address.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                address.setText("");
            }
        });
        port.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                port.setText("");
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBinding();
                address.setEditable(false);
                refreshButton.setEnabled(true);
                port.setEditable(false);
                saveButton.setEnabled(true);
                stopButton.setEnabled(true);
                showDialog("Server started successfully!");
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBinding();
                showDialog("Server successfully stopped!");
                saveButton.setEnabled(false);
                stopButton.setEnabled(false);
                startButton.setEnabled(true);
                address.setEditable(true);
                refreshButton.setEnabled(false);
                port.setEditable(true);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                    String fileName = fileChooser.getSelectedFile().getPath();
                    try {
                        document = DOMParser.transformDataToDocument(registration.getData());
                        save(document, fileName);
                        showDialog("Saved successfully!");
                    } catch (TransformerException | ParserConfigurationException | IllegalAccessException ex) {
                        showDialog("Could not save\n" + ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Could not save\n" + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                    String fileName = fileChooser.getSelectedFile().getPath();
                    try {
                        document = DOMParser.parse(fileName);
                        DOMParser.transformDocumentToData(document, registration.getData());
                    } catch (SAXException | ParserConfigurationException | IOException ex) {
                        showDialog("Could not load\n" + ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Could not load\n" + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });

        scroll.setPreferredSize(new Dimension(200,200));
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Current Info: ");
                constantDataUpdate();
            }
        });
    }

    private void showDialog(String s) {
        showServer.append(s + "\n");
        //JOptionPane.showMessageDialog(null, s);
    }

    private void constantDataUpdate() {
        showServer.append("\n" + registration.getInfo());
        user_num.setText(String.valueOf(registration.getSize()));
    }

    private void exportObjectInRegister() {
        try {
            stub = (Conferention) UnicastRemoteObject.exportObject(registration, 0);
        } catch (RemoteException e) {
            showDialog("FATAL ERROR\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createBinding() {
        try {
            if (registry == null)
                registry = LocateRegistry.createRegistry(Integer.parseInt(port.getText()));
            String name = "Registrable";
            registry.rebind(name, stub);
        } catch (RemoteException e) {
            showDialog("Could not start server\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void removeBinding() {
        try {
            Registry registry = LocateRegistry.getRegistry(Integer.parseInt(port.getText()));
            String name = "Registrable";
            registry.unbind(name);
        } catch (RemoteException | NotBoundException e) {
            showDialog("Could not stop server\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void save(Document document, String fileName) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Server gui = new Server();
                gui.getFrame().setContentPane(gui.getRootPanel());
                gui.getRootPanel().setPreferredSize(new Dimension(650, 300));
                gui.getFrame().setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                gui.getFrame().pack();
                gui.getFrame().setExtendedState(JFrame.NORMAL);
                gui.getFrame().setVisible(true);
            }
        });
    }
}
