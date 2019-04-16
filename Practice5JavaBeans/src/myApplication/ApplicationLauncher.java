package myApplication;

import mybeans.first.DataSheetChangeEvent;
import mybeans.first.DataSheetChangeListener;
import mybeans.first.DataSheetTable;
import mybeans.second.DataSheetGraph;
import xmlpac.Data;
import xmlpac.DataHandler;
import xmlpac.DataSheet;
import xmlpac.DataSheetToXML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ApplicationLauncher extends JFrame {
    DataSheet dataSheet;
    private DataSheetTable dataTable = new DataSheetTable();
    private DataSheetGraph dataGraph = new DataSheetGraph();

    private final JFileChooser fileChooser = new JFileChooser();

    private JPanel buttonPanel = new JPanel();
    private JPanel panel = new JPanel(new BorderLayout());
    private JPanel panelComponents = new JPanel(new GridLayout(1,2,5,2));

    public ApplicationLauncher() {
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 500));
        fileChooser.setCurrentDirectory(new File("."));

        //buttons
        JButton exitButton = new JButton("Exit");
        JButton clearButton = new JButton("Clear");
        JButton saveButton = new JButton("Save");
        JButton readButton = new JButton("Read");

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO:
                DataSheet dsheet = new DataSheet();
                dsheet.setDateItem(new Data());
                dataTable.getTableModel().setDataSheet(dsheet);
                dataTable.revalidate();
                dataGraph.setDataSheet(dsheet);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                    String fileName = fileChooser.getSelectedFile().getPath();
                    DataSheetToXML.saveXMLDoc(DataSheetToXML.createDataSheetDOM(dataSheet), fileName);
                    JOptionPane.showMessageDialog(null,
                            "File " + fileName.trim() + " saved!",
                            "Результаты сохранены", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                    String fileName = fileChooser.getSelectedFile().getPath();
                    try {
                        DataHandler handler = new DataHandler();
                        handler.parsingBySAX(dataSheet, new FileInputStream(fileName));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    dataTable.getTableModel().setDataSheet(dataSheet);
                    dataGraph.setDataSheet(dataSheet);
                    dataTable.revalidate();
                }
            }
        });

        buttonPanel.add(readButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(clearButton);

//place for data
        dataSheet = new DataSheet();
        dataSheet.setDateItem(new Data());

//making components
        dataGraph.setDataSheet(dataSheet);
        dataTable.getTableModel().setDataSheet(dataSheet);

        dataTable.getTableModel().addDataSheetChangeListener(
                new DataSheetChangeListener() {
                    public void dataChanged(DataSheetChangeEvent e) {
                        dataGraph.revalidate();
                        dataGraph.repaint();
                    }
                });

        panelComponents.add(dataTable.getRootPanel());
        panelComponents.add(dataGraph);
        panel.add(panelComponents, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ApplicationLauncher();
            }
        });

    }
}
