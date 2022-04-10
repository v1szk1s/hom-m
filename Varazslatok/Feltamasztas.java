package Varazslatok;

import java.util.List;

import Display.Csatater;
import Egysegek.Egyseg;
import IO.IO;
import Jatekosok.Hos;
import Jatekosok.Player;
import Log.Log;
/**
 * A csatateren meghalt sajat egysegek feltamasztasaert szolgal.
 * Itt van megvalositva a jatekos fele csinalt kerdesek, mivel uj varazslathoz mas dolgok kellhetnek, es igy eleg ccsak itt megcsinalni.
 */
public class Feltamasztas extends Varazslat{

    public Feltamasztas(){
        super("Feltamasztas", 120, 6);
    }

    @Override
    public int varazsol(Csatater csatater) {
        if(getManaCost() > getHos().getMana()){
            Log.log("Nincs eleg manad!", true);
            return -1;
        }
        List<Integer> lista = getHos().getPlayer().getHalottEgysegPosok();
        if(lista.size() == 0){
            Log.log("Nincs halott egyseged!", true);
            return -1;
        }



        int halottak = getHos().getPlayer().getHalottEgysegPosok().size();
        Player ellenseg = getHos().getPlayer().equals(csatater.getP1())?csatater.getP2():csatater.getP1();
        for(var i:lista){
            for(var j:getHos().getPlayer().getEloEgysegPosok()){
                if(i == j){
                    halottak--;
                }
            }
            for(var j:ellenseg.getEloEgysegPosok()){
                if(i == j){
                    halottak--;
                }
            }
            
        }


        if(halottak <= 0){
            Log.log("Ha allnak a siron nem lehet feltamasztani!");
            return -1;
        }
       
        int valasz = -1;
        while(valasz == -1){
            csatater.refresh(lista);
            if(valasz == -2){
                return -1;
            }
            valasz = IO.menuSzamos("Melyik csapatodat szeretned feltamasztani?", lista);
        }

        getHos().getPlayer().getHalottEgysegOnPosition(valasz).addEletero(getSzorzo()*getHos().getVarazsero());
        
        getHos().koltMana(getManaCost());
        Log.log(getHos().getPlayer().getHalottEgysegOnPosition(valasz) + " egyseg feltamasztasa!");
        return 0;
    }

    public void gepVarazsol(Egyseg e){
        e.addEletero(getSzorzo()*getHos().getVarazsero());
        
        getHos().koltMana(getManaCost());
        Log.log(getHos().getPlayer().getNev() + " " + e.getNev() + " egyseg feltamasztasa!");
    }

    public int getSzorzo() {
        return 50;
    }
    public String[] info(){
        return new String[]{nev, "Leiras: Egy kivalasztott sajat egysag feltamasztasa.", "Ar: " + ar, "Mana: " + mana};
    }

}
