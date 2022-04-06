package Log;
import java.util.LinkedList;

public class Log {
    private static LinkedList<String> logok = new LinkedList<>();


    public static String get(int i){
        try{
            return logok.get(i);// == null ? "":logok.get(i);
        }catch(Exception e){
            return "";
        }
        
    }

    public static void log(String msg){
        logok.addFirst(msg);
    }
}
