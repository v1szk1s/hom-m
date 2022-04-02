package Display;
import java.util.Scanner;
import Jatekosok.Jatekos;
import Jatekosok.Hos;


public class Display {

    Jatekos player;
    Varazslat varazslatok;
    static boolean err = false;
    static int balMargo = 66;
    static final int topMargo = 1;
    private static Scanner sc = new Scanner(System.in);

    public Display(Jatekos player){
        this.player = player;
    }

    public static int varazsbolt(){

        Hos hos = player.getHos();
        int error = 0;
        int melyik = -1;
        while (true){

            clear();
            String msg = "Melyik varazslatot szeretned megvenni?\n";
            System.out.println("\n".repeat(topMargo));
            System.out.printf("%s%s\n", " ".repeat(balMargo-(msg.length()/2)), msg);

            printHeader();

            for (int i = 0; i < hos.getTulStr().length; i++) {
                String margo = " ".repeat(balMargo);
                int len = hos.getTulStr()[i].length();
                len = 10 - len; // tulajdonsag pontok eltolasa

                System.out.printf("%s%d. %s %s [%d]\n", margo, i + 1, hos.getTulStr()[i], " ".repeat(len), hos.getTul()[i]);
            }

            String errMsg = error(error);
            msg = "A tovabblepeshez irja be hogy tovabb.\n";
            int msgMargo = balMargo- msg.length()/2;
            int errMsgMargo = balMargo - errMsg.length()/2;
            System.out.printf("\n%s%s%s%s%sValasz:" ," ".repeat(balMargo- 3),errMsg, " ".repeat(msgMargo), msg, " ".repeat(msgMargo));
            try{
                String most= sc.next();
                if("tovabb".equals(most.toLowerCase())|| "n".equals(most.toLowerCase()) || "q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase()) || "quit".equals(most.toLowerCase()) || "next".equals(most.toLowerCase())|| "kesz".equals(most.toLowerCase()) ){
                    return;
                }
                melyik = Integer.parseInt(most)-1;
                if (melyik >= 6 || melyik < 0){
                    error = 4;
                    continue;
                }
                error = player.levelUp(melyik);
            }catch (Exception e){
                error = 4;
                continue;
            }

        }
    }

    public static int menu(String kerdes, String[] opciok){

        String margo = " ".repeat(balMargo);
        int valasz = -1;

            while(true){
                if(valasz >= 1  && valasz <= opciok.length){
                    err = false;
                    return valasz;
                }
                clear();
                System.out.println("\n".repeat(topMargo));
                System.out.println(margo + kerdes + "\n");

                for (int i = 0; i < opciok.length; i++) {
                    System.out.printf(margo + "%d. %s\n", i + 1, opciok[i]);
                }
                System.out.print((err ? Color.RED + "\n" + margo + "A listabol adj meg elemet [1, 2, 3...]!\n" + Color.WHITE + margo + "Valasz: " : "\n\n" + margo + "Valasz: "));
                String most = "";

                try {
                most = sc.next();
                    if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase())||"quit".equals(most.toLowerCase()) ){
                        return -1;
                    }
                valasz = Integer.parseInt(most);
                }catch (Exception e){
                    err = true;
                    continue;
                }
            }
    }

    public void levelSystem() {
        Hos hos = player.getHos();
        int error = 0;
        int melyik = -1;
        while (true){
            
            clear();
            String msg = "Melyik tulajdonsagot szeretned fejleszteni?\n";
            System.out.println("\n".repeat(topMargo));
            System.out.printf("%s%s\n", " ".repeat(balMargo-(msg.length()/2)), msg);

            printHeader();

            for (int i = 0; i < hos.getTulStr().length; i++) {
                String margo = " ".repeat(balMargo);
                int len = hos.getTulStr()[i].length();
                len = 10 - len; // tulajdonsag pontok eltolasa
                
                System.out.printf("%s%d. %s %s [%d]\n", margo, i + 1, hos.getTulStr()[i], " ".repeat(len), hos.getTul()[i]);
            }

            String errMsg = error(error);
            msg = "A tovabblepeshez irja be hogy tovabb.\n";
            int msgMargo = balMargo- msg.length()/2;
            int errMsgMargo = balMargo - errMsg.length()/2;
            System.out.printf("\n%s%s%s%s%sValasz:" ," ".repeat(balMargo- 3),errMsg, " ".repeat(msgMargo), msg, " ".repeat(msgMargo));
            try{
                String most= sc.next();
                if("tovabb".equals(most.toLowerCase())|| "n".equals(most.toLowerCase()) || "q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase()) || "quit".equals(most.toLowerCase()) || "next".equals(most.toLowerCase())|| "kesz".equals(most.toLowerCase()) ){
                    return;
                }
                melyik = Integer.parseInt(most)-1;
                if (melyik >= 6 || melyik < 0){
                    error = 4;
                    continue;
                }
                error = player.levelUp(melyik);
            }catch (Exception e){
                error = 4;
                continue;
            }

        }
    }

    public void printHeader(){
        String penzEmoji = "\uD83E\uDE99";
        String faceFullOfMoney = "\uD83E\uDD11";
        System.out.printf("%sArany:  %d %s\n", " ".repeat((int)((float)balMargo*1.5)), player.getArany(), faceFullOfMoney);
        String tulAr = String.format("%sTulajdonsag ar: %d %s\n", " ".repeat(balMargo-27), player.getTulajdonsagAr(), penzEmoji);
        System.out.printf("%s%s\n", tulAr, " ".repeat(balMargo - (tulAr.length()/2)));
    }

    /**
     * @param num Az error kod szama.
     * @return A megfelelo szamú error kod szovege
     * */
    public static String error(int num){
        switch (num){
            case 1:
                return Color.RED  + "Nincs eleg aranyad!\n" + Color.WHITE;
            case 2:
                return Color.RED  + "Maximum 10 pont lehet egy kepessegen!\n" + Color.WHITE;
            case 3:
                return Color.RED  + "Egy varazslatot csak egyszer tudsz megvenni!\n" + Color.WHITE;
            case 4:
                return Color.RED + "A listabol adj meg elemet [1, 2, 3...]!\n" + Color.WHITE;
            default:
                return "\n";
        }
    }

    public static void printInfo(String melyik){
        if("levelSystem".equals(melyik)) {

        }
        if("helptamadas".equals(melyik)){

        }
        switch (melyik){
            case "levelSystem":
                System.out.println("Itt tudod a Hosod tulajdonsagpontjait fejleszteni.\n A tovabblepeshez ird be hogy: tovabb aztan enter. Tovabbi informacioert ird: help<tulajdonsag nev> (pl. helptamadas)");
                break;
            case "helptamadas":
                System.out.println("Tamadas: az egysegek sebzeset noveli meg, tulajdonsagpontonkent 10%-kal.");
                break;
            case "helpvedekezes":
                System.out.println("Vedekezes: az egysegeket ert sebzest csokkenti, tulajdonsagpontonkent 5%-kal.");
                break;
            case "helpvarazsero":
                System.out.println("Varazsero: a hos altal idezett varazslatok erosseget noveli.");
                break;
            case "helptudas":
                System.out.println("Tudas: a hos maximalis mannapontjait noveli, tulajdonsagpontonkent 10-zel.");
                break;
            case "helpmoral":
                System.out.println("Moral: az egysegek kezdemenyezeset noveli, tulajdonsagpontonkent 1-gyel.");
                break;
            case "helpszerencse":
                System.out.println("Szerencse: az egysegek kritikus tamadasanak eselyet noveli, tulajdonsagpontonkent 5%- kal.");
                break;
            case "nehezseg":
                System.out.println("Valassz nehezsegi szintet!");
                break;
            case "helpalltulajdonsag":
                System.out.println("Itt tudod a Hosod tulajdonsagpontjait fejleszteni.\n A tovabblepeshez ird be hogy: tovabb aztan enter. Tovabbi informacioert ird: help<tulajdonsag nev> (pl. helptamadas)");
                System.out.println("Tamadas: az egysegek sebzeset noveli meg, tulajdonsagpontonkent 10%-kal.");
                System.out.println("Vedekezes: az egysegeket ert sebzest csokkenti, tulajdonsagpontonkent 5%-kal.");
                System.out.println("Varazsero: a hos altal idezett varazslatok erosseget noveli.");
                System.out.println("Tudas: a hos maximalis mannapontjait noveli, tulajdonsagpontonkent 10-zel.");
                System.out.println("Moral: az egysegek kezdemenyezeset noveli, tulajdonsagpontonkent 1-gyel.");
                System.out.println("Szerencse: az egysegek kritikus tamadasanak eselyet noveli, tulajdonsagpontonkent 5%- kal.");
                break;
            default:
                break;
        }
    }




    public static void clear(){
        try{
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")){
               new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            }else{
                System.out.print("\033[H\033[2J");
                System.out.flush();
        
            }
        }
        catch (final Exception e){}
    }

}