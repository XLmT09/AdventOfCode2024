import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IllegalFormatPrecisionException;
import java.util.Set;
import java.util.List;

public class DayFive {
    public static void main(String[] args) {
        firstPart();
        secondPart();
    }
    
    public static void firstPart() {
        String filePath = "data.txt";
        int solution = 0;

        HashMap<Integer, Set<Integer>> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                
                if (line.trim().isEmpty()) {
                    break;
                }

                String[] parts = line.split("\\|");
                
                int leftVal = Integer.parseInt(parts[0]);
                int rightVal = Integer.parseInt(parts[1]);

                if (map.containsKey(rightVal)) {
                    map.get(rightVal).add(leftVal);
                } else {
                    Set<Integer> newList = new HashSet<>();
                    newList.add(leftVal);
                    map.put(rightVal, newList);
                }
            }

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(",");
                List<Integer> numberList = new ArrayList<>();
                boolean isValid = true;
                
                for (String number : numbers) {
                    numberList.add(Integer.parseInt(number.trim()));
                }

                for (int i = 1; i < numberList.size(); i++) {
                    Integer curr = numberList.get(i);
                    for (int j = i -1; j >= 0; j--) {
                        Integer keyToCheck = numberList.get(j);
                        
                        if (map.get(keyToCheck) == null) {
                            continue;
                        }

                        if (map.get(keyToCheck).contains(curr)) {
                            isValid = false;
                        }
                    }
                }

                if (!isValid) continue;
                 solution += numberList.get(numberList.size() / 2);
            }

            System.out.println("Part 1 - Solution is: " + solution);
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }
    }

    public static void secondPart() {
        String filePath = "data.txt";
        int solution = 0;

        HashMap<Integer, Set<Integer>> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                
                if (line.trim().isEmpty()) {
                    break;
                }

                String[] parts = line.split("\\|");
                
                int leftVal = Integer.parseInt(parts[0]);
                int rightVal = Integer.parseInt(parts[1]);

                if (map.containsKey(rightVal)) {
                    map.get(rightVal).add(leftVal);
                } else {
                    Set<Integer> newList = new HashSet<>();
                    newList.add(leftVal);
                    map.put(rightVal, newList);
                }
            }

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(",");
                List<Integer> numberList = new ArrayList<>();
                boolean isValid = true;
                
                for (String number : numbers) {
                    numberList.add(Integer.parseInt(number.trim()));
                }

                for (int i = 1; i < numberList.size(); i++) {
                    Integer curr = numberList.get(i);
                    for (int j = i -1; j >= 0; j--) {
                        Integer keyToCheck = numberList.get(j);
                        
                        if (map.get(keyToCheck) == null) {
                            continue;
                        }

                        if (map.get(keyToCheck).contains(curr)) {
                            isValid = false;
                        }
                    }
                }

                if (isValid) continue;
                
                List<Integer> newNumberList = new ArrayList<>();
                newNumberList.add(numberList.get(0));

                for (Integer number : numberList) {
                    boolean wasShiftMade = false;
                    for (int j = 0; j < newNumberList.size(); j++) {
                        Integer keyToCheck = newNumberList.get(j);

                        if (map.get(keyToCheck) == null) {
                            continue;
                        }

                        if (number == keyToCheck) {
                            wasShiftMade = true;
                            break;
                        }
                        if (map.get(keyToCheck).contains(number)) {
                            newNumberList.add(j, number);
                            wasShiftMade = true;
                            break;
                        }
                    }

                    if (!wasShiftMade) {
                        newNumberList.add(number);
                    }
                }
                
                solution += newNumberList.get(newNumberList.size() / 2);
            }

            System.out.println("Part 2 - Solution is: " + solution);
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }
    }
}
