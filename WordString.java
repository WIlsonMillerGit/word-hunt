import java.util.ArrayList;

public class WordString{
    private String word;
    private ArrayList<Position> nodes;
    private Position position;
    private int wordLength;
    public ArrayList<Position> getNodes(){
        return nodes;
    }
    public String getWord(){
        return word;
    }
    public Position getPosition(){
        return position;
    }
    public String toString(){
        return getWord();
    }
    public int length(){
        return wordLength;
    }

    public WordString(String w, Position p){
        word = w;
        wordLength = w.length();
        nodes = new ArrayList<Position>();
        nodes.add(p);
        position = p;
    }

    public WordString(String newLetter, Position p, WordString ws){
        nodes = new ArrayList<Position>(ws.getNodes());
        nodes.add(p);
        word = ws.getWord() + newLetter;
        wordLength = word.length();
        position = p;
    }

    public boolean validPos(Position p, int width, int height){
        if(p.getX() < 0 || p.getY() < 0 || p.getX() > width - 1 || p.getY() > height - 1) return false;
        for(int i = 0; i < nodes.size(); i++){
        if(p.equals(nodes.get(i))) return false;
        }
        return true;
    }
}