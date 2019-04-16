package Task1;

import javax.swing.*;
import java.awt.*;

import static Task1.MyClass.ClassAnalysis;

public class MyFrame extends JFrame {

    MyFrame() {
        super("Анализатор класса");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,600);
        GridBagConstraints c = new GridBagConstraints();

        JPanel panel = new JPanel(new GridLayout(3,0));
        JPanel panel1 = new JPanel(new GridLayout(1,3, 0, 50));

        JButton analysis = new JButton("Анализ");

        JButton clean = new JButton("Очистить");
        JButton exit = new JButton("Завершить");
        panel1.add(analysis);
        panel1.add(clean);
        panel1.add(exit);

        JPanel panel2 = new JPanel(new GridLayout(1,0));

        JLabel label = new JLabel("Полное имя класса: ");
        JTextField textField = new JTextField(20);
        panel2.add(label);
        panel2.add(textField);

        JPanel panel3 = new JPanel(new GridLayout());

        JTextArea textArea = new JTextArea(10, 15 );
        JScrollPane scroll = new JScrollPane(textArea);
        panel3.add(scroll);

        textField.addActionListener(e -> JOptionPane.showMessageDialog(MyFrame.this,
                            "Ваш класс: " + textField.getText()));

        analysis.addActionListener(e -> textArea.setText(ClassAnalysis(textField.getText())));

        clean.addActionListener(e -> textArea.setText(""));

        exit.addActionListener(e -> System.exit(0));

        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel1);

        setContentPane(panel);
        setSize(650,430);
    }

}
