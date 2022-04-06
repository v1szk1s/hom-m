package Varazslatok;

import Bolt.Veheto;

public abstract class Varazslat implements Veheto{
    protected String nev;
    protected int ar;
    protected int mana;

    public Varazslat(String nev, int ar, int mana){
        this.nev = nev;
        this.ar = ar;
        this.mana = mana;
    }

    public String getNev(){
        return nev;
    }

    public int getAr() {
        return ar;
    }

    public int getMana() {
        return mana;
    }

    public abstract String[] info();

}
