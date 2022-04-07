package Egysegek;

import Display.Color;
import Jatekosok.Player;

public class Griff extends Egyseg{
    public Griff(Player kie){
        super("Griff", 15, 30, 7, 15, kie);
    }

    public int getSebzes(){
        return getMennyiseg() * (int)Math.random()*6+5;
    }

    public String getIcon(){
        return "G";
    }

    public String[] info(){
        return new String[]{nev, "ar: " + ar, "sebzes: 5-15" + "\teletero: " + eletero, "sebesseg: " + sebesseg + "\tkezdemenyezes: " + kezdemenyezes};
    }
}
