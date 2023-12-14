package App.Commands.OnStart;

import java.util.Scanner;

public class InitialUserLocation {

    static Scanner scnr = new Scanner(System.in);

    public static int[] execute() {

        int xy[] = { 0, 0 };

        while (true) {
            System.out.print("Enter User x-value: ");

            String xinput = scnr.nextLine();

            if (xinput.equals("")) {

                break;

            } else {
                try {

                    xy[0] = Integer.parseInt(xinput);
                    if (xy[0] < 0) {
                        System.out.println("X-value cannot be negative. Enter valid x-vallue.");
                        continue;
                    }
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid x-value entered, try again.");
                    continue;
                }
            }
        }

        while (true) {
            System.out.print("Enter user y-value: ");

            String yinput = scnr.nextLine();

            if (yinput.equals("")) {

                break;

            } else {
                try {
                    xy[1] = Integer.parseInt(yinput);
                    if (xy[1] < 0) {
                        System.out.println("Y-value cannot be negative. Enter valid y-value.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid y-value entered, try again.");
                    continue;
                }
            }
        }

        System.out.println("\nUser details successfully registered.\n");

        return xy;
    }
}
