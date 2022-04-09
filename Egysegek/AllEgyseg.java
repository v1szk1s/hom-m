package Egysegek;

public class AllEgyseg {

    private static final Egyseg[] allEgyseg= new Egyseg[]{new Foldmuves(), new Ijasz(), new Griff()};
    
    public static Egyseg[] getAllEgyseg(){

        return allEgyseg;
    }
}
