package Display;

import java.util.Scanner;

import Log.Log;
import Egysegek.Egyseg;
import Jatekosok.*;

public class Csatater {
    
    static Scanner sc = new Scanner(System.in);

    private static int[] elsoKetSor = {1, 2, 13, 14, 25, 26, 37, 38, 49, 50, 61, 62, 73, 74, 85, 86, 97, 98, 109, 110};


    private static String margo = " ".repeat(8);
    private static String teto = margo + "┌───────" + "┬───────".repeat(11) + "┐";
    private static String koztes = margo + "│       ".repeat(12) + "│";
    private static String elvalaszto = margo + "├" + "───────┼".repeat(11) + "───────┤";
    private static String alja = margo + "└───────" + "┴───────".repeat(11) + "┘";


    public static int elhelyez(Player p1){
        Egyseg[] egysegek = p1.getEgysegek();
        int melyik = -1;
        String errMsg = "\n";

        while(true){
            showSzamozott(elsoKetSor, p1);
            System.out.println("Meilyik csapatodat szeretned elhelyezni? (formatum: csapat <sorszama> <pozicio szam>)");
            for(int i = 0; i < egysegek.length; i++){
                System.out.println(i+1 + ". "+ egysegek[i].getNev() +  "\t[" +egysegek[i].getMennyiseg() + " db]");
    
            }
            System.out.println(errMsg);
            System.out.println("A tovabblepeshez irja be hogy tovabb, vagy csak egy t betut.");
            System.out.print("Valasz: ");
            String most = "";
            try{
                most = sc.nextLine();
                String[] t = most.split(" ");

                if("q".equals(t[0].toLowerCase()) ||"exit".equals(t[0].toLowerCase()) || "quit".equals(t[0].toLowerCase()) ){
                    return 1;
                }else if(("t".equals(most.toLowerCase()) || "tovabb".equals(t[0].toLowerCase())) &&  !p1.isMindenkiElhelyezve()){
                    errMsg = Info.error("Nincs mindenki elhelyezve!");
                }else if("t".equals(most.toLowerCase()) || "tovabb".equals(t[0].toLowerCase()) || "n".equals(t[0].toLowerCase())){
                    return 0;
                }

                melyik = Integer.parseInt(t[0])-1;
                int hova = Integer.parseInt(t[1]);
                if (melyik >= egysegek.length || melyik < 0){
                    errMsg = Info.error(4);
                    continue;
                }
                if(contains(elsoKetSor, hova)){
                    
                    errMsg = Info.error(p1.elhelyez(egysegek[melyik], hova));
                }else{
                    errMsg = Info.error("Csak az elso ket sorba lehet rakni!");
                }
                


            }catch(Exception e){
                if(("t".equals(most.toLowerCase()) || "tovabb".equals(most.toLowerCase())) &&  !p1.isMindenkiElhelyezve()){
                    errMsg = Info.error("Nincs mindenki elhelyezve!\n");
                }else
                    errMsg = Info.error(5);
            }
        }


    }

    public static void showSzamozott(int[] ideSzabad, Player p1){
        Display.clear();
        System.out.println(teto);
        int counter = 1;

        for (int i = 0; i < 10; i++){
            //
            for (int j = 0; j < 12; j++){
                if(j == 0){
                    System.out.print(margo);
                }
                String msg = "";
                if(contains(ideSzabad, counter)){
                    msg = Color.GREEN + counter + Color.RESET;
                }else{
                    msg = Integer.toString(counter);
                }
                
                
                System.out.print("│ " + msg + " ".repeat(6-Integer.toString(counter).length()));
                if(j == 11){
                    System.out.print("│");
                    System.out.println(margo + (i == 9?Log.get(3):"") + (i == 8?Log.get(7):"") + (i == 7?Log.get(11):""));
                }
                counter++;
                
            }
            
            for (int k = 0; k < 12; k++){
                if(k == 0){
                    System.out.print(margo);
                }
                String xd = (p1.getEgysegOnPosition(i, k) == null? "":p1.getEgysegOnPosition(i, k).getIcon());

                System.out.print("│ " + xd + " ".repeat(6-xd.length()));
                if(k == 11){
                    System.out.print("│" + margo);
                }
            }

            
            //System.out.print("│" + margo);
            System.out.println((i == 9?Log.get(2):"") + (i == 8?Log.get(6):"") + (i == 7?Log.get(10):""));
            System.out.print(koztes + margo);
            System.out.println((i == 9?Log.get(1):"") + (i == 8?Log.get(5):"") + (i == 7?Log.get(9):""));
            if(i != 9){
                System.out.print(elvalaszto);
                System.out.println(margo + (i == 8?Log.get(4):"") + (i == 7?Log.get(8):""));
            }
        }
        System.out.print(alja);
        System.out.println(margo + Log.get(0));

    }

    // public void refresh(){
    //     Display.clear();

    //     System.out.print(teto);
    //     System.out.println(margo + p1.getInfo()[0]);
    //     for (int i = 0; i < 10; i++){
    //         System.out.print(koztes);
    //         System.out.println(margo + (i == 9?Log.get(3):"") + (i == 8?Log.get(6):""));
    //         for (int j = 0; j < 12; j++){
    //             if(j == 0){
    //                 System.out.print(margo);
    //             }

    //             if (null != null){
    //                 System.out.print("\uD83D\uDE00");  // fixen 7 hosszunak kell lennie
    //             }else{
    //                 System.out.print("│       ");
    //             }

    //         }

    //         System.out.print("│" + margo);
    //         System.out.println((i == 9?Log.get(2):"") + (i == 8?Log.get(6):""));
    //         System.out.print(koztes + margo);
    //         System.out.println((i == 9?Log.get(1):"") + (i == 8?Log.get(4):"") + (i == 7?Log.get(8):""));
    //         if(i != 9){
    //             System.out.print(elvalaszto);
    //             System.out.println(margo + (i == 8?Log.get(4):"") + (i == 7?Log.get(7):""));
    //         }
    //     }
    //     System.out.print(alja);
    //     System.out.println(margo + Log.get(0));
    // }



    private static boolean contains(int[] t, int szam){
        for(var num:t){
            if(num == szam)
                return true;
        }
        return false;
    }

}
