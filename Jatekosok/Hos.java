package Jatekosok;

public class Hos {

    private int tamadas;
    private int vedekezes;
    private int varazsero;
    private int tudas;
    private int moral;
    private int szerencse;

    public Hos(){
        tamadas = 1;
        vedekezes = 1;
        varazsero = 1;
        tudas = 1;
        moral = 1;
        szerencse =1;
    }
    public Hos(int tamadas, int vedekezes, int varazsero, int tudas, int moral, int szerencse){
        this.tamadas = tamadas;
        this.vedekezes = vedekezes;
        this.varazsero = varazsero;
        this.tudas = tudas;
        this.moral = moral;
        this.szerencse =szerencse;
    }

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


    public String[] getStatok() {
        return new String[]{"tamadas: " + tamadas,
                "vedekezes: " + vedekezes,
                "varazsero: " + varazsero,
                "tudas: " + tudas,
                "moral: " + moral,
                "szerencse: " + szerencse};
    }
}
