import java.util.Scanner;

import Commands.*;

public class Main {
    static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {
        int topId = 0;
        System.out.println("nice");
        while (true) {
            System.out.println("Enter Command :");
            String inputString = scnr.nextLine();
            if (inputString.equals("add cycle")) {
                AddCycle.execute(topId);
            }
        }
    }
}
