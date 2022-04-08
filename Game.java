import Display.Csatater;
import Display.Display;
import Egysegek.Egyseg;
import Jatekosok.*;
import Log.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
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
        
        
        //Egyseg e = p1.getEgysegek()[1];
        //e.helyez(1);
        //refresh(getPosInRange(e));
//        System.out.println(getTavolsag(e, 60));
        // for(int i = 0; i < 10;i++){
        //     for(int j = 0; j < 12; j++){
        //      System.out.print(i*12+j+1 + ":" + getTavolsag(e, (i*12+j+1)) + " | ");
        //     }
        //     System.out.println();
        // }
        
        //refresh(getPosInRange(e));
        // for(int i = 0; i < 120; i++){
        //     System.out.println(getTavolsag(e, (i+1)));
        // }
        //System.out.println(e.getSebesseg());
        //System.out.println(e.getNumPos());
        //System.out.println(72);
        //System.out.println(getTavolsag(e, 86));
        ArrayList<Egyseg> allEgyseg = new ArrayList<>();
        
        for(var v:p1.getEgysegek()){
            allEgyseg.add(v);
        }
        for(var v:p2.getEgysegek()){
            allEgyseg.add(v);
        }
        allEgyseg.sort(new EgysegComparator());
        Log.log("Ebben a sorrendben lesz a harc:");
        for(var v:allEgyseg){
            Log.log(v.getPlayer().getNev() + ": " + v.getNev());
        }
        

        for(var e:allEgyseg){
            if(e.getPlayer() instanceof Jatekos){
                Log.log("Te jossz");
                if(!chooseAction(e))
                    return;
            }else{
                ((Gep)p2).play(e);
                Log.log("Ellenfel ezt csinalta: seemit");
            }
        }

        Csatater.incKor();

    }

    private boolean chooseAction(Egyseg e){
        int cselekves = -1;
        while(cselekves == -1){
            refresh();
            cselekves = menu("Mit szeretnel csinalni?", new String[]{"Varakozik", e.getNev() + " Mozog", e.getNev() + " Tamad", "Hos Tamad", "Hos Varazsol"});
            if(cselekves == -2){
                return true;
            }
            switch(cselekves){
                case -2:
                    return false;
                case 0:
                    return true;
                case 1:
                    mozog(e);
                    return true;
                case 2:
                    tamad();
                    return true;
                case 3:
                    hosTamad();
                    return true;
                case 4:
                    hosVarazsol();
                    return true;
                default:
                    return true;
            }
        }
        err = false;
        return true;
    }

    private void hosVarazsol(){

    }

    private void hosTamad(){

    }

    private void mozog(Egyseg e){
        ArrayList<Integer> hovaJo = getPosInRange(e);
        refresh(hovaJo);
        int valasz = -1;
        while(valasz == -1){
            refresh(hovaJo);
            valasz = menu("Hova szeretnel lepni? (Csak a zolden megjelolt mezokre lephetsz)", hovaJo);
            if(valasz == -2){
                return;
            }
        }
        e.helyez(valasz);
    }

    private void tamad(){

    }

    private void refresh(){
        Csatater.showPalya(null,new Player[]{p1, p2});
    }
    private void refresh(List<Integer> t){
        Csatater.showPalya(t,new Player[]{p1, p2});
    
    }

    public static int menu(String kerdes, List<Integer> t){
        int valasz = -1;

        System.out.println(kerdes + "\n");

        System.out.print((err ? "\n"+ Info.error("Csak a zolden jelolt mezok kozul valaszthat!\n") + "Valasz: " : "\n\n" + "Valasz: "));
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
        //System.out.println(maxTav);
        for(int i = 0; i < 120; i++){
            if(p1.getEgysegOnPosition(i+1) == null && p2.getEgysegOnPosition(i+1) == null){
                if(getTavolsag(e, i+1) <= maxTav){
                    jok.add(i+1);
                } 
            }
 
        }
        return jok;
    }

    public int getTavolsag(Egyseg e, int dest){
        if(e.getNumPos() == dest){
            return 0;
        }
        
        Node[][] nodes = new Node[10][12];
        
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 12; j++){
                nodes[i][j] = new Node(i*12 + j);
            }
        }
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 12; j++){
                addSzomszedok(nodes, i, j,dest);
            }
        }
        nodes[e.getPos().getY()][e.getPos().getX()].setDistance(0);
        // for(Node[] i:nodes){
        //     for(Node j:i){
        //         try{
        //         System.out.println(j);
        //         }catch(Exception b){
        //             System.out.println(b.toString());
        //         }
    
        //     }
        // }

        Set<Node> nudes = new HashSet<>();
        for(var i:nodes){
            for(var j:i){
                nudes.add(j);
            }
        }

        Set<Node> unSettledNodes = new HashSet<>();
        Set<Node> settledNodes = new HashSet<>();
        unSettledNodes.add(getLowest(nudes));
        int i = 0;

        while(unSettledNodes.size() != 0){
            Node current = getLowest(unSettledNodes);
            //System.out.println("c: "+ (current.getId() + 1));
            unSettledNodes.remove(current);
            // System.out.println(current);
            for(Node n:current.getSzomszedok()){
                
                if(!settledNodes.contains(n)){
                    //System.out.print(" |" + n.getId());
                    // if(current.getSzomszedok().contains(n)){
                    //     continue;
                    // }
                    n.setDistance(current.getDistance()+1);
                    unSettledNodes.addAll(n.getSzomszedok());
                    settledNodes.add(n);
                }
            }
            //System.out.println("\n"+current);
            settledNodes.add(current);
            i++;
        }
        int min = Integer.MAX_VALUE;
        
        for(var v:settledNodes){
            if(v.getId() == dest-1){
                 min = v.getDistance();
            }
            //System.out.println((v.getId()+1) + ": " + v.getDistance());
        }
        return min;
    }

    public void addSzomszedok(Node[][] n, int i, int j, int dest){
        Node n1 = null;
        Node n2 = null;
        Node n3 = null;
        Node n4 = null;
        Node n5 = null;
        Node n6 = null;
        Node n7 = null;
        Node n8 = null;
        if(i-1 >= 0 && j-1 >= 0){
            n1 = n[i-1][j-1];
        }
        if(i-1 >= 0){
            n2 = n[i-1][j];
        }
        if(i-1 >= 0 && j+1 <= 11){
            n3 = n[i-1][j+1];
        }
        if(j-1 >= 0){
            n4 = n[i][j-1];
        }
        if(j+1 <= 11){
            n5 = n[i][j+1];
        }
        if(i+1 <= 9){
            n6 = n[i+1][j];
        }
        if(i+1 <= 9 && j-1 >= 0){
            n7 = n[i+1][j-1];
        }
        if(i+1 <= 9 && j+1 <= 11){
            n8 = n[i+1][j+1];
        }
        Position most = Position.convertToPos(dest);
        if(n1 != null && /*((i-1) == most.getY() && (j-1) == most.getX())  || */ (p1.getEgysegOnPosition(new Position(i-1, j-1)) == null && p2.getEgysegOnPosition(new Position(i-1, j-1)) == null)){
            n[i][j].addSzomszed(n1);
        }
        if(n2 != null && /* ((i-1) == most.getY() && (j) == most.getX())  || */ p1.getEgysegOnPosition(new Position(i-1, j)) == null && p2.getEgysegOnPosition(new Position(i-1, j)) == null){
            n[i][j].addSzomszed(n2);
        }
        if(n3 != null && /*((i-1) == most.getY() && (j+1) == most.getX())  || */ p1.getEgysegOnPosition(new Position(i-1, j+1)) == null && p2.getEgysegOnPosition(new Position(i-1, j+1)) == null){
            n[i][j].addSzomszed(n3);
        }
        if(n4 != null &&  /*((i) == most.getY() && (j-1) == most.getX())  || */ p1.getEgysegOnPosition(new Position(i, j-1)) == null && p2.getEgysegOnPosition(new Position(i, j-1)) == null){
            n[i][j].addSzomszed(n4);
        }
        if(n5 != null && /* ((i) == most.getY() && (j+1) == most.getX())  ||*/ p1.getEgysegOnPosition(new Position(i, j+1)) == null && p2.getEgysegOnPosition(new Position(i, j+1)) == null){
            n[i][j].addSzomszed(n5);
        }
        if(n6 != null && /* ((i+1) == most.getY() && (j) == most.getX())  || */ p1.getEgysegOnPosition(new Position(i+1, j)) == null && p2.getEgysegOnPosition(new Position(i+1, j)) == null){
            n[i][j].addSzomszed(n6);
        }
        if(n7 != null && /**((i+1) == most.getY() && (j-1) == most.getX())  || */ p1.getEgysegOnPosition(new Position(i+1, j-1)) == null && p2.getEgysegOnPosition(new Position(i+1, j-1)) == null){
            n[i][j].addSzomszed(n7);
        }
        if(n8 != null && /**((i+1) == most.getY() && (j+1) == most.getX())  || */ p1.getEgysegOnPosition(new Position(i+1, j+1)) == null && p2.getEgysegOnPosition(new Position(i+1, j+1)) == null){
            n[i][j].addSzomszed(n8);
        }

        
    }

    private static Node getLowest(Set<Node> nodes){
        Node node = null;
        int min = Integer.MAX_VALUE;
        for(Node n:nodes){
            if(n.getDistance() < min){
                node = n;
                min = n.getDistance();
            }
        }
        return node;
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

class Node{
    private int id;
    private int dist = Integer.MAX_VALUE;
    private Set<Node> szomszedok = new HashSet<>();

    public Set<Node> getSzomszedok(){
        return szomszedok;
    }

    public void addSzomszed(Node szomszed){
        szomszedok.add(szomszed);
    }
    public void setDistance(int num){
        this.dist = num;
    }
    public int getDistance(){
        return dist;
    }
    public int getId(){
        return id;
    }
    public Node(int id){
        this.id = id;
    }
    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof Node && this.id == ((Node)other).id;
    }

    @Override
    public int hashCode(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.id);
        //  buffer.append(getSzomszedok().iterator().next());
        return buffer.toString().hashCode();
    }

    public String toString(){
        StringBuffer b = new StringBuffer();
        b.append("node " + (id+1) + " dist: " + dist );
        b.append(" somszedok: ");
        for(var v:szomszedok){
            b.append(v.getId()+1 + ":" + v.getDistance() +", ");
        }

        return b.toString();

    }
}