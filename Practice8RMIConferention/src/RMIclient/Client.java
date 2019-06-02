package RMIclient;

import RMIserver.Conferention;
import RMIserver.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

public class Client extends JFrame {
    private Conferention stub;
    private ArrayList<User> participants = new ArrayList<>();

    private static JFrame frame = new JFrame("Conferention_Client");
    private JPanel rootPanel;
    private JButton exitButton;
    private JButton sendToServerButton;
    private JButton cleanButton;
    private JButton infoButton;
    private JTextField name;
    private JTextField port;
    private JTextField address;
    private JTextField age;
    private JTextField surname;
    private JTextField users_num;

    private static JFrame getFrame() {
        return frame;
    }

    private Container getRootPanel() {
        return this.rootPanel;
    }


    public Client() {

        users_num.setEditable(false);

        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name.setText("");
            }
        });
        surname.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                surname.setText("");
            }
        });
        age.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                age.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        sendToServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (checkInput()) {
                        registerParticipant();}
                    else showDialog("Please, Input the Values.");
                } catch (RemoteException e1) {
                    showDialog("Remote Error!");
                }
            }

            private boolean checkInput() {
                return (!name.getText().isEmpty() &&
                        !surname.getText().isEmpty() &&
                        !age.getText().isEmpty());
            }
        });
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersInfo ui = new UsersInfo(participants, getFrame(), "Add Card", true);
                try {
                    if (stub != null)
                        ui.setText(stub.getInfo());
                } catch (RemoteException e1) {
                    showDialog("Connect to the Server.");
                    e1.printStackTrace();
                }
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                surname.setText("");
                age.setText("");
            }
        });
    }

    private void registerParticipant() throws RemoteException {
        getLocateRegistry();
        updateInfo();
        showDialog("Successfully!");
    }

    private void getLocateRegistry() {
        try {
            Registry registry = LocateRegistry.getRegistry(address.getText(), Integer.parseInt(port.getText()));
            String findName = "Registrable";
            stub = (Conferention) registry.lookup(findName); // Binds a remote reference to the specified name in this registry
        } catch (RemoteException | NotBoundException e) {
            showDialog("Failed to register\n" + e.getMessage());
            address.setEditable(true);
            port.setEditable(true);
            e.printStackTrace();
        }
    }

    private void showDialog(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    private void updateInfo() throws RemoteException {
        participants.add(getUserInfo());
        users_num.setText(String.valueOf(stub.register(getUserInfo())));
    }

    private User getUserInfo() {
        String name = this.name.getText();
        String surname = this.surname.getText();
        int age = Integer.parseInt(this.age.getText());
        return new User(name, surname, age);
    }

    public static void main(String[] args) {
        Client gui = new Client();
        gui.getFrame().setContentPane(gui.getRootPanel());
        gui.getRootPanel().setPreferredSize(new Dimension(700, 300));
        gui.getFrame().setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gui.getFrame().pack();
        gui.getFrame().setExtendedState(JFrame.NORMAL);
        gui.getFrame().setVisible(true);
    }

}
