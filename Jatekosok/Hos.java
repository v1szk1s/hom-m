package Jatekosok;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Display.Color;
import Display.Csatater;
import Egysegek.Egyseg;
import Log.Log;
import Varazslatok.Varazslat;

public class Hos {

    private int tamadas;
    private int vedekezes;
    private int varazsero;
    private int tudas;
    private int moral;
    private int szerencse;
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
        return tudas * 10;
    }
    public int getMoral(){
        return moral;
    }
    public int getSzerencse(){
        return szerencse;
    }
    public int[] getTul(){
        return new int[]{tamadas, vedekezes, varazsero, tudas, moral, szerencse};
    }
    public String[] getTulStr(){
        return new String[]{"Tamadas", "Vedekezes", "Varazserő", "Tudas", "Moral", "Szerencse"};
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
        return varazslatok.contains(varazs);
    }

    public boolean tudCselekedni(){
        if(korAmikorCsinaltValamit == Csatater.getKor()){
            
            return false;
        }
        return true;
    }

    public int tamad(Egyseg e){
        e.setEletero(e.getEletero()-(tamadas*10));
        korAmikorCsinaltValamit = Csatater.getKor();
        Log.log(tamadas*10 + " sebzes -> " +e.getPlayer().getNev() + " " + e.getNev());
        return 0;
    }

    public int varazsol(Varazslat varazs){
        if(varazs.getManaCost() > getMana()){
            return -1;
        }
        return 0;
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
