import java.util.*;
import java.io.*;

public class Days11 {

    private static ArrayList<long[]> numberTables = new ArrayList<long[]>();
    private static long[] zeroTable = {1, 1, 1, 2, 4, 4, 7, 14, 16, 20, 39, 62, 81, 110, 200, 328, 418, 667, 1059, 1546, 2377, 3572, 5602, 8268, 12343, 19778, 29165, 43726, 67724, 102131, 156451, 234511, 357632, 549949, 819967, 1258125, 1916299, 2886408, 4414216, 6669768, 10174278, 15458147, 23333796, 35712308, 54046805, 81997335, 125001266, 189148778, 288114305, 437102505}; 
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input11.txt");
        Scanner in = new Scanner(file);
        String[] input = in.nextLine().split(" ");
        in.close(); 
        long sum1 = 0;
        long sum2 = 0;
        long[] holdArray;
        for (int i = 1; i < 10; i++){
            holdArray = new long[40];
            for (int j = 0; j < 40; j++){
                holdArray[j] = blink(i, 0, j);
            }
            numberTables.add(holdArray); 
        }
        for (String i : input){
            sum1 += blink(Long.valueOf(i), 0, 25); // part 1
        }
        for (String i : input){
            sum2 += blink(Long.valueOf(i), 0, 75); // part 2
        }
        //System.out.println("0 chain: " + blink(0, 0, 8));
        System.out.println("Part 1: "+ sum1);
        System.out.println("Part 2: "+ sum2);
        
    }

    static long blink(long stoneNum, int blinkNum, int maxBlink){
        long stones = 0;
        if (blinkNum == maxBlink){
            return 1;
        } else if (stoneNum == 0){
            if (maxBlink-blinkNum < 50){
                return zeroTable[maxBlink-blinkNum];
            }
            if (blinkNum +4 <= maxBlink){
                stones += blink(2, blinkNum + 4, maxBlink);
                stones += blink(0, blinkNum + 4, maxBlink);
                stones += blink(2, blinkNum + 4, maxBlink);
                stones += blink(4, blinkNum + 4, maxBlink);
            } else if (blinkNum + 3 == maxBlink){
                return 2;
            } else {
                return 1;
            }
        } else if (String.valueOf(stoneNum).length() % 2 == 0){
            stones += blink((stoneNum/(long)(Math.pow(10, String.valueOf(stoneNum).length()/2))), blinkNum + 1, maxBlink);
            stones += blink((stoneNum%(long)(Math.pow(10, String.valueOf(stoneNum).length()/2))), blinkNum + 1, maxBlink);
        } else {
            if (maxBlink-blinkNum < 40 && stoneNum >= 1 && stoneNum <= 9 && numberTables.size() == 9){
                return numberTables.get((int)stoneNum -1)[maxBlink-blinkNum];
            }
            stones += blink(stoneNum * 2024, blinkNum+1, maxBlink);
        }
        return stones;
    }
}
