package Varazslatok;

public class Villamcsapas extends Varazslat{
    public Villamcsapas(String nev, int ar, int mana) {
        super("Villamcsapás", 60, 5);
    }

    public int getSzorzo() {
        return 30;
    }
    public  static String info(){
        return "Villámcsapás:\n" +
                "Leírás: Egy kiválasztott ellenséges egységre (varázserő * 30) sebzés okozása\n" +
                "Ár: " + getAr()+ "\n" +
                "Mana: " + getMana() + "\n\n";
    }
}
