import Display.Csatater;
import Display.Display;
import Display.Position;
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
        //debug();
        //single();

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
        p.buyEgyseg(new Ijasz(), 50);
        //p.buyEgyseg(new Griff(), 0);
        p.buyEgyseg(new Griff(), 10);

        p.elhelyez(p.getEgysegek().get(0), 53);
        p.elhelyez(p.getEgysegek().get(1), 54);
        p.getEgysegek().get(1).sebez(999);
        // p.elhelyez(p.getEgysegek().get(1), 38);
        // p.elhelyez(p.getEgysegek().get(2), 50);
        p.getHos().buyVarazslat(new Villamcsapas());
        p.getHos().buyVarazslat(new Tuzlabda());
        p.getHos().buyVarazslat(new Feltamasztas());
        g.elhelyez();
        Game game = new Game(p, g);
        
        //game.play();
        for(var v:p.getEgysegek()){
            System.out.println(v.getEletero());
        }
        p.getEgysegek().get(1).addEletero(1);
        for(var v:p.getEgysegek()){
            System.out.println(v.getEletero());
        }
        // for(var v:p.getEgysegPosok()){
        //     System.out.println(v);
        // }
        // Csatater cs = new Csatater(p, g);
        // cs.refresh();
        // p.getEgysegek().get(0).tamad(g.getEgysegek().get(0));

        //System.out.println(p.getEgysegOnPosition(Position.convertToPos(54)));
        //System.out.println(p.getHos().getVarazslatok().get(0).getHos().hashCode());
        //p.getHos().getVarazslatok().get(0).varazsol(csatater);
        


    }

    static void hack(){
        Gep g = new Gep(2);
        Jatekos p = new Jatekos(1);
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
