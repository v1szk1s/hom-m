package Varazslatok;

public class Tuzlabda extends Varazslat {
    public static final String nev = "Tűzlabda";
    public static final int ar = 120;
    public static final int mana = 6;



    public int getSzorzo(){
        return 20;
    }
    public  static String[] info(){
        return new String[]{nev, "Leiras: Egy kivalasztott mezo körüli 3x3-as területen levo összes (sajat, illetve ellenseges) egysegre (varazsero * 20) sebzes okozasa.", "ar: " + Integer.toString(ar), "Mana: " + Integer.toString(mana)};
    }

}
