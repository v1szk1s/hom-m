import Display.Csatater;
import Display.Display;
import Egysegek.AllEgyseg;
import Egysegek.Egyseg;
import Egysegek.Ijasz;
import Egysegek.Foldmuves;
import Egysegek.Griff;
import GameLogic.Game;
import Jatekosok.Player;
import Jatekosok.Hos;
import Jatekosok.Gep;
import Jatekosok.Jatekos;
import Varazslatok.*;
import Log.Log;


class Main {

    public static void main(String[] args) {
        hack();  
        debug();
          

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
        Gep g = new Gep(2);
        //p.buyEgyseg(AllEgyseg.getAllEgyseg()[0], 100);
        //p.buyEgyseg(AllEgyseg.getAllEgyseg()[1], 1);
        System.out.println(p.buyEgyseg(AllEgyseg.getAllEgyseg()[2], 86));
        System.out.println(p.getEgysegek().get(0).getMennyiseg());
        //p.elhelyez(p.getEgysegek().get(0), 55);
        //p.elhelyez(p.getEgysegek().get(0), 38);
        //p.elhelyez(p.getEgysegek().get(0), 72);
        //Csatater csatater = new Csatater(p, g);
        //csatater.elhelyez();
        //g.elhelyez();
        //Game game = new Game(p, g);
        //Egyseg e = p.getEgysegek().get(2);
        //Csatater.showPalya(null, new Player[]{p, g});
        //System.out.println(e2.getUresMezok(g));
       // System.out.println(e.getNev() + e.getPos());
        //System.out.println(e.getSzomszedok(g));


    }

    static void hack(){
        Gep g = new Gep(2);
        Jatekos p = new Jatekos(1);
        p.buyEgyseg(AllEgyseg.getAllEgyseg()[0], 100);
        p.buyEgyseg(AllEgyseg.getAllEgyseg()[1], 1);
        p.buyEgyseg(AllEgyseg.getAllEgyseg()[2], 2);
        p.elhelyez(p.getEgysegek().get(0), 55);
        p.elhelyez(p.getEgysegek().get(1), 38);
        p.elhelyez(p.getEgysegek().get(2), 50);
        Csatater csatater = new Csatater(p, g);
        //csatater.elhelyez();
        //Display.showStatok(g);
        Game game = new Game(p, g);
        game.play();
        



        //
        //game.playKor();
        //Csatater.showPalya(null,new Player[]{p, g});
    }



    static void single(){
        int nehezseg = Display.menuFullScreen("Kerlek valassz nehezsegi fokozatot:", new String[]{"Konnyu", "Kozepes", "Nehez"});
        if(nehezseg == -1){
            gameOver();
            return;
        }
        Jatekos player = new Jatekos(nehezseg);
        if(Display.levelSystem(player) == -2){
            return;
        }
        if(Display.varazsShop(player) == -1){
            return;
        }
        if(Display.egysegShop(player) == -2){
            return;
        }
        Gep ellenfel = new Gep(nehezseg);
        Csatater csatater = new Csatater(player, ellenfel);
        csatater.elhelyez();
        Game game = new Game(player, ellenfel);
        game.play();
        //Display.showStatok(g);
    }

    static void multi(){

    }

    static void setup(){

    }

    static void gameOver(){
        Display.gg();

    }
}
