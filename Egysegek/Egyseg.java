package Egysegek;

public abstract class Egyseg {
    private String nev;
    private int ar;
    private int sebzes;
    private int eletero;
    private int sebesseg;
    private int kezdemenyezes;


    public Egyseg(String nev, int ar, int sebzes, int eletero, int sebesseg, int kezdemenyezes) {
        this.nev = nev;
        this.ar = ar;
        this.sebzes = sebzes;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
    }

    abstract void special();
}
