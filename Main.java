import javax.swing.UIDefaults.ProxyLazyValue;

import Display.Csatater;
import Display.Display;
import Jatekosok.Gep;
import Jatekosok.Jatekos;
import Varazslatok.*;
import Log.Log;


class Main {

    public static void main(String[] args) {

        hack();        
        // int mode = Display.menu("Menu:", new String[]{"Egyjatekos", "Tobbjatekos"});
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
        p.buyEgyseg("1", 2);
        p.buyEgyseg("2", 66);
        Csatater.elhelyez(p);    
        Display.showStatok(g);
    }

    static void single(){
        int nehezseg = Display.menu("Kerlek valassz nehezsegi fokozatot:", new String[]{"Konnyu", "Kozepes", "Nehez"});
        if(nehezseg == -1){
            gameOver();
            return;
        }
        Jatekos player = new Jatekos(nehezseg);
        Display.levelSystem(player);
        Display.varazsShop(player);
        Display.egysegShop(player);
        Gep g = new Gep();
        Csatater.elhelyez(player);
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
