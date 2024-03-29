package Varazslatok;

import java.util.ArrayList;
import java.util.List;

import Display.Csatater;
import Display.Position;
import Egysegek.Egyseg;
import GameLogic.Tavolsag;
import IO.IO;
import Jatekosok.Hos;
import Log.Log;
/**
 * Csatateren minden egyseget sebez egy 3x3-as teruleten.
 */
public class Tuzlabda extends Varazslat{
    public Tuzlabda(){
        super("Tuzlabda", 120, 9);
    }

    @Override
    public int varazsol(Csatater csatater) {
        if(getManaCost() > getHos().getMana()){
            Log.log("Nincs eleg manad!");
            return -1;
        }
        List<Integer> lista = new ArrayList<>();
        for(int i = 0; i <= 120; i++){
            lista.add(i);
        }

        int valasz = -1;
        while(valasz == -1){
            csatater.refresh(lista);
            if(valasz == -2){
                return -1;
            }
            valasz = IO.menuSzamos("Mely csapatokat szeretned megsutni egy kicsit?", lista);
        }
        

        int sebzes = getHos().getVarazsero() * getSzorzo();
        StringBuilder builder = new StringBuilder();
        List<Egyseg> l = Tavolsag.getSzomszedok(Position.convertToPos(valasz), csatater.getP1(), csatater.getP2());
        //l.add()
        for(var v:l){
            v.sebez(sebzes);
            builder.append(v.getNev() + ",");
        }
        getHos().koltMana(getManaCost());

        if(l.size() == 0){
            Log.log("Hat ezzel nem talaltal el semmit...");
        }else{
            Log.log("Tuzlabda: " + sebzes + " sebzes okozasa ezekre az egysegekre: " + builder.toString() + " egysegekre!");
        }

        return 0;
    }

    public void gepVarazsol(Egyseg e){
        getHos().koltMana(getManaCost());
        int sebzes = getHos().getVarazsero() * getSzorzo();
        StringBuilder builder = new StringBuilder();
        
        List<Egyseg> l = Tavolsag.getSzomszedok(e.getPos(), e.getPlayer(), this.getHos().getPlayer());
        for(var v:l){
            v.sebez(sebzes);
            builder.append(v.getNev() + ",");
        }

        if(l.size() == 0){
            Log.log("Hat ezzel nem talaltalt el semmit...");
        }else{
            Log.log(getHos().getPlayer().getNev() + ": "+ sebzes + " sebzes okozasa ezekre az egysegekre: " + builder.toString() + " egysegekre!");
        }
    }





    public int getSzorzo(){
        return 20;
    }
    public String[] info(){
        return new String[]{nev, "Leiras: Egy kivalasztott mezo körüli 3x3-as területen levo összes (sajat, illetve ellenseges) egysegre (varazsero * 20) sebzes okozasa.", "ar: " + Integer.toString(ar), "Mana: " + Integer.toString(mana)};
    }

}
