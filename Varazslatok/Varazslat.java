package Varazslatok;

public abstract class Varazslat {
    private String nev;
    private static int ar;
    private static int mana;

    public Varazslat(String nev, int ar, int mana) {
        this.nev = nev;
        this.ar = ar;
        this.mana = mana;
    }

    public String getNev() {
        return nev;
    }

    public static int getAr() {
        return ar;
    }

    public static int getMana() {
        return mana;
    }

    public static String info() {
        return "Varázslat";
    }

}
