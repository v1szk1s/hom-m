package Egysegek;

import Bolt.Veheto;
import Display.Position;
import Jatekosok.Player;
import Log.Log;

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

    public boolean helyez(int num){
        Position pos = Position.convertToPos(num);
        if(pos.getY() >= 0 && pos.getY() <= 9 && pos.getX() >= 0 &&  pos.getX() <= 11){
            Log.log(kie.getNev() + " " + getNev() + " egysege lepett: " + Position.convertToSzam(this.pos) + "->" + num);
            this.pos = pos;
            return true;
        }
        return false;
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
    public int getNumPos(){
        return pos.getY() * 12 + pos.getX() + 1;
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

    public void tamad(Egyseg kit){
        boolean kritikus = false;
        if(getPlayer().getHos().getSzerencse()/20 > Math.random()){
            kritikus = true;
        }
        int mennyi = kit.getMennyiseg();
        double sebzes = getSebzes() * getPlayer().getHos().getTamadas()/10;
        sebzes = sebzes * (1 + (double)kit.getPlayer().getHos().getvedekezes()/20);

        sebzes = kritikus? sebzes*2:sebzes;
        int eletero = kit.getMennyiseg();
        kit.setEletero(Math.max(0, kit.getEletero()-(int)Math.round(sebzes)));
        Log.log("itt");
        Log.log(getPlayer().getNev() + getNev() + " " + " csapata " + sebzes + " sebzest okozott Meghalt: " + (mennyi - kit.getMennyiseg()) + " ellensege " + kit.getNev());
    }

    public int getSzomszedok(Player p){
        int szomszedok = 0;
        szomszedok += p.getEgysegOnPosition(new Position(pos.getY()-1, pos.getX()-1)) == null?0:1;
        szomszedok += p.getEgysegOnPosition(new Position(pos.getY()-1, pos.getX())) == null?0:1;
        szomszedok += p.getEgysegOnPosition(new Position(pos.getY()-1, pos.getX()+1)) == null?0:1;
        szomszedok += p.getEgysegOnPosition(new Position(pos.getY(), pos.getX()-1)) == null?0:1;
        szomszedok += p.getEgysegOnPosition(new Position(pos.getY(), pos.getX()+1)) == null?0:1;
        szomszedok += p.getEgysegOnPosition(new Position(pos.getY()+1, pos.getX()-1)) == null?0:1;
        szomszedok += p.getEgysegOnPosition(new Position(pos.getY()+1, pos.getX())) == null?0:1;
        szomszedok += p.getEgysegOnPosition(new Position(pos.getY()+1, pos.getX()+1)) == null?0:1;
        return szomszedok;
    }
    public int getUresMezok(Player p2){
        int x = pos.getX();
        int y = pos.getY();
        int uresek = 0;
        if(y-1 >= 0 && x-1 >= 0 && (kie.getEgysegOnPosition(new Position(y-1, x-1)) == null && p2.getEgysegOnPosition(new Position(y-1, x-1)) == null)){
            uresek++;
        }
        if(y-1 >= 0 && (kie.getEgysegOnPosition(new Position(y-1, x)) == null && p2.getEgysegOnPosition(new Position(y-1, x)) == null)){
            uresek++;
        }
        if(y-1 >= 0 && x+1 <= 11 && (kie.getEgysegOnPosition(new Position(y-1, x+1)) == null && p2.getEgysegOnPosition(new Position(y-1, x+1)) == null)){
            uresek++;
        }
        if(x-1 >= 0 && (kie.getEgysegOnPosition(new Position(y, x-1)) == null && p2.getEgysegOnPosition(new Position(y, x-1)) == null)){
            uresek++;
        }
        if(x+1 <= 11 && (kie.getEgysegOnPosition(new Position(y, x+1)) == null && p2.getEgysegOnPosition(new Position(y, x+1)) == null)){
            uresek++;
        }
        if(y+1 <= 9 && (kie.getEgysegOnPosition(new Position(y+1, x)) == null && p2.getEgysegOnPosition(new Position(y+1, x)) == null)){
            uresek++;
        }
        if(y+1 <= 9 && x-1 >= 0 && (kie.getEgysegOnPosition(new Position(y+1, x-1)) == null && p2.getEgysegOnPosition(new Position(y+1, x-1)) == null)){
            uresek++;
        }
        if(y+1 <= 9 && x+1 <= 11 && (kie.getEgysegOnPosition(new Position(y+1, x+1)) == null && p2.getEgysegOnPosition(new Position(y+1, x+1)) == null)){
            uresek++;
        }
        return uresek;
    }
    public abstract String[] info();
    public abstract boolean tudTamadni(Player p);

}
