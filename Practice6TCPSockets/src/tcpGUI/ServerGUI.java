package tcpGUI;

import tcpServer.MetroServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

public class ServerGUI extends JFrame implements TestRedirect.Consumer {
    private JPanel rootPanel;
    private JPanel scrollPanel;
    private JScrollPane scroll;
    private JTextArea showServer;
    private JButton startButton;
    private JButton exitButton;
    private JButton stopButton;
    private JPanel buttonPanel;
    private MetroServer srv;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public ServerGUI() {
        PrintStream ps = System.out;
        System.setOut(new PrintStream(new TestRedirect.StreamCapturer("STDOUT", this, ps)));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srv = new MetroServer(8081);
                srv.start();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (srv != null) {
                    try {
                        srv.getServSock().close();
                        System.out.println("Stopping server...");
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex);
                    }
                }
            }
        });

        scrollPanel = new JPanel();
        showServer = new JTextArea();
        scrollPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        scrollPanel.setLayout(new BoxLayout(scrollPanel,1));
        scrollPanel.add(showServer);
        scroll = new JScrollPane(scrollPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rootPanel.add(scroll, BorderLayout.CENTER);
        rootPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server");
        ServerGUI gui = new ServerGUI();
        frame.setContentPane(gui.rootPanel);
        gui.getRootPanel().setPreferredSize(new Dimension(500, 300));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.NORMAL);
        frame.setVisible(true);
    }

    @Override
    public void appendText(String text) {
        if (EventQueue.isDispatchThread()) {
            showServer.append(text);
            showServer.setCaretPosition(showServer.getText().length());
        } else {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    appendText(text);
                }
            });

        }
    }
}
