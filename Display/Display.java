package Display;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Egysegek.*;
import Jatekosok.Player;
import Jatekosok.Hos;
import Varazslatok.*;

/**
 * Az elokesziteshez szukseges kepernyok megjeleniteseert felel.
 * Valasztasi kepernyoket jelenit meg, kezeli a rossz inputokat.
 * Igaz hogy van az IO osztaly, amibe at lehetne pakolni egy csomo mindent, ez sokkal hamarabb kesz volt, szoval marad igy.
 */
public class Display{
    
    static boolean err = false;
    static int balMargo = 80;
    static String margo = " ".repeat(balMargo);
    static String kisMargo = " ".repeat(30);
    static int topMargo = 1;
    static final String top = "\n".repeat(topMargo);
    private static Scanner sc = new Scanner(System.in);
    private static String infoMsg = "A tovabblepeshez irja be hogy tovabb. (vagy csak egy t betut)\n";
    private List<Varazslat> mindenVarazslat = new ArrayList<>();
    


    /**
     * Egy adott playernek a statjait jeleniti meg, kulon kepernyon.
     * @param p
     */
    public static void showStatok(Player p){
        clear();
        System.out.println();
        System.out.println(margo + p.getNev() + ":\n");
        for(var v:p.getHos().getStatok()){
            System.out.println(margo + "\t- " +v);
        }
        System.out.println("\n\n" + margo + "Varazslatok:\n");
        for(var v:p.getHos().getVarazslatok()){
            System.out.println(margo + "\t- " + v.getNev());
        }
        System.out.println("\n" + margo + "Folytatashoz nyomj enter!");
    }

    public static int egysegShop(Player player){
        int melyik = -1;
        String errMsg = "";
        while (true){
    
            clear();
            printHeader(player);
            String msg = "Melyik egysegbol szeretnel venni?\n";
            System.out.println("\n".repeat(topMargo));
            System.out.printf("%s%s\n", " ".repeat(balMargo-(msg.length()/2)), msg);
    
            //String margo = " ".repeat(30);
            List<Egyseg> egysegek= Arrays.asList(new Foldmuves(), new Ijasz(), new Griff());

            for (int i = 0; i < egysegek.size(); i++) {
                
                System.out.printf(margo + (i+1) + ". ");
                for(int j = 0; j < egysegek.get(i).info().length; j++){
                    if(j == 0){      
                        System.out.println(egysegek.get(i).info()[j]);
                    }else{
                        System.out.println(margo + egysegek.get(i).info()[j]);
                    }
                    
                }
                System.out.println(Color.RESET);
                System.out.println(margo + "Maximum ennyit tudsz venni: " + player.maxMennyitVehet(egysegek.get(i)) + " db");
                System.out.println("\n");
            }

            //msg = "A tovabblepeshez irja be hogy tovabb.\n";
            int msgMargo = balMargo- msg.length()/2;
            //int errMsgMargo = balMargo - errMsg.length()/2;
            System.out.println(" ".repeat(balMargo- 3) + errMsg);
            System.out.println(" ".repeat(msgMargo) + infoMsg);
            System.out.println(" ".repeat(msgMargo) + "Kerem igy adja meg mit akar venni: <sorszam> <mennyit> (pl. 2 15 -> 15 ijasz vetele)");
            System.out.printf("\n%sValasz: ", " ".repeat(msgMargo));
            try{
                String input = sc.nextLine();

                if("q".equals(input.toLowerCase()) ||"exit".equals(input.toLowerCase()) || "quit".equals(input.toLowerCase()) ){
                    return -2;
                }else if( player.getEgysegek().size() <= 0){
                    errMsg = "Legalabb egy egysegednek lennie kell!";

                }else if(("t".equals(input.toLowerCase()) || "tovabb".equals(input.toLowerCase()) || "n".equals(input.toLowerCase()))){
                    
                    return 0;
                }else{
                    errMsg = Info.error(6);
                }

                String[] t = input.split(" ");
                Egyseg venniValo = null;
                try{
                    int mennyi = Integer.parseInt(t[0]);
                    for(var egyseg:egysegek){
                        if(egyseg.getNev().toLowerCase().equals(t[1].toLowerCase())){
                            venniValo = egyseg;
                            errMsg = player.buyEgyseg(egyseg, mennyi);
                            break;
                        }
                    }
                }catch(Exception ex){}

                if(venniValo == null){
                    int e = Integer.parseInt(t[0])-1;
                    int mennyi = Integer.parseInt(t[1]);
                    errMsg = player.buyEgyseg(egysegek.get(e), mennyi);    
                }
                
                
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
            printHeader(player);
            String msg = "Melyik varázslatot szeretnéd megvenni?\n";
            System.out.println("\n".repeat(topMargo));
            System.out.printf("%s%s\n", " ".repeat(balMargo-(msg.length()/2)), msg);

            

            
    
            String margo = " ".repeat(30);
            int i = 0;
            Varazslat[] varazsok = new Varazslat[]{new Villamcsapas(), new Tuzlabda(), new Feltamasztas(), new Gyogyit()};
            for (var v:varazsok) {
                boolean isMegvan = player.getHos().vanVarazslat(v);
                System.out.println((isMegvan? Color.GREEN:""));
                System.out.printf("%s%d. ",margo, i+1);
                i++;
                for(int j = 0; j < v.info().length; j++){
                    if(j == 0){
                        System.out.print(v.info()[j] + (isMegvan? Color.WHITE + " ".repeat(20)+ Color.GREEN_BACKGROUND + "MEGVEVE" + Color.RESET + Color.GREEN:"") + "\n");
                    }else{
                        margo = " ".repeat(30);
                    }
                    System.out.printf("%s%s\n", margo, v.info()[j]);
                }
                System.out.println(Color.RESET);
            }

            

            //msg = "A tovabblepeshez irja be hogy tovabb. (vagy csak egy t betut)\n";
            int msgMargo = balMargo- infoMsg.length()/2;
            //int errMsgMargo = balMargo - errMsg.length()/2;
            System.out.println(margo+errMsg);
            System.out.println(margo + infoMsg);
            System.out.print(margo + "Valasz: ");
            //System.out.println();
            //System.out.printf("\n%s%s%s%s%sValasz: " ," ".repeat(balMargo- 3),errMsg, " ".repeat(msgMargo), infoMsg, " ".repeat(msgMargo));
            try{
                String most= sc.nextLine();
                if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase()) || "quit".equals(most.toLowerCase()) ){
                    return -2;
                }
                if("t".equals(most.toLowerCase()) || "tovabb".equals(most.toLowerCase()) || "n".equals(most.toLowerCase()) ){
                    return 0;
                }
                melyik = Integer.parseInt(most)-1;
                if (melyik >= varazsok.length || melyik < 0){
                    errMsg = Info.error(4);
                    continue;
                }
                Varazslat vett = varazsok[melyik];

                errMsg = player.getHos().buyVarazslat(vett) + "\n";
            }catch (Exception e){
                errMsg = Info.error(4);
            }

        }
    }

    public static int menuFullScreen(String kerdes, String[] opciok){

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
            printHeader(player);
            String msg = "Melyik tulajdonsagot szeretned fejleszteni?\n";
            System.out.println("\n".repeat(topMargo));
            System.out.printf("%s%s\n", " ".repeat(balMargo-(msg.length()/2)), msg);


            String tulAr = String.format("%sTulajdonsag ar: %d\n", " ".repeat(balMargo-27), player.getTulajdonsagAr());
            System.out.printf("%s%s\n", tulAr, " ".repeat(balMargo - (tulAr.length()/2)));
            for (int i = 0; i < hos.getTulStr().length; i++) {
                String margo = " ".repeat(balMargo);
                int len = hos.getTulStr()[i].length();
                len = 10 - len; // tulajdonsag pontok eltolasa
                
                System.out.printf("%s%d. %s %s [%d]\n", margo, i + 1, hos.getTulStr()[i], " ".repeat(len), hos.getTul()[i]);
            }

            String errMsg = Info.error(error);
           
            int msgMargo = balMargo- infoMsg.length()/2;
            //int errMsgMargo = balMargo - errMsg.length()/2;
            System.out.printf("\n%s%s%s%s%sValasz: " ," ".repeat(balMargo- 3),errMsg, " ".repeat(msgMargo), infoMsg, " ".repeat(msgMargo));
            try{
                String most= sc.nextLine();
                if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase()) || "quit".equals(most.toLowerCase()) ){
                    return -2;
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
        System.out.println();
        String margo = " ".repeat((int)((float)balMargo*1.5));
        System.out.printf("%sArany:  %d\n", margo, player.getArany());
        if(player.getHos().getMana() > 0){
            System.out.println(margo + "Mana: " + player.getHos().getMana() + " │" + Color.BLUE + "|".repeat(player.getHos().getMana()/2) + Color.RESET + " ".repeat((player.getHos().getMaxMana()-player.getHos().getMana())/2) + "│");
        }
        //int maxEgysegSzam = 5;
        if(player.getEgysegek().size() > 0){
            for(var e:player.getEgysegek()){
                System.out.println(margo + e.getNev() + ":\t" + e.getMennyiseg());
                //System.out.println("\n".repeat(maxEgysegSzam-player.getEgysegek().size()));
            }
        }else{
            //System.out.println("\n".repeat(maxEgysegSzam-player.getEgysegSzam()));
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

    public static void gameOver(){
        animate(new String[]{"Game", " over"});
        Display.waitForInput();
    }

    public static void draw(){
        animate("That's a draw");
        Display.waitForInput();
    }

    public static void win(){
        animate("You won!");
        Display.waitForInput();
    }

    public static void waitForInput(){
        sc.nextLine();
    }

    private static void animate(String text){
        for(int i = 1; i <= text.length(); i++){
            clear();
            System.out.println("\n".repeat(topMargo));
            System.out.print(" ".repeat(balMargo) + text.substring(0, i));
            try{
                Thread.sleep(80);
            }catch(Exception e){};
            
        }
    }
    /**
     * animate word by word
     * @param text
     */
    private static void animate(String[] text){
        StringBuilder builder = new StringBuilder();

        for(var s:text){
            for(int i = 0; i < s.length(); i++){
                clear();
                builder.append(s.charAt(i));
                System.out.println("\n".repeat(topMargo));
                System.out.print(" ".repeat(balMargo) + builder.toString());
                try{
                    Thread.sleep(80);
                }catch(Exception e){};
                
            }
            try{
                Thread.sleep(800);
            }catch(Exception e){};
        }
    }

    public static void sleep(long meddig){
        try{
            Thread.sleep(meddig);
        }catch(Exception e){}
    }
}

class Bemenet{
    private static Scanner sc = new Scanner(System.in);

    public static int getBemenet(int min, int max){
        int melyik;
        try{
            String most= sc.nextLine();
            if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase()) || "quit".equals(most.toLowerCase()) ){
                return -2;
            }
            if("t".equals(most.toLowerCase()) || "tovabb".equals(most.toLowerCase()) || "n".equals(most.toLowerCase()) ) {
                return -1;
            }
            melyik = Integer.parseInt(most)-1;
            if (melyik >= min && melyik <= max){
                return melyik;
            }
        }catch (Exception e){
            return -3;
        }
        return -3;
    }
}