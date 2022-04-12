package Egysegek;

import java.util.ArrayList;
import java.util.List;

import Bolt.Veheto;
import Display.Color;
import Display.Csatater;
import Display.Position;
import GameLogic.Tavolsag;
import Jatekosok.Player;
import Log.Log;

/**
 * A jatekban szereplo egysegek ososztalya.
 * Itt sok hasznos funkcio talalhato, ez kezeli a tamadast, vissza tamadast, es rengeteg szukseges dolgot ami a szamitashokhoz kellhet.
 */
public abstract class Egyseg implements Veheto {
    protected String nev;
    protected int ar;
    protected int sebzes;
    protected int eletero;
    private int maxElet;
    private int osszElet;
    protected int sebesseg;
    protected float kezdemenyezes;
    private Position pos;
    private boolean isTavolsagi;
    int korAmikorVisszatamadt = -1;
    Player kie;



    public Egyseg(String nev, int ar, int eletero, int sebesseg, int kezdemenyezes, Boolean isTavolsagi) {
        pos = new Position(-1, -1);
        this.nev = nev;
        this.ar = ar;
        this.eletero = eletero;
        maxElet = eletero;
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
        maxElet = osszElet;
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
            Log.log(kie.getNev() + " " + getNev() + " lepett: " + Position.convertToSzam(this.pos) + "->" + Position.convertToSzam(pos));
            this.pos = pos;
            return true;
        }
        return false;
    }

    public void setPos(Position pos){
        this.pos = pos;
    }

    //public boolean move(){}

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
        if(osszElet % eletero > 0){
            return (osszElet / eletero)+1;
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
    public double getKezdemenyezes(){
        return kezdemenyezes * getPlayer().getHos().getMoral();
    }

    public void setEletero(int num){
        osszElet = Math.max(0, num);
    }

    public void addEletero(int num){
        //Log.log("num: " + num + "eletero: " + eletero);
        setEletero(Math.min((osszElet+num), maxElet));
    }

    public void sebez(int num){
        setEletero(getEletero() - num);
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
        
        float sebzes = getSebzes();
        //System.out.println(sebzes);
        sebzes *= (1+((float)getPlayer().getHos().getTamadas()/10));
        //System.out.println(sebzes);
        float vedekezes = 1 - ((float)kit.getPlayer().getHos().getvedekezes()/20);
        sebzes *= vedekezes;
        sebzes = kritikus? sebzes*2:sebzes;
        int vegsoSebzes = Math.round(sebzes);
        kit.setEletero(kit.getEletero()-vegsoSebzes);
        if(kritikus){
            Log.log(getPlayer().getNev() + " " + getNev() + ":" + Color.RED_BACKGROUND + " Kritikus talalat!");
            Log.log(Color.RESET + Color.sebzesColor() + vegsoSebzes + " sebzest" + Color.RESET + " okozott Meghalt: " + (mennyi - kit.getMennyiseg()) + " " + kit.getNev());
        }else {
            Log.log(getPlayer().getNev() + " " + getNev() + ": " + Color.sebzesColor() + vegsoSebzes + " sebzest" + Color.RESET + " okozott Meghalt: " + (mennyi - kit.getMennyiseg()) + " " + kit.getNev());
        }
        
       
       if(kit.getEletero() > 0)
            kit.visszaTamad(this);
    }
    public void visszaTamad(Egyseg kit){
        if(isTavolsagi || kit.isTavolsagi()){
            return;
        }
        if(korAmikorVisszatamadt == Csatater.getKor()){
            return;
        }
        korAmikorVisszatamadt = Csatater.getKor();
        if(this instanceof Griff){
            korAmikorVisszatamadt = -1;
        }

        korAmikorVisszatamadt = Csatater.getKor();
        int mennyi = kit.getMennyiseg();

        boolean kritikus = false;
        if((double)getPlayer().getHos().getSzerencse()/20 > Math.random()){
            kritikus = true;
        }
        
        float sebzes = getSebzes();
        // System.out.println(sebzes);
        sebzes *= (1+((float)getPlayer().getHos().getTamadas()/10));
        // System.out.println(sebzes);
        float vedekezes = 1 - ((float)kit.getPlayer().getHos().getvedekezes()/20);
        sebzes *= vedekezes;
        sebzes = kritikus? sebzes*2:sebzes;
        int vegsoSebzes = Math.round(sebzes/2);
        kit.setEletero(kit.getEletero()-vegsoSebzes);

        if(kritikus){
            Log.log("Kritikus visszatamadas!", true);
        }else{
            Log.log(getPlayer().getNev() + " " + getNev() + " visszatamadas: " + vegsoSebzes + " sebzest okozott Meghalt: " + (mennyi - kit.getMennyiseg()) + " " + kit.getNev());
        }
        
    }

    /**
     * Visszadja az adott egyseg ellenseges szomszedait  
     * @param ellenseg Ellenseges ellenseg.ayer
     * @return  osszes szomszedos ellenseges egyseg
     */
    public List<Egyseg> getSzomszedok(Player ellenseg){
        List<Egyseg> szomszedok = new ArrayList<>();
        int y = pos.getY();
        int x = pos.getX();
        // System.out.println(y+ " " + x);
        // System.out.println(ellenseg.getEloEgysegOnPosition(4, 10));

        if(y-1 >= 0 && x-1 >= 0 &&  ellenseg.getEloEgysegOnPosition(y-1, x-1) != null){
            szomszedok.add(ellenseg.getEloEgysegOnPosition(y-1, x-1));
        }
        if(y-1 >= 0 &&  ellenseg.getEloEgysegOnPosition(y-1, x) != null){
            szomszedok.add(ellenseg.getEloEgysegOnPosition(y-1, x));
        }
        if(y-1 >= 0 && x+1 <= 11 && ellenseg.getEloEgysegOnPosition(y-1, x+1) != null){
            szomszedok.add(ellenseg.getEloEgysegOnPosition(y-1, x+1));
        }
        if(x-1 >= 0 &&  ellenseg.getEloEgysegOnPosition(y, x-1) != null){
            szomszedok.add(ellenseg.getEloEgysegOnPosition(y, x-1));
        }
        if(x+1 <= 11 &&  ellenseg.getEloEgysegOnPosition(y, x+1) != null){
            szomszedok.add(ellenseg.getEloEgysegOnPosition(y, x+1));
        }
        if(y+1 <= 9 &&  ellenseg.getEloEgysegOnPosition(y+1, x) != null){
            szomszedok.add(ellenseg.getEloEgysegOnPosition(y+1, x));
        }
        if(y+1 <= 9 && x-1 >= 0  && ellenseg.getEloEgysegOnPosition(y+1, x-1) != null){
            szomszedok.add(ellenseg.getEloEgysegOnPosition(y+1, x-1));
        }
        if(y+1 <= 9 && x+1 <= 11 && ellenseg.getEloEgysegOnPosition(y+1, x+1) != null){
            szomszedok.add(ellenseg.getEloEgysegOnPosition(y+1, x+1));
        }
        
        return szomszedok;
    }
    public int getUresMezok(Player ellenseg){
        int x = pos.getX();
        int y = pos.getY();
        int uresek = 0;
        if(y-1 >= 0 && x-1 >= 0 && (kie.getEloEgysegOnPosition(new Position(y-1, x-1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y-1, x-1)) == null)){
            uresek++;
        }
        if(y-1 >= 0 && (kie.getEloEgysegOnPosition(new Position(y-1, x)) == null && ellenseg.getEloEgysegOnPosition(new Position(y-1, x)) == null)){
            uresek++;
        }
        if(y-1 >= 0 && x+1 <= 11 && (kie.getEloEgysegOnPosition(new Position(y-1, x+1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y-1, x+1)) == null)){
            uresek++;
        }
        if(x-1 >= 0 && (kie.getEloEgysegOnPosition(new Position(y, x-1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y, x-1)) == null)){
            uresek++;
        }
        if(x+1 <= 11 && (kie.getEloEgysegOnPosition(new Position(y, x+1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y, x+1)) == null)){
            uresek++;
        }
        if(y+1 <= 9 && (kie.getEloEgysegOnPosition(new Position(y+1, x)) == null && ellenseg.getEloEgysegOnPosition(new Position(y+1, x)) == null)){
            uresek++;
        }
        if(y+1 <= 9 && x-1 >= 0 && (kie.getEloEgysegOnPosition(new Position(y+1, x-1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y+1, x-1)) == null)){
            uresek++;
        }
        if(y+1 <= 9 && x+1 <= 11 && (kie.getEloEgysegOnPosition(new Position(y+1, x+1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y+1, x+1)) == null)){
            uresek++;
        }
        return uresek;
    }

    public List<Position> getUresMezoPosok(Player ellenseg){
        List<Position> lista = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();

        if(y-1 >= 0 && x-1 >= 0 && (kie.getEloEgysegOnPosition(new Position(y-1, x-1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y-1, x-1)) == null)){
            lista.add(new Position(y-1, x-1));
        }
        if(y-1 >= 0 && (kie.getEloEgysegOnPosition(new Position(y-1, x)) == null && ellenseg.getEloEgysegOnPosition(new Position(y-1, x)) == null)){
            lista.add(new Position(y-1, x));
        }
        if(y-1 >= 0 && x+1 <= 11 && (kie.getEloEgysegOnPosition(new Position(y-1, x+1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y-1, x+1)) == null)){
            lista.add(new Position(y-1, x+1));
        }
        if(x-1 >= 0 && (kie.getEloEgysegOnPosition(new Position(y, x-1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y, x-1)) == null)){
            lista.add(new Position(y, x-1));
        }
        if(x+1 <= 11 && (kie.getEloEgysegOnPosition(new Position(y, x+1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y, x+1)) == null)){
            lista.add(new Position(y, x+1));
        }
        if(y+1 <= 9 && (kie.getEloEgysegOnPosition(new Position(y+1, x)) == null && ellenseg.getEloEgysegOnPosition(new Position(y+1, x)) == null)){
            lista.add(new Position(y+1, x));
        }
        if(y+1 <= 9 && x-1 >= 0 && (kie.getEloEgysegOnPosition(new Position(y+1, x-1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y+1, x-1)) == null)){
            lista.add(new Position(y+1, x-1));
        }
        if(y+1 <= 9 && x+1 <= 11 && (kie.getEloEgysegOnPosition(new Position(y+1, x+1)) == null && ellenseg.getEloEgysegOnPosition(new Position(y+1, x+1)) == null)){
            lista.add(new Position(y+1, x+1));
        }
        return lista;
    }



    public abstract String[] info();
    /**
     * Visszaadja, hogy az adott egyseg tud e tamadni
     * @param p Player akit tamadni akarunk
     * @return  ha tudunk tamadni return igaz, kulonben hamis
     */
    public abstract boolean tudTamadni(Player p);
    /**
     * 
     * @param p aki meghivta
     * @return
     */
    public List<Egyseg> getTamadhatoEgysegek(Player ellenfel){
        if(isTavolsagi){
            return ellenfel.getEloEgysegek();
        }

        return getSzomszedok(ellenfel);
       
    }

}
