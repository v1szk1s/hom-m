package Display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Log.Log;
import Egysegek.Egyseg;
import Jatekosok.*;

public class Csatater {
    
    static Scanner sc = new Scanner(System.in);
    private static int kor = 1;
    private static int[] elsoKetSor = {1, 2, 13, 14, 25, 26, 37, 38, 49, 50, 61, 62, 73, 74, 85, 86, 97, 98, 109, 110};
    private static List<Integer> elsoSorok = new LinkedList<>();
    private Player p1;
    private Player p2;

    private static String margo = " ".repeat(8);
    private static String teto = margo + "┌───────" + "┬───────".repeat(11) + "┐";
    //private static String koztes = margo + "│       ".repeat(12) + "│";
    private static String elvalaszto = margo + "├" + "───────┼".repeat(11) + "───────┤";
    private static String alja = margo + "└───────" + "┴───────".repeat(11) + "┘";

    public Csatater(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public static void incKor(){
        kor ++;
    }

    public int elhelyez(){

        Egyseg[] egysegek = p1.getEgysegek();
        int melyik = -1;
        String errMsg = "\n";
        for(int i:elsoKetSor){
            elsoSorok.add(i);
        }
        while(true){
            showPalya(elsoSorok, new Player[]{p1});
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
                    
                    errMsg = Info.error(p1.elhelyez(egysegek[melyik], Position.convertToPos(hova)));
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

    public static void showPalya(List<Integer> ideSzabad, Player[] p){
        Display.clear();
        System.out.println(margo + kor + ". kor");
        System.out.print(teto);
        System.out.println(margo + p[0].getInfo(0));
        int counter = 1;

        for (int i = 0; i < 10; i++){
            //
            for (int j = 0; j < 12; j++){
                if(j == 0){
                    System.out.print(margo);
                }
                String msg = "";
                if(ideSzabad != null && ideSzabad.contains(counter)){
                    msg = Color.GREEN + counter + "."+ Color.RESET;
                }else{
                    msg = Integer.toString(counter) + ".";
                }
                
                
                System.out.print("│ " + msg + " ".repeat(6-Integer.toString(counter).length()-1));
                if(j == 11){
                    System.out.print("│");
                    System.out.print(margo + (i == 0?p[0].getInfo(1) + p[0].getInfo(2):"") + (i == 1?p[0].getInfo(9) + p[0].getInfo(10):""));   //pinfo

                    if(p.length > 1){
                        System.out.print((i == 3?p[1].getInfo(0):""));// + p[1].getInfo(1):"") + (i == 3?p[1].getInfo(8) + "\t" + p[1].getInfo(9):""));
                    }
                    System.out.print((i == 9?Log.get(3):"") + (i == 8?Log.get(7):"") + (i == 7?Log.get(11):""));
                    System.out.println();
                }
                counter++;
                
            }
            
            for (int k = 0; k < 12; k++){
                if(k == 0){
                    System.out.print(margo);
                }
                String xd = (p[0].getEgysegOnPosition(new Position(i, k)) == null? "":" " + p[0].getEgysegOnPosition(new Position(i, k)).getIcon() + " ");
                String color = Color.GREEN_BACKGROUND;
                if(p.length > 1){
                    if(p[1].getEgysegOnPosition(new Position(i, k)) != null){
                        xd = " " + p[1].getEgysegOnPosition(new Position(i, k)).getIcon() + " ";
                        color = Color.RED_BACKGROUND;
                    } 
                    
                }
                System.out.print("│ " + color + xd +  Color.RESET + " ".repeat(6-xd.length()));
                if(k == 11){
                    System.out.print("│" + margo);
                }
            }

            
            //System.out.print("│" + margo);
            System.out.print((i == 9?Log.get(2):"") + (i == 8?Log.get(6):"") + (i == 7?Log.get(10):""));
            System.out.print((i == 0?p[0].getInfo(3) + p[0].getInfo(4):"") + (i == 1?p[0].getInfo(11) + "\t" + p[0].getInfo(12):""));
            if(p.length > 1){
                System.out.print((i == 3?p[1].getInfo(2) + p[1].getInfo(3):"") + (i == 4?p[1].getInfo(9) + p[1].getInfo(10):""));
            }
            System.out.println();

            for (int k = 0; k < 12; k++){
                if(k == 0){
                    System.out.print(margo);
                }
                String xd = (p[0].getEgysegOnPosition(new Position(i, k)) == null? "":p[0].getEgysegOnPosition(new Position(i, k)).getEletero() + " hp");
                //String color = Color.GREEN_BACKGROUND;
                if(p.length > 1){
                    if(p[1].getEgysegOnPosition(new Position(i, k)) != null){
                        xd = p[1].getEgysegOnPosition(new Position(i, k)).getEletero() + " hp";
                        //color = Color.RED_BACKGROUND;
                    } 
                    
                }
                System.out.print("│" + xd +  Color.RESET + " ".repeat(7-xd.length()));
                if(k == 11){
                    System.out.print("│" + margo);
                }
            }

            System.out.print((i == 0?p[0].getInfo(5) + "\t" + p[0].getInfo(6):""));
            if(p.length > 1){
                System.out.print((i == 3?p[1].getInfo(4) + "\t" + p[1].getInfo(5):"") + (i == 4?p[1].getInfo(11) + "\t" + p[1].getInfo(12):""));
            }
            System.out.print((i == 9?Log.get(1):"") + (i == 8?Log.get(5):"") + (i == 7?Log.get(9):""));
            System.out.println();
            if(i != 9){
                System.out.print(elvalaszto);

                System.out.print(margo + (i == 8?Log.get(4):"") + (i == 7?Log.get(8):""));
                System.out.print((i == 0?p[0].getInfo(7) + "\t" + p[0].getInfo(8):""));
                if(p.length > 1){
                    System.out.print((i == 3?p[1].getInfo(6) + "\t" + p[1].getInfo(7):"") + (i == 4?p[1].getInfo(13) + "\t" + p[1].getInfo(14):""));
                }
                System.out.println();
            }
        }
        System.out.print(alja);
        System.out.println(margo + Log.get(0));
    }



    private static boolean contains(int[] t, int szam){
        for(var num:t){
            if(num == szam)
                return true;
        }
        return false;
    }

}
