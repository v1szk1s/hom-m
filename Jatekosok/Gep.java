package Jatekosok;

import Display.Display;

public class Gep extends Player{
    
    public static int[] utolsoKetSor = {11, 12, 23, 24, 35, 36, 47, 48, 59, 60, 71, 72, 83, 84, 95, 96, 107, 108, 119, 120};

    public Gep(){
        super(2);
        setNev("Ellenfel");
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){
                levelUp(i);
            }
            buyVarazslat(Display.getVarazslat((int)Math.random()*3));
            buyVarazslat(Display.getVarazslat((int)Math.random()*3));
            buyEgyseg("griff", maxMennyitVehet(getAllEgyseg()[2])/2);
            buyEgyseg("ijasz", (int) (maxMennyitVehet(getAllEgyseg()[1]) * 0.75));
            buyEgyseg("foldmuves", maxMennyitVehet(getAllEgyseg()[1]));
        }
    }





    
    
}
