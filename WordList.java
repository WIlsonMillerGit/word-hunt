import java.io.*;
import java.util.ArrayList;

public class WordList{
    
    public ArrayList<String> words = new ArrayList<String>();
    
    public ArrayList<String> getWords(){
        return words;
    }
    
    public WordList(String filename) throws IOException{
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null){
            words.add(st.toLowerCase());
        }

        br.close();
    }
    /*
    public void readFile(String filename) throws IOException{
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null){
            words.add(st);
        }
        br.close();
    }
    */
    /*
    public static void writeFile(String filename, String text) throws IOException {
        try (FileWriter f = new FileWriter(filename, true); 
                 BufferedWriter b = new BufferedWriter(f); 
                 PrintWriter p = new PrintWriter(b);){ 
            p.println(text); 
        } catch (IOException i) { 
            i.printStackTrace(); 
        }
    }
    */
}