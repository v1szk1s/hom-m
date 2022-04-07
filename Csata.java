import Egysegek.*;
import Log.Log;

public class Csata{

    public boolean tamad(Egyseg kivel, Egyseg kit){

        boolean kritikus = false;
        if(kivel.getPlayer().getHos().getSzerencse()/20 > Math.random()){
            kritikus = true;
        }
        int mennyi = kit.getMennyiseg();
        double sebzes = kivel.getSebzes() * kivel.getPlayer().getHos().getTamadas()/10;
        sebzes = sebzes * (1 + (double)kit.getPlayer().getHos().getvedekezes()/20);

        sebzes = kritikus? sebzes*2:sebzes;
        int eletero = kit.getMennyiseg();
        kit.setEletero(Math.max(0, kit.getEletero()-(int)Math.round(sebzes)));
        Log.log(kivel.getPlayer().getNev() + kivel.getNev() + " " + " csapata " + sebzes + " sebzest okozott Meghalt: " + (mennyi - kit.getMennyiseg()) + " ellensege " + kit.getNev());
        return kritikus;
    }

}