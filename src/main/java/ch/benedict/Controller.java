package ch.benedict;

public class Controller {

    private PersonenStorage storage;
    private HauptFenster hauptFenster;
    private FormularFenster formularFenster;

    /**
     * Konstruktor für die Controller-Klasse. Erzeugt eine Instanz der
     * PersonenStorage-Klasse
     * und des HauptFenster-Fensters. Die Methode start() wird aufgerufen, um das
     * Hauptfenster anzuzeigen.
     */
    public Controller() {
        storage = new PersonenStorage();
        storage.hinzufugen("Muster", "Hans", "Männlich", "23.04.1964", "756.2020.2648.97", "ZH", 2);
        storage.hinzufugen("Muster", "Hanna", "Weiblich", "17.09.1966", "756.2645.9872.20", "ZH", 2);
        storage.hinzufugen("Erwin", "Ernst", "Männlich", "01.01.1990", "756.2020.2678.20", "ZH", 2);
        storage.hinzufugen("Erwin", "Erika", "Weiblich", "01.01.1990", "756.2020.2648.20", "ZH", 2);
        storage.hinzufugen("Erwin", "Egon", "Männlich", "01.01.1999", "756.2020.2048.20", "ZH", 2);

        hauptFenster = new HauptFenster(storage, this);
        start();
    }

    /**
     * Öffnet das Hauptfenster der Anwendung.
     */
    private void start() {
        hauptFenster.setVisible(true);
    }

    /**
     * Öffnet ein Formularfenster und setzt das Hauptfenster auf inaktiv.
     * 
     * @param istNeu Gibt an, ob es sich um ein neues Formular handelt oder nicht.
     */
    public void formularFensterOeffnen(boolean istNeu) {
        formularFenster = new FormularFenster(storage, this, istNeu);
        formularFenster.setVisible(true);
        // make HauptFenster dormant
        hauptFenster.setEnabled(false);
    }

    /**
     * Schließt das übergebene FormularFenster und aktiviert das HauptFenster
     * wieder.
     * 
     * @param formularFenster das FormularFenster, das geschlossen werden soll
     * @return gibt die Zahl 2 zurück, um anzuzeigen, dass das FormularFenster
     *         erfolgreich geschlossen wurde
     */
    public int formularFensterSchliessen(FormularFenster formularFenster) {
        // make HauptFenster active again
        hauptFenster.setPersonenInfo();
        hauptFenster.setEnabled(true);
        formularFenster.dispose();
        return 2;
    }

}
