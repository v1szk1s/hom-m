package Egysegek;

import Jatekosok.Player;

public class Ijasz extends Egyseg {
    public Ijasz(Player kie){
        super("Ijasz", 6, 7, 4, 9, kie, true);
    }
    
    public float getSebzes(){
        return getMennyiseg() * (float)Math.random()*2+2;
    }

    public String getIcon(){
        return "I";
    }

    public boolean tudTamadni(Player kit){
        if(this.getSzomszedok(kit).size() == 0){
            return true;
        }
        return false;
    }


    public String[] info(){

        return new String[]{nev, "ar: " + ar, "sebzes: 2-4" + "\teletero: " + eletero, "sebesseg: " + sebesseg + "\tkezdemenyezes: " + kezdemenyezes};
    }

}