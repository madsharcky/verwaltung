package ch.benedict;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HauptFenster extends JFrame {
    private PersonenStorage storage;
    private JLabel titleLabel;
    private JLabel vollerNameLabel;
    private JButton zurueckButton;
    private JButton vorwaertsButton;
    private JButton bearbeiteButton;
    private JButton neuButton;
    private JPanel hauptPanel;
    private JPanel buttonPanel;

    public HauptFenster(PersonenStorage storage) {
        super("HauptFenster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        this.storage = storage;

        hauptPanel = new JPanel();
        hauptPanel.setLayout(new GridLayout(3, 1));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        titleLabel = new JLabel("Personenverwaltung");
        vollerNameLabel = new JLabel("Voller Name");
        zurueckButton = new JButton("Zurueck");
        vorwaertsButton = new JButton("Vorwaerts");
        bearbeiteButton = new JButton("Bearbeiten");
        neuButton = new JButton("Neu");

        buttonPanel.add(zurueckButton);
        buttonPanel.add(vorwaertsButton);
        buttonPanel.add(bearbeiteButton);
        buttonPanel.add(neuButton);

        hauptPanel.add(titleLabel);
        hauptPanel.add(vollerNameLabel);
        hauptPanel.add(buttonPanel);

        add(hauptPanel);

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Zurueck Button gedrueckt");
            }
        });
    }

    public void setVollerName(String vollerName) {
        vollerNameLabel.setText(vollerName);
    }

    public void setVorwaertsButtonListener(ActionListener listener) {
        vorwaertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Vorwaerts Button gedrueckt");
            }
        });
    }

    public void setBearbeiteButtonListener(ActionListener listener) {
        bearbeiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Bearbeite Button gedrueckt");
            }
        });
    }

    public void setNeuButtonListener(ActionListener listener) {
        neuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Neu Button gedrueckt");
            }
        });
    }
}
