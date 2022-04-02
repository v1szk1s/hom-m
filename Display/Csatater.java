package Display;

import Egysegek.Egyseg;

public class Csatater {
    Egyseg[] egysegek;

    public Csatater(Egyseg[] egysegek){
        this.egysegek = egysegek;
    }

    public static void refresh(Object[][] t){

        String teto = "┌───────" + "┬───────".repeat(11) + "┐";
        String koztes = "│       ".repeat(12) + "│";
        String elvalaszto = "├" + "───────┼".repeat(11) + "───────┤";
        String alja = "└───────" + "┴───────".repeat(11) + "┘";


        System.out.println(teto);

        for (int i = 0; i < t.length; i++){
            System.out.println(koztes);
            for (int j = 0; j < t[0].length; j++){
                if (t[i][j] != null){
                    System.out.print("\uD83D\uDE00");
                }else{
                    System.out.print("│  \uD83D\uDE00   ");
                }

            }
            System.out.println("│\n" + koztes);
            System.out.print((i == t.length-1 ? "": elvalaszto+"\n"));
        }
        System.out.println(alja);
    }



    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
