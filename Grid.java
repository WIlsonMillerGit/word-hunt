import java.util.ArrayList;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Grid{
    int size;
    String[][] grid;
    String[] cantstartwith = {"bb", "bc", "bf", "bg", "bj", "bk", "bm", "bn", "bp", "bq", "bs", "bt", "bv", "bx", "bz", "cb", "cc", "cd", "cf", "cg", "cj", "ck", "cm", "cp", "cq", "cv", "cx", "db", "dc", "dd", "df", "dg", "dk", "dl", "dm", "dn", "dp", "dq", "dt", "dx", "fb", "fc", "fd", "ff", "fg", "fh", "fk", "fm", "fn", "fp", "fq", "fs", "ft", "fv", "fw", "fx", "fz", "gb", "gc", "gd", "gf", "gg", "gk", "gp", "gq", "gs", "gt", "gv", "gx", "gz", "hb", "hc", "hd", "hf", "hg", "hh", "hj", "hk", "hl", "hn", "hp", "hq", "hs", "ht", "hv", "hx", "hz", "ie", "ii", "iq", "iy", "jb", "jc", "jd", "jf", "jg", "jj", "jk", "jl", "jm", "jp", "jq", "jr", "js", "jt", "jv", "jw", "jx", "jz", "kc", "kd", "kf", "kj", "kk", "km", "kp", "kq", "kt", "kx", "kz", "lb", "lc", "ld", "lf", "lg", "lj", "lk", "lm", "ln", "lp", "lq", "lr", "ls", "lt", "lv", "lx", "lz", "mc", "md", "mf", "mj", "mk", "ml", "mq", "ms", "mt", "mx", "nb", "nc", "nf", "nj", "nl", "nm", "nn", "np", "nq", "nr", "ns", "nv", "nw", "nx", "nz", "pb", "pc", "pd", "pg", "pj", "pk", "pm", "pp", "pq", "pv", "px", "qb", "qc", "qd", "qe", "qf", "qg", "qh", "qj", "qk", "ql", "qm", "qn", "qp", "qq", "qr", "qs", "qt", "qv", "qx", "qy", "qz", "rb", "rc", "rd", "rf", "rg", "rj", "rk", "rl", "rm", "rn", "rp", "rq", "rr", "rs", "rt", "rv", "rw", "rx", "rz", "ss", "sx", "sz", "tb", "td", "tf", "tg", "tk", "tl", "tn", "tp", "tq", "tt", "tv", "tx", "uo", "uq", "uu", "uw", "uy", "uz", "vb", "vc", "vd", "vf", "vg", "vh", "vj", "vk", "vm", "vn", "vp", "vq", "vs", "vt", "vv", "vw", "vx", "vz", "wb", "wc", "wd", "wf", "wg", "wj", "wk", "wl", "wm", "wn", "wp", "wq", "ws", "wt", "wv", "ww", "wx", "wz", "xb", "xc", "xd", "xf", "xg", "xh", "xj", "xk", "xl", "xm", "xn", "xp", "xq", "xs", "xt", "xv", "xw", "xx", "xz", "yh", "yj", "yk", "yq", "yv", "yx", "yy", "yz", "zb", "zc", "zd", "zf", "zg", "zj", "zk", "zm", "zn", "zp", "zq", "zr", "zs", "zt", "zv", "zx"};
    int[] skipTo = {0, 16202, 31437, 56479, 73099, 84433, 95067, 104420, 114944, 124548, 126859, 130254, 138312, 154138, 160706, 169601, 193947, 195358, 210379, 242373, 256942, 266466, 271056, 276979, 277288, 278324, 279496};
    ArrayList<String> validWords; 
    
    public Grid(String letters, int s) throws IOException{
        size = s;
        grid = new String[size][size];
        for(int i = 0; i < size * size; i++){
            grid[i/size][i%size] = String.valueOf(letters.charAt(i));
        }
        validWords = new WordList("csw.txt").getWords();
    }

    public String getLetter(Position p){
        return grid[p.getY()][p.getX()];
    }
    
    public String toString(){
        String returnString = "";
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                returnString += grid[i][j] + " ";
            }
            returnString += "\n";
        }
        return returnString;
    }

    public ArrayList<String> findAllWords(){
        ArrayList<String> wordList = new ArrayList<String>();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                wordList.addAll(stringWords(new Position(i, j)));
            }
        }
        int longestWord = 0;
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).length() > longestWord) longestWord = wordList.get(i).length();
        }
        Set<String> set = new HashSet<>(wordList);
        wordList.clear();
        wordList.addAll(set);
        
        ArrayList<String> sortedList = new ArrayList<String>();
        for(int i = longestWord; i > 2; i--){
            for(int j = 0; j < wordList.size(); j++){
                if(wordList.get(j).length() == i) sortedList.add(wordList.get(j));
            }
        }
        
        return sortedList;
    }

    public boolean isValid(String s){
        if(s.charAt(0) >= 'a' && s.charAt(0) <= 'z'){
            for(int i = skipTo[s.charAt(0) - 97]; i < skipTo[s.charAt(0) - 96]; i++){
                if(s.equals(validWords.get(i))) return true;
            }
        }
        return false;
    }
    public boolean validStart(String s){
        if(s.charAt(0) >= 'a' && s.charAt(0) <= 'z'){
            for(int i = skipTo[s.charAt(0) - 97]; i < skipTo[s.charAt(0) - 96]; i++){
                if((validWords.get(i)).startsWith(s)) return true;
            }
        }
        return false;
    }
    
    public ArrayList<String> stringWords(Position p){
        ArrayList<WordString> stringList = new ArrayList<WordString>();
        WordString w = new WordString(getLetter(p), p);
        ArrayList<WordString> test = generateWords(w, 0);
        stringList.addAll(test);

        ArrayList<String> returnWords = new ArrayList<String>();
        for(int i = 0; i < stringList.size(); i++){
            returnWords.add(stringList.get(i).getWord());
        }
        return returnWords;
    }
    public static int countPoints(ArrayList<String> stringArray){
        int[] scoreTable = {100,400,800,1400,1800,2200,2600};
        int points = 0;
        for(int i = 0; i < stringArray.size(); i++){
            if(stringArray.get(i).length() > 9 || stringArray.get(i).length() < 3){
                points += scoreTable[6];
            }else{
                points += scoreTable[stringArray.get(i).length() - 3];
            }
        }
        return points;
    }
    
    public ArrayList<WordString> generateWords(WordString ws, int depth){
        if(depth == 1){ //if word starts with an imposible first two letters, stops the chain
            for(int i = 0; i < cantstartwith.length; i++){
                if(ws.getWord().equals(cantstartwith[i])) return new ArrayList<WordString>();
            }
        }
        ArrayList<WordString> generatedList = new ArrayList<WordString>();
        if(depth > 1 && isValid(ws.getWord())){
            generatedList.add(ws);
        }
        if(depth > 1 && !validStart(ws.getWord())){
            return new ArrayList<WordString>();
        }
        for(int i = 0; i < 9; i++){
            Position p = new Position(ws.getPosition().getX() + (i%3) - 1, ws.getPosition().getY() + (i/3) - 1);
            if(ws.validPos(p, size, size)){
                generatedList.addAll(generateWords(new WordString(getLetter(p), p, ws), depth + 1));
            }
            //System.out.println(((ws.getPosition().getX() + (i%3) - 1) + ", " + (ws.getPosition().getY() + (i/3) - 1)) + " : " + ws.validPos(new Position(ws.getPosition().getX() + (i%3) - 1, ws.getPosition().getY() + (i/3) - 1), size, size));
        }
        return generatedList;
    }
}