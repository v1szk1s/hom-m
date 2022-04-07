package Egysegek;
import Bolt.Veheto;
import Display.Position;
import Jatekosok.Player;

public abstract class Egyseg implements Veheto {
    protected String nev;
    protected int ar;
    protected int sebzes;
    protected int eletero;
    private int osszElet;
    protected int sebesseg;
    protected int kezdemenyezes;
    private Position pos;
    Player kie;


    public Egyseg(String nev, int ar, int eletero, int sebesseg, int kezdemenyezes, Player kie) {
        pos = new Position(-1, -1);
        this.nev = nev;
        this.ar = ar;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        this.kie = kie;
        osszElet = 0;
    }

    public Player getPlayer(){
        return kie;
    }

    public void addMennyiseg(int mennyi){
        osszElet += mennyi * eletero;
    }

    public boolean helyez(Position pos){
        if(pos.getY() >= 0 && pos.getY() <= 9 && pos.getX() >= 0 &&  pos.getX() <= 11){
            this.pos = pos;
            return true;
        }
        return false;
    }

    public void setPos(Position pos){
        this.pos = pos;
    }

    public Position getPos(){
        return pos;
    }


    public int getMennyiseg(){
        if(osszElet == 0){
            return 0;
        }
        return osszElet / eletero;
    }

    public String getNev() {
        return nev;
    }
    public int getAr() {
        return ar;
    }
    public abstract int getSebzes();

    public int getEletero() {
        return osszElet;
    }
    public int getSebesseg(){
        return sebesseg;
    }
    public int getKezdemenyezes(){
        return kezdemenyezes;
    }

    public void setEletero(int num){
        osszElet = Math.max(num, 0);
    }

    // public void setMennyiseg(int num){
    //     osszElet = Math.max(eletero-num, 0);
    // }

    public boolean isEl(){
        return eletero > 0;
    }

    public abstract String getIcon();

    public static boolean equals(Egyseg e1, Egyseg e2){
        if(e1.getNev().equals(e2.getNev()))
            return true;
        return false;
    }

    public abstract String[] info();

}
