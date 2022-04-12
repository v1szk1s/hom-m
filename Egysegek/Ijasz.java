package Egysegek;

import Display.Color;
import Jatekosok.Player;
/**
 * Ijasz egyseg osztalya
 */
public class Ijasz extends Egyseg {
    public Ijasz(){
        super("Ijasz", 6, 7, 4, 9, true);
    }
    
    public int getSebzes(){
        return getMennyiseg() * (int)(Math.random()*2+2);
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

        return new String[]{Color.BOLD + Color.BRIGHT_BLUE +  nev + Color.RESET, "ar: " + ar, "sebzes: 2-4" + "\teletero: " + eletero, "sebesseg: " + sebesseg + "\tkezdemenyezes: " + kezdemenyezes};
    }

}