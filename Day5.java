import java.io.*;
import java.util.*;;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input5.txt");
        Scanner in = new Scanner(file);
        ArrayList<Integer> num1 = new ArrayList<Integer>(); 
        ArrayList<ArrayList<Integer>> num2 = new ArrayList<ArrayList<Integer>>();
        ArrayList<String> book = new ArrayList<String>();
        int blankCheck = 0;
        String curLine = "";
        while (in.hasNextLine()){
            curLine = in.nextLine();
            int side1 = Integer.valueOf(curLine.substring(0, 2));
            int side2 = Integer.valueOf(curLine.substring(3));
            if (blankCheck == 0){
                if (!curLine.equals("")){
                    if (num1.contains(side1)){
                        for (int i = 0; i < num1.size(); i++){
                            if (num1.get(i) == side1){
                                num2.get(i).add(side2);
                            }
                        }
                    } else {
                        num1.add(side1);
                        num2.add(new ArrayList<Integer>());
                        num2.get(num1.size()-1).add(side2);
                    }
                } else {
                    blankCheck =1;
                }
            } else {
                book.add(curLine);
            }
        }
        in.close();
        int count = 0;
        int count2 = 0;
        for (String i : book){
            count += part1(i, num1, num2);
            count2 += part2(i, num1, num2);
        }
        System.out.println("Part 1: " + count);
        System.out.println("Part 2: " + count2);
    }




    static int part1(String book, ArrayList<Integer> num1, ArrayList<ArrayList<Integer>> num2){
        String[] page = book.split(",");
        for (int i = page.length -1; i > 0; i--){
            int curPage = Integer.valueOf(page[i]);
            for (int p = 0; p < num1.size(); p++){
                if (num1.get(p) == curPage){
                    for (int j = i -1; j >= 0; j--){
                        if (num2.get(p).contains(Integer.valueOf(page[j]))){
                            return 0;
                        }
                    }
                }
            }
        }
        return Integer.valueOf(page[page.length/2]);
    }

    static int part2(String book, ArrayList<Integer> num1, ArrayList<ArrayList<Integer>> num2){
        int check = 0;
        String[] page = book.split(",");
        for (int i = page.length -1; i > 0; i--){
            int curPage = Integer.valueOf(page[i]);
            int curLoc = i;
            for (int p = 0; p < num1.size(); p++){
                if (num1.get(p) == curPage){
                    for (int j = i - 1; j >= 0; j--){
                        if (num2.get(p).contains(Integer.valueOf(page[j]))){
                            String hold = page[curLoc];
                            page[curLoc] = page[j];
                            page[j] = hold;
                            curLoc = j;
                            check++;
                        }
                    }
                }
            }
            if (curLoc != i){
                i++;
            }
        }
        if (check != 0){
            return Integer.valueOf(page[page.length/2]);
        }
        return 0;
    }
}
