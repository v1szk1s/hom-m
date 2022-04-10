package Varazslatok;

import Display.Csatater;
import IO.IO;
import Jatekosok.Hos;
import Jatekosok.Player;
import Log.Log;

public class Villamcsapas extends Varazslat{
 
    public Villamcsapas(){
        super("Villamcsapas", 60, 5);
    }


    @Override
    public int varazsol(Csatater csatater) {
        if(getManaCost() > getHos().getMana()){
            Log.log("Nincs eleg manad!");
            return -1;
        }
        Player ellenseg = getHos().getPlayer().equals(csatater.getP1())?csatater.getP2():csatater.getP1();
        int valasz = -1;
        while(valasz == -1){
            csatater.refresh();
            if(valasz == -2){
                return -1;
            }
            valasz = IO.menuSzamos("Melyik ellenseget szeretned megvaillamlaztani?", ellenseg.getEgysegPosok());
        }
        int sebzes = getHos().getVarazsero() * getSzorzo();
        ellenseg.getEgysegOnPosition(valasz).sebez(sebzes);
        getHos().koltMana(getManaCost());
        Log.log(sebzes + " sebzes okozasa " + ellenseg.getNev() + " " + ellenseg.getEgysegOnPosition(valasz).getNev() + " egysegere!");
        return 0;

    }

    public int getSzorzo() {
        return 30;
    }
    public String[] info(){
        return new String[]{"Villamcsapas:", "Leiras: Egy kivalasztott ellenseges egysegre (varazsero * 30) sebzes okozasa", "ar: " + ar, "Mana: " + mana};
    }
}
