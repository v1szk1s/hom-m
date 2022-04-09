package Varazslatok;

import java.util.List;

public class AllVarazslat {

    private static Varazslat[] varazsok = new Varazslat[]{new Tuzlabda(), new Villamcsapas(), new Feltamasztas()};
    public static Varazslat[] getAllVarazslat(){
        return varazsok;
    }

}
