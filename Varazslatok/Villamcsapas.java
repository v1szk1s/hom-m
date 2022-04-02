package Varazslatok;

public class Villamcsapas extends Varazslat{
    public static final String nev = "Tűzlabda";
    public static final int ar = 120;
    public static final int mana = 6;

    public int getSzorzo() {
        return 30;
    }
    public  static String[] info(){
        return new String[]{"Villámcsapás:\n", "Leírás: Egy kiválasztott ellenséges egységre (varázserő * 30) sebzés okozása\n", "Ár: ", "Mana: "};
    }
}
