package Display;
import java.util.Scanner;

import Egysegek.Egyseg;
import Jatekosok.*;
import Varazslatok.*;


public class Display{
    private static Varazslat[] varazslatok = new Varazslat[]{new Villamcsapas(), new Tuzlabda(), new Feltamasztas()};
    static boolean err = false;
    static int balMargo = 66;
    static String margo = " ".repeat(balMargo);
    static int topMargo = 1;
    static final String top = "\n".repeat(topMargo);
    private static Scanner sc = new Scanner(System.in);
    private static String infoMsg = "A tovabblepeshez irja be hogy tovabb. (vagy csak egy t betut)\n";
    
    public static  Varazslat getVarazslat(int i){
        return varazslatok[i];
    }



    public static void showStatok(Player p){
        clear();
        System.out.println();
        System.out.println(margo + p.getNev() + "\n");
        for(var v:p.getHos().getStatok()){
            System.out.println(margo + v);
        }
    }

    public static int egysegShop(Player player){
        int melyik = -1;
        String errMsg = "";
        while (true){
            clear();
            String msg = "Melyik egysegbol szeretnel venni?\n";
            System.out.println("\n".repeat(topMargo));
            System.out.printf("%s%s\n", " ".repeat(balMargo-(msg.length()/2)), msg);
            printHeader(player);

    
            String margo = " ".repeat(30);
            Egyseg[] egysegek = player.getAllEgyseg();

            for (int i = 0; i < egysegek.length; i++) {
                
                System.out.printf("%s%d. ",margo, i+1);
                for(int j = 0; j < egysegek[i].info().length; j++){
                    if(j == 0){      
                        margo = "";
                    }else{
                        margo = " ".repeat(30);
                    }
                    System.out.printf("%s%s\n", margo, egysegek[i].info()[j]);
                }
                System.out.println(Color.RESET);
                System.out.println(margo + "Maximum ennyit tudsz venni: " + player.maxMennyitVehet(egysegek[i]));
                System.out.println("\n");
            }

            //msg = "A tovabblepeshez irja be hogy tovabb.\n";
            int msgMargo = balMargo- msg.length()/2;
            //int errMsgMargo = balMargo - errMsg.length()/2;
            System.out.println(" ".repeat(balMargo- 3) + errMsg);
            System.out.println(" ".repeat(msgMargo) + infoMsg);
            System.out.println(" ".repeat(msgMargo) + "Kerem igy adja meg mit akar venni: <egysegnev> <mennyit> (pl. ijasz 15)");
            System.out.printf("\n%sValasz: ", " ".repeat(msgMargo));
            try{
                String input = sc.nextLine();
                String[] t = input.split(" ");
                String most = t[0];
                if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase()) || "quit".equals(most.toLowerCase()) ){
                    return 1;
                }else if( player.getEgysegek().length <= 0){
                    errMsg = "Legalabb egy egysegednek lennie kell!";

                }else if(("t".equals(most.toLowerCase()) || "tovabb".equals(most.toLowerCase()) || "n".equals(most.toLowerCase()))){
                    
                    return 0;
                }else{
                    errMsg = Info.error(6);
                }
                int mennyi = Integer.parseInt(t[1]);
                errMsg = player.buyEgyseg(most, mennyi);
                
            }catch (Exception e){
                errMsg = Info.error(5);
            }

        }
    }

    public static int varazsShop(Player player){

        int melyik = -1;
        String errMsg = "";
        while (true){
            clear();
            String msg = "Melyik varázslatot szeretnéd megvenni?\n";
            System.out.println("\n".repeat(topMargo));
            System.out.printf("%s%s\n", " ".repeat(balMargo-(msg.length()/2)), msg);

            printHeader(player);

            
    
            String margo = " ".repeat(30);

            for (int i = 0; i < varazslatok.length; i++) {
                boolean isMegvan = player.vanVarazslat(varazslatok[i]);
                System.out.println((isMegvan? Color.GREEN:""));
                System.out.printf("%s%d. ",margo, i+1);
                for(int j = 0; j < varazslatok[i].info().length; j++){
                    if(j == 0){
                        
                        System.out.print(varazslatok[i].info()[j] + (isMegvan? Color.WHITE + " ".repeat(20)+ Color.GREEN_BACKGROUND + "MEGVEVE" + Color.RESET + Color.GREEN:"") + "\n");
                    }else{
                        margo = " ".repeat(30);
                    }
                    System.out.printf("%s%s\n", margo, varazslatok[i].info()[j]);
                }
                System.out.println(Color.RESET);
                System.out.println("\n");
            }

            

            //msg = "A tovabblepeshez irja be hogy tovabb. (vagy csak egy t betut)\n";
            int msgMargo = balMargo- infoMsg.length()/2;
            //int errMsgMargo = balMargo - errMsg.length()/2;
            System.out.printf("\n%s%s%s%s%sValasz: " ," ".repeat(balMargo- 3),errMsg, " ".repeat(msgMargo), infoMsg, " ".repeat(msgMargo));
            try{
                String most= sc.nextLine();
                if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase()) || "quit".equals(most.toLowerCase()) ){
                    return 1;
                }
                if("t".equals(most.toLowerCase()) || "tovabb".equals(most.toLowerCase()) || "n".equals(most.toLowerCase()) ){
                    return 0;
                }
                melyik = Integer.parseInt(most)-1;
                if (melyik >= varazslatok.length || melyik < 0){
                    errMsg = Info.error(4);
                    continue;
                }
                errMsg = player.buyVarazslat(varazslatok[melyik]) + "\n";
            }catch (Exception e){
                errMsg = Info.error(4);
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
                most = sc.nextLine();
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

    public static int levelSystem(Player player) {
        Hos hos = player.getHos();
        int error = 0;
        int melyik = -1;
        while (true){
            
            clear();
            String msg = "Melyik tulajdonsagot szeretned fejleszteni?\n";
            System.out.println("\n".repeat(topMargo));
            System.out.printf("%s%s\n", " ".repeat(balMargo-(msg.length()/2)), msg);

            printHeader(player);
            String tulAr = String.format("%sTulajdonsag ar: %d\n", " ".repeat(balMargo-27), player.getTulajdonsagAr());
            System.out.printf("%s%s\n", tulAr, " ".repeat(balMargo - (tulAr.length()/2)));
            for (int i = 0; i < hos.getTulStr().length; i++) {
                String margo = " ".repeat(balMargo);
                int len = hos.getTulStr()[i].length();
                len = 10 - len; // tulajdonsag pontok eltolasa
                
                System.out.printf("%s%d. %s %s [%d]\n", margo, i + 1, hos.getTulStr()[i], " ".repeat(len), hos.getTul()[i]);
            }

            String errMsg = Info.error(error);
            msg = "A tovabblepeshez irja be hogy tovabb.\n";
            int msgMargo = balMargo- msg.length()/2;
            //int errMsgMargo = balMargo - errMsg.length()/2;
            System.out.printf("\n%s%s%s%s%sValasz: " ," ".repeat(balMargo- 3),errMsg, " ".repeat(msgMargo), msg, " ".repeat(msgMargo));
            try{
                String most= sc.nextLine();
                if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase()) || "quit".equals(most.toLowerCase()) ){
                    return 1;
                }
                if("t".equals(most.toLowerCase()) || "tovabb".equals(most.toLowerCase()) || "n".equals(most.toLowerCase()) ) {
                    return 0;
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

    public static void printHeader(Player player){
        String margo = " ".repeat((int)((float)balMargo*1.5));
        System.out.printf("%sArany:  %d\n", margo, player.getArany());
        if(player.getHos().getMana() > 0){
            System.out.printf("%sMana: [%s]\n", margo, "#".repeat(Math.round(player.getHos().getMana()/10)));
        }
        if(player.getEgysegek() != null){
            for(int i = 0; i < player.getEgysegek().length; i++){
                System.out.println(margo + player.getEgysegek()[i].getNev() + ":\t" + player.getEgysegek()[i].getMennyiseg());
            }
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

    public static void gg(){
        animate("Game over");
        sc.nextLine();
    }

    private static void animate(String text){
        for(int i = 1; i <= text.length(); i++){
            clear();
            System.out.println("\n".repeat(topMargo));
            System.out.print(" ".repeat(balMargo) + text.substring(0, i));
            try{
                Thread.sleep(100);
            }catch(Exception e){};
            
        }
    }
}