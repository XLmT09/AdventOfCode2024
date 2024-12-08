import java.io.*;
import java.util.regex.*;

public class DayThree {
    public static void main(String[] args) {
        firstPart();    
        secondPart();
    }

    static void firstPart() {
        String filePath = "data.txt";
        int solution = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Regular expression to match the format mul(X,Y)
            Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                // Find all occurrences of mul(X,Y) in the line
                while (matcher.find()) {
                    // Extract X and Y as strings
                    String xStr = matcher.group(1);
                    String yStr = matcher.group(2);

                    //System.out.println(xStr);
                    //System.out.println(yStr);

                    // Convert X and Y to integers
                    int x = Integer.parseInt(xStr);
                    int y = Integer.parseInt(yStr);

                    // Calculate and print the product
                    int product = x * y;
                    
                    solution += product;
                }  
            }
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }

        System.out.println("Part 1 - The total mul instructions: " + solution);
    }

    static void secondPart() {
        String filePath = "data.txt";
        int solution = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            boolean doFlag = true;

            while ((line = br.readLine()) != null) {
                int lineLength = line.length();
                
                
                for (int i = 0; i < lineLength;) {
                    // check for do
                    if (i + 3 < lineLength && line.substring(i, i + 4).equals("do()")) {
                        doFlag = true;
                        i += 3;
                        continue;
                    } 
                    if (i + 6 < lineLength && line.substring(i, i + 7).equals("don't()")) {
                        doFlag = false;
                        i += 6;
                        continue;
                    }
                    
                    if (!doFlag) {
                        i++;
                        continue;
                    }

                    if (i + 3 < lineLength && line.startsWith("mul(", i)) {
                        int start = i + 4;
                        // return the index of the char ")" from start
                        int end = line.indexOf(")", start);
                        if (end != -1) {
                            String content = line.substring(start, end);
                            String[] parts = content.split(",");
                            String xStr, yStr = null;

                            if (parts.length == 2) {
                                xStr = parts[0];
                                yStr = parts[1];
                                System.out.println(xStr + " " + yStr);

                                if (isInteger(xStr) && isInteger(yStr) && xStr.length() < 4 && yStr.length() < 4) {
                                    System.out.println(xStr + " " + yStr);
                                    int x = Integer.parseInt(xStr);
                                    int y = Integer.parseInt(yStr);
                                    int product = x * y;
                                    solution += product;
                                }
                            }
                            i++;
                        }
                    } else {
                        i++;
                    }
                }
            }  
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }

        System.out.println("Part 2 - The total mul instructions: " + solution);
    }

    // Helper method to check if a string is a valid integer
    static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // Successfully converted to an integer
        } catch (NumberFormatException e) {
            return false; // Not a valid integer
        }
    }
}