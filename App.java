import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

class Main {
    static boolean[] letterCheck = new boolean[26];
    public static void main(String[] args) throws IOException{
        //swotty, cwtch,
        Scanner s = new Scanner(System.in);
        System.out.println('z' - 97);
        System.out.println("program started. Press any key to continue, or break to stop the program");
        while(true){
            System.out.println("\n\n\n\n\n\n\n\ntype the grid\n----------------");
            String grid = s.nextLine();
            if(grid.length() < 16) continue;
            Grid g1 = new Grid(grid, 4);
            System.out.println(g1);
            long time = System.nanoTime();
            ArrayList<String> answers = g1.findAllWords();
            System.out.println("Valid Words: " + answers);
            System.out.println("Total Words: " + answers.size());
            System.out.println("Total Points: " + Grid.countPoints(answers));
            System.out.println((System.nanoTime() - time)/(Math.pow(10,7)));
        }
        
    //System.out.println(g1.stringWords(new Position(0,0)));
    }
}