package Egysegek;

import Jatekosok.Player;

public class Foldmuves extends Egyseg{
    public Foldmuves(Player kie){
        super("Foldmuves", 2, 3, 4, 8, kie);
    }


    public int getSebzes(){
        return getMennyiseg();
    }

    public String getIcon(){
        return "F";
    }

    public String[] info(){

        return new String[]{nev, "ar: " + ar, "sebzes: 1-1" + "\teletero: " + eletero, "sebesseg: " + sebesseg + "\tkezdemenyezes: " + kezdemenyezes};
    }
}
