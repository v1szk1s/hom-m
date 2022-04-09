package Jatekosok;
import Varazslatok.*;

import java.util.ArrayList;
import java.util.List;

import Bolt.Veheto;
import Display.Color;
import Display.Position;
import Egysegek.*;
import Log.Log;

public class Player {
    private int arany;
    private int tulajdonsagAr;
    private Hos hos;
//    private Varazslat[] varazslatok;
    private List<Egyseg> egysegek;
    String nev;
    Player ellenfel;

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

    public void setEllenfel(Player ellenfel){
        this.ellenfel = ellenfel;
    }

    public Player getEllenfel(){
        return ellenfel;
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
        System.out.println("" +num + pos);

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
    public Egyseg getEgysegOnPosition(int num){
        Position pos = Position.convertToPos(num);
        for(var e: egysegek){
            if(e.getPos().getY() == pos.getY() && e.getPos().getX() == pos.getX() && e.getEletero() > 0){
                return e;
            }
        }
        return null;
    }

    public String getInfo(int hanyadik){
        ArrayList<String> most = new ArrayList<>();
        most.add(nev + ":");
        most.add("");
        most.add("Mana: " + getHos().getMana() + "[" +"#".repeat(Math.round(getHos().getMana()/10)) + "]");
        most.add("");
        for(var e:egysegek){
            int db = e.getMennyiseg();
            int hp = e.getEletero();
            most.add(e.getNev() + ": \t" + db + " db\t" + hp + " hp");
                //System.out.println(margo + player.getEgysegek()[i].getNev() + ":\t" + player.getEgysegek()[i].getMennyiseg());
            
        }
        most.add("");
        most.add("");
        most.add("Varazslatok: ");
        most.add("");
        int i = 0;
        if(getHos().getVarazslatok().size() > 0){
            for(var v:getHos().getVarazslatok()){
                most.add(v.getNev());
                i++;
            }
        }

        if(i == 0){
            most.add("-");
        }
        try{
            return most.get(hanyadik);
        }catch(Exception e){}
        return "";
    }

    public boolean isLost(){
        for(var egyseg:egysegek){
            if(egyseg.getEletero() > 0){
                return false;
            }
        }
        return true;
    }


    public List<Egyseg> getEgysegek(){
       return egysegek;
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

    public int maxMennyitVehet(Veheto valami){
        return arany / valami.getAr();
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

    public int getTulajdonsagAr() {
        return tulajdonsagAr;
    }


    public Hos getHos() {
        return hos;
    }



    
}
