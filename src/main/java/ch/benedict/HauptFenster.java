package ch.benedict;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HauptFenster extends JFrame {
    private PersonenStorage storage;
    private final Controller controller;
    private JLabel titleLabel;
    private JLabel vollerNameLabel;
    private JButton zurueckButton;
    private JButton vorwaertsButton;
    private JButton bearbeitenButton;
    private JButton neuButton;
    private JPanel hauptPanel;
    private JPanel buttonPanel;

    public HauptFenster(PersonenStorage storage, final Controller controller) {
        super("HauptFenster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        this.storage = storage;
        this.controller = controller;

        getContentPane().setLayout(new BorderLayout(20, 20));

        hauptPanel = new JPanel();
        hauptPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 20, 20));

        titleLabel = new JLabel("Person:");
        vollerNameLabel = new JLabel("VollerName");
        zurueckButton = new JButton("<");
        vorwaertsButton = new JButton(">");
        bearbeitenButton = new JButton("Bearbeiten");
        neuButton = new JButton("Neu");

        buttonPanel.add(zurueckButton);
        buttonPanel.add(vorwaertsButton);
        buttonPanel.add(new JLabel()); // added as separator
        buttonPanel.add(bearbeitenButton);
        buttonPanel.add(neuButton);

        hauptPanel.add(titleLabel);
        hauptPanel.add(vollerNameLabel);
        hauptPanel.add(buttonPanel);

        add(hauptPanel);

        pack();

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Zurueck Button gedrueckt");
            }
        });

        vorwaertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Vorwaerts Button gedrueckt");
            }
        });

        bearbeitenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Bearbeite Button gedrueckt");
                controller.formularFensterOeffnen();
            }
        });

        neuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Neu Button gedrueckt");
            }
        });
    }

    public void setVollerName(String vollerName) {
        vollerNameLabel.setText(vollerName);
    }

}
