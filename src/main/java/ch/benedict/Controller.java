package ch.benedict;

public class Controller {

    private PersonenStorage storage;
    private HauptFenster hauptFenster;

    public Controller() {
        storage = new PersonenStorage();
        hauptFenster = new HauptFenster(storage);
        start();
    }

    private void start() {
        hauptFenster.setVisible(true);
    }

}
