package Jatekosok;

import Varazslatok.*;

public class Jatekos {
    private int arany;
    private int tulajdonsagAr;
    private Hos hos;
    Varazslat[] varazslat;

    public Jatekos(int szint){
        switch (szint){
            case 3:
                this.arany = 700;
                break;
            case 2:
                this.arany = 1000;
                break;
            case 1:
                this.arany = 1300;
                break;
        }
        this.tulajdonsagAr = 5;
        hos = new Hos();
        varazslat[0] = new Tuzlabda();
    }

    public int levelUp(int melyik){
        if (arany >= tulajdonsagAr){
            if(hos.levelUp(melyik)){
                arany -= tulajdonsagAr;
                tulajdonsagAr = (int) Math.ceil((double)tulajdonsagAr*1.1);
                return 0;
            }
            return 2;
        }
        return 1;
    }
    public String getAranyStr(){
        return Integer.toString(arany);
    }
    public int getArany(){
        return arany;
    }

    public int getTulajdonsagAr() {
        return tulajdonsagAr;
    }

    public Hos getHos() {
        return hos;
    }
}
