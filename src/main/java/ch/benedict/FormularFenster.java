package ch.benedict;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

public class FormularFenster extends JFrame {
    private PersonenStorage storage;
    private final Controller controller;
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel vornameLabel;
    private JLabel gesclechtLabel;
    private JLabel geburtsdatumLabel;
    private JLabel ahvLabel;
    private JLabel regionLabel;
    private JLabel kinderLabel;

    private JTextField nameTextField;
    private JTextField vornameTextField;
    private JRadioButton geschlechtRadioButton;
    private JTextField geburtsdatumTextField;
    private JTextField ahvTextField;
    private JComboBox regionComboBox;
    private JTextField kinderTextField;

    private JButton speichernButton;
    private JButton abbrechenButton;
    private JButton loeschenButton;

    private JPanel hauptPanel;
    private JPanel buttonPanel;

    private ArrayList<String> regionen;

    public FormularFenster(PersonenStorage storage, final Controller controller) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.formularFensterSchliessen(FormularFenster.this);
            }
        });
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.storage = storage;
        this.controller = controller;

        regionen = new ArrayList<String>();
        regionen.add("Zürich");
        regionen.add("Bern");
        regionen.add("Luzern");
        regionen.add("Uri");
        regionen.add("Schwyz");
        regionen.add("Obwalden");
        regionen.add("Nidwalden");
        regionen.add("Glarus");
        regionen.add("Zug");
        regionen.add("Freiburg");
        regionen.add("Solothurn");
        regionen.add("Basel-Stadt");
        regionen.add("Basel-Landschaft");
        regionen.add("Schaffhausen");
        regionen.add("Appenzell Ausserrhoden");
        regionen.add("Appenzell Innerrhoden");
        regionen.add("St. Gallen");
        regionen.add("Graubünden");
        regionen.add("Aargau");
        regionen.add("Thurgau");
        regionen.add("Tessin");
        regionen.add("Waadt");
        regionen.add("Wallis");
        regionen.add("Neuenburg");
        regionen.add("Genf");
        regionen.add("Jura");

        getContentPane().setLayout(new BorderLayout(20, 20));

        hauptPanel = new JPanel();
        hauptPanel.setLayout(new GridLayout(8, 2, 20, 20));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 20, 20));

        titleLabel = new JLabel("Person:");
        nameLabel = new JLabel("Name:");
        vornameLabel = new JLabel("Vorname:");
        gesclechtLabel = new JLabel("Geschlecht:");
        geburtsdatumLabel = new JLabel("Geburtsdatum:");
        ahvLabel = new JLabel("AHV:");
        regionLabel = new JLabel("Region:");
        kinderLabel = new JLabel("Kinder:");

        nameTextField = new JTextField();
        vornameTextField = new JTextField();
        geschlechtRadioButton = new JRadioButton();
        geburtsdatumTextField = new JTextField();
        ahvTextField = new JTextField();
        regionComboBox = new JComboBox();
        kinderTextField = new JTextField();

        speichernButton = new JButton("Speichern");
        abbrechenButton = new JButton("Abbrechen");
        loeschenButton = new JButton("Löschen");

        buttonPanel.add(speichernButton);
        buttonPanel.add(abbrechenButton);
        buttonPanel.add(loeschenButton);

        hauptPanel.add(titleLabel);
        hauptPanel.add(new JLabel()); // added as separator
        hauptPanel.add(nameLabel);
        hauptPanel.add(nameTextField);
        hauptPanel.add(vornameLabel);
        hauptPanel.add(vornameTextField);
        hauptPanel.add(gesclechtLabel);
        hauptPanel.add(geschlechtRadioButton);
        hauptPanel.add(geburtsdatumLabel);
        hauptPanel.add(geburtsdatumTextField);
        hauptPanel.add(ahvLabel);
        hauptPanel.add(ahvTextField);
        hauptPanel.add(regionLabel);
        hauptPanel.add(regionComboBox);
        hauptPanel.add(kinderLabel);
        hauptPanel.add(kinderTextField);

        getContentPane().add(hauptPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                speichern();
            }
        });

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                abbrechen();
            }
        });

        loeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loeschen();
            }
        });

        for (String region : regionen) {
            regionComboBox.addItem(region);
        }
    }

    private void speichern() {
        // validate all the inputs
        if (nameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie einen Namen ein.");
            return;
        }
        if (vornameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie einen Vornamen ein.");
            return;
        }
        if (geburtsdatumTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie ein Geburtsdatum ein.");
            return;
        }
        if (ahvTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie eine AHV Nummer ein.");
            return;
        }
        if (kinderTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie die Anzahl Kinder ein.");
            return;
        }
        if (regionComboBox.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie eine Region aus.");
            return;
        }
        if (validateAHV(ahvTextField.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie eine gültige AHV Nummer ein.");
            return;
        }
        if (validateGeburtsdatum(geburtsdatumTextField.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie ein gültiges Geburtsdatum ein.");
            return;
        }
        if (validateKinder(kinderTextField.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie eine gültige Anzahl Kinder ein.");
            return;
        }

        String name = nameTextField.getText();
        String vorname = vornameTextField.getText();
        boolean geschlecht = geschlechtRadioButton.isSelected();
        String geburtsdatum = geburtsdatumTextField.getText();
        String ahv = ahvTextField.getText();
        String region = (String) regionComboBox.getSelectedItem();
        String kinder = kinderTextField.getText();

        // Person person = new Person(name, vorname, geschlecht, geburtsdatum, ahv,
        // region, kinder);

        // storage.hinzufuegen(person);
        System.out.println("Person gespeichert");
    }

    private void abbrechen() {
        controller.formularFensterSchliessen(this);
        System.out.println("abbrechen");
    }

    private void loeschen() {
        // storage.loeschen();
        System.out.println("loeschen");
    }

    // validate the AHV number, it should be 13 digits and a valid AHV number
    private boolean validateAHV(String ahv) {
        if (ahv.length() != 13) {
            return false;
        }
        // check with regex
        return ahv.matches("[0-9]+");
    }

    // validate the date of birth in the format dd.mm.yyyy, it should be a valid
    // date and older then 1 day
    private boolean validateGeburtsdatum(String datum) {
        // check with regex
        if (!datum.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
            return false;
        }
        // check if the date is valid
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(datum);
            // check if the date is older then 1 day
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            cal.add(Calendar.DATE, -1);
            Date yesterday = cal.getTime();
            if (date.before(yesterday)) {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
        return false;
    }

    // validate the number of children, it should be a number between 0 and 99
    private boolean validateKinder(String kinder) {
        if (kinder.length() > 2) {
            return false;
        }
        int[] kinderArray = new int[kinder.length()];
        for (int i = 0; i < kinder.length(); i++) {
            kinderArray[i] = Integer.parseInt(kinder.substring(i, i + 1));
        }
        if (kinderArray.length == 1) {
            return kinderArray[0] >= 0 && kinderArray[0] <= 9;
        }
        if (kinderArray.length == 2) {
            return kinderArray[0] >= 0 && kinderArray[0] <= 9 && kinderArray[1] >= 0 && kinderArray[1] <= 9;
        }
        return false;
    }
}
