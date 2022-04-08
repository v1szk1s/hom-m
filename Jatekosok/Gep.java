package Jatekosok;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Display.Display;
import Display.Position;
import Egysegek.Egyseg;

public class Gep extends Player{
    int[] xd = {11, 12, 23, 24, 35, 36, 47, 48, 59, 60, 71, 72, 83, 84, 95, 96, 107, 108, 119, 120};
    static ArrayList<Integer> utolsoKetSor = new ArrayList<Integer>();
    

    public Gep(){
        super(2);
        setNev("Ellenfel");
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){
                levelUp(i);
            }
            buyVarazslat(Display.getVarazslat(0));
            buyVarazslat(Display.getVarazslat(1));
            buyEgyseg("griff", maxMennyitVehet(getAllEgyseg()[2])/2);
            buyEgyseg("ijasz", (int) (maxMennyitVehet(getAllEgyseg()[1]) * 0.75));
            buyEgyseg("foldmuves", maxMennyitVehet(getAllEgyseg()[1]));
            Random rnd = new Random();
            
        }
    }

    public void play(Egyseg e){

    }

    public void elhelyez(int melyik){
        if(melyik < 0 || melyik > 2){
            return;
        }
        getEgysegek()[melyik].setPos(Position.convertToPos(xd[8+melyik]));
    }    
}
