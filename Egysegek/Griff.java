package Egysegek;

import Display.Color;
import Jatekosok.Player;
/**
 * Griff egyseg osztalya
 */
public class Griff extends Egyseg{
    public Griff(){
        super("Griff", 15, 30, 7, 15, false);
    }

    public int getSebzes(){
        return getMennyiseg() * (int) Math.round(Math.random()*5+5);
    }

    public String getIcon(){
        return "G";
    }

    public boolean tudTamadni(Player kit){
        if(this.getSzomszedok(kit).size() > 0){
            return true;
        }
        return false;
    }

    public String[] info(){
        return new String[]{Color.BOLD + Color.BRIGHT_ORANGE + nev + Color.RESET, "ar: " + ar, "sebzes: 5-10" + "\teletero: " + eletero, "sebesseg: " + sebesseg + "\tkezdemenyezes: " + kezdemenyezes};
    }
}
