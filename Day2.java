import java.io.*;
import java.util.*;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input2.txt");
        Scanner in = new Scanner(file);
        int count = 0;
        int count2 = 0;
        while (in.hasNextLine()){
            ArrayList<String> list = new ArrayList<String>(Arrays.asList(in.nextLine().split(" ")));
            count += part1(list);
            count2 += part2(list);
        }
        in.close();
        System.out.println("Part 1: " + count);
        System.out.println("Part 2: " + count2);
    }
            
            
    public static int part1(ArrayList<String> list){
        if (Integer.valueOf(list.get(0)) < Integer.valueOf(list.get(1))){
            for (int i = 0; i < list.size()-1; i++){
                if (!(Integer.valueOf(list.get(i)) - Integer.valueOf(list.get(i+1)) <= -1 && Integer.valueOf(list.get(i)) - Integer.valueOf(list.get(i+1)) >= -3)){
                    return 0;
                }
            }
            return 1;
        } else if (Integer.valueOf(list.get(0)) > Integer.valueOf(list.get(1)) ){
            for (int i = 0; i < list.size()-1; i++){
                if (!(Integer.valueOf(list.get(i)) - Integer.valueOf(list.get(i+1)) <= 3 && Integer.valueOf(list.get(i)) - Integer.valueOf(list.get(i+1)) >= 1)){
                    return 0;
                }
            }
            return 1;
        }
        return 0;
    }

    public static int part2(ArrayList<String> list){
        if (part1(list) == 1){
            return 1;
        }
        for (int i = 0; i < list.size()-1; i++){
            ArrayList<String> listHold = list;
            listHold.remove(i);
            if (part1(listHold) == 1){
                return 1;
            }
        }
        return 0;
    }
}
