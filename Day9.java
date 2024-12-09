import java.util.*;
import java.io.*;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input9.txt");
        Scanner in = new Scanner(file);
        String[] input = in.nextLine().split("");
        in.close(); 
        int diskSize = 0;
        for (String i : input){
            diskSize += Integer.valueOf(i);
        }
        System.out.println("Part 1: " + part1(input, diskSize));
        System.out.println("Part 2: " + part2(input, diskSize));
    }   



    static public long part1(String[] input, int diskSize){
        String[] disk = diskSetup(input, diskSize);

        int openPlace = 0;
        for (int i = diskSize-1; i >= 0; i--){
            if (i-1 > openPlace){
                while (!disk[openPlace].equals(".")){
                    openPlace++;
                }
                if (!disk[i].equals(".")){
                    disk[openPlace++] = disk[i];
                    disk[i] = ".";
                }
            }
        }

        long blockSum = diskSum(disk, diskSize);
        return blockSum;
    }

    static public long part2(String[] input, int diskSize){
        String[] disk = diskSetup(input, diskSize);
        int openPlace = 0;
        int fromBack = 0;
        int fromFront = 0;
        Boolean hasSpace = true;
        for (int i = input.length-1; i >= 0; i--){
            fromBack = 1;
            hasSpace = true;
            for (int k = input.length-1; k > i; k--){
                fromBack += Integer.valueOf(input[k]);
            }
            fromFront = diskSize - fromBack;
            openPlace = 0;
            while (openPlace < fromFront){
                hasSpace = true;
                if (disk[openPlace].equals(".")){
                    for (int j = 0; j < Integer.valueOf(input[i]); j++){
                        if(!disk[openPlace+j].equals(".")){
                            hasSpace = false;
                        }
                    }
                    if (hasSpace){
                        for (int j = 0; j < Integer.valueOf(input[i]); j++){
                            disk[openPlace++] = disk[fromFront];
                            disk[fromFront] = ".";
                            fromFront--;
                        }
                        openPlace += diskSize;
                    }  else {
                        openPlace++;
                    }
    
                } else {
                    openPlace++;
                }
                
            }
            
        }
        long blockSum = diskSum(disk, diskSize);
        return blockSum;
    }

    static String[] diskSetup(String[] input, int diskSize){
        String[] disk = new String[diskSize];
        int blockNum = 0;
        int diskPlace = 0;
        int curBlock = 0;
        for (int i = 0; i < input.length; i++){
            curBlock = Integer.valueOf(input[i]);
            if (i % 2 == 0){
                for (int j = 0; j < curBlock; j++){
                    disk[diskPlace++] = String.valueOf(blockNum);
                }
                blockNum++;
            } else {
                for (int j = 0; j < curBlock; j++){
                    disk[diskPlace++] = ".";
                }
            }
        }
        return disk;
    }

    static long diskSum(String[] disk, int diskSize){
        long blockSum = 0;
        for (int i = 0; i < diskSize; i++){
            if (!disk[i].equals(".")){
                blockSum += (Integer.valueOf(disk[i]) * i);
            }
        }
        return blockSum;
    }
}
