package Varazslatok;

import java.util.ArrayList;
import java.util.List;

import Display.Color;
import Display.Csatater;
import Display.Position;
import Egysegek.Egyseg;
import GameLogic.Tavolsag;
import IO.IO;
import Jatekosok.Hos;
import Log.Log;
/**
 * A csatateren elo sajat egysegek gyogyitasart szolgal.
 * 
 */
public class Gyogyit extends Varazslat{
    public Gyogyit(){
        super("Gyogyit", 75, 5);
    }

    @Override
    public int varazsol(Csatater csatater) {
        if(getManaCost() > getHos().getMana()){
            Log.log("Nincs eleg manad!", true);
            return -1;
        }
        int gyogyithato = 0;
        for(var v:getHos().getPlayer().getEloEgysegek()){
            if(v.getEletero() < v.getMaxElet()){
                gyogyithato++;
            }
        }
        if(gyogyithato == 0){
            Log.log("Mindenki teljesen egeszseges!");
            return -1;
        }

        List<Egyseg> lista = getHos().getPlayer().getEloEgysegek();

        int valasz = -1;
        while(valasz == -1){
            csatater.refresh(getHos().getPlayer().getEloEgysegPosok());
            if(valasz == -2){
                return -1;
            }
            valasz = IO.menuSzamos("Mely csapatodat szeretned gyogyitani?", getHos().getPlayer().getEloEgysegPosok());
        }
        
        //StringBuilder builder = new StringBuilder();
       // List<Egyseg> l = Tavolsag.getSzomszedok(Position.convertToPos(valasz), csatater.getP1(), csatater.getP2());

        getHos().getPlayer().getEgysegOnPosition(valasz).addEletero(getHos().getVarazsero()*25);
        getHos().koltMana(getManaCost());
        Log.log(getHos().getPlayer().getNev() + " " + getHos().getPlayer().getEgysegOnPosition(valasz).getNev() + "egyseg gyogyitasa ("+ Color.GREEN+"+" + getHos().getVarazsero()*25 + " hp"+Color.RESET +")" );

        return 0;
    }

    public void gepVarazsol(Egyseg e){
        e.addEletero(getHos().getVarazsero()*25);
        getHos().koltMana(getManaCost());
        Log.log(getHos().getPlayer().getNev() + " " + e.getNev() + "egyseg gyogyitasa (+" + getHos().getVarazsero()*25 + " hp)" );

    }



    public int getSzorzo(){
        return 20;
    }
    public String[] info(){
        return new String[]{nev, "Leiras: Egy kivalasztott sajat egyseg (varazsero * 25) eleterovel valo gyogyitasa.", "ar: " + Integer.toString(ar), "Mana: " + Integer.toString(mana)};
    }

}

