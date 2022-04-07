import Display.Csatater;
import Display.Display;
import Jatekosok.*;
import Varazslatok.*;
import Log.Log;


class Main {

    public static void main(String[] args) {

        hack();        
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

        
        //single();
    }

    static void hack(){
        Gep g = new Gep();
        Jatekos p = new Jatekos(1);
        p.buyEgyseg("1", 100);
        p.buyEgyseg("2", 100);
        p.buyEgyseg("3", 2);
        p.elhelyez(p.getEgysegek()[0], 13);
        p.elhelyez(p.getEgysegek()[1], 38);
        p.elhelyez(p.getEgysegek()[2], 50);
        Csatater csatater = new Csatater(p, g);
        //csatater.elhelyez();
        //Display.showStatok(g);
        g.elhelyez(0);
        g.elhelyez(1);
        g.elhelyez(2);

        Game game = new Game(p, g);
        game.playKor();

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
