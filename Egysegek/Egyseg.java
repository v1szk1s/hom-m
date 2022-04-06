package Egysegek;
import Bolt.Veheto;

public abstract class Egyseg implements Veheto {
    protected String nev;
    protected int ar;
    protected int sebzes;
    protected int eletero;
    protected int sebesseg;
    protected int kezdemenyezes;
    protected int mennyiseg;
    private int x;
    private int y;


    public Egyseg(String nev, int ar, int eletero, int sebesseg, int kezdemenyezes) {
        x = -1;
        y = -1;
        this.nev = nev;
        this.ar = ar;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        mennyiseg = 0;
    }

    public void addMennyiseg(int mennyi){
        mennyiseg += mennyi;
    }

    public boolean helyez(int y, int x){
        if(y >= 0 && y <= 9 && x >= 0 && x <= 11){
            this.y = y;
            this.x = x;
            return true;
        }
        return false;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }

    public int getMennyiseg(){
        return mennyiseg;
    }

    public String getNev() {
        return nev;
    }
    public int getAr() {
        return ar;
    }
    public abstract int getSebzes();

    public int getEletero() {
        return eletero;
    }
    public int getSebesseg(){
        return sebesseg;
    }
    public int getKezdemenyezes(){
        return kezdemenyezes;
    }

    public abstract String getIcon();

    public static boolean equals(Egyseg e1, Egyseg e2){
        if(e1.getNev().equals(e2.getNev()))
            return true;
        return false;
    }

    public abstract String[] info();

}
