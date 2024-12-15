import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DayFour {
    public static void main(String[] args) {
        firstPart();
        secondPart();    
    }

    static void firstPart() {
        String filePath = "data.txt";
        int solution = 0;
        List<int[]> directions = new ArrayList<>();
        List<List<Character>> dataSet = new ArrayList<>();
        directions.add(new int[]{1,0});
        directions.add(new int[]{-1,0});
        directions.add(new int[]{0,1});
        directions.add(new int[]{0,-1});
        directions.add(new int[]{1,1});
        directions.add(new int[]{-1,-1});
        directions.add(new int[]{1,-1});
        directions.add(new int[]{-1,1});

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Regular expression to match the format mul(X,Y)
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    row.add(c); // Add each character to the row
                }
                dataSet.add(row); // Add the row to the 2D list
            }

            int row_length = dataSet.size();
            int col_length = dataSet.get(0).size();

            for (int row = 0; row < row_length; row++) {
                for (int col = 0; col < col_length; col++) {
                    for (int[] direction : directions) {
                        if (checkValidRoute(row, col, direction[0], direction[1], row_length, col_length, dataSet)) {
                            solution += 1;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }

        System.out.println("Part 1 - The total XMAS strings is: " + solution);
    }

    static void secondPart() {
        String filePath = "data.txt";
        int solution = 0;
        List<List<Character>> dataSet = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Regular expression to match the format mul(X,Y)
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    row.add(c); // Add each character to the row
                }
                dataSet.add(row); // Add the row to the 2D list
            }

            int row_length = dataSet.size();
            int col_length = dataSet.get(0).size();

            for (int row = 0; row < row_length; row++) {
                for (int col = 0; col < col_length; col++) {
                    if (checkValidRouteTwo(row, col, row_length, col_length, dataSet)) {
                        solution += 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }

        System.out.println("Part 2 - The total X-MAS strings is: " + solution);
    }

    static boolean checkValidRoute(int row, int col, int dr, int dc, int row_length, int col_length, List<List<Character>> dataSet) {
        String target = "XMAS";

        for (int i = 0; i < target.length(); i++) {
            int r = row + i * dr;
            int c = col + i * dc;

            if (!(0 <= r && r < row_length && 0 <= c && c < col_length) || dataSet.get(r).get(c) != target.charAt(i)) {
                return false;
            }
        } 

        return true;
    }

    static boolean checkValidRouteTwo(int row, int col, int row_length, int col_length, List<List<Character>> dataSet) {
        List<int[]> directions = new ArrayList<>();
        directions.add(new int[]{1,1});
        directions.add(new int[]{1,-1});
        
        if (dataSet.get(row).get(col) != 'A') {
            return false;
        }

        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];
            int invert_r = row - direction[0];
            int invert_c = col - direction[1];

            if (!(0 <= r && r < row_length && 0 <= c && c < col_length &&
                 0 <= invert_r && invert_r < row_length && 0 <= invert_c && invert_c < col_length)) {
                    return false;
            }
                        
            if (!((dataSet.get(r).get(c) == 'M' && dataSet.get(invert_r).get(invert_c) == 'S') || (dataSet.get(r).get(c) == 'S' && dataSet.get(invert_r).get(invert_c) == 'M'))) {
                    return false;
            }

        }

        return true;
    }
}