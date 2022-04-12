package Varazslatok;

import Display.Color;
import Display.Csatater;
import Egysegek.Egyseg;
import IO.IO;
import Jatekosok.Gep;
import Jatekosok.Hos;
import Jatekosok.Player;
import Log.Log;
/**
 * Ellenseges egysegek megvillamositasaert van.
 */
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

            csatater.refresh(ellenseg.getEloEgysegPosok());
            if(valasz == -2){
                return -1;
            }
            valasz = IO.menuSzamos("Melyik ellenseget szeretned megvaillamlaztani?", ellenseg.getEgysegPosok());
            if(valasz == -2){
                return -1;
            }
        }
        int sebzes = getHos().getVarazsero() * getSzorzo();
        ellenseg.getEgysegOnPosition(valasz).sebez(sebzes);
        getHos().koltMana(getManaCost());
        Log.log("Villamcsapas: " + Color.RED + sebzes + " sebzes" + Color.RESET + " okozasa " + ellenseg.getNev() + " " + ellenseg.getEgysegOnPosition(valasz).getNev() + " egysegere!");
        return 0;

    }
    /**
     * 
     * @param e kit
     */
    public void gepVarazsol(Egyseg e){
        this.getHos().setKorAmikorCsinaltValamit(Csatater.getKor());
        int sebzes = getHos().getVarazsero() * getSzorzo();
        e.sebez(sebzes);
        getHos().koltMana(getManaCost());
        Log.log( getHos().getPlayer().getNev() + " Villamcsapas: " + sebzes + " sebzes okozasa " + " " + e.getNev() + " "+ e.getPlayer().getNev()+"egysegere!");
        return;
    }

    public int getSzorzo() {
        return 30;
    }
    public String[] info(){
        return new String[]{nev, "Leiras: Egy kivalasztott ellenseges egysegre (varazsero * 30) sebzes okozasa", "ar: " + ar, "Mana: " + mana};
    }
}
