package Commands;

import java.util.Scanner;

public class Help {
    static Scanner scnr = new Scanner(System.in);

    public static void execute() {
        System.out.print("Select an option by entering the number or module name [enter anything else to exit] :\n" +
                "   Command Modules:\n" +
                "       1. add+delete commands\n" +
                "       2. update commands\n" +
                "       3. get commands\n" +
                "       4. rent commands\n" +
                "       5. help command\n\n");

        System.out.print("Enter selection :");
        String userInput = scnr.nextLine();
        switch (userInput) {
            case "1":
            case "add":
            case "delete":
            case "add+delete":
                adddelete();
                break;
            case "2":
            case "update":
                update();
                break;
            case "3":
            case "get":
                get();
                break;
            case "4":
            case "rent":
                rent();
                break;
            case "5":
            case "help":
                System.out.println(
                        "help() - this command, returns a list of modules and allows you to see details of available commands");
                break;
            default:
                System.out.println("exited");
                break;
        }
    }

    static void adddelete() {
        System.out.print("Enter the number or command name to see more details [enter anything else to exit]\n\n" +
                "Commands in add+delete module: (short hand)\n" +
                "   1. add cycle (a c)\n" +
                "   2. delete cycle id (d c i)\n" +
                "   3. delete cycle location (d c l)\n" +
                "   4. delete cycle area (d c a)\n\n");
        System.out.print("Enter selection: ");
        String userInput = scnr.nextLine();
        switch (userInput) {
            case "1":
            case "add cycle":
            case "a c":
                System.out.println(
                        "add cycle (x-value: +ve int (0), y-value: +ve int(0), hoursRented: +ve double (0), isRented: boolean (false))\r\n"
                                + //
                                "\r\n" + //
                                "adds record with given values.");
                break;
            case "2":
            case "delete cycle id":
            case "d c":
                System.out.println("delete cycle id (cycleId1: +ve int(required), cyceId2:+ve int (cycleId1))\r\n" + //
                        "\r\n" + //
                        "deletes cycles in inclusive range(Id1, Id2), confirms number of successfully deleted records.");
                break;
            case "3":
            case "delete cycle location":
            case "d c l":
                System.err.println(
                        "delete cycle location (x-value: +ve int(0), y-value: +ve (0), arSide: +ve non-zero int(1), arSide2: +ve non-zero int(arSide))\r\n"
                                + //
                                "\r\n" + //
                                "delete records in rectangle of length = arSide, breadth = arSide2 & bottom-left-corner= x,y.");
                break;
            case "4":
            case "delete cycle area":
            case "d c a":
                System.out.println(
                        "delete cycle area (/ same as 1st above/ , /same as 2nd above/ , x range: +ve int(0), y range: +ve int (x))\r\n"
                                + //
                                "\r\n" + //
                                "delete records in a rectangle of sides (x range2)-1,(y range2 -1) range centered at x,y");
                break;
            default:
                System.out.println("exited");
                break;
        }
    }

    static void update() {
        System.out.print("Enter the number or command name to see more details [enter anything else to exit]\n\n" +
                "Commands in update module: (short hand)\n" +
                "   1. update cycle (u c)\n" +
                "   2. update cycle location (u c l)\n" +
                "   3. update cycle rented (u c r)\n" +
                "   4. update user location (u u l)\n\n");
        System.out.print("Enter selection: ");
        String userInput = scnr.nextLine();
        switch (userInput) {
            case "1":
            case "update cycle":
            case "u c":
                System.out.println(
                        "\nupdate cycle (cycleId: +ve int (required), range end cycleId : +ve int >= cycleId (cycleId), x-value: +ve int (unchanged), y-value: +ve int(unchanged), mark as not rented? : boolean (false))\r\n"
                                + //
                                "\r\n" + //
                                "updates locations of all rented cycles in inclusive range and can set rented status of all to false\n");
                break;
            case "2":
            case "update cycle location":
            case "u c l":
                System.out.println(
                        "\nupdate cycle location (cycleId: +ve int (required), x-value: +ve int (unchanged), y-value: +ve int(unchanged))\r\n"
                                + //
                                "\r\n" + //
                                "updates cycle location by Id.\n");
                break;
            case "3":
            case "update cycle rented":
            case "u c r":
                System.out.println(
                        "\nupdate cycle rented (cycleId: +ve int (required), range end cycleId : +ve int >= cycleId (cycleId))\r\n"
                                + //
                                "\r\n" + //
                                "updates the rented status of all rented cycles in inclusive range to false\n");
                break;
            case "4":
            case "update user location":
            case "u u l":
                System.out.println("\nupdate user location (x-value: +ve int (0), y-value: +ve int(0))\r\n" + //
                        "\r\n" + //
                        "updates user location.\n");
                break;
            default:
                System.out.println("exited");
                break;
        }
    }

    static void get() {
        System.out.print("\nEnter the number or command name to see more details [enter anything else to exit]\n\n" +
                "Commands in get module: (short hand)\n" +
                "   1. get user (g u)\n" +
                "   2. get user location (g u l)\n" +
                "   3. get user hoursRented (g u h)\n" +
                "   4. get user bill (g u b)\n" +
                "   5. get cycle (g c)\n" +
                "   6. get cycle rented (g c r)\n" +
                "   7. get cycle id (g c i)\n" +
                "   8. get cycle hoursRented (g c h)\n" +
                "   9. get cycle proximity (g c p)\n\n");
        System.out.print("Enter selection: ");
        String userInput = scnr.nextLine();
        switch (userInput) {
            case "1":
            case "get user":
            case "g u":
                System.out.println("\nget user (ratePerHour in euro : +ve non-zero float (0.5))\r\n" + //
                        "\r\n" + //
                        "returns user location, rented cycles, total rented hours & total spending in euro.\n");
                break;
            case "2":
            case "get user location":
            case "g u l":
                System.out.println("\nget user location ()\r\n" + //
                        "\r\n" + //
                        "returns user location\n");
                break;
            case "3":
            case "get user hoursRented":
            case "g u h":
                System.out.println("\nget user hoursRented ()\r\n" + //
                        "\r\n" + //
                        "returns users total rented hours\n");
                break;
            case "4":
            case "get user bill":
            case "g u b":
                System.out.println("\nget user bill (ratePerHour in euro : +ve non-zero float (0.5))\r\n" + //
                        "\r\n" + //
                        "returns user total spending (in euro)\n");
                break;
            case "5":
            case "get cycle":
            case "g c":
                System.out.println("\nget cycle ()\r\n" + //
                        "\r\n" + //
                        "returns all records.\n");
                break;
            case "6":
            case "get cycle rented":
            case "g c r":
                System.out.println("\nget cycle rented ()\r\n" + //
                        "\r\n" + //
                        "returns all rented cycles\n");
                break;
            case "7":
            case "get cycle id":
            case "g c i":
                System.out.println(
                        "\nget cycle id (cycleId: +ve int(required), OptionalcycleId: +ve int (cycleId), include isRented : boolean (false))\r\n"
                                + //
                                "\r\n" + //
                                "returns records by id range, range inclusive\n");
                break;
            case "8":
            case "get cycle hoursRented":
            case "g c h":
                System.out.println(
                        "\nget cycle hoursRented (maxhoursRented: +ve int (unlimited), include isRented : boolean (false), minhoursRented: +ve int <= maxhoursRented (0))\r\n"
                                + //
                                "\r\n" + //
                                "returns records by rented hours, range inclusive.\n");
                break;
            case "9":
            case "get cycle proximity":
            case "g c p":
                System.out.println(
                        "\nget cycle proximity (range x: +ve int (5), range y: +ve int (x), include isRented : boolean (false))\r\n"
                                + //
                                "\r\n" + //
                                "returns records by distance from user.\n");
                break;
            default:
                System.out.println("exited");
                break;
        }
    }

    static void rent() {
        System.out.print("\nEnter the number or command name to see more details [enter anything else to exit]\n\n" +
                "Commands in rent module: (short hand)\n" +
                "   1. rent cycle (r c)\n" +
                "   2. rent cycle location (r c l)\n" +
                "   3. rent cycle proximity (r c p)\n" +
                "   4. rent cycle area (r c a)\n\n");
        System.out.print("Enter selection: ");
        String userInput = scnr.nextLine();
        switch (userInput) {
            case "1":
            case "rent cycle":
            case "r c":
                System.out.println(
                        "\nrent cycle (cycleId: +ve int (required), number of cycles : +ve non-zero int (1), hours to rent: +ve non-zero int <= 24 (1), rate per hour: +ve float (0.5))\r\n"
                                + //
                                "\r\n" + //
                                "rents every available cycle with cycleId >= given id till required number of cycles are marked for rent.\nIf not enough, available cycles are marked for rent. bill is calculated, confirmation is asked\n");
                break;
            case "2":
            case "rent cycle location":
            case "r c l":
                System.out.println(
                        "\nrent cycle location (number of cycles : +ve nono-zero int (1), hours to rent: +ve non-zero int <= 24 (1), x-value: +ve int(0), y-value: +ve (0), rate per hour: +ve float (0.5))\r\n"
                                + //
                                "\r\n" + //
                                "rents available cycles by location, if enough cycles, calculates bill and asks for confirmation, if not enough cycles presents invoice for max cycles available & asks for confirmation, if more cycles than needed at location lowest id's are rented\n");
                break;
            case "3":
            case "rent cycle proximity":
            case "r c p":
                System.out.println(
                        "\nrent cycle proximity (number of cycles : +ve non-zero int (1), hours to rent: +ve non-zero int <= 24 (1), range x: +ve int (5), range y: +ve int (5), rate per hour: +ve float (0.5))\r\n"
                                + //
                                "\r\n" + //
                                "rents available cycles by proximity to user location, if enough cycles, calculates bill and asks for confirmation, if not enough cycles presents invoice for max cycles available & asks for confirmation, if more cycles than needed at location lowest id's are rented\n");
                break;
            case "4":
            case "rent cycle area":
            case "r c a":
                System.out.println(
                        "\nrent cycle area (number of cycles : +ve non-zero int (1), hours to rent: +ve non-zero int <= 24 (1), x-value: +ve int(0), y-value: +ve (0), range x: +ve int (5), range y: +ve int (5), rate per hour: +ve float (0.5))\r\n"
                                + //
                                "\r\n" + //
                                "rents available cycles by proximity to given location, if enough cycles, calculates bill and asks for confirmation, if not enough cycles presents invoice for max cycles available & asks for confirmation, if more cycles than needed at location lowest id's are rented\n");
                break;
            default:
                System.out.println("exited");
                break;
        }
    }
}
