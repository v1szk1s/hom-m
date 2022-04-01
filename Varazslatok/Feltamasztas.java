package Varazslatok;

public class Feltamasztas extends Varazslat{
    public Feltamasztas(String nev, int ar, int mana) {
        super("Feltámasztás", 120, 6);
    }

    public int getSzorzo() {
        return 50;
    }
    public  static String info(){
        return "Feltámasztás:\n" +
                "LeírásEgy kiválasztott saját egység feltámasztása.\n" +
                "Ár: " + getAr() + "\n" +
                "Mana: " + getMana() + "\n\n";
    }

}
