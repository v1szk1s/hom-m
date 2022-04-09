import Display.Csatater;
import Display.Display;
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
        Gep g = new Gep();
        Jatekos p = new Jatekos(1);
        p.buyEgyseg("1", 100);
        p.buyEgyseg("2", 1);
        p.buyEgyseg("3", 2);
        p.elhelyez(p.getEgysegek()[0], 55);
        p.elhelyez(p.getEgysegek()[1], 38);
        p.elhelyez(p.getEgysegek()[2], 72);
        Csatater csatater = new Csatater(p, g);
        //csatater.elhelyez();
        g.elhelyez();
        Game game = new Game(p, g);
        Egyseg e = p.getEgysegek()[2];
        Csatater.showPalya(null, new Player[]{p, g});
        //System.out.println(e2.getUresMezok(g));
        System.out.println(e.getNev() + e.getPos());
        System.out.println(e.getSzomszedok(g));


    }

    static void hack(){
        Gep g = new Gep();
        Jatekos p = new Jatekos(1);
        p.buyEgyseg("1", 100);
        p.buyEgyseg("2", 100);
        p.buyEgyseg("3", 2);
        p.elhelyez(p.getEgysegek()[0], 55);
        p.elhelyez(p.getEgysegek()[1], 38);
        p.elhelyez(p.getEgysegek()[2], 50);
        Csatater csatater = new Csatater(p, g);
        //csatater.elhelyez();
        //Display.showStatok(g);
        g.elhelyez();
        Game game = new Game(p, g);
        
        while(true){
            if(p.isLost() || g.isLost()){
                Display.gg();
                return;
            }
            if(game.playKor() == false){
                //Display.gg();
                return;
            }
        }



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
        Display.levelSystem(player);
        Display.varazsShop(player);
        Display.egysegShop(player);
        Gep g = new Gep();
        Csatater csatater = new Csatater(player, g);
        csatater.elhelyez();
        Display.showStatok(g);
    }

    static void multi(){

    }

    static void setup(){

    }

    static void gameOver(){
        Display.gg();

    }
}
