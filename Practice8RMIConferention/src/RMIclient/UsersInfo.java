package RMIclient;

import RMIserver.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UsersInfo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea showInfo;

    public UsersInfo(ArrayList<User> participants, JFrame parent, String title, boolean bool) {
        super(parent,title,bool);
        setContentPane(contentPane);
        contentPane.setPreferredSize(new Dimension(350, 200));
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setText(toString(participants));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    private String toString(ArrayList<User> participants) {
        StringBuilder b = new StringBuilder();
        for(User u: participants)
            b.append(u.toString() + "\n");
        return b.toString();
    }

    public void setText(String s) {
        this.showInfo.append(s);
    }

    private void onOK() {
        setVisible(false);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
