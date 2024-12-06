import java.io.*;
import java.util.*;


public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input6.txt");
        Scanner in = new Scanner(file);
        ArrayList<String[]> startList = new ArrayList<String[]>();
        while (in.hasNextLine()){
            startList.add(in.nextLine().split("")); // makes a psudo 2d array out of the input using array lists and normal lists to get height and width easily
        }
        in.close();
        int height = startList.size();
        int width = startList.get(0).length;
        int[][] grid = new int[height][width];
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < height; i++){ // sets up an integer array to make working with the array easier and tracks the X and Y value of the starting position
            for (int j = 0; j < width; j++){
                if (startList.get(i)[j].equals(".")){
                    grid[i][j] = 0;
                } else if (startList.get(i)[j].equals("#")){
                    grid[i][j] = 2;
                } else if (startList.get(i)[j].equals("^")){
                    grid[i][j] = 1;
                    startX = j;
                    startY = i;
                }
            }
        }
        System.out.println("Part 1: " + part1(grid, startX, startY));
        System.out.println("Part 2: " + part2(grid, startX, startY));
        System.out.println("Max: " + (height * width));
    }

    static int part1(int[][] startGrid, int startX, int startY){
        int[][] grid = startGrid;
        int x = startX;
        int y = startY;
        int height = grid.length;
        int width = grid[0].length;
        boolean left = false;
        int direction = 0;
        int count = 1;
        while (!left){
            boolean wall = false;
            while (!wall){
                if (direction == 0){ // up
                    if (y == 0){
                        wall = true;
                        left = true;
                    } else {
                        if (grid[y-1][x] == 2){
                            wall = true;
                            direction++;
                        } else if (grid[y-1][x] == 0){
                            y--;
                            grid[y][x] = 1;
                            count++;
                        } else if (grid[y-1][x] == 1){
                            y--;
                        }
                    }
                } else if (direction == 1){ // right
                    if (x == width-1){
                        wall = true;
                        left = true;
                    } else {
                        if (grid[y][x+1] == 2){
                            wall = true;
                            direction++;
                        } else if (grid[y][x+1] == 1){
                            x++;
                        } else if (grid[y][x+1] == 0){
                            x++;
                            grid[y][x] = 1;
                            count++;
                        }
                    }
                } else if (direction == 2){ // down
                    if (y == height -1){
                        wall = true;
                        left = true;
                    } else {
                        if (grid[y+1][x] == 2){
                            wall = true;
                            direction++;
                        } else if (grid[y+1][x] == 0){
                            y++;
                            grid[y][x] = 1;
                            count++;
                        } else if (grid[y+1][x] == 1){
                            y++;
                        }
                    } 
                } else if (direction == 3){ // left
                    if (x == 0){
                        wall = true;
                        left = true;
                    } else {
                        if (grid[y][x-1] == 2){
                            wall = true;
                            direction = 0;;
                        } else if (grid[y][x-1] == 1){
                            x--;
                        } else if (grid[y][x-1] == 0){
                            x--;
                            grid[y][x] = 1;
                            count++;
                        }
                    } 
                }
            }
        }
        return count;
    }


    static int part2(int[][] grid, int startX, int startY){
        int x = startX;
        int y = startY;
        int height = grid.length;
        int width = grid[0].length;
        boolean left = false;
        int direction = 0;
        int count = 0;
        int hold = 0;
        ArrayList<int[]> turns = new ArrayList<int[]>();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                turns.clear();
                
                x = startX;
                y = startY;
                left = false;
                direction = 0;
                if (i != y || j != x){
                    hold = grid[i][j];
                    grid[i][j] = 2;
                }
                //System.out.println(j + ", " + i);
                while (!left){
                    boolean wall = false;
                    while (!wall){
                        if (direction == 0){ // up
                            if (y == 0){
                                wall = true;
                                left = true;
                            } else if (grid[y-1][x] == 2){
                                wall = true;
                                direction++;
                                int[] turn = {x, y, direction};
                                int prevCount = count;
                                for (int m = 0; m < turns.size(); m++){
                                    int[] q = turns.get(m);
                                    if (q[0] == x && q[1] == y && q[2] == direction){
                                        left = true;
                                        count++;
                                    }
                                }
                                if (prevCount == count){
                                    turns.add(turn);
                                }
                                
                                //System.out.println(turns.get(turns.size()-1)[0]);
                            } else if (grid[y-1][x] == 0){
                                y--;
                            } else if (grid[y-1][x] == 1){
                                y--;
                            }
                        } else if (direction == 1){ // right
                            if (x == width-1){
                                wall = true;
                                left = true;
                            } else if (grid[y][x+1] == 2){
                                wall = true;
                                direction++;
                                int[] turn = {x, y, direction};
                                int prevCount = count;
                                for (int m = 0; m < turns.size(); m++){
                                    int[] q = turns.get(m);
                                    if (q[0] == x && q[1] == y && q[2] == direction){
                                        left = true;
                                        count++;
                                    }
                                }
                                if (prevCount == count){
                                    turns.add(turn);
                                }
                                //System.out.println(turns.get(turns.size()-1)[0]);
                            } else if (grid[y][x+1] == 1){
                                x++;
                            } else if (grid[y][x+1] == 0){
                                x++;
                            }
                        } else if (direction == 2){ // down
                            if (y == height -1){
                                wall = true;
                                left = true;
                            } else if (grid[y+1][x] == 2){
                                wall = true;
                                direction++;
                                int[] turn = {x, y, direction};
                                int prevCount = count;
                                for (int m = 0; m < turns.size(); m++){
                                    int[] q = turns.get(m);
                                    if (q[0] == x && q[1] == y && q[2] == direction){
                                        left = true;
                                        count++;
                                    }
                                }
                                if (prevCount == count){
                                    turns.add(turn);
                                }
                                //System.out.println(turns.get(turns.size()-1)[0]);
                            } else if (grid[y+1][x] == 0){
                                y++;
                            } else if (grid[y+1][x] == 1){
                                y++;
                            }
                        } else if (direction == 3){ // left
                            if (x == 0){
                                wall = true;
                                left = true;
                            } else if (grid[y][x-1] == 2){
                                wall = true;
                                direction = 0;
                                int[] turn = {x, y, direction};
                                int prevCount = count;
                                for (int m = 0; m < turns.size(); m++){
                                    int[] q = turns.get(m);
                                    if (q[0] == x && q[1] == y && q[2] == direction){
                                        left = true;
                                        count++;
                                    }
                                }
                                if (prevCount == count){
                                    turns.add(turn);
                                }
                            } else if (grid[y][x-1] == 1){
                                x--;
                            } else if (grid[y][x-1] == 0){
                                x--;
                            }
                        }
                    }
                }
                grid[i][j] = hold;
            }
        }
        return count;
    }
}
