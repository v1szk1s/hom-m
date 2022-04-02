package Varazslatok;

public class Feltamasztas extends Varazslat{
    public static final String nev = "Feltamasztas";
    public static final int ar = 120;
    public static final int mana = 6;

    public int getSzorzo() {
        return 50;
    }
    public  static String[] info(){
        return new String[]{nev, "Leírás: Egy kiválasztott saját egység feltámasztása.", ("Ár: " + Integer.toString(ar)), "Mana: " + Integer.toString(mana)};
    }

}