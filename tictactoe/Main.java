package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        //Enter data
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter cells: ");
        // Reading data using readLine
        String input = reader.readLine().replace("\"", "");

        System.out.println(input);

        if (input.length() != 9) {
            System.out.println("Error! Wrong input length.");
            System.exit(0);
        } else {

            char[] inCh = new char[9];

            try {
                input.getChars(0, 9, inCh, 0);
                int checker = 0;
                for (char ch : inCh) {
                    if (ch == 'X' || ch == 'O' || ch == ' ') {
                        checker++;
                    } else {
                        System.out.println("Error! Wrong characters.");
                        System.exit(1);
                    }
                    if (checker == 9) System.out.println("Valid input!");
                }
                System.out.println("---------\n" +
                        "| " + inCh[0] + " " + inCh[1] + " " + inCh[2] + " " + "|\n" +
                        "| " + inCh[3] + " " + inCh[4] + " " + inCh[5] + " " + "|\n" +
                        "| " + inCh[6] + " " + inCh[7] + " " + inCh[8] + " " + "|\n" +
                        "---------");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
