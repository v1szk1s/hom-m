package Varazslatok;

public class Tuzlabda extends Varazslat {
    public static final String nev = "Tűzlabda";
    public static final int ar = 120;
    public static final int mana = 6;

    public int getSzorzo(){
        return 20;
    }
    public  static String[] info(){
        return new String[]{nev, "Leírás: Egy kiválasztott mező körüli 3x3-as területen lévő összes (saját, illetve ellenséges) egységre (varázserő * 20) sebzés okozása.", "Ár: " + Integer.toString(ar), "Mana: " + Integer.toString(mana)};
    }

}
