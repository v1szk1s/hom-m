package IO;

import java.util.List;
import java.util.Scanner;

import Display.Color;
import Display.Info;
import Varazslatok.Varazslat;


/**
 * Ez az osztaly a csatateren belul valo felhasznalo inputjait kezelo class file.
 * Ez a file a display irasa  utan joval kesobb jott letre es lusta voltam athozni onnan a dolgokat.
 * 
 */
public class IO {
    
    private static int margoSize = 3;
    private static String margo = " ".repeat(margoSize);
    public static boolean err = false;
    private static Scanner sc = new Scanner(System.in);

    public static void setMargoSize(int m){
        margoSize = m;
    }


    public static int menuSzamos(String kerdes, List<Integer> t){
        int valasz = -1;

        System.out.println(margo + kerdes + "\n");

        System.out.print((err ? "\n"+ margo + Info.error("Csak a zolden jelolt mezok kozul valaszthat!\n") + margo + "Valasz: " : "\n\n" + margo + "Valasz: "));
        String most = "";
        try {
            most = sc.nextLine();
            if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase())||"quit".equals(most.toLowerCase()) ){
                return -2;
            }
            valasz = Integer.parseInt(most);
            if(!t.contains(valasz)){
                err = true;
                return -1;
            }
        }catch (Exception e){
            err = true;
            return -1;
        }
        err = false;
        return valasz;
    }

    public static int menu(String kerdes, String[] opciok){
        int valasz = -1;

        System.out.println(margo + kerdes + "\n");
        if(opciok != null){
            for (int i = 0; i < opciok.length; i++) {
                System.out.println(margo + (i + 1) + ". " + opciok[i]);
            }
        }
        

        System.out.print((err ? Color.RED + "\n" + margo + "A listabol adj meg elemet [1, 2, 3...]!\n" + Color.WHITE + margo + "Valasz: " : "\n\n" + margo + "Valasz: "));
        String most = "";
        try {
            most = sc.nextLine();
            if(most.trim().length() == 0){
                return -1;
            }
            if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase())||"quit".equals(most.toLowerCase()) ){
                return -2;
            }
            valasz = Integer.parseInt(most);
            if(valasz < 1 || valasz > opciok.length){
                err = true;
                return -1;
            }
        }catch (Exception e){
            err = true;
            return -1;
        }
        err = false;
        return valasz - 1;
    }

    public static int menu(String kerdes, List<Varazslat> opciok){
        int valasz = -1;

        System.out.println(margo + kerdes + "\n");
        if(opciok != null){
            for (int i = 0; i < opciok.size(); i++) {
                System.out.println(margo + (i + 1) + ". " + opciok.get(i).getNev());
            }
        }
        

        System.out.print((err ? Color.RED + "\n" + margo + "A listabol adj meg elemet [1, 2, 3...]!\n" + Color.WHITE + margo + "Valasz: " : "\n\n" + margo + "Valasz: "));
        String most = "";
        try {
            most = sc.nextLine();
            if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase())||"quit".equals(most.toLowerCase()) ){
                return -2;
            }
            valasz = Integer.parseInt(most);
            if(valasz < 1 || valasz > opciok.size()){
                err = true;
                return -1;
            }
        }catch (Exception e){
            err = true;
            return -1;
        }
        err = false;
        return valasz - 1;
    }

        
    // public static int menuSzamos(String kerdes, ArrayList<Integer> t){
    //     int valasz = -1;

    //     System.out.println(margo + kerdes + "\n");



    //     System.out.print((err ? Color.RED + "\n" + margo + "A listabol adj meg elemet [1, 2, 3...]!\n" + Color.WHITE + margo + "Valasz: " : "\n\n" + margo + "Valasz: "));
    //     String most = "";
    //     try {
    //         most = sc.nextLine();
    //         if("q".equals(most.toLowerCase()) ||"exit".equals(most.toLowerCase())||"quit".equals(most.toLowerCase()) ){
    //             return -2;
    //         }
    //         valasz = Integer.parseInt(most);
    //         if(!t.contains(valasz)){
    //             err = true;
    //             return -1;
    //         }
    //     }catch (Exception e){
    //         err = true;
    //         return -1;
    //     }
    //     err = false;
    //     return valasz;
    // }

    
    
}
