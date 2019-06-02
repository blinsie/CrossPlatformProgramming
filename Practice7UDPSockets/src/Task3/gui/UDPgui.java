package Task3.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPgui extends JFrame {
    private Messanger messanger = null;

    private JPanel rootPanel;
    private JButton connectButton;
    private JButton stopButton;
    private JButton reconnectButton;
    private JButton clearButton;
    private JTextArea textArea;
    private JTextField textFieldMsg;
    private JButton sendButton;
    private JTextField address;
    private JTextField port;
    private JTextField name;
    private JPanel buttonPanel;
    private JPanel serverInfoPanel;
    private JPanel textAreaPanel;
    private JScrollPane scroll;
    private JPanel messagePanel;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private class UITasksImpl implements UITasks {
        @Override
        public String getMessage() {
            String res = textFieldMsg.getText();
            textFieldMsg.setText("");
            return res;
        }

        @Override
        public void setText(String txt) {
            textArea.append(txt + "\n");
        }
    }

    public UDPgui() {

        /**
         * MouseListeners for JTextFields
         */
        address.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                address.setText("");
            }
        });
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name.setText("");
            }
        });
        port.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                port.setText("");
            }
        });
        textFieldMsg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textFieldMsg.setText("");
            }
        });

        /**
         * Adding actions to the buttons
         */
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UITasks ui = (UITasks) Proxy.newProxyInstance(getClass().getClassLoader(),
                        new Class[]{UITasks.class},
                        new EDTInvocationHandler(new UITasksImpl()));

                try {
                    messanger = new MessanderImpl(InetAddress.getByName(
                            address.getText()), Integer.parseInt(port.getText()),
                            name.getText(), ui);
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
                messanger.start();
            }
        });
        reconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messanger.stop();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messanger.send();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UDP");
        UDPgui gui = new UDPgui();
        frame.setContentPane(gui.getRootPanel());
        gui.getRootPanel().setPreferredSize(new Dimension(500, 300));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.NORMAL);
        frame.setVisible(true);

    }

}
