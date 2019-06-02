package tcpGUI;

import tcpServer.operations.AddMetroCardOperation;
import tcpWork.MetroCard;
import tcpWork.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static tcpGUI.ClientGUI.setFullCard;

public class AddCardGUI extends JFrame {

    private JDialog dialog;
    private AddMetroCardOperation op = new AddMetroCardOperation();
    private MetroCard metroCard = new MetroCard();
    private User user;
    private JTextArea IDNumberTextArea;
    private JButton addCardButton;
    private JTextField idField;
    private JTextField collegeField;
    private JTextField balanceField;
    private JTextField userNameField;
    private JTextField userSurnameField;
    private JTextField userSexField;
    private JTextField userBirthField;
    private JPanel rootPanel;
    private JLabel balanceLabel;
    private JLabel collageLable;
    private JLabel idNumberLable;
    private JLabel nameLable;
    private JLabel surnameLable;
    private JLabel sexLable;
    private JLabel birthLable;
    private JLabel userLable;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void inputError(String message) {
        JOptionPane.showMessageDialog(AddCardGUI.this,
                message);
    }

    public boolean checkInput() {
        if (balanceField.getText().isEmpty()) {
            StringBuffer sb = new StringBuffer();
            sb.append("Please, pull the field ");
            sb.append("Balance");
            balanceField.setBackground(Color.PINK);
            if (idField.getText().isEmpty()) {
                sb.append(" and the field ID Number");
                idField.setBackground(Color.PINK);
            }
            sb.append("!");
            inputError(sb.toString());
            return false;
        }
        return true;
    }

    public AddCardGUI() {

        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkInput()) {
                    user = new User(userNameField.getText(), userSurnameField.getText(), userSexField.getText(),
                            userBirthField.getText());

                    metroCard = new MetroCard(user, idField.getText(),
                            collegeField.getText(), Double.valueOf(balanceField.getText()));

                    op = new AddMetroCardOperation(metroCard);

                    setFullCard(op);
                    dialog.setVisible(false);
                }
            }
        });

        dialog = new JDialog(ClientGUI.frame, "Add Card", true);
        dialog.setContentPane(rootPanel);
        getRootPanel().setPreferredSize(new Dimension(350, 200));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

}
