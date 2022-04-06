package Egysegek;

public class Ijasz extends Egyseg {
    public Ijasz(){
        super("Ijasz", 6, 3, 4, 9);
    }
    
    public int getSebzes(){
        return mennyiseg * (int)Math.random()*3+2;
    }

    public String getIcon(){
        return "I";
    }

    public String[] info(){

        return new String[]{nev, "ar: " + ar, "sebzes: 2-4" + "\teletero: " + eletero, "sebesseg: " + sebesseg + "\tkezdemenyezes: " + kezdemenyezes};
    }

}