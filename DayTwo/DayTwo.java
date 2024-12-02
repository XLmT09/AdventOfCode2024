import java.io.*;
import java.util.*;

public class DayTwo {
    public static void main(String[] args) {
        firstPart();
        secondPart();
    }

    static void firstPart() {
        String filePath = "data.txt";
        int solution = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");

                boolean isSafeRecord = true;
                
                if (!checkAllIncrOrDec(parts)) {
                    continue;
                }

                for (int i = 0; i < parts.length - 1; i++) {
                    int currVal = Integer.parseInt(parts[i]);
                    int nextVal = Integer.parseInt(parts[i+1]);

                    if (!(Math.abs(nextVal - currVal) >= 1 &&  Math.abs(nextVal - currVal) <= 3)) {
                        isSafeRecord = false;
                    }
                }

                if (isSafeRecord) {
                    solution += 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }

        System.out.println("Number of safe reports is: " + solution);
    }

    static void secondPart() {
        String filePath = "data.txt";
        int solution = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                boolean isSafe = false;

                for (int i = 0; i < parts.length; i++) {
                    String[] result = new String[parts.length - 1];
                    int index = 0;

                    for (int j = 0; j < parts.length; j++) {
                        if (j != i) {
                            result[index++] = parts[j];
                        }
                    }

                    if (isRecordSafe(result)) {
                        isSafe = true;
                        break;
                    }
                }

                if (isSafe) {
                    solution += 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }

        System.out.println("Part 2 - Number of safe reports is: " + solution);
    }




    static boolean checkAllIncrOrDec(String[] parts) {
        boolean isRecordDecr = true;
        boolean isRecordIncr = true;

        // check for incr
        for (int i = 0; i < parts.length - 1; i++) {
            int currVal = Integer.parseInt(parts[i]);
            int nextVal = Integer.parseInt(parts[i+1]);

            if (currVal - nextVal >= 0) {
                isRecordIncr = false;
            }
        }

        if (isRecordIncr) {
            return true;
        }

        // check for decr
        for (int i = 0; i < parts.length - 1; i++) {
            int currVal = Integer.parseInt(parts[i]);
            int nextVal = Integer.parseInt(parts[i+1]);

            if (currVal - nextVal <= 0) {
                isRecordDecr = false;
            }
        }

        return isRecordDecr;
    }

    static boolean isRecordSafe(String[] record) {
        boolean isSafeRecord = true;
        
        if (!checkAllIncrOrDec(record)) {
            return false;
        }

        for (int i = 0; i < record.length - 1; i++) {
            int currVal = Integer.parseInt(record[i]);
            int nextVal = Integer.parseInt(record[i+1]);

            if (!(Math.abs(nextVal - currVal) >= 1 &&  Math.abs(nextVal - currVal) <= 3)) {
                isSafeRecord = false;
            }
        }

        return isSafeRecord;
    }
}