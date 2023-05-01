package ch.benedict;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class FormularFenster extends JFrame {
    private PersonenStorage storage;
    private final Controller controller;
    private Boolean isNew;
    private String selectedGeschlecht;

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
    private ButtonGroup geschlechtRadioButtonGroup;
    private JTextField geburtsdatumTextField;
    private JFormattedTextField ahvTextField;
    private JComboBox regionComboBox;
    private JSpinner kinderTextField;

    private JButton speichernButton;
    private JButton abbrechenButton;
    private JButton loeschenButton;

    private JPanel hauptPanel;
    private JPanel buttonPanel;
    private JPanel geschlechtPanel;

    private ArrayList<String> regionen;

    /**
     * Diese Klasse stellt ein Formularfenster dar, in dem Personendaten bearbeitet
     * werden können.
     * Das Formular enthält Eingabefelder für Name, Vorname, Geschlecht,
     * Geburtsdatum, AHV-Nummer,
     * Region und Kinder. Es kann entweder ein neues Formular angelegt oder ein
     * vorhandenes bearbeitet werden.
     * Wenn das Fenster geschlossen wird, wird der zugehörige Controller informiert.
     * 
     * @param storage    Der PersonenStorage, in dem die Personendaten gespeichert
     *                   werden.
     * @param controller Der Controller, der die Interaktion mit dem Formularfenster
     *                   steuert.
     * @param isNew      Gibt an, ob es sich um ein neues Formular oder eine
     *                   Bearbeitung eines vorhandenen Formulars handelt.
     */
    public FormularFenster(PersonenStorage storage, final Controller controller, Boolean isNew) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.formularFensterSchliessen(FormularFenster.this);
            }
        });
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Personenformular");
        setResizable(false);
        setLocationRelativeTo(null);

        this.storage = storage;
        this.controller = controller;
        this.isNew = isNew;

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
        // radio button
        geschlechtRadioButtonGroup = new ButtonGroup();
        geschlechtPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton geschlechtRadioButton1 = new JRadioButton("Männlich");
        JRadioButton geschlechtRadioButton2 = new JRadioButton("Weiblich");
        JRadioButton geschlechtRadioButton3 = new JRadioButton("Anderes");
        geschlechtRadioButtonGroup.add(geschlechtRadioButton1);
        geschlechtRadioButtonGroup.add(geschlechtRadioButton2);
        geschlechtRadioButtonGroup.add(geschlechtRadioButton3);
        geschlechtPanel.add(geschlechtRadioButton1);
        geschlechtPanel.add(geschlechtRadioButton2);
        geschlechtPanel.add(geschlechtRadioButton3);
        selectedGeschlecht = "";
        geschlechtRadioButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // set the selected gender to "Männlich"
                selectedGeschlecht = "Männlich";
            }
        });
        geschlechtRadioButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // set the selected gender to "Weiblich"
                selectedGeschlecht = "Weiblich";
            }
        });
        geschlechtRadioButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // set the selected gender to "Anderes"
                selectedGeschlecht = "Anderes";
            }
        });

        geburtsdatumTextField = new JTextField();
        // add placeholder for date that is only shown if the textfield is empty and not
        // focused
        if (isNew) {
            geburtsdatumTextField.setText("TT.MM.JJJJ");
            geburtsdatumTextField.setForeground(Color.GRAY);
        }
        geburtsdatumTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (geburtsdatumTextField.getText().equals("TT.MM.JJJJ")) {
                    geburtsdatumTextField.setText("");
                    geburtsdatumTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (geburtsdatumTextField.getText().equals("")) {
                    geburtsdatumTextField.setText("TT.MM.JJJJ");
                    geburtsdatumTextField.setForeground(Color.GRAY);
                }
            }
        });
        // make a new number formatter for ahv that looks like ##.####.####.##
        MaskFormatter formatter;
        try {
            formatter = new MaskFormatter("###.####.####.##");
            formatter.setPlaceholderCharacter('0');
            ahvTextField = new JFormattedTextField(formatter);
        } catch (ParseException e) {
        }

        // add placeholder for ahv that is only shown if the textfield is empty and not
        // focused
        if (isNew) {
            ahvTextField.setText("7561234567897");
            ahvTextField.setForeground(Color.GRAY);
        }
        ahvTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ahvTextField.getText().equals("7561234567897")) {
                    ahvTextField.setText("");
                    ahvTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ahvTextField.getText().equals("")) {
                    ahvTextField.setText("7561234567897");
                    ahvTextField.setForeground(Color.GRAY);
                }
            }
        });
        regionComboBox = new JComboBox();
        kinderTextField = new JSpinner();

        speichernButton = new JButton("Speichern");
        abbrechenButton = new JButton("Abbrechen");
        loeschenButton = new JButton("Löschen");

        buttonPanel.add(speichernButton);
        buttonPanel.add(abbrechenButton);
        if (!isNew) {
            buttonPanel.add(loeschenButton);
        }
        hauptPanel.add(titleLabel);
        hauptPanel.add(new JLabel()); // added as separator
        hauptPanel.add(nameLabel);
        hauptPanel.add(nameTextField);
        hauptPanel.add(vornameLabel);
        hauptPanel.add(vornameTextField);
        hauptPanel.add(gesclechtLabel);
        hauptPanel.add(geschlechtPanel);
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

        // TODO: uncomment this
        // if (!isNew) {
        // // set the values of the person to the textfields
        // Person person = storage.getPerson
        // nameTextField.setText(person.getName());
        // vornameTextField.setText(person.getVorname());
        // if (person.getGeschlecht().equals("Männlich")) {
        // geschlechtRadioButton1.setSelected(true);
        // } else if (person.getGeschlecht().equals("Weiblich")) {
        // geschlechtRadioButton2.setSelected(true);
        // } else {
        // geschlechtRadioButton3.setSelected(true);
        // }
        // geburtsdatumTextField.setText(person.getGeburtsdatum());
        // ahvTextField.setText(person.getAhv());
        // regionComboBox.setSelectedItem(person.getRegion());
        // kinderTextField.setValue(person.getKinder());
        // }
    }

    /**
     * Speichert eine Person, indem alle Eingaben validiert werden.
     * Wenn alle Eingaben korrekt sind, wird eine neue Person erstellt und in der
     * Datenbank gespeichert.
     * Wenn die Eingaben nicht korrekt sind, wird eine Fehlermeldung angezeigt und
     * die Person wird nicht gespeichert.
     * 
     * @return void
     */
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
        if (selectedGeschlecht.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie ein Geschlecht aus.");
            return;
        }
        if (geburtsdatumTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie ein Geburtsdatum ein.");
            return;
        }
        if (ahvTextField.getText().isEmpty() || ahvTextField.getText().equals("756.1234.5678.97")) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie eine AHV Nummer ein.");
            return;
        }
        if (regionComboBox.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie eine Region aus.");
            return;
        }
        if (validateGeburtsdatum(geburtsdatumTextField.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie ein gültiges Geburtsdatum ein.");
            return;
        }

        String name = nameTextField.getText();
        String vorname = vornameTextField.getText();
        String geschlecht = selectedGeschlecht;
        String geburtsdatum = geburtsdatumTextField.getText();
        String ahv = ahvTextField.getText();
        String region = (String) regionComboBox.getSelectedItem();
        String kinder = kinderTextField.getValue().toString();

        // TODO: uncomment this
        // if (isNew) {
        // Person person = new Person(name, vorname, geschlecht, geburtsdatum, ahv,
        // region, kinder);
        // storage.hinzufuegen(person);
        // } else {
        // storage.speichern();
        // }

        System.out.println("Person gespeichert");
        controller.formularFensterSchliessen(this);
    }

    /**
     * Schließt das aktuelle Fenster und ruft die Methode
     * "formularFensterSchliessen" des Controllers auf.
     * Diese Methode wird aufgerufen, wenn der Benutzer den "Abbrechen"-Button
     * betätigt.
     */
    private void abbrechen() {
        controller.formularFensterSchliessen(this);
        System.out.println("abbrechen");
    }

    /**
     * Löscht alle Einträge aus dem Speicher.
     */
    private void loeschen() {
        // storage.loeschen();
        System.out.println("loeschen");
    }

    /**
     * Überprüft das übergebene Geburtsdatum auf das Format dd.MM.yyyy und ob es
     * gültig ist.
     * 
     * @param datum das zu prüfende Geburtsdatum im Format dd.MM.yyyy
     * @return true, wenn das Datum gültig und älter als einen Tag ist, andernfalls
     *         false
     */
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
}
