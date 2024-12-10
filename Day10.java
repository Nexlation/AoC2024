import java.util.*;
import java.io.*;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input10.txt");
        Scanner in = new Scanner(file);
        ArrayList<String[]> list = new ArrayList<String[]>();
        while (in.hasNextLine()){
            list.add(in.nextLine().split(""));
        }
        in.close(); 
        for (String[] i : list){
            System.out.println("" + Arrays.toString(i));
        }
        System.out.println("Part 1: " + driver(list, 1));
        System.out.println("Part 2: " + driver(list, 2));
    }


    static int driver(ArrayList<String[]> list, int part){
        ArrayList<int[]> nineReached = new ArrayList<int[]>();
        int sum = 0;
        for (int i = 0; i < list.size(); i++){
            for (int j =0; j < list.get(0).length; j++){
                if (list.get(i)[j].equals("0")){
                    nineReached.clear();
                    sum += recursiveSearch(list, i, j, nineReached, part);
                }
            }
        }

        return sum;
    }

    static int recursiveSearch(ArrayList<String[]> list, int i, int j, ArrayList<int[]> nineReached, int part){
        int height = list.size();
        int width = list.get(0).length;
        int retVal = 0;
        int nextNum = Integer.valueOf(list.get(i)[j]) + 1;
        if (list.get(i)[j].equals("9")){
            if (part == 1){
                for (int[] k : nineReached){
                    if (k[0] == j && k[1] == i){
                        return 0;
                    }
                }
                int[] add = {j, i};
                nineReached.add(add);
            }
            return 1;
        }
        if (i-1 >= 0 && Integer.valueOf(list.get(i-1)[j]) == nextNum){
            retVal += recursiveSearch(list, i-1, j, nineReached, part);
        }
        if (i +1 < height && Integer.valueOf(list.get(i+1)[j]) == nextNum){
            retVal += recursiveSearch(list, i+1, j, nineReached, part);
        }
        if (j-1 >= 0 && Integer.valueOf(list.get(i)[j-1]) == nextNum){
            retVal += recursiveSearch(list, i, j-1, nineReached, part);
        }
        if (j+1 < width && Integer.valueOf(list.get(i)[j+1]) == nextNum){
            retVal += recursiveSearch(list, i, j+1, nineReached, part);
        }
        return retVal;
    }
}
