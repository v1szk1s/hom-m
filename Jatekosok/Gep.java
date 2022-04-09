package Jatekosok;

import java.util.ArrayList;
import java.util.Random;

import Display.Display;
import Display.Position;
import Egysegek.*;
import Varazslatok.AllVarazslat;

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
            getHos().buyVarazslat(AllVarazslat.getAllVarazslat()[1]);
            getHos().buyVarazslat(AllVarazslat.getAllVarazslat()[0]);
            
            buyEgyseg(new Griff(), maxMennyitVehet(new Griff())/2);
            elhelyez();
            // buyEgyseg(AllEgyseg.getAllEgyseg()[2], maxMennyitVehet(AllEgyseg.getAllEgyseg()[3])/2);
            // buyEgyseg(AllEgyseg.getAllEgyseg(), (int) (maxMennyitVehet(getAllEgyseg()[1]) * 0.75));
            // getHos().buyEgyseg("foldmuves", maxMennyitVehet(getAllEgyseg()[1]));
            //Random rnd = new Random();
            
        
    }

    public void play(Egyseg e){

    }

    public void elhelyez(){
        int i = 0;
        for(var e:this.getEgysegek()){
            e.setPos(Position.convertToPos(xd[8+i]));
            i++;
        }
        
    }    
}
