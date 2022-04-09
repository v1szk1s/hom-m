package Display;

public class Position {
    int y;
    int x;
    public Position(int y, int x){
        this.y = y;
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }

    public void setY(int y){
        this.y = y;
    }
    public void setX(int x){
        this.x = x;
    }

    public static Position convertToPos(int szam){
        int y = (szam-1)/12;
        int x = (szam-1)%12;
        return new Position(y, x);
    }
    public static Position convertToPos(int y, int x){
        return new Position(y, x);
    }

    public static int convertToSzam(Position coo){
        return coo.getY()*12 + coo.getX() + 1;
    }
    @Override
    public boolean equals(Object o){
        if(((Position)o).getX() == x && ((Position)o).getY() == y)
            return true;
        return false;
    }

    public String toString(){
        return "y: " + y + ", x: " + x;

    }
    
}
