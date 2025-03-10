public class Position{
    private int x;
    private int y;
    public Position(int newx, int newy){
        x = newx;
        y = newy;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean equals(Position p){
        return (x == p.getX() && y == p.getY());
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}