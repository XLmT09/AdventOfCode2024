import java.io.*;
import java.util.*;

public class DayOne {
    public static void main(String[] args) {
        firstPart();    
        secondPart();
    }

    static void firstPart() {
        String filePath = "data.txt";
        int solution = 0;

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                
                leftList.add(Integer.parseInt(parts[0]));
                rightList.add(Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        for (int i = 0; i < leftList.size(); i++) {
            int leftVal = leftList.get(i);
            int rightVal = rightList.get(i);

            if (leftVal >= rightVal)
                solution += leftVal - rightVal;
            else
                solution += rightVal - leftVal;
        }

        System.out.println("The total distance is " + solution);
    }

    static void secondPart() {
        String filePath = "data.txt";
        int solution = 0;

        List<Integer> leftList = new ArrayList<>();
        Map<Integer, Integer> rightMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                
                leftList.add(Integer.parseInt(parts[0]));

                int rightNumber = Integer.parseInt(parts[1]);
                rightMap.put(rightNumber, rightMap.getOrDefault(rightNumber, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }

        Collections.sort(leftList);

        for (int i = 0; i < leftList.size(); i++) {
            solution += leftList.get(i) * rightMap.getOrDefault(leftList.get(i), 0);
        }

        System.out.println("The total score is " + solution);
    }
}