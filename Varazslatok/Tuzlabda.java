package Varazslatok;

public class Tuzlabda extends Varazslat {
    public Tuzlabda(String nev, int ar, int mana) {
        super("Tűzlabda", 120, 9);
    }

    public int getSzorzo(){
        return 20;
    }
    public  static String info(){
        return "Tűzlabda:\n" +
                "Leírás: Egy kiválasztott mező körüli 3x3-as területen lévő összes (saját, illetve ellenséges) egységre (varázserő * 20) sebzés okozása.\n" +
                "Ár: " + getAr() + "\n" +
                "Mana: " + getMana() + "\n\n";
    }

}
