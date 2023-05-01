package ch.benedict;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HauptFenster extends JFrame {
    private PersonenStorage storage;
    private JLabel titleLabel;
    private JLabel NameLabel;
    private JLabel vornameLabel;
    private JButton zurueckButton;
    private JButton vorwaertsButton;
    private JButton bearbeitenButton;
    private JButton neuButton;
    private JPanel hauptPanel;
    private JPanel buttonPanel;

    /**
     * Erzeugt das Hauptfenster der Anwendung, in dem die Liste der Personen
     * angezeigt wird.
     * 
     * @param storage    ein Objekt der Klasse PersonenStorage, das die
     *                   Personenliste enthält
     * @param controller ein Objekt der Klasse Controller, das die Steuerung der
     *                   Anwendung übernimmt
     */
    public HauptFenster(PersonenStorage storage, final Controller controller) {
        super("HauptFenster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.storage = storage;

        getContentPane().setLayout(new BorderLayout(20, 20));

        hauptPanel = new JPanel();
        hauptPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 20, 20));

        titleLabel = new JLabel("Person:");
        NameLabel = new JLabel("Name");
        vornameLabel = new JLabel("Vorname");
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
        hauptPanel.add(NameLabel);
        hauptPanel.add(vornameLabel);
        hauptPanel.add(buttonPanel);

        add(hauptPanel);

        setPersonenInfo();

        pack(); // resize window to fit content

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // TODO: uncomment this
                // storage.zurueck();
                setPersonenInfo();
                System.out.println("Zurueck Button gedrueckt");
            }
        });

        vorwaertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // TODO: uncomment this
                // storage.vorwaerts();
                setPersonenInfo();
                System.out.println("Vorwaerts Button gedrueckt");
            }
        });

        bearbeitenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Bearbeite Button gedrueckt");
                controller.formularFensterOeffnen(false);
            }
        });

        neuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Neu Button gedrueckt");
                controller.formularFensterOeffnen(true);
            }
        });
    }

    /**
     * Setzt die Personeninformationen in den zugehörigen Labels.
     * Dabei wird der Name in das Label "NameLabel" und der Vorname in das Label
     * "vornameLabel" gesetzt.
     */
    private void setPersonenInfo() {
        // TODO: uncomment this
        // NameLabel.setText(storage.getPerson().getName());
        // vornameLabel.setText(storage.getPerson().getVorname());
    }

}
