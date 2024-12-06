import java.io.*;
import java.util.*;


public class Day3 {
    private static int locks;
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input3.txt");
        Scanner in = new Scanner(file);
        locks = 0;
        int count = 0;
        int count2 = 0;
        while (in.hasNextLine()){
            ArrayList<String> list = new ArrayList<String>(Arrays.asList(in.nextLine().split("mul")));
            count += part1(list);
            count2 += part2(list);
        }
        in.close();
        System.out.println("Part 1: " + count);
        System.out.println("Part 2: " + count2);
    }
    
    public static int part1(ArrayList<String> list){
        int count = 0;
        for (String i : list){
            if (i.length() != 0 && i.charAt(0) == '(' ){
                int close = i.indexOf(")");
                int comma = i.indexOf(",");
                int space = i.indexOf(" ");
                int n1 = 0;
                int n2 = 0;
                if (close >= 4 && close <= 8 && comma >= 2 && comma <= 4 && !(space <= close && space >= 0)){
                    n1 = Integer.valueOf(i.substring(1, comma));
                    n2 = Integer.valueOf(i.substring(comma + 1, close));
                    count += n1 * n2;
                }
            }
        }
        return count;
    }


    public static int part2(ArrayList<String> list){
        int count = 0;
        for (String i : list){
            if (i.length() != 0 && i.charAt(0) == '(' && locks == 0 && locks == 0){
                int close = i.indexOf(")");
                int comma = i.indexOf(",");
                int space = i.indexOf(" ");
                int n1 = 0;
                int n2 = 0;
                if (close >= 4 && close <= 8 && comma >= 2 && comma <= 4 && !(space <= close && space >= 0)){
                    n1 = Integer.valueOf(i.substring(1, comma));
                    n2 = Integer.valueOf(i.substring(comma + 1, close));
                    count += n1 * n2;
                }
            }
            int doIndex = 0;
            int dontIndex = 0;
            String hold = i;
            while (doIndex != -1 || dontIndex != -1){
                doIndex = hold.indexOf("do()");
                dontIndex = hold.indexOf("don't()");
                if (doIndex > dontIndex){
                    lockUpdate(0);
                    hold = hold.substring(doIndex+4);
                } else if (doIndex < dontIndex){
                    lockUpdate(1);
                    hold = hold.substring(dontIndex+7);
                }
            }
        }
        return count;
    }
    public static int lockStatus(){
        return locks;
    }
    public static void lockUpdate(int update){
        locks = update;
    }
}
