package App.Commands.OnStart;

import java.util.Scanner;

public class SetRentPerHour {

    static Scanner scnr = new Scanner(System.in);

    public static float execute() {
        float rentPerHour = 0.5f;

        while (true) {

            System.out.print("\nEnter rent per hour rate: ");
            String userInput = scnr.nextLine();

            if (userInput.equals("")) {
                break;
            } else {
                try {
                    rentPerHour = Float.parseFloat(userInput);
                    if (rentPerHour < 0) {
                        System.out.println("Invalid rent Per hour rate, please enter a valid rate");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rent Per hour rate, please enter a valid rate");
                    continue;
                }
            }
        }

        System.out.println("\nRent per hour rate set successfully to " + rentPerHour);

        return rentPerHour;
    }
}
