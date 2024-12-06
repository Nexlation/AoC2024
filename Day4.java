import java.util.*;
import java.io.*;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input4.txt");
        Scanner in = new Scanner(file);
        int height = 1;
        int width = in.nextLine().length();
        while (in.hasNextLine()){
            in.nextLine();
            height++;
        }
        in.close();
        File file2 = new File("input4.txt");
        Scanner in2 = new Scanner(file2);
        char[][] inputArray = new char[height][width];
        int q = 0;
        while (in2.hasNextLine()){
            String curLine = in2.nextLine();
            for (int p = 0; p < width; p++){
                inputArray[q][p] = curLine.charAt(p);
            }
            q++;
        }
        System.out.println("list done");
        in2.close();
        System.out.println("Part 1: " + part1(inputArray, height, width));
        System.out.println("Part 2: " + part2(inputArray, height, width));
    }
        
        
        
    static int part1(char[][] list, int height, int width){
        int count = 0;
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (list[i][j] == 'X'){
                    if (i + 3 < height && list[i+1][j] == 'M' && list[i+2][j] == 'A' && list[i+3][j] == 'S'){
                        count++;
                        //System.out.println("Pair " + count + " at " + i + ", " + j + " down");
                    }
                    if(i - 3 >= 0 && list[i-1][j] == 'M' && list[i-2][j] == 'A' && list[i-3][j] == 'S'){
                        count++;
                        //System.out.println("Pair " + count + " at " + i + ", " + j + " up"); 
                    }
                    if (j + 3 < width && list[i][j+1] == 'M' && list[i][j+2] == 'A' && list[i][j+3] == 'S'){
                        count++;
                        //System.out.println("Pair " + count + " at " + i + ", " + j + " right");
                    }
                    if (j - 3 >= 0 && list[i][j-1] == 'M' && list[i][j-2] == 'A' && list[i][j-3] == 'S'){
                        count++;
                        //System.out.println("Pair " + count + " at " + i + ", " + j + " left");
                    }
                    if (i + 3 < height && j + 3 < width && list[i+1][j+1] == 'M' && list[i+2][j+2] == 'A' && list[i+3][j+3] == 'S'){
                        count++;
                        //System.out.println("Pair " + count + " at " + i + ", " + j + " down right");
                    }
                    if (i - 3 >= 0 && j + 3 < width && list[i-1][j+1] == 'M' && list[i-2][j+2] == 'A' && list[i-3][j+3] == 'S'){
                        count++;
                        //System.out.println("Pair " + count + " at " + i + ", " + j + " up right");
                    }
                    if (i + 3 < height && j - 3 >= 0 && list[i+1][j-1] == 'M' && list[i+2][j-2] == 'A' && list[i+3][j-3] == 'S'){
                        count++;
                        //System.out.println("Pair " + count + " at " + i + ", " + j + " down left");
                    }
                    if (i - 3 >= 0 && j - 3 >= 0 && list[i-1][j-1] == 'M' && list[i-2][j-2] == 'A' && list[i-3][j-3] == 'S'){
                        count++;
                        //System.out.println("Pair " + count + " at " + i + ", " + j + " up left");
                    }
                }
            }

        }
        return count;
    }

    static int part2(char[][] list, int height, int width){
        int count = 0;
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (list[i][j] == 'A' && i + 1 < height && i -1 >= 0 && j + 1 < width && j -1 >= 0){
                    if (list[i+1][j+1] == 'M'){
                        if (list[i-1][j+1] == 'M' && list[i+1][j-1] == 'S' && list[i-1][j-1] == 'S'){
                            count++;
                        }
                        else if (list[i+1][j-1] == 'M' && list[i-1][j+1] == 'S' && list[i-1][j-1] == 'S'){
                            count++;
                        }
                    }
                    if (list[i+1][j+1] == 'S'){
                        if (list[i-1][j+1] == 'S' && list[i+1][j-1] == 'M' && list[i-1][j-1] == 'M'){
                            count++;
                        }
                        else if (list[i+1][j-1] == 'S' && list[i-1][j+1] == 'M' && list[i-1][j-1] == 'M'){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
