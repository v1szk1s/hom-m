package Egysegek;

public class Foldmuves extends Egyseg{
    public Foldmuves(){
        super("Foldmuves", 2, 3, 4, 8);
    }


    public int getSebzes(){
        return 1;
    }

    public String getIcon(){
        return "F";
    }

    public String[] info(){

        return new String[]{nev, "ar: " + ar, "sebzes: 1-1" + "\teletero: " + eletero, "sebesseg: " + sebesseg + "\tkezdemenyezes: " + kezdemenyezes};
    }
}
