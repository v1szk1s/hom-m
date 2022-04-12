import java.util.ArrayList;
import java.util.List;

import Display.Csatater;
import Display.Display;
import Display.Position;
import Egysegek.Egyseg;
import Egysegek.Ijasz;
import Egysegek.Foldmuves;
import Egysegek.Griff;
import GameLogic.Game;
import GameLogic.Tavolsag;
import Jatekosok.Player;
import Jatekosok.Hos;
import Jatekosok.Gep;
import Jatekosok.Jatekos;
import Varazslatok.*;
import Log.Log;

/**
 * Ez a foprogramunk, igazibol itt tenyleg nincs olyan sokminen. A jetek meneteert felel.
 */
class Main {

    public static void main(String[] args) {
        //hack();  
        //debug();
        single();

        // int mode = Display.menuFullScreen("Menu:", new String[]{"Egyjatekos", "Tobbjatekos"});
        // switch(mode){
        //     case 1:
        //         single();
        //         break;
        //     case 2:
        //         multi();
        //         break;
        //     default:
        //         Display.gg();
        // }


    }

    static void debug(){
        
        Jatekos p = new Jatekos(1);
        p.setNev("xd");
        Gep g = new Gep(2);
        //p.buyEgyseg(new Ijasz(), 50);
        //p.buyEgyseg(new Griff(), 0);
        p.buyEgyseg(new Foldmuves(), 50);
        p.buyEgyseg(new Ijasz(), 50);
        p.buyEgyseg(new Griff(), 40);
        

        p.elhelyez(p.getEgysegek().get(0), 1);
        p.elhelyez(p.getEgysegek().get(1), 2);
        p.elhelyez(p.getEgysegek().get(2), 14);
        //p.elhelyez(p.getEgysegek().get(1), 54);
        //p.getEgysegek().get(1).sebez(999);
        // p.elhelyez(p.getEgysegek().get(1), 38);
        // p.elhelyez(p.getEgysegek().get(2), 50);
        //p.getHos().buyVarazslat(new Villamcsapas());
        //p.getHos().buyVarazslat(new Tuzlabda());
        //p.getHos().buyVarazslat(new Gyogyit());
        //g.elhelyez();
        g.elhelyez(g.getEgysegek().get(2), 72);
        Csatater cs = new Csatater(p, g);
        Tavolsag tav = new Tavolsag(p, g);
        

        
        List<Integer> most = new ArrayList<>();
        System.out.println(tav.getTavolsag(p.getEgysegek().get(0), g.getEgysegek().get(2)));
        for(var v:p.getEgysegek().get(0).getUresMezoPosok(p)){
            //System.out.println();
            most.add(Position.convertToSzam(v));
        }
        cs.refresh(most);
        Game game = new Game(p, g);
        
        
        //Tavolsag tav = new Tavolsag(p, g);
        //cs.refresh(tav.getPosInRange(p.getEgysegek().get(0)));
        //System.out.println(tav.getTavolsag(p.getEgysegek().get(0), 38));
        game.play();



    }

    static void hack(){
        Jatekos p = new Jatekos(1);
        Gep g = new Gep(2);
        
        p.buyEgyseg(new Foldmuves(), 100);
        p.buyEgyseg(new Ijasz(), 1);
        p.buyEgyseg(new Griff(), 2);
        p.elhelyez(p.getEgysegek().get(0), 55);
        p.elhelyez(p.getEgysegek().get(1), 38);
        p.elhelyez(p.getEgysegek().get(2), 50);
        p.getHos().buyVarazslat(new Villamcsapas());
        p.getHos().buyVarazslat(new Tuzlabda());
        p.getHos().buyVarazslat(new Feltamasztas());
        g.elhelyez();
        Game game = new Game(p, g);
        game.play();
        for(var v:g.getEgysegek()){
            System.out.println(v.getMennyiseg());
        }


        //
        //game.playKor();
        //Csatater.showPalya(null,new Player[]{p, g});
    }



    static void single(){
        int nehezseg = Display.menuFullScreen("Kerlek valassz nehezsegi fokozatot:", new String[]{"Konnyu", "Kozepes", "Nehez"});
        if(nehezseg == -1){
            Display.gameOver();
            return;
        }
        Jatekos player = new Jatekos(nehezseg);
        if(Display.levelSystem(player) == -2){
            return;
        }
        if(Display.varazsShop(player) == -2){
            return;
        }
        if(Display.egysegShop(player) == -2){
            return;
        }
        Gep ellenfel = new Gep(nehezseg);
        Csatater csatater = new Csatater(player, ellenfel);
        if(csatater.elhelyez() == -2){
            return;
        }
        Display.showStatok(ellenfel);
        Display.waitForInput();
        Game game = new Game(player, ellenfel);
        game.play();
        //Display.showStatok(g);
    }

    static void multi(){

    }

    static void setup(){

    }

}
