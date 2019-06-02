package tcpGUI;

import tcpServer.Client;
import tcpServer.operations.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;

public class ClientGUI extends JFrame implements TestRedirect.Consumer {

    public static JFrame frame;
    private static AddCardGUI cardGUI;
    private static Client client;
    private JPanel rootPanel;
    private JPanel buttonPanel;
    private JButton addCardButton;
    private JButton stopButton;
    private JButton payMoneyButton;
    private JButton showCardButton;
    private JButton removeCardButton;
    private JButton addMoneyButton;
    private JTextArea showClient;
    private JTextField addCField;
    private JTextField removeField;
    private JTextField addMField_money;
    private JTextField addMField_id;
    private JTextField payField_money;
    private JTextField payField_id;
    private JButton exitButton;
    private JButton startButton;
    private JTextField server;
    private JTextField port;
    private JButton addFullCardButton;
    private JScrollPane scroll;
    private JPanel scrollPanel;


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void inputError(String message) {
        JOptionPane.showMessageDialog(ClientGUI.this, message);
    }

    public static void setFullCard(AddMetroCardOperation op) {
        client.applyOperation(op);
    }

    public ClientGUI() {
        PrintStream ps = System.out;
        System.setOut(new PrintStream(
                new TestRedirect.StreamCapturer("STDOUT", this, ps)));

        /**
        *
        * MOUSE LISTENERS for TestFields*/

        addCField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addCField.setText("");
            }
        });
        removeField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removeField.setText("");
            }
        });
        addMField_money.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addMField_money.setText("");
            }
        });
        addMField_id.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addMField_id.setText("");
            }
        });
        payField_money.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                payField_money.setText("");
            }
        });
        payField_id.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                payField_id.setText("");
            }
        });

        /**
         *
         * Action Listeners for Buttons
         */
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(server.getText().equals(""))) {
                    if (!(port.getText().equals(""))) {
                        client = new Client(server.getText(), Integer.parseInt(port.getText()));
                    } else {
                        inputError("Please, Input PORT.");
                        port.setBackground(Color.PINK);
                    }
                } else {
                    inputError("Please, Input SERVER.");
                    server.setBackground(Color.PINK);
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client != null) {
                    new StopOperation();
                    client.finish();
                } else System.out.println("Please, Start The Client.");
            }
        });

        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: checking the condition serNum == int
                if (client != null) {
                    if (!(addCField.getText().equals("")) || !(addCField.getText().equals("id card"))) {
                        AddMetroCardOperation op = new AddMetroCardOperation(addCField.getText());
                        client.applyOperation(op);
                    } else {
                        inputError("Please, Input Serial Number Of The Card.");
                        addCField.setBackground(Color.PINK);
                    }
                } else System.out.println("Please, Start The Client.");
            }
        });

        removeCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client != null) {
                    if (!(removeField.getText().equals(""))
                            || !(removeField.getText().equals("id card"))) {

                        RemoveCardOperation op = new RemoveCardOperation(removeField.getText());
                        client.applyOperation(op);
                    } else {
                        inputError("Please, Input Serial Number Of The Card.");
                        removeField.setBackground(Color.PINK);
                    }
                } else System.out.println("Please, Start The Client.");
            }
        });

        showCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowCardsOperation op = new ShowCardsOperation();
                client.applyOperation(op);
            }
        });

        addMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client != null) {
                    if (!(addMField_id.getText().equals(""))
                            || !(addMField_id.getText().equals("id card"))) {
                        if (!(addMField_money.getText().equals(""))
                                || !(addMField_money.getText().equals("balance"))) {
                            Double m;
                            try{
                                m = Double.valueOf(addMField_money.getText());
                            } catch (NumberFormatException nex) {
                                System.out.println("Incorrect Money Field.");
                                m = 0.0;
                            }
                            AddMoneyOperation op = new AddMoneyOperation(addMField_id.getText(), m);
                            client.applyOperation(op);
                        } else {
                            inputError("Please, Input Amount Of The Money.");
                            addMField_money.setBackground(Color.PINK);
                        }
                    } else {
                        inputError("Please, Input Serial Number Of The Card.");
                        addMField_id.setBackground(Color.PINK);
                    }
                } else System.out.println("Please, Start The Client.");
            }
        });

        payMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client != null) {
                    if (!(payField_id.getText().equals(""))
                            || !(payField_id.getText().equals("id card"))) {
                        if (!(payField_money.getText().equals(""))
                                || !(payField_money.getText().equals("payment"))) {
                            Double m;
                            try {
                                m = Double.valueOf(payField_money.getText());
                            } catch (NumberFormatException nfe) {
                                System.out.println("Incorrect Payment Field.");
                                m = 0.0;
                            }
                            PayMoneyOperation op = new PayMoneyOperation(
                                    payField_id.getText(), m);
                            client.applyOperation(op);
                        } else {
                            inputError("Please, Input The Payment.");
                            payField_money.setBackground(Color.PINK);
                        }
                    } else {
                        inputError("Please, Input Serial Number Of The Card.");
                        payField_id.setBackground(Color.PINK);
                    }
                } else System.out.println("Please, Start The Client.");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        addFullCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client != null)
                    cardGUI = new AddCardGUI();
                else System.out.println("Please, Start The Client.");
            }
        });

        scrollPanel = new JPanel();
        showClient = new JTextArea();
        scrollPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        scrollPanel.setLayout(new BoxLayout(scrollPanel,1));
        scrollPanel.add(showClient);
        scroll = new JScrollPane(scrollPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rootPanel.add(scroll);
    }

    @Override
    public void appendText(String text) {
        if (EventQueue.isDispatchThread()) {
            showClient.append(text);
            showClient.setCaretPosition(showClient.getText().length());
        } else {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    appendText(text);
                }
            });

        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Client");
        ClientGUI gui = new ClientGUI();
        frame.setContentPane(gui.rootPanel);
        gui.getRootPanel().setPreferredSize(new Dimension(500, 300));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.NORMAL);
        frame.setVisible(true);
    }

}
