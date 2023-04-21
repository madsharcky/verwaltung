package ch.benedict;

public class Controller {

    private PersonenStorage storage;
    private HauptFenster hauptFenster;

    public Controller() {
        storage = new PersonenStorage();
        hauptFenster = new HauptFenster(storage, this);
        start();
    }

    private void start() {
        hauptFenster.setVisible(true);
    }

    public void formularFensterOeffnen() {
        FormularFenster formularFenster = new FormularFenster(storage, this);
        formularFenster.setVisible(true);
        // make HauptFenster dormant
        hauptFenster.setEnabled(false);
    }

    public int formularFensterSchliessen(FormularFenster formularFenster) {
        // make HauptFenster active again
        hauptFenster.setEnabled(true);
        formularFenster.dispose();
        return 2;
    }

}
