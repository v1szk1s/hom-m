package Varazslatok;

import Jatekosok.Hos;

public class Villamcsapas extends Varazslat{
 
    public Villamcsapas(){
        super("Villamcsapas", 60, 5);
    }

    public int getSzorzo() {
        return 30;
    }
    public String[] info(){
        return new String[]{"Villamcsapas:", "Leiras: Egy kivalasztott ellenseges egysegre (varazsero * 30) sebzes okozasa", "ar: " + ar, "Mana: " + mana};
    }
}
