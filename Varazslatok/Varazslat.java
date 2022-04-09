package Varazslatok;

import Bolt.Veheto;
import Jatekosok.Hos;
public abstract class Varazslat implements Veheto{
    protected String nev;
    protected int ar;
    protected int mana;
    private Hos kie;

    public Varazslat(String nev, int ar, int mana){
        this.nev = nev;
        this.ar = ar;
        this.mana = mana;
        
    }

    public void setHos(Hos kie){
        this.kie = kie;
    }

    public Hos getHos(){
        return kie;
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
