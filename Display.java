import java.util.Scanner;

public class Display {

    Jatekos player;
    static boolean err = false;
    static int balMargo = 66;
    static final int topMargo = 1;
    private static Scanner sc = new Scanner(System.in);

    public Display(Jatekos player){
        this.player = player;
    }

    public static int optionChoose(String kerdes, String[] opciok,int balMargo){

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
                System.out.print((err ? Color.RED + "\n" + margo + "A listából adj meg elemet [1, 2, 3...]!\n" + Color.WHITE + margo + "Válasz: " : "\n\n" + margo + "Válasz: "));
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
        String penzEmoji = "\uD83E\uDE99";
        String faceFullOfMoney = "\uD83E\uDD11";
        Hos hos = player.getHos();
        int error = 0;
        int melyik = -1;
        while (true){
            
            clear();
            String msg = "Melyik tulajdonságot szeretnéd fejleszteni?\n";
            System.out.println("\n".repeat(topMargo));
            System.out.printf("%s%s\n", " ".repeat(balMargo-(msg.length()/2)), msg);
            System.out.printf("%sArany:  %d %s\n", " ".repeat((int)((float)balMargo*1.5)), player.getArany(), faceFullOfMoney);
            String tulAr = String.format("%sTulajdonság ár: %d %s\n", " ".repeat(balMargo-27), player.getTulajdonsagAr(), penzEmoji);
            System.out.printf("%s%s\n", tulAr, " ".repeat(balMargo - (tulAr.length()/2)));
            for (int i = 0; i < hos.getTulStr().length; i++) {
                String margo = " ".repeat(balMargo);
                int len = hos.getTulStr()[i].length();
                len = 10 - len; // tulajdonsag pontok eltolasa
                
                System.out.printf("%s%d. %s %s [%d]\n", margo, i + 1, hos.getTulStr()[i], " ".repeat(len), hos.getTul()[i]);
            }
            System.out.println("errrr:" + error);
            String errMsg = error(error);
            msg = "A továbblépéshez írja be hogy tovabb.\n";
            int msgMargo = balMargo- msg.length()/2;
            int errMsgMargo = balMargo - errMsg.length()/2;
            System.out.printf("\n%s%s%s%s%sVálasz:" ," ".repeat(balMargo- 3),errMsg, " ".repeat(msgMargo), msg, " ".repeat(msgMargo));
            try{
                String most= sc.next();
                if("tovabb".equals(most.toLowerCase())|| "n".equals(most.toLowerCase()) || "q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase()) || "quit".equals(most.toLowerCase()) || "next".equals(most.toLowerCase())|| "kesz".equals(most.toLowerCase()) ){
                    return;
                }
                melyik = Integer.parseInt(most)-1;
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

    }

    /**
     * @param num Az error kód száma.
     * @return A megfelelő számú error kód szövege
     * */
    private String error(int num){
        switch (num){
            case 1:
                return Color.RED  + "Nincs elég aranyad!\n" + Color.WHITE;
            case 2:
                return Color.RED  + "Maximum 10 pont lehet egy kepessegen!\n" + Color.WHITE;
            case 3:
                return Color.RED  + "Egy varázslatot csak egyszer tudsz megvenni!\n" + Color.WHITE;
            case 4:
                return Color.RED + "A listából adj meg elemet [1, 2, 3...]!\n" + Color.WHITE;
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
                System.out.println("Itt tudod a Hősöd tulajdonságpontjait fejleszteni.\n A továbblépéshez ird be hogy: tovabb aztán enter. További informacióért írd: help<tulajdonság név> (pl. helptamadas)");
                break;
            case "helptamadas":
                System.out.println("Támadás: az egységek sebzését növeli meg, tulajdonságpontonként 10%-kal.");
                break;
            case "helpvedekezes":
                System.out.println("Védekezés: az egységeket ért sebzést csökkenti, tulajdonságpontonként 5%-kal.");
                break;
            case "helpvarazsero":
                System.out.println("Varázser®: a h®s által idézett varázslatok erősségét növeli.");
                break;
            case "helptudas":
                System.out.println("Tudás: a h®s maximális mannapontjait növeli, tulajdonságpontonként 10-zel.");
                break;
            case "helpmoral":
                System.out.println("Morál: az egységek kezdeményezését növeli, tulajdonságpontonként 1-gyel.");
                break;
            case "helpszerencse":
                System.out.println("Szerencse: az egységek kritikus támadásának esélyét növeli, tulajdonságpontonként 5%- kal.");
                break;
            case "nehezseg":
                System.out.println("Válassz nehézségi szintet!");
                break;
            case "helpalltulajdonsag":
                System.out.println("Itt tudod a Hősöd tulajdonságpontjait fejleszteni.\n A továbblépéshez ird be hogy: tovabb aztán enter. További informacióért írd: help<tulajdonság név> (pl. helptamadas)");
                System.out.println("Támadás: az egységek sebzését növeli meg, tulajdonságpontonként 10%-kal.");
                System.out.println("Védekezés: az egységeket ért sebzést csökkenti, tulajdonságpontonként 5%-kal.");
                System.out.println("Varázserő: a hős által idézett varázslatok erősségét növeli.");
                System.out.println("Tudás: a hős maximális mannapontjait növeli, tulajdonságpontonként 10-zel.");
                System.out.println("Morál: az egységek kezdeményezését növeli, tulajdonságpontonként 1-gyel.");
                System.out.println("Szerencse: az egységek kritikus támadásának esélyét növeli, tulajdonságpontonként 5%- kal.");
                break;
            default:
                break;
        }
    }



    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}