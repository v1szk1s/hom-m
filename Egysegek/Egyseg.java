package Egysegek;

import java.util.ArrayList;
import java.util.List;

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
    boolean isTavolsagi;
    Player kie;



    public Egyseg(String nev, int ar, int eletero, int sebesseg, int kezdemenyezes, Boolean isTavolsagi) {
        pos = new Position(-1, -1);
        this.nev = nev;
        this.ar = ar;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;

        this.isTavolsagi = isTavolsagi;
        osszElet = 0;
    }

    public void setPlayer(Player kie){
        this.kie = kie;
    }


    public boolean isTavolsagi(){
        return isTavolsagi;
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
        // if(osszElet % eletero > 0){
        //     return (osszElet / eletero)+1;
        // }
        return osszElet / eletero;
    }

    public String getNev() {
        return nev;
    }
    public int getAr() {
        return ar;
    }
    public abstract float getSebzes();

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

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Egyseg)){
            return false;
        }
        if(((Egyseg)o).getNev().equals(getNev()))
            return true;
        return false;
    }
    @Override
    public int hashCode(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(getNev());
        buffer.append(getAr());
        return buffer.toString().hashCode();
    }



    public void tamad(Egyseg kit){
        int mennyi = kit.getMennyiseg();
        boolean kritikus = false;
        if((double)getPlayer().getHos().getSzerencse()/20 > Math.random()){
            kritikus = true;
        }
        float sebzes = getSebzes() * (1+((float)getPlayer().getHos().getTamadas()/10));
        float vedekezes = 1 - ((float)kit.getPlayer().getHos().getvedekezes()/20);
        sebzes *= vedekezes;
        sebzes = kritikus? sebzes*2:sebzes;
        int vegsoSebzes = Math.round(sebzes);
        kit.setEletero(kit.getEletero()-vegsoSebzes);

        Log.log(getPlayer().getNev() + getNev() + " " + " csapata " + vegsoSebzes + " sebzest okozott Meghalt: " + (mennyi - kit.getMennyiseg()) + " ellensege " + kit.getNev());
    }

    public List<Egyseg> getSzomszedok(Player p){
        List<Egyseg> szomszedok = new ArrayList<>();
        int y = pos.getY();
        int x = pos.getX();

        if(y-1 >= 0 && x-1 >= 0 &&  p.getEgysegOnPosition(new Position(y-1, x-1)) != null){
            szomszedok.add(p.getEgysegOnPosition(new Position(pos.getY()-1, pos.getX()-1)));
        }
        if(y-1 >= 0 &&  p.getEgysegOnPosition(new Position(y-1, x)) != null){
            szomszedok.add(p.getEgysegOnPosition(new Position(pos.getY()-1, pos.getX())));
        }
        if(y-1 >= 0 && x+1 <= 11 && p.getEgysegOnPosition(new Position(y-1, x+1)) != null){
            szomszedok.add(p.getEgysegOnPosition(new Position(pos.getY()-1, pos.getX()+1)));
        }
        if(x-1 >= 0 &&  p.getEgysegOnPosition(new Position(y, x-1)) != null){
            szomszedok.add(p.getEgysegOnPosition(new Position(pos.getY(), pos.getX()-1)));
        }
        if(x+1 <= 11 &&  p.getEgysegOnPosition(new Position(y, x+1)) != null){
            szomszedok.add(p.getEgysegOnPosition(new Position(pos.getY(), pos.getX()+1)));
        }
        if(y+1 <= 9 &&  p.getEgysegOnPosition(new Position(y+1, x)) != null){
            szomszedok.add(p.getEgysegOnPosition(new Position(pos.getY()+1, pos.getX()-1)));
        }
        if(y+1 <= 9 && x-1 >= 0  && p.getEgysegOnPosition(new Position(y+1, x-1)) != null){
            szomszedok.add(p.getEgysegOnPosition(new Position(pos.getY()+1, pos.getX())));
        }
        if(y+1 <= 9 && x+1 <= 11 && p.getEgysegOnPosition(new Position(y+1, x+1)) != null){
            szomszedok.add(p.getEgysegOnPosition(new Position(pos.getY()+1, pos.getX()+1)));
        }
        
       
       
       
        
       
       

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
