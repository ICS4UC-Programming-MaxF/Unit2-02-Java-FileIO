import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reads integers from input.txt line by line, sums each line,
 * and writes the result (or an error message) to output.txt.
 * If input.txt does not exist, it is created with sample data.
 */
public final class FileIO {

    private FileIO() {
    }

    public static void main(final String[] args) {
        try {
            File inputFile = new File("input.txt");

            if (!inputFile.exists()) {
                FileWriter starter = new FileWriter(inputFile);
                starter.write("1 2 3\n4 five 6\n-3 7 2\n\n0\n");
                starter.close();
            }

            Scanner input = new Scanner(inputFile);
            FileWriter output = new FileWriter("output.txt");

            while (input.hasNextLine()) {
                String line = input.nextLine().trim();

                if (line.isEmpty()) {
                    output.write("Error: no data on this line\n");
                    continue;
                }

                String[] pieces = line.split(" ");
                int sum = 0;
                String badValue = null;

                for (String piece : pieces) {
                    try {
                        sum += Integer.parseInt(piece);
                    } catch (NumberFormatException error) {
                        badValue = piece;
                        break;
                    }
                }

                if (badValue == null) {
                    output.write(sum + "\n");
                } else {
                    output.write("Error: \"" + badValue
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