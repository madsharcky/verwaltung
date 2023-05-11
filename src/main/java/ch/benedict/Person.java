package ch.benedict;

public class Person {
    private String name;
    private String vorname;
    private String geschlecht;
    private String geburtsdatum;
    private String ahv;
    private String region;
    private int kinder;
    
    public Person(String name, String vorname, String geschlecht, String geburtsdatum, String ahv, String region, int kinder) {
        this.name = name;
        this.vorname = vorname;
        this.geschlecht = geschlecht;
        this.geburtsdatum = geburtsdatum;
        this.ahv = ahv;
        this.region = region;
        this.kinder = kinder;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getVorname() {
        return vorname;
    }
    
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    public String getGeschlecht() {
        return geschlecht;
    }
    
    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }
    
    public String getGeburtsdatum() {
        return geburtsdatum;
    }
    
    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
    
    public String getAHV() {
        return ahv;
    }
    
    public void setAHV(String ahv) {
        this.ahv = ahv;
    }
    
    public String getRegion() {
        return region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    
    public int getKinder() {
        return kinder;
    }
    
    public void setKinder(int kinder) {
        this.kinder = kinder;
    }
}
