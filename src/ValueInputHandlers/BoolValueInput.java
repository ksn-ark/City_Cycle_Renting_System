package ValueInputHandlers;

import java.util.Scanner;

public class BoolValueInput {

    static Scanner scnr = new Scanner(System.in);

    public static boolean[] parser(int maxArgs, boolean defaultArgValues[]) throws InvalidInputException {

        boolean valueArgs[] = new boolean[maxArgs];

        for (int valueCount = 0; valueCount < maxArgs; valueCount++) {

            boolean value;

            System.out.print("enter value ");

            String inputString = scnr.nextLine().toLowerCase();

            switch (inputString) {
                case "":
                    value = defaultArgValues[valueCount];
                    break;
                case "true":
                    value = true;
                    break;
                case "false":
                    value = false;
                    break;
                default:
                    System.out.println(inputString + " is an invallid boolean arg");
                    throw new InvalidInputException(inputString);
            }
            valueArgs[valueCount] = value;
        }

        return valueArgs;
    }
}
