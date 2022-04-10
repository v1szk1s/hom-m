package Log;
import java.util.LinkedList;

import Display.Color;
/**
 * Rovid megis annal hasznosabb osztaly.
 * A csatater megjelenitesi utan, a csatateren torteno dolgokat jegyzi fel, valamint adja vissz.
 */
public class Log {
    private static LinkedList<String> logok = new LinkedList<>();


    public static String get(int i){
        try{
            return logok.get(i) + " ".repeat(70-logok.get(i).length())+  Color.RESET;// == null ? "":logok.get(i);
        }catch(Exception e){
            return "";
        }
        
    }

    public static void log(String msg){
        logok.addFirst(msg);
    }

    public static void log(int msg){
        logok.addFirst(Integer.toString(msg));
    }

    public static void log(String msg, boolean error){
        logok.addFirst(Color.RED_BACKGROUND + msg + Color.RESET);
    }
}
