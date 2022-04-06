package Egysegek;

public class Griff extends Egyseg{
    public Griff(){
        super("Griff", 15, 30, 7, 15);
    }

    public int getSebzes(){
        return mennyiseg * (int)Math.random()*6+5;
    }

    public String getIcon(){
        return "G";
    }

    public String[] info(){
        return new String[]{nev, "ar: " + ar, "sebzes: 5-15" + "\teletero: " + eletero, "sebesseg: " + sebesseg + "\tkezdemenyezes: " + kezdemenyezes};
    }
}
