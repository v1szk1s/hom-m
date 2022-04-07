import Display.Csatater;
import Display.Display;
import Egysegek.Egyseg;
import Jatekosok.*;
import Log.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Display.*;

public class Game {
    private Player p1;
    private Player p2;
    static Scanner sc = new Scanner(System.in);
    static boolean err = false;
    public Game(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public void playKor(){

        System.out.println(hanyszor);
        System.out.println(getTavolsag(p1.getEgysegek()[0], 55));
        // ArrayList<Egyseg> allEgyseg = new ArrayList<>();
        // refresh();
    
        
        // for(var v:p1.getEgysegek()){
        //     allEgyseg.add(v);
        // }
        // for(var v:p2.getEgysegek()){
        //     allEgyseg.add(v);
        // }
        // allEgyseg.sort(new EgysegComparator());

        // for(var e:allEgyseg){
        //     if(e.getPlayer() instanceof Jatekos){
        //         Log.log("Te jossz");
        //         chooseAction(e);
        //     }else{
        //         ((Gep)p2).play(e);
        //         Log.log("Ellenfel ezt csinalta: seemit");
        //     }
        // }

        // Csatater.incKor();

    }

    private void chooseAction(Egyseg e){
        int cselekves = -1;
        while(cselekves == -1){
            refresh();
            mozog(e);
            cselekves = menu("Mit szeretnel csinalni?", new String[]{"Varakozik", "Mozog", "Egyseggel Tamad", "Hossel Tamad", "Hossel Varazsol"});
            if(cselekves == -2){
                return;
            }
            switch(cselekves){
                case 0:
                    return;
                case 1:
                    mozog(e);
                    return;
                case 2:
                    tamad();
                    return;
                case 3:
                    hosTamad();
                case 4:
                    hosVarazsol();
            }
        }
        err = false;
        mozog(e);
    
        //refresh();
    }

    private void hosVarazsol(){

    }

    private void hosTamad(){

    }

    private void mozog(Egyseg e){
        //refresh();
        //System.out.println(getPosInRange(e));
        //int hova = menu("Hova szeretnel mozogni?", null);

    }

    private void tamad(){

    }




    private void refresh(){
        Csatater.showPalya(null,new Player[]{p1, p2});
    }
    private void refresh(List<Integer> t){
        Csatater.showPalya(t,new Player[]{p1, p2});
    
    }

    public static int menu(String kerdes, String[] opciok){
        int valasz = -1;

        System.out.println(kerdes + "\n");
        if(opciok != null){
            for (int i = 0; i < opciok.length; i++) {
                System.out.printf("%d. %s\n", i + 1, opciok[i]);
            }
        }
        

        System.out.print((err ? "\n"+ Info.error("A listabol adj meg elemet [1, 2, 3...]!\n") + "Valasz: " : "\n\n" + "Valasz: "));
        String most = "";
        try {
            most = sc.nextLine();
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

    public ArrayList<Integer> getPosInRange(Egyseg e){
        ArrayList<Integer> jok = new ArrayList<>();
        int maxTav = e.getSebesseg();
        System.out.println("itt");
        // for(int i = 0 ; i < 10; i++){
        //     System.out.println("itt");
        //     if(getTavolsag(e, i) < maxTav){
        //         System.out.println(i);
        //     }
        // }
        return jok;
    }

static int hanyszor = 0;

    public int getTavolsag(Egyseg e, int num){
        Position celPos = Position.convertToPos(num);
        
        List<Position> akadalyok = new LinkedList<>();
        Set<Position> vizsgalando = new HashSet<>();

        for(var a:p1.getEgysegek()){
            akadalyok.add(a.getPos());
        }
        for(var a:p2.getEgysegek()){
            akadalyok.add(a.getPos());
        }

        Position startPos = e.getPos();
        vizsgalando.add(startPos);
        int tavolsag = 0;
        kulso: while(true){
            
            vizsgalando = kifejt(vizsgalando, akadalyok);
            //System.out.println(vizsgalando);
            tavolsag++;
            for(Position p:vizsgalando){
                if(p.equals(celPos))
                    break kulso;
            }


        }
        System.out.println(hanyszor);

        return tavolsag;
    }

    private Set<Position> kifejt(Set<Position> halmaz, List<Position> akadalyok){
        
        Set<Position> ujHalmaz = new HashSet<>();
        for(Position p:halmaz){
            hanyszor++;
            Position p1 = new Position(p.getY()+1, p.getX());
            Position p2 = new Position(p.getY()+1, p.getX()-1);
            Position p3 = new Position(p.getY()+1, p.getX()+1);
            Position p4 = new Position(p.getY()-1, p.getX());
            Position p5 = new Position(p.getY()-1, p.getX()+1);
            Position p6 = new Position(p.getY()-1, p.getX()-1);
            Position p7 = new Position(p.getY(), p.getX()-1);
            Position p8 = new Position(p.getY(), p.getX()+1);

            if(onMap(p1) && !akadalyok.contains(p1)){
                ujHalmaz.add(p1);
            }
            if(onMap(p2) && !akadalyok.contains(p2) ){
                ujHalmaz.add(p2);
            }
            if(onMap(p3) && !akadalyok.contains(p3) ){
                ujHalmaz.add(p3);
            }
            if(onMap(p4) && !akadalyok.contains(p4) ){
                ujHalmaz.add(p4);
            }
            if(onMap(p5) && !akadalyok.contains(p5) ){
                ujHalmaz.add(p5);
            }
            if(onMap(p6) && !akadalyok.contains(p6) ){
                ujHalmaz.add(p6);
            }
            if(onMap(p7) && !akadalyok.contains(p7) ){
                ujHalmaz.add(p7);
            }
            if(onMap(p8) && !akadalyok.contains(p8)){
                ujHalmaz.add(p8);
            }
        }
        for(var a:halmaz){
           // ujHalmaz.remove(a);
        }

        
        return ujHalmaz;
    }

    private boolean onMap(Position pos){
        if(pos.getX() < 0 || pos.getX() > 9 || pos.getY() < 0 || pos.getY() > 11){
            return false;
        }
        return true;
    }

}


class EgysegComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        int elso = ((Egyseg)o1).getKezdemenyezes();
        int masodik = ((Egyseg)o2).getKezdemenyezes();

        return elso > masodik? (elso == masodik? 0: -1):1;
    }

}