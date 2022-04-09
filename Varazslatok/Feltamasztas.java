package Varazslatok;

import Jatekosok.Hos;

public class Feltamasztas extends Varazslat{

    public Feltamasztas(){
        super("Feltamasztas", 120, 6);
    }

    public int getSzorzo() {
        return 50;
    }
    public String[] info(){
        return new String[]{nev, "Leiras: Egy kivalasztott sajat egysag feltamasztasa.", "Ar: " + ar, "Mana: " + mana};
    }

}
