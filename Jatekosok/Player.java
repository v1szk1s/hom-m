package Jatekosok;
import Varazslatok.*;

import java.util.ArrayList;
import java.util.List;


import Display.Color;
import Display.Position;
import Egysegek.*;
import Log.Log;

/**
 * Ez az ososztalya az osszes jatekosnak.
 * Ez az osztaly eleg sokmindent kezel, a jatekos egysegeit, varazslatait is ez az osztaly kezeli,
 * uj egyseg vetelet is ez az osztaly kezeli.
 */
public class Player {
    private int arany;
    private int tulajdonsagAr;
    private Hos hos;
    private List<Egyseg> egysegek;
    String nev;

    public Player(int szint){
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
        hos = new Hos(this);
        egysegek = new ArrayList<>();
        nev = "Player";
    }

    public boolean isMindenkiElhelyezve(){
        for(var e:getEgysegek()){
            if(e.getPos().getY() == -1 || e.getPos().getX() == -1){
                return false;
            }
        }
        return true;
    }
    public String elhelyez(Egyseg e, int num){
        Position pos = Position.convertToPos(num);

        if(getEgysegOnPosition(pos) == null){
            for(var egyseg: egysegek){
                if(e.equals(egyseg)){
                    egyseg.setPos(pos);;
                    return "";
                }
                //Log.log(e.getNev() + ", " + egyseg.getNev());
            }
        }
        return "Ezen a mezon mar vannak.";
    }

    public String elhelyez(Egyseg e, Position pos){

        if(pos.getY()< 0 || pos.getY() > 11  || pos.getX() < 0 || pos.getX() > 9){
            return "Nem lehet ide lepni, mivel nincs a palyan!";
        }

        if(getEgysegOnPosition(pos) == null){
            for(var egyseg: egysegek){
                if(e.equals(egyseg)){
                    egyseg.setPos(pos);;
                    return "";
                }
                //Log.log(e.getNev() + ", " + egyseg.getNev());
            }
        }
        return "Ezen a mezon mar vannak.";
    }

    public Egyseg getEgysegOnPosition(Position pos){
        for(var e: egysegek){
            if(e.getPos().getY() == pos.getY() && e.getPos().getX() == pos.getX() && e.getEletero() > 0){
                return e;
            }
        }
        return null;
    }
    public Egyseg getHalottEgysegOnPosition(Position pos){
        for(var e: egysegek){
            if(e.getPos().getY() == pos.getY() && e.getPos().getX() == pos.getX() && e.getEletero() == 0){
                return e;
            }
        }
        return null;
    }

    public Egyseg getHalottEgysegOnPosition(int num){
        Position pos = Position.convertToPos(num);
        for(var e: egysegek){
            if(e.getPos().getY() == pos.getY() && e.getPos().getX() == pos.getX() && e.getEletero() == 0){
                return e;
            }
        }
        return null;
    }

    public Egyseg getEloEgysegOnPosition(int num){
        Position pos = Position.convertToPos(num);
        for(var e: egysegek){
            if(e.getPos().getY() == pos.getY() && e.getPos().getX() == pos.getX() && e.getEletero() > 0){
                return e;
            }
        }
        return null;
    }
    public Egyseg getEloEgysegOnPosition(Position pos){
        for(var e: egysegek){
            if(e.getPos().getY() == pos.getY() && e.getPos().getX() == pos.getX() && e.getEletero() > 0){
                return e;
            }
        }
        return null;
    }
    public Egyseg getEloEgysegOnPosition(int y, int x){
        Position pos = new Position(y, x);
        for(var e: egysegek){
            if(e.getPos().getY() == pos.getY() && e.getPos().getX() == pos.getX() && e.getEletero() > 0){
                return e;
            }
        }
        return null;
    }

    public Egyseg getEgysegOnPosition(int num){
        Position pos = Position.convertToPos(num);
        for(var e: egysegek){
            if(e.getPos().getY() == pos.getY() && e.getPos().getX() == pos.getX()){
                return e;
            }
        }
        return null;
    }
    public Egyseg getEgysegOnPosition(int y, int x){
        Position pos = Position.convertToPos(y,x);
        for(var e: egysegek){
            if(e.getPos().getY() == pos.getY() && e.getPos().getX() == pos.getX()){
                return e;
            }
        }
        return null;
    }

    public String getInfo(int hanyadik){
        ArrayList<String> most = new ArrayList<>();
        most.add(nev + ":");
        most.add("Mana: " + getHos().getMana() + " │" + Color.BLUE + "|".repeat(getHos().getMana()/2) + Color.RESET + " ".repeat((getHos().getMaxMana()-getHos().getMana())/2) + "│");
        for(var e:egysegek){
            if(e.isEl() == false){
                continue;
            }
            int db = e.getMennyiseg();
            int hp = e.getEletero();
            String minBar = hp > 0 ? Color.RED + "|" + Color.RESET:"";
            most.add(e.getNev() + " HP: │" + minBar + Color.RED + "|".repeat(Math.max(0, e.getEletero()/70-Integer.toString(hp).length()/2)) + Color.RESET+ " " + hp + " " + Color.RED +"|".repeat(Math.max(0,e.getEletero()/70-Integer.toString(hp).length()/2)) + minBar + Color.RESET + "│");
                //System.out.println(margo + player.getEgysegek()[i].getNev() + ":\t" + player.getEgysegek()[i].getMennyiseg());
            
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Varazslatok: ");
        
        int i = 0;
        if(getHos().getVarazslatok().size() > 0){
            builder.append(getHos().getVarazslatok().get(0).getNev());
            for(var v:getHos().getVarazslatok()){
                if(i==0){
                    i++;
                    continue;
                }
                builder.append(", " + v.getNev());
            }
        }
        most.add(builder.toString());
        return hanyadik< most.size()? most.get(hanyadik):"";
    }

    public boolean isLost(){
        
        return getEloEgysegek().size() == 0;
    }


    public List<Egyseg> getEgysegek(){
       return egysegek;
    }

    public List<Egyseg> getEloEgysegek(){
        List<Egyseg> lista = new ArrayList<>();
        for(var v:egysegek){
            if(v.getEletero() > 0){
                lista.add(v);
            }
        }
        return lista;
    }

    public List<Egyseg> getHalottEgysegek(){
        List<Egyseg> lista = new ArrayList<>();
        for(var v:egysegek){
            if(v.getEletero() == 0){
                lista.add(v);
            }
        }
        return lista;
    }

    public String buyEgyseg(Egyseg egyseg, int mennyi){
        
        if(egyseg.getAr()*mennyi > arany){
            return Color.RED + "Nincs eleg aranyad!" + Color.RESET;
        }
        for(var e:egysegek){
            if(e.equals(egyseg)){        
                e.addMennyiseg(mennyi);
                arany -= egyseg.getAr()* mennyi;
                return "";
            }
        }
        egyseg.setPlayer(this);
        egyseg.addMennyiseg(mennyi);

        arany -= egyseg.getAr() * mennyi;
        egysegek.add(egyseg);

        return "";
    }

    public int getEgysegSzam(){
        return egysegek.size();
    }

    public List<Integer> getEgysegPosok(){
        List<Integer> lista = new ArrayList<>();
        for(var v:getEgysegek()){
            lista.add(v.getNumPos());
        }
        return lista;
    }

    public List<Integer> getEloEgysegPosok(){
        List<Integer> lista = new ArrayList<>();
        for(var v:getEgysegek()){
            if(v.getEletero() > 0){
                lista.add(v.getNumPos());
            }
        }
        return lista;
    }

    public List<Integer> getHalottEgysegPosok(){
        List<Integer> lista = new ArrayList<>();
        for(var v:getEgysegek()){
            if(v.getEletero() == 0){
                lista.add(v.getNumPos());
            }

        }
        return lista;
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

    public void setNev(String nev){
        this.nev = nev;
    }

    public String getNev(){
        return nev;
    }

    public String getAranyStr(){
        return Integer.toString(arany);
    }
    public int getArany(){
        return arany;
    }
    public void setArany(int num){
        arany = Math.max(0, num);
    }
    public void koltArany(int num){
        setArany(getArany() - num);
    }

    public int getTulajdonsagAr() {
        return tulajdonsagAr;
    }


    public Hos getHos() {
        return hos;
    }

    public int maxMennyitVehet(Egyseg egyseg) {
        return arany / egyseg.getAr();
    }


    public boolean equals(Object o){
        if(!(o instanceof Player)){
            return false;
        }
        if(((Player)o).getNev() == nev){
            return true;
        }
        return false;
    }
    
}
