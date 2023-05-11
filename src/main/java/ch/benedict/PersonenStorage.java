package ch.benedict;
import java.util.ArrayList;

public class PersonenStorage {
    private ArrayList<Person> personen;
    private int position;

    public PersonenStorage() {
        this.personen = new ArrayList<>();
        this.position = -1;  // position to -1, indicating that there is no current Person selected.
    }

    public Person getPerson() {
        if (position >= 0 && position < personen.size()) {
            return personen.get(position);
        }
        return null;
    }

    public boolean speichern() {
        // implementation for saving personen to file or database
        return true;
    }

    public boolean hinzufugen(String name, String vorname, String geschlecht, String ahv, String region, int kinder) {
        Person p = new Person(name, vorname, geschlecht, null, ahv, region, kinder);
        return personen.add(p);
    }

    public void loeschen() {
        if (position >= 0 && position < personen.size()) {
            personen.remove(position);
        }
    }

    public void vorwaerts() {
        if (position < personen.size() - 1) {
            position++;
        }
    }

    public void zurueck() {
        if (position > 0) {
            position--;
        }
    }
}
