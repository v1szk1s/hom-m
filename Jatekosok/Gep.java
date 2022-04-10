package Jatekosok;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Display.Csatater;
import Display.Display;
import Display.Position;
import Egysegek.*;
import GameLogic.Tavolsag;
import Log.Log;
import Varazslatok.Feltamasztas;
import Varazslatok.Villamcsapas;

/**
 * Ez az osztaly felelos az ellenseg okos donteseiert, vagyis ez az osztaly kepviseli az ellenfelet, es ez fog teged sokszor legyozni, remelem.
 */
public class Gep extends Player{
    private int[] xd = {11, 12, 23, 24, 35, 36, 47, 48, 59, 60, 71, 72, 83, 84, 95, 96, 107, 108, 119, 120};
    static ArrayList<Integer> utolsoKetSor = new ArrayList<Integer>();
    private int nehezseg;

    public Gep(int nehezseg){
        super(2);
        this.nehezseg = nehezseg;
        setNev("Ellenfel");
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){
                levelUp(i);
            }
        }
            getHos().buyVarazslat(new Villamcsapas());
            
            
            buyEgyseg(new Griff(), maxMennyitVehet(new Griff())/2);
            buyEgyseg(new Ijasz(), (int) (maxMennyitVehet(new Ijasz())* 0.75));
            buyEgyseg(new Foldmuves(), maxMennyitVehet(new Foldmuves()));
            //elhelyez();
            // buyEgyseg(AllEgyseg.getAllEgyseg()[2], maxMennyitVehet(AllEgyseg.getAllEgyseg()[3])/2);
            // buyEgyseg(AllEgyseg.getAllEgyseg(), (int) (maxMennyitVehet(getAllEgyseg()[1]) * 0.75));
            // getHos().buyEgyseg("foldmuves", maxMennyitVehet(getAllEgyseg()[1]));
            //Random rnd = new Random();
            
        
    }
    /**
     * 
     * @param csatater
     * @param e az egyseg amivel cselekedni tud
     */
    public void play(Csatater csatater, Egyseg e){
        if(nehezseg == 0){
            return;
        }
        if(e.getEletero() == 0){
            return;
        }
        
        Log.log(getNev() + " gondolkozik...");
        csatater.refresh();
        Display.sleep((long)(Math.random() * 1000 + 1000));

        Player ellenseg = this.equals(csatater.getP1())? csatater.getP2():csatater.getP1();
        
        if(e.tudTamadni(csatater.getP1())){
            e.tamad(e.getTamadhatoEgysegek(csatater, this).get(0));
            return;
        }
        if(getHos().tudCselekedni()){
            List<Egyseg> enemik = ellenseg.getEloEgysegek();
            if(enemik.size() == 0){
                return;
            }
            Egyseg gyenge = enemik.get(0);
            int min = Integer.MAX_VALUE;
            for(var v:enemik){
                if(v.getEletero() < min){
                    min = v.getEletero();
                    gyenge = v;
                }
            }
            if(getHos().tudVarazsolni(getHos().getVarazslatok().get(0)) && Math.random() > 0.5){
               getHos().getVarazslatok().get(0).gepVarazsol(gyenge);
            }else{
                getHos().tamad(gyenge);
            }

        }

        Tavolsag tav = new Tavolsag(csatater.getP1(), csatater.getP2());
        //if()
        List<Egyseg> kozelbenVan = tav.getEgysegInRange(e, e.getSebesseg(), 2);
        //e.move()
        if(kozelbenVan.size() > 0 && kozelbenVan.get(0).getUresMezok(ellenseg) > 0){
            for(var v:kozelbenVan.get(0).getUresMezoPosok(ellenseg)){
                if(tav.getTavolsag(e, Position.convertToSzam(v)) <= e.getSebesseg()){
                    e.setPos(v);
                    return;
                }
            }
        }
        List<Integer> hovaLephet = tav.getUresekInRange(e, e.getSebesseg());
        int hovaLepjen = -1;
        int legkozelebbiRange = Integer.MAX_VALUE;
        for(var i:hovaLephet){
            for(var j:ellenseg.getEgysegek()){
                int m = tav.getTavolsag(j, i);
                if(m < legkozelebbiRange){
                    legkozelebbiRange = m;
                    hovaLepjen = i;
                }
            }
        }
        e.setPos(Position.convertToPos(hovaLepjen));
    }




    public void elhelyez(){
        Log.log(getNev() + " is elhelyezte az egysegeit");
        for(var e:this.getEgysegek()){
            int hova = (int) (Math.random() * 18);
            while(getEgysegOnPosition(Position.convertToPos(xd[2+hova])) != null){
                hova = (int) (Math.random() * 18);
            }
            e.setPos(Position.convertToPos(xd[2+hova]));
        }
        
    }    
}
