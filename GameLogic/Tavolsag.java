package GameLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Display.Position;
import Egysegek.Egyseg;
import Jatekosok.Player;
/**
 * Erre az osztalyra vagyok a legbuszkebb. Ez az osztaly a csatateren valo tavolsagokat tudja kiszamolni.
 * Az arra kell, hogy csak megfelelo tavolsagban levo teruletre tudjanak mozogni az egysegek.
 */
public class Tavolsag {
    Player p1;
    Player p2;

    public Tavolsag(Player p1, Player p2){
        this.p1 = p1;
        this.p2 =p2;
    }
    /**
     * Megkeresi az osszes egyseget bizonyos tavolsagon belul.
     * 
     * @param e Egyseg amitol meruk a tavolsagot
     * @param tav Milyen tavolsagban nezzuk az egysegeket
     * @param miket ha 1 akkor friendly, 2 akkor enemy, 3 akkor minda kettot beleveszi
     * @return egysegeknek a poziciojukat numPos-kent
     */
    // public ArrayList<Egyseg> getEgysegInRange(Egyseg e, int tav, int miket){
    //     ArrayList<Egyseg> lista = new ArrayList<>();
    //     if(miket == 1 || miket == 3){
    //         for(var egyseg:p1.getEgysegek()){
    //             if(getTavolsag(e, egyseg) <= tav){
    //                 lista.add(egyseg);
    //             }
    //         }
    //     }
    //     if(miket == 2 || miket == 3){
    //         for(var egyseg:p2.getEgysegek()){
    //             if(getTavolsag(e, egyseg) <= tav){
    //                 lista.add(egyseg);
    //             }
    //         }
    //     }


    //     return lista;
    // }

    public ArrayList<Egyseg> getEloEgysegInRange(Egyseg e, Player ellenfel){
        ArrayList<Egyseg> lista = new ArrayList<>();
        for(var egyseg:ellenfel.getEloEgysegek()){
            if(getTavolsag(e, egyseg) <= e.getSebesseg()){
                lista.add(egyseg);
            }
        }


        return lista;
    }

    public ArrayList<Integer> getUresekInRange(Egyseg e, Player ellenseg){
        int tav = e.getSebesseg();
        ArrayList<Integer> lista = new ArrayList<>();
        
        for(int i = 0; i < 120; i++){
            if(ellenseg.getEloEgysegOnPosition(i) != null || e.getPlayer().getEloEgysegOnPosition(i) != null){
                continue;
            }
            int mostTav = getTavolsag(e, i);

            if(mostTav != -1 && mostTav <= tav){
                lista.add(i);
            }
        }
        return lista;
    }
    /**
     * Visszaadja az adott egysegnek elerheto mazok szamat.
     * @param e a vizsgalt egyseg
     * @return poziciok numPoskent
     */
    public ArrayList<Integer> getPosInRange(Egyseg e){
        ArrayList<Integer> jok = new ArrayList<>();
        int maxTav = e.getSebesseg();
        //System.out.println(maxTav);
        for(int i = 0; i < 120; i++){
            if(p1.getEloEgysegOnPosition(i+1) == null && p2.getEloEgysegOnPosition(i+1) == null){
                if(getTavolsag(e, i+1) <= maxTav){
                    jok.add(i+1);
                } 
            }
 
        }
        return jok;
    }
    /**
     * Ket egyseg tavolsagat adja vissz 
     * @param e elso egyseg
     * @param e2 masodik egyseg
     * @return ket egyseg tavolsaga int
     */
    public int getTavolsag(Egyseg e, Egyseg e2){
        int y = e2.getPos().getY();
        int x = e2.getPos().getX();
        List<Position> posok = new ArrayList<>();
        if(y-1 >= 0 && x-1 >= 0 && (p1.getEgysegOnPosition(new Position(y-1, x-1)) == null && p2.getEgysegOnPosition(new Position(y-1, x-1)) == null)){
            posok.add( new Position(y-1, x-1));
        }
        if(y-1 >= 0 && (p1.getEgysegOnPosition(new Position(y-1, x)) == null && p2.getEgysegOnPosition(new Position(y-1, x)) == null)){
            posok.add(new Position(y-1, x));
        }
        if(y-1 >= 0 && x+1 <= 11 && (p1.getEgysegOnPosition(new Position(y-1, x+1)) == null && p2.getEgysegOnPosition(new Position(y-1, x+1)) == null)){
            posok.add(new Position(y-1, x+1));
        }
        if(x-1 >= 0 && (p1.getEgysegOnPosition(new Position(y, x-1)) == null && p2.getEgysegOnPosition(new Position(y, x-1)) == null)){
            posok.add(new Position(y, x-1));
        }
        if(x+1 <= 11 && (p1.getEgysegOnPosition(new Position(y, x+1)) == null && p2.getEgysegOnPosition(new Position(y, x+1)) == null)){
            posok.add(new Position(y, x+1));
        }
        if(y+1 <= 9 && (p1.getEgysegOnPosition(new Position(y+1, x)) == null && p2.getEgysegOnPosition(new Position(y+1, x)) == null)){
            posok.add(new Position(y+1, x));
        }
        if(y+1 <= 9 && x-1 >= 0 && (p1.getEgysegOnPosition(new Position(y+1, x-1)) == null && p2.getEgysegOnPosition(new Position(y+1, x-1)) == null)){
            posok.add(new Position(y+1, x-1));
        }
        if(y+1 <= 9 && x+1 <= 11 && (p1.getEgysegOnPosition(new Position(y+1, x+1)) == null && p2.getEgysegOnPosition(new Position(y+1, x+1)) == null)){
            posok.add( new Position(y+1, x+1));
        }
        if(posok.size() == 0){
            return -1;
        }
        int tav = Integer.MAX_VALUE;
        for(var pos:posok){
            int most = getTavolsag(e, Position.convertToSzam(pos));
            if( most < tav){
                tav = most; 
            }

        }

        return tav + 1;
    }
    /**
     * Egy egyseg es egy ures mezo kozott.
     * Ha pozicion megis van egyseg akkor Integer.MAX_INT erteket adja vissza
     * @param e egyseg amit vizsgalunk
     * @param dest  celpozicio numPoskent
     * @return  a ketto tavolsaga
     */
    public int getTavolsag(Egyseg e, int dest){
        if(p1.getEgysegOnPosition(dest) != null){
            //return getTavolsag(e, p1.getEgysegOnPosition(dest));
            return -1;
        }
        if(p2.getEgysegOnPosition(dest) != null){
            return -1;
            //return getTavolsag(e, p2.getEgysegOnPosition(dest));
        }
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

        Set<Node> nudes = new HashSet<>();
        for(var i:nodes){
            for(var j:i){
                nudes.add(j);
            }
        }
        
        Set<Node> unSettledNodes = new HashSet<>();
        Set<Node> settledNodes = new HashSet<>();
        unSettledNodes.add(getLowest(nudes));

        
        while(unSettledNodes.size() != 0){
            Node current = getLowest(unSettledNodes);
            //System.out.println(current.getId() + " " + );
            //System.out.println("c: "+ (current.getId() + 1));
            unSettledNodes.remove(current);
            
            //System.out.println(current);

            for(Node n:current.getSzomszedok()){
                
                if(!settledNodes.contains(n)){
                    int tav = current.getDistance()+ 1;
                    if(tav < n.getDistance()){
                        n.setDistance(tav);
                    }
                    //System.out.println(n);
                    unSettledNodes.add(n);
                    //settledNodes.add(n);
                }
            }
            settledNodes.add(current);
            //System.out.println("\n"+current);
            
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

    /**
     * Egy adott 3x3-as pozicion minen egyseget visszaad
     * @param pos   a vizsgalt pozicio
     * @param p1    elso player
     * @param p2    2. player
     * @return  Az Egysegeket tartalmazo lista.
     */
    public static List<Egyseg> getSzomszedok(Position pos, Player p1, Player p2){
        List<Egyseg> szomszedok = new ArrayList<>();
        int y = pos.getY();
        int x = pos.getX();

        if(p1.getEloEgysegOnPosition(y, x) != null){
            szomszedok.add(p1.getEloEgysegOnPosition(y, x));
        }
        if(y-1 >= 0 && x-1 >= 0 &&  p1.getEloEgysegOnPosition(y-1, x-1) != null){
            szomszedok.add(p1.getEloEgysegOnPosition(y-1, x-1));
        }
        if(y-1 >= 0 &&  p1.getEloEgysegOnPosition(y-1, x) != null){
            szomszedok.add(p1.getEloEgysegOnPosition(y-1, x));
        }
        if(y-1 >= 0 && x+1 <= 11 && p1.getEloEgysegOnPosition(y-1, x+1) != null){
            szomszedok.add(p1.getEloEgysegOnPosition(y-1, x+1));
        }
        if(x-1 >= 0 &&  p1.getEloEgysegOnPosition(y, x-1) != null){
            szomszedok.add(p1.getEloEgysegOnPosition(y, x-1));
        }
        if(x+1 <= 11 &&  p1.getEloEgysegOnPosition(y, x+1) != null){
            szomszedok.add(p1.getEloEgysegOnPosition(y, x+1));
        }
        if(y+1 <= 9 &&  p1.getEloEgysegOnPosition(y+1, x) != null){
            szomszedok.add(p1.getEloEgysegOnPosition(y+1, x));
        }
        if(y+1 <= 9 && x-1 >= 0  && p1.getEloEgysegOnPosition(y+1, x-1) != null){
            szomszedok.add(p1.getEloEgysegOnPosition(y+1, x-1));
        }
        if(y+1 <= 9 && x+1 <= 11 && p1.getEloEgysegOnPosition(y+1, x+1) != null){
            szomszedok.add(p1.getEloEgysegOnPosition(y+1, x+1));
        }
        if(p2 != null){
            if(p2.getEloEgysegOnPosition(y, x) != null){
                szomszedok.add(p2.getEloEgysegOnPosition(y, x));
            }
            if(y-1 >= 0 && x-1 >= 0 &&  p2.getEloEgysegOnPosition(y-1, x-1) != null){
                szomszedok.add(p2.getEloEgysegOnPosition(y-1, x-1));
            }
            if(y-1 >= 0 &&  p2.getEloEgysegOnPosition(y-1, x) != null){
                szomszedok.add(p2.getEloEgysegOnPosition(y-1, x));
            }
            if(y-1 >= 0 && x+1 <= 11 && p2.getEloEgysegOnPosition(y-1, x+1) != null){
                szomszedok.add(p2.getEloEgysegOnPosition(y-1, x+1));
            }
            if(x-1 >= 0 &&  p2.getEloEgysegOnPosition(y, x-1) != null){
                szomszedok.add(p2.getEloEgysegOnPosition(y, x-1));
            }
            if(x+1 <= 11 &&  p2.getEloEgysegOnPosition(y, x+1) != null){
                szomszedok.add(p2.getEloEgysegOnPosition(y, x+1));
            }
            if(y+1 <= 9 &&  p2.getEloEgysegOnPosition(y+1, x) != null){
                szomszedok.add(p2.getEloEgysegOnPosition(y+1, x));
            }
            if(y+1 <= 9 && x-1 >= 0  && p2.getEloEgysegOnPosition(y+1, x-1) != null){
                szomszedok.add(p2.getEloEgysegOnPosition(y+1, x-1));
            }
            if(y+1 <= 9 && x+1 <= 11 && p2.getEloEgysegOnPosition(y+1, x+1) != null){
                szomszedok.add(p2.getEloEgysegOnPosition(y+1, x+1));
            }
        }
        
        return szomszedok;
    }

    private void addSzomszedok(Node[][] n, int i, int j, int dest){
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
        if(n1 != null && /*((i-1) == most.getY() && (j-1) == most.getX())  || */ (p1.getEloEgysegOnPosition(new Position(i-1, j-1)) == null && p2.getEloEgysegOnPosition(new Position(i-1, j-1)) == null)){
            n[i][j].addSzomszed(n1);
        }
        if(n2 != null && /* ((i-1) == most.getY() && (j) == most.getX())  || */ p1.getEloEgysegOnPosition(new Position(i-1, j)) == null && p2.getEloEgysegOnPosition(new Position(i-1, j)) == null){
            n[i][j].addSzomszed(n2);
        }
        if(n3 != null && /*((i-1) == most.getY() && (j+1) == most.getX())  || */ p1.getEloEgysegOnPosition(new Position(i-1, j+1)) == null && p2.getEloEgysegOnPosition(new Position(i-1, j+1)) == null){
            n[i][j].addSzomszed(n3);
        }
        if(n4 != null &&  /*((i) == most.getY() && (j-1) == most.getX())  || */ p1.getEloEgysegOnPosition(new Position(i, j-1)) == null && p2.getEloEgysegOnPosition(new Position(i, j-1)) == null){
            n[i][j].addSzomszed(n4);
        }
        if(n5 != null && /* ((i) == most.getY() && (j+1) == most.getX())  ||*/ p1.getEloEgysegOnPosition(new Position(i, j+1)) == null && p2.getEloEgysegOnPosition(new Position(i, j+1)) == null){
            n[i][j].addSzomszed(n5);
        }
        if(n6 != null && /* ((i+1) == most.getY() && (j) == most.getX())  || */ p1.getEloEgysegOnPosition(new Position(i+1, j)) == null && p2.getEloEgysegOnPosition(new Position(i+1, j)) == null){
            n[i][j].addSzomszed(n6);
        }
        if(n7 != null && /**((i+1) == most.getY() && (j-1) == most.getX())  || */ p1.getEloEgysegOnPosition(new Position(i+1, j-1)) == null && p2.getEloEgysegOnPosition(new Position(i+1, j-1)) == null){
            n[i][j].addSzomszed(n7);
        }
        if(n8 != null && /**((i+1) == most.getY() && (j+1) == most.getX())  || */ p1.getEloEgysegOnPosition(new Position(i+1, j+1)) == null && p2.getEloEgysegOnPosition(new Position(i+1, j+1)) == null){
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


class Node{
    private int id;
    private int dist = Integer.MAX_VALUE-1;
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
        for(var v:this.getSzomszedok()){
            buffer.append(v.getId());
        }
        //  buffer.append(getSzomszedok().iterator().next());
        return buffer.toString().hashCode();
    }

    public String toString(){
        StringBuffer b = new StringBuffer();
        b.append("node " + (id+1) + " dist: " + dist );
        b.append(" somszedok: ");
        for(var v:szomszedok){
            int i = v.getDistance() == Integer.MAX_VALUE? -1:v.getDistance();
            b.append(v.getId()+1 + " : " + v.getDistance() +", ");
        }

        return b.toString();

    }
}