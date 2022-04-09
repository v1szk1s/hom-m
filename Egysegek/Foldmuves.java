package Egysegek;

import Jatekosok.Player;

public class Foldmuves extends Egyseg{
    public Foldmuves(){
        super("Foldmuves", 2, 3, 4, 8, false);
    }


    public int getSebzes(){
        return getMennyiseg();
    }

    public String getIcon(){
        return "F";
    }

    public boolean tudTamadni(Player kit){
        if(this.getSzomszedok(kit).size() > 0){
            return true;
        }
        return false;

    }
    

    public String[] info(){

        return new String[]{nev, "ar: " + ar, "sebzes: 1-1" + "\teletero: " + eletero, "sebesseg: " + sebesseg + "\tkezdemenyezes: " + kezdemenyezes};
    }
}
