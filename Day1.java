import java.io.*;
import java.util.*;

public class Day1{
    public static void main(String[] args) throws FileNotFoundException{
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        File file = new File("input1.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextInt()){
            list1.add(in.nextInt());
            list2.add(in.nextInt());
        }
        in.close();
        int size = list1.size();
        for (int i = 0; i < size; i++ ){
            int lowest1 = 99999999;
            int low1loc = i;
            int lowest2 = 99999999;
            int low2loc = i;
            int start = i; 
            for (int j = start; j < size; j++){
                if (list1.get(j) < lowest1){
                    lowest1 = list1.get(j);
                    low1loc = j;
                }
                if (list2.get(j) < lowest2){
                    lowest2 = list2.get(j);
                    low2loc = j;
                }
            }
            list1.set(low1loc, list1.get(i));
            list1.set(i, lowest1);
            list2.set(low2loc, list2.get(i));
            list2.set(i, lowest2);
        }
        int total = 0;
        int total2 = 0;
        for (int i = 0; i < size; i++){
            total += Math.abs(list1.get(i) - list2.get(i));
        }
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (list1.get(i).equals(list2.get(j))){
                    total2 += list1.get(i);
                }
            }
        }
        System.out.println("Part 1: " + total);
        System.out.println("Part 2: " + total2);
    }
}