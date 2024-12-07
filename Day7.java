import java.io.*;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input7.txt");
        Scanner in = new Scanner(file);
        ArrayList<long[]> list = new ArrayList<long[]>();
        while (in.hasNextLine()){
            String[] hold2 = in.nextLine().split( " ");
            long[] hold = new long[hold2.length];
            hold[0] = Long.valueOf(hold2[0].substring(0, hold2[0].length()-1));
            for (int i = 1; i < hold.length; i++){
                hold[i] = Integer.valueOf(hold2[i]);
            }
            list.add(hold);
        }
        in.close();
        System.out.println("Part 1: " + part2(list));
        //System.out.println("Part 2: " + count2);
    }


    static long part1(ArrayList<long[]> list){
        long goal;
        String binary;
        long total = 0;
        for (long[] line : list){
            goal = line[0];
            long max = (long)Math.pow(2, line.length-2) ;
            for (int i = 0; i < max; i++){
                long sum = line[1];
                binary = Integer.toBinaryString(i+4092);
                for (int j = 1; j < line.length-1; j++){
                    if (binary.substring(binary.length()-j, binary.length()-j+1).equals("0")){
                        sum += line[j+1];
                    } else if (binary.substring(binary.length()-j, binary.length()-j+1).equals("1")){
                        sum *= line[j+1];
                    }
                }
                if (sum == goal){
                    total += goal;
                    i += max;
                    System.out.println(goal);
                }
            }
        }

        return total;
    }
    static long part2(ArrayList<long[]> list){
        long goal;
        long total = 0;
        for (long[] line : list){
            goal = line[0];
            long max = (long)Math.pow(3, line.length-2) ;
            for (int i = 0; i < max; i++){
                long sum = line[1];
                int current = i;
                for (int j = 1; j < line.length-1; j++){
                    if (current % 3 == 0){
                        sum += line[j+1];
                    } else if (current % 3 == 1){
                        sum *= line[j+1];
                    } else if (current % 3 == 2){
                        sum *= (long)Math.pow(10, String.valueOf(line[j+1]).length());
                        sum += line[j+1];
                    } 
                    current /= 3;
                }
                if (sum == goal){
                    total += goal;
                    i += max;
                    System.out.println(goal);
                }
            }
        }

        return total;
    }
}
