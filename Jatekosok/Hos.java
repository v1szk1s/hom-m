package Jatekosok;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Display.Color;
import Display.Csatater;
import Egysegek.Egyseg;
import IO.IO;
import Log.Log;
import Varazslatok.Varazslat;

public class Hos {

    private int tamadas;
    private int vedekezes;
    private int varazsero;
    private int tudas;
    private int moral;
    private int szerencse;
    private int mana;
    private int maxMana;
    List<Varazslat> varazslatok;
    Player kie;
    private int korAmikorCsinaltValamit = -1;

    public Hos(Player kie){
        tamadas = 1;
        vedekezes = 1;
        varazsero = 1;
        tudas = 1;
        moral = 1;
        szerencse =1;
        this.kie = kie;
        varazslatok = new ArrayList<>();
        mana = tudas * 10;
        maxMana = tudas * 10;
    }
    // public Hos(int tamadas, int vedekezes, int varazsero, int tudas, int moral, int szerencse){
    //     this.tamadas = tamadas;
    //     this.vedekezes = vedekezes;
    //     this.varazsero = varazsero;
    //     this.tudas = tudas;
    //     this.moral = moral;
    //     this.szerencse =szerencse;
    //     varazslatok = new HashSet<>();
    // }

    public boolean levelUp(int melyiket){
        switch (melyiket){
            case 0:
                return incTamadas();
            case 1:
                return incVedekezes();
            case 2:
                return incVarazsero();
            case 3:
                return incTudas();
            case 4:
                return incMoral();
            case 5:
                return incSzerencse();
            default:
                return false;
        }

    }
    private boolean incTamadas(){
        if(tamadas < 10){
            tamadas++;
            return true;
        }
        return false;
    }
    private boolean incVedekezes(){
        if(vedekezes < 10){
            vedekezes++;
            return true;
        }
        return false;
    }
    private boolean incVarazsero(){
        if(varazsero < 10){
            varazsero++;
            return true;
        }
        return false;
    }
    private boolean incTudas(){
        if(tudas < 10){
            tudas++;
            maxMana = tudas *10;
            mana = tudas *10;
            return true;
        }
        return false;
    }
    private boolean incMoral(){
        if(moral < 10){
            moral++;
            return true;
        }
        return false;
    }
    private boolean incSzerencse(){
        if(szerencse < 10){
            szerencse++;
            return true;
        }
        return false;
    }

    public int getTamadas(){
        return tamadas;
    }
    public int getvedekezes(){
        return vedekezes;
    }
    public int getVarazsero(){
        return varazsero;
    }
    public int getMana(){
        return mana;
    }
    public int getMaxMana(){
        return maxMana;
    }
    public int getMoral(){
        return moral;
    }
    public int getSzerencse(){
        return szerencse;
    }

    public void setMana(int num){
        mana = Math.max(0, num);
    }

    public void koltMana(int num){
        setMana(getMana()-num);
    }

    public int[] getTul(){
        return new int[]{tamadas, vedekezes, varazsero, tudas, moral, szerencse};
    }
    public String[] getTulStr(){
        return new String[]{"Tamadas", "Vedekezes", "Varazsero", "Tudas", "Moral", "Szerencse"};
    }

    public boolean tudVarazsolni(Varazslat varazs){
        if(varazs.getManaCost() <= getMana())
             return true;

        return false;
    }

    public String buyVarazslat(Varazslat varazs){

        if(varazs.getAr() > kie.getArany()){
            return "Nincs eleg aranyod!";
        }
        if(varazslatok.contains(varazs)){
            return Color.RED + "Ezt a varazslatot mar megvetted!" + Color.WHITE;
        }
        varazs.setHos(this);
        varazslatok.add(varazs);
        return "";
    }

    public List<Varazslat> getVarazslatok(){
        return varazslatok;
    }

    public List<Varazslat> getJoVarazslatok(){
        List<Varazslat> lista = new ArrayList<>();
        for(var v:varazslatok){
            if(tudVarazsolni(v)){
                lista.add(v);
            }
        }
        return lista;
    }


    public boolean vanVarazslat(Varazslat varazs){
        if(varazslatok.size() == 0 ){
            return false;
        }
        for(var v:varazslatok){
            if(v.getNev().equals(varazs.getNev())){
                return true;
            }
        }
        return false;
    }

    public boolean tudCselekedni(){
        if(korAmikorCsinaltValamit == Csatater.getKor()){
            
            return false;
        }
        return true;
    }

    public void setKorAmikorCsinaltValamit(int num){
        this.korAmikorCsinaltValamit = num;
    }

    public int tamad(Egyseg e){
        e.setEletero(e.getEletero()-(tamadas*10));
        korAmikorCsinaltValamit = Csatater.getKor();
        Log.log(kie.getNev() + " " + tamadas*10 + " sebzes okozott " +e.getPlayer().getNev() + " " + e.getNev() + " egysegere");
        return 0;
    }
    public Player getPlayer(){
        return kie;
    }

    public String[] getStatok() {
        return new String[]{"Tamadas: " + tamadas,
                "Vedekezes: " + vedekezes,
                "Varazsero: " + varazsero,
                "Tudas: " + tudas,
                "Moral: " + moral,
                "Szerencse: " + szerencse};
    }
}
