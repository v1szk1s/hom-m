package GameLogic;

import Display.*;
import Egysegek.Egyseg;
import IO.IO;
import Jatekosok.*;
import Log.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * A jatek csata reszet kezeli, lenyegeben ez az egesz jateknak a lelke. Itt tortennek a fontos dolgok.
 */
public class Game {
    private Player p1;
    private Player p2;
    Csatater csatater;

    public Game(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
        csatater = new Csatater(p1, p2);
        ((Gep)p2).elhelyez();

    }

    public void play(){
        while(playKor()){
            if(p1.isLost() && p2.isLost()){
                Log.log("Vege a jateknak, dontetlen lett, a folytatashoz: press any key", true);
                csatater.refresh();
                Display.waitForInput();
                Display.draw();
                return;
            }
            if(p1.isLost()){
                Log.log("Bena vagy. Vesztettel. folytatashoz: press any key", true);
                csatater.refresh();
                Display.waitForInput();
                Display.gameOver();
                return;
            }
            if(p2.isLost()){
                Log.log("Nyertel, nem is olyan rossz. folytatashoz: press any key", true);
                csatater.refresh();
                Display.waitForInput();
                Display.win();
                return;
            }
            Log.log("--------------------- Kor vege ---------------------");
            Log.log("Nyomj entert a folytatashoz");
        }
    }

    private boolean playKor(){
        
        List<Egyseg> allEgyseg = new ArrayList<Egyseg>();
        allEgyseg.clear();
        allEgyseg.addAll(p1.getEloEgysegek());
        allEgyseg.addAll(p2.getEloEgysegek());
        allEgyseg.sort(new EgysegComparator());


        Log.log(Csatater.getKor() + ". Kor");
        Log.log("Ebben a sorrendben lesz a harc:");

        for(var v:allEgyseg){
            Log.log(v.getPlayer().getNev() + ": " + v.getNev());
        }
        csatater.refresh();
        Display.sleep(1000);

        for(var e:allEgyseg){
            if(!e.isEl()){
                continue;
            }
            if(p1.isLost() || p2.isLost()){
                break;
            }
            if(e.getPlayer() instanceof Jatekos){
                if(e.getEletero() == 0){
                    continue;
                }
                int valasz = -1;
                while(valasz != 0){
                    valasz = chooseAction(e);
                    if(valasz == -2){
                        return false;
                    }
                }
                
            }else{
                ((Gep)p2).play(csatater, e);
            }
        }

        Csatater.incKor();
        return true;

    }

    private int chooseAction(Egyseg e){
        int cselekves = -1;
        while(cselekves == -1){
            csatater.refresh();
            cselekves = IO.menu("Mit szeretnel csinalni? Ezzel az egyseggel vagy: " + Color.CYAN_BACKGROUND + e.getNev() + Color.RESET, new String[]{"Tamad", "Mozog", "Hos Tamad", "Hos Varazsol", "Varakozik"});

            switch(cselekves){
                case -2:
                    return -2;
                case 0:
                    return tamad(e);

                case 1:
                    return mozog(e);
                
                case 2:
                    hosTamad();
                    return -1;
                    
                case 3:
                    hosVarazsol();
                    return -1;
                    
                case 4:
                //varakozik
                    return 0;
                default:
                    return -1;
            }
        }
        return -1;
    }



    private int tamad(Egyseg e){
        if(e.tudTamadni(p2) == false){
            if(e.isTavolsagi()){
                Log.log("Nem merek tamadni, tul kozel enemy!", true);
            }else{
                Log.log("Nincs a kozelben senki...", true);
            }
            
            return -1;
        }
        List<Integer> hovaJo; 
        if(e.isTavolsagi()){
           hovaJo = p2.getEloEgysegPosok();
        }else{
            hovaJo = new ArrayList<>();
            for(var v:e.getSzomszedok(p2)){
                hovaJo.add(v.getNumPos());
            }
        }
        int valasz = -1;
        while(valasz == -1){
            csatater.refresh(hovaJo);
            valasz = IO.menuSzamos("Kit szeretnel megtamadni? (Csak a zolden megjelolt mezokre lephetsz)", hovaJo);
            if(valasz == -2){
                return -1;
            }
        }
        e.tamad(p2.getEgysegOnPosition(valasz));
        return 0;
    }

    private int mozog(Egyseg e){
        if(e.getUresMezok(p1) == 0){
            Log.log("Ezzel az egyseggel nem tudsz mozogni!", true);
            return -1;
        }
        Tavolsag t = new Tavolsag(p1, p2);

        ArrayList<Integer> hovaJo = t.getPosInRange(e);
        int valasz = -1;
        while(valasz == -1){
            csatater.refresh(hovaJo);
            valasz = IO.menuSzamos("Hova szeretnel lepni? (Csak a zolden megjelolt mezokre lephetsz)", hovaJo);
            if(valasz == -2){
                return -1;
            }
        }
        e.helyez(valasz);
        return 0;
    }

    private int hosTamad(){
        if(!p1.getHos().tudCselekedni()){
            Log.log("A hosod elfaradt, ebben a korben nem tud tobbet csinalni!", true);
            return -1;
        }
        csatater.refresh();
        List<Integer> mostJo = p2.getEloEgysegPosok();
        int valasz = -1;
        while(valasz == -1){
            csatater.refresh(mostJo);
            valasz = IO.menuSzamos("Kit szeretnel tamadni a hosoddel?", mostJo);
            if(valasz == -2){
                return -1;
            }
        }
        p1.getHos().tamad(p2.getEgysegOnPosition(valasz));
        
        return 0;
    }

    private int hosVarazsol(){
        csatater.refresh();
        if(!p1.getHos().tudCselekedni()){
            Log.log("A hosod elfaradt, ebben a korben nem tud tobbet csinalni!", true);
            return -1;
        }
        if(p1.getHos().getVarazslatok().size() == 0){
            Log.log("Nem tudsz varazsolni, mivel nincs egy se!", true);
            return -1;
        }

        if(p1.getHos().getJoVarazslatok().size() == 0){
            Log.log("Nem tudsz varazsolni, mivel nincs eleg manad!");
            return -1;
        }

        int valasz = -1;
        while(valasz == -1){
            valasz = IO.menu("Melyik varazslatod szeretned hasznalni?", p1.getHos().getJoVarazslatok());
            if(valasz == -2){
                return -1;
            }
        }
        
        if(p1.getHos().getJoVarazslatok().get(valasz).varazsol(csatater) == 0){
            p1.getHos().setKorAmikorCsinaltValamit(Csatater.getKor());
        }
        return -1;
        //return p1.getHos().varazsol();
        
    }




    

}

class EgysegComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        double elso = ((Egyseg)o1).getKezdemenyezes();
        double masodik = ((Egyseg)o2).getKezdemenyezes();

        return elso > masodik? (elso == masodik? 0: -1):1;
    }

}

