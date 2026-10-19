/**
 * Reads integers from input.txt line by line, sums each line,
 * and writes the sum (or an error message) to output.txt.
 *
 * @author  MF-ROB
 * @version 1.0
 * @since   2026-10-19
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

    public static void main(String[] args) {
        try {
            System.out.println("Looking in: " + new File("input.txt").getAbsolutePath());
            Scanner input = new Scanner(new File("input.txt"));
            FileWriter output = new FileWriter("output.txt");
            int lineNumber = 0;

            // Loop through every line of the input file
            while (input.hasNextLine()) {
                lineNumber++;
                String line = input.nextLine().trim();

                if (line.isEmpty()) {
                    output.write("Line " + lineNumber + " error: no data on this line\n");
                    continue;
                }

                String[] pieces = line.split("\\s+");
                int sum = 0;
                String badValue = null;

                for (String piece : pieces) {
                    try {
                        // Convert from String to int
                        sum += Integer.parseInt(piece);
                    } catch (NumberFormatException error) {
                        badValue = piece;
                        break;
                    }
                }

                if (badValue == null) {
                    output.write("Line " + lineNumber + " sum: " + sum + "\n");
                } else {
                    output.write("Line " + lineNumber + " error: \"" + badValue
                        + "\" is not a valid integer\n");
                }
            }

            input.close();
            output.close();
        } catch (IOException error) {
            System.out.println("File error: " + error.getMessage());
        }
    }
}