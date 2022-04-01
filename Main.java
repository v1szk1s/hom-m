import Varazslatok.Feltamasztas;
import Varazslatok.Tuzlabda;
import Varazslatok.Villamcsapas;

class Main {

    public static void main(String[] var0) {


        int nehezseg = Display.optionChoose("Kérlek válassz nehézségi fokozatot:", new String[]{"Könnyű", "Közepes", "Nehéz"}, 50);
        Jatekos player = new Jatekos(nehezseg);
        Display display = new Display(player);
        display.levelSystem();
        Display.optionChoose("Melyik varázslatot szeretnéd megvenni?", new String[]{Villamcsapas.info(), Tuzlabda.info(), Feltamasztas.info()},25);
    }
}
