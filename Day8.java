import java.io.*;
import java.util.*;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input8.txt");
        Scanner in = new Scanner(file);
        ArrayList<String[]> list = new ArrayList<String[]>();
        while (in.hasNextLine()){
            String[] hold = in.nextLine().split("");
            list.add(hold);
        }
        in.close();
        System.out.println( "Part 1: " + part1(list));
        System.out.println("Part 2: " + part2(list));
    }

    static int part1(ArrayList<String[]> list){
        int height = list.size();
        int width = list.get(0).length;
        String[][] map = new String[height][width];
        for (int i = 0; i < height; i ++){
            for (int j = 0; j < width; j++){
                map[i][j] = ".";
            }
        }
        ArrayList<String> antenna = new ArrayList<String>();
        ArrayList<ArrayList<int[]>> antennaCoords = new  ArrayList<ArrayList<int[]>>();
        ArrayList<int[]> goodCoords = new ArrayList<int[]>();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                int[] coord = {j, i};
                if (!list.get(i)[j].equals(".")){
                    if (antenna.contains(list.get(i)[j])){
                        for (int p = 0; p < antenna.size(); p++){
                            if (antenna.get(p).equals(list.get(i)[j])){
                                antennaCoords.get(p).add(coord);
                            }
                        }
                    } else {
                        antenna.add(list.get(i)[j]);
                        antennaCoords.add(new ArrayList<int[]>());
                        antennaCoords.get(antennaCoords.size()-1).add(coord);

                    }
                }
            }
        }
        for (int i = 0; i < antenna.size(); i++){
            //System.out.print(antenna.get(i) + ": ");
            for (int j = antennaCoords.get(i).size()-1; j > 0; j--){

                for(int k = j-1; k >= 0; k--){
                    int[] test1 = antennaCoords.get(i).get(j);
                    int[] test2 = antennaCoords.get(i).get(k);
                    int diffX = test1[0] - test2[0];
                    int diffY = test1[1] - test2[1];
                    int[] hold1 = {test1[0] + diffX, test1[1] + diffY};
                    goodCoords.add(hold1);
                    int[] hold2 = {test2[0] - diffX, test2[1] - diffY};
                    goodCoords.add(hold2);
                    //System.out.print("{[" + hold1[0] + ", " + hold1[1] + "][" + hold2[0] + ", "+ hold2[1] + "]}");
                }
            }
        }
        int count = 0;
        for (int i = 0; i < goodCoords.size(); i++){
            if (goodCoords.get(i)[0] >= width || goodCoords.get(i)[0] <  0 || goodCoords.get(i)[1] >= height || goodCoords.get(i)[1] < 0){
            } else {
                map[goodCoords.get(i)[0]][goodCoords.get(i)[1]] = "#";
            }
        }
        for (String[] i : map){
            for (String j : i){
                //System.out.print(j);
                if (j.equals("#")){
                    count++;
                }
            }
            //System.out.println();
        }
        return count;
        
    }

    static int part2(ArrayList<String[]> list){
        int height = list.size();
        int width = list.get(0).length;
        String[][] map = new String[height][width];
        for (int i = 0; i < height; i ++){
            for (int j = 0; j < width; j++){
                map[i][j] = ".";
            }
        }
        ArrayList<String> antenna = new ArrayList<String>();
        ArrayList<ArrayList<int[]>> antennaCoords = new  ArrayList<ArrayList<int[]>>();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                int[] coord = {j, i};
                if (!list.get(i)[j].equals(".")){
                    map[i][j] = "#";
                    if (antenna.contains(list.get(i)[j])){
                        for (int p = 0; p < antenna.size(); p++){
                            if (antenna.get(p).equals(list.get(i)[j])){
                                antennaCoords.get(p).add(coord);
                            }
                        }
                    } else {
                        antenna.add(list.get(i)[j]);
                        antennaCoords.add(new ArrayList<int[]>());
                        antennaCoords.get(antennaCoords.size()-1).add(coord);

                    }
                }
            }
        }
        int[] test1 = new int[2];
        int[] test2 = new int[2];
        for (int i = 0; i < antenna.size(); i++){
            // System.out.print(antenna.get(i) + ": ");
            // for (int q = 0; q < height; q ++){
            //     for (int p = 0; p < width; p++){
            //         map[q][p] = ".";
            //     }
            // }
            for (int j = antennaCoords.get(i).size()-1; j >= 0; j--){

                for(int k = j-1; k >= 0; k--){
                    test1[0] = antennaCoords.get(i).get(j)[0];
                    test2[0] = antennaCoords.get(i).get(k)[0];
                    test1[1] = antennaCoords.get(i).get(j)[1];
                    test2[1] = antennaCoords.get(i).get(k)[1];
                    map[test1[1]][test1[0]] = "#";
                    map[test2[1]][test2[0]] = "#";
                    int diffX = test1[0] - test2[0];
                    int diffY = test1[1] - test2[1];
                    int[] hold1 = new int[2];
                    while (test1[0] + diffX >= 0 && test1[0] + diffX < width && test1[1] + diffY >= 0 &&  test1[1] + diffY < height){
                        hold1[0] = test1[0] + diffX;
                        hold1[1] = test1[1] + diffY;
                        map[hold1[1]][hold1[0]] = "#";
                        test1[0] = test1[0] + diffX;
                        test1[1] = test1[1] + diffY;
                    }
                    test1[0] = antennaCoords.get(i).get(j)[0];
                    test2[0] = antennaCoords.get(i).get(k)[0];
                    test1[1] = antennaCoords.get(i).get(j)[1];
                    test2[1] = antennaCoords.get(i).get(k)[1];
                    while (test1[0] - diffX >= 0 && test1[0] - diffX < width && test1[1] - diffY >= 0 &&  test1[1] - diffY < height){
                        hold1[0] = test1[0] - diffX;
                        hold1[1] = test1[1] - diffY;
                        map[hold1[1]][hold1[0]] = "#";
                        test1[0] = test1[0] - diffX;
                        test1[1] = test1[1] - diffY;
                    }
                    //System.out.print("{[" + hold1[0] + ", " + hold1[1] + "][" + hold2[0] + ", "+ hold2[1] + "]}");
                }
            }
            // System.out.println(antenna.get(i) + ": ");
            // for (String[] m : map){
            //     for (String n : m){
            //         System.out.print(n);
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            
        }
        int count = 0;
        for (String[] i : map){
            for (String j : i){
                System.out.print(j);
                if (j.equals("#")){
                    count++;
                }
            }
            System.out.println();
        }
        return count;
        
    }
}
